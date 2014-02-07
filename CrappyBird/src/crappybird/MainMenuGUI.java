package crappybird;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 * Main Menu GUI.
 *
 * @author JamesHealey94 <jameshealey1994.gmail.com>
 */
public class MainMenuGUI extends JFrame {

    /**
     * Creates a new instance of the Main Menu GUI.
     *
     * @throws HeadlessException    thrown if user does not have a mouse or keyboard.
     */
    public MainMenuGUI() throws HeadlessException {
        this.setTitle("Main Menu");
        this.setLocationRelativeTo(null);
        this.setBackground(Color.CYAN);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setIconImage(null); // TODO create Icon
        this.setLayout(new BorderLayout());

        final JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.setVisible(true);
        this.add(buttonPanel);

        final JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGameGUI();
            }
        });
        buttonPanel.add(playButton);

        final JButton scoresButton = new JButton("Scores");
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowScoresGUI();
            }
        });
        buttonPanel.add(scoresButton);

        this.pack();
    }

    /**
     * Creates and shows the game GUI.
     */
    private void createAndShowGameGUI() {
        final GameGUI gameGUI = new GameGUI();
        gameGUI.setLocationRelativeTo(this);
        gameGUI.setVisible(true);
        gameGUI.start();
    }

    /**
     * Creates and shows the scores GUI.
     */
    private void createAndShowScoresGUI() {
        final ScoresGUI scoresGUI = new ScoresGUI();
        scoresGUI.setLocationRelativeTo(this);
        scoresGUI.setVisible(true);
    }
}