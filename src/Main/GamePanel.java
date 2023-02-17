package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private BufferedImage img, subImg;
    private MouseInputs mouseInputs;
    private float xDelta = 100.0F, yDelta = 100.0F;

    private int frames;
    private long lastCheck;
    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener( new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
        importImg();

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
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

        //This will allow us to only see one image
        //subImg = img.getSubimage(0,0, 64,40); // row 1 column 1 image in sprite sheet
        subImg = img.getSubimage(1*64, 2*40, 64,40); // row 2 column 3 image in sprite sheet
        g.drawImage(subImg, (int) xDelta, (int) yDelta, 120, 80, null);

    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try{
            img = ImageIO.read(is);
        }catch(IOException e ){
            e.printStackTrace();
        }
    }



}
