package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100.0F, yDelta = 100.0F;
    private float xDir = 1F, yDir = 1F;
    private Color color = new Color(100,100,100);
    private Random random = new Random();
    private int frames;
    private long lastCheck;
    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener( new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void setRectPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // JComponent method
        updateRectangle();
        //g.drawRect(100, 100, 200, 50)
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
    }

    public void updateRectangle(){
        xDelta += xDir;
        yDelta += yDir;
        if (xDelta > 400 || xDelta < 0){
            xDir *= -1;
            color = getRandColor();
        }
        if (yDelta > 400 || yDelta < 0){
            yDir *= -1;
            color = getRandColor();
        }
    }

    public Color getRandColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r,g,b);
    }

}
