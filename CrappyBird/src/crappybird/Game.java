package crappybird;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 * Represents a Game of CrappyBird.
 * The game runs until the player hits a block.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class Game implements Runnable {

    /**
     * The current Bird in the game.
     */
    private final Bird bird;

    /**
     * The thread the game runs on.
     */
    private Thread thread;

    /**
     * The JComponent the Game is being played on.
     */
    private final JComponent gamePanel;

    /**
     * The points the player of the game has scored.
     */
    private int points = 0;

    // TODO improve - multiple obstacles
    private Rectangle top = null;
    private Rectangle bottom = null;

    /**
     * Constructor - Creates a Game on a GamePanel, sets the PositionManager,
     * and adds Balls, Blocks and a Bat.
     *
     * @param gamePanel     GamePanel the Game is displayed on
     */
    public Game(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.bird = new Bird(40, 30, ((gamePanel.getY() + gamePanel.getHeight()) / 2), new Color (255, 216, 1, 155));
    }

    /**
     * Starts the thread of the game.
     */
    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        final int width = 40;
        final int breathingRoom = 60;
        final int stepX = -1;
        while (bird.isAlive()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((top == null) || (top.x + top.width < 0)) {
                final Point topStartingPoint = new Point(gamePanel.getWidth(), gamePanel.getY());
                final int topHeight = new Random().nextInt(gamePanel.getHeight() - bird.height - breathingRoom);
                final Dimension topDimension = new Dimension(width, topHeight);
                top = new Rectangle(topStartingPoint, topDimension);

                final Point bottomStartingPoint = new Point(gamePanel.getWidth(), top.height + (int) bird.getHeight() + breathingRoom);
                final int bottomHeight = gamePanel.getHeight() - (top.height + (int) bird.getHeight() + breathingRoom);
                final Dimension bottomDimension = new Dimension(width, bottomHeight);
                bottom = new Rectangle(bottomStartingPoint, bottomDimension);
            }

            top.x += stepX;
            bottom.x += stepX;
            bird.step();

            gamePanel.repaint();

            if ((bird.getY() > gamePanel.getHeight()) || hasCollided(bird)) {
                bird.setColor(Color.RED);
                bird.setAlive(false);
                gamePanel.repaint();
            } else {
                points++;
            }
        }
    }

    private boolean hasCollided(Bird bird) {
        for (Rectangle obstacle : this.getObstacles()) {
            if ((isTouchingX(bird, obstacle)) && (isTouchingY(bird, obstacle) || (bird.getY() < 0))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if the passed bird and obstacle are in the same X coordinates.
     *
     * @return      if the passed bird and obstacle are in the same X coordinates
     */
    private boolean isTouchingX(Bird bird, Rectangle obstacle) {
        return ((bird.getX() + bird.getWidth()) >= obstacle.getX())
                && ((bird.getX()) <= (obstacle.getX() + obstacle.getWidth()));
    }

    /**
     * Returns if the passed bird and obstacle are in the same Y coordinates.
     *
     * @return      if the passed bird and obstacle are in the same Y coordinates
     */
    private boolean isTouchingY(Bird bird, Rectangle obstacle) {
        return ((bird.getY() + bird.getHeight()) >= obstacle.getY())
                && ((bird.getY()) <= (obstacle.getY() + obstacle.getHeight()));
    }

    /**
     * Returns the thread the Game is on.
     *
     * @return  the thread the Game is on
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Get the value of points.
     *
     * @return the value of points
     */
    public int getPoints() {
        return points;
    }

    public Bird getBird() {
        return bird;
    }

    public List<Rectangle> getObstacles() {
        final List<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(top);
        obstacles.add(bottom);
        return obstacles;
    }
}