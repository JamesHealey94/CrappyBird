package crappybird;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Game GUI.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class GameGUI extends JFrame {

    /**
     * JPanel used to display the Game.
     */
    private final GamePanel gamePanel;

    /**
     * Constructor - Sets up JFrame.
     */
    GameGUI() {
        this.setTitle("CrappyBird");
        this.setBackground(Color.CYAN);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        gamePanel = new GamePanel();
        gamePanel.setBackground(new Color(178, 255, 255, 155)); // light blue - sky
        this.add(gamePanel, BorderLayout.CENTER);
        this.setResizable(false);
        this.pack();
    }

    /**
     * Starts a Breakout game in the Game Panel.
     */
    public void start() {
        final Thread thread = new Thread(gamePanel);
        thread.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
}