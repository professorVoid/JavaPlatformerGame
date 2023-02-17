package Main;

import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel){
        add(gamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Setting the game frame to the center
        setResizable(false);
        pack();
        setVisible(true);

    }
}
