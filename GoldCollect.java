package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main game class for GoldCollect.
 */
class GoldCollect extends Game {
    private Player player;
    private List<Collectible> collectibles = new ArrayList<>();
    private List<Obstacle> obstacles = new ArrayList<>();
    private int score = 0;
    private long lastUpdate = System.currentTimeMillis();
    private Random random = new Random();
    private long startTime = System.currentTimeMillis(); // Track game start time

    // Inner class for Timer
    private class Timer {
        private long start = System.currentTimeMillis();
        public boolean isTimeUp(long duration) { return System.currentTimeMillis() - start > duration; }
        public void reset() { start = System.currentTimeMillis(); }
    }

    // Inner class for ScoreTracker
    private class ScoreTracker {
        public void updateScore(int delta) { score += delta; }
        public int getScore() { return score; }
    }

    private ScoreTracker scoreTracker = new ScoreTracker();
    private Timer spawnTimer = new Timer();

    public GoldCollect() {
        super("GoldCollect!", 800, 600);
        this.setFocusable(true);
        player = new Player(new Point(400, 300));
        collectibles.add(new Collectible(new Point(100, 100), JewelType.GOLD));
        obstacles.add(new Obstacle(new Point(200, 200), ObstacleType.SPIKE));

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) { updateMovement(e, true); }
            public void keyReleased(KeyEvent e) { updateMovement(e, false); }
            private void updateMovement(KeyEvent e, boolean pressed) {
                int key = e.getKeyCode();
                boolean up = player.up, down = player.down, left = player.left, right = player.right;
                if (key == KeyEvent.VK_W) up = pressed;
                if (key == KeyEvent.VK_S) down = pressed;
                if (key == KeyEvent.VK_A) left = pressed;
                if (key == KeyEvent.VK_D) right = pressed;
                player.setMovement(up, down, left, right);
            }
        });
    }

    @Override
    public void paint(Graphics brush) {
        long now = System.currentTimeMillis();
        double deltaTime = (now - lastUpdate) / 1000.0;
        lastUpdate = now;
        long elapsedTime = (now - startTime) / 1000; // Elapsed time in seconds

        if (elapsedTime >= 60) { // Game ends at 60 seconds
            brush.setColor(Color.BLACK);
            brush.fillRect(0, 0, width, height);
            brush.setColor(Color.WHITE);
            brush.drawString("Final Score: " + scoreTracker.getScore(), width / 2 - 50, height / 2); // Center the text
            return; // Stop further rendering and updates
        }

        brush.setColor(Color.BLACK);
        brush.fillRect(0, 0, width, height);

        player.update(deltaTime);
        player.draw(brush);

        collectibles.removeIf(c -> {
            c.update(deltaTime);
            c.draw(brush);
            if (c.collidesWith(player)) {
                scoreTracker.updateScore(c.getValue());
                return true;
            }
            return false;
        });

        obstacles.removeIf(o -> {
            o.update(deltaTime);
            o.draw(brush);
            if (o.collidesWith(player)) {
                scoreTracker.updateScore(o.getPenalty());
                return true;
            }
            return false;
        });

        if (spawnTimer.isTimeUp(1000)) {
            int rand = random.nextInt(100); // 0-99
            JewelType jewel = rand < 65 ? JewelType.GOLD : (rand < 90 ? JewelType.DIAMOND : JewelType.EMERALD); // 65% gold, 25% diamond, 10% emerald
            collectibles.add(new Collectible(new Point((int)(Math.random()*700)+50, (int)(Math.random()*500)+50), jewel));
            obstacles.add(new Obstacle(new Point((int)(Math.random()*700)+50, (int)(Math.random()*500)+50), ObstacleType.SPIKE));
            spawnTimer.reset();
        }

        // Display timer in top right corner
        brush.setColor(Color.WHITE);
        brush.drawString("Time: " + elapsedTime + "s", width - 60, 20);

        brush.drawString("Score: " + scoreTracker.getScore(), 10, 20);
    }

    public static void main(String[] args) {
        GoldCollect game = new GoldCollect();
        game.repaint();
    }
}