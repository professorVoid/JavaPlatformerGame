package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Utils.Constants.PlayerConstants.*;
import static Utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private BufferedImage[][] animations;

    private BufferedImage img;
    private MouseInputs mouseInputs;
    private float xDelta = 100.0F, yDelta = 100.0F;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int frames;
    private long lastCheck;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener( new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
        importImg();
        loadAnimations();

    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];
        for ( int j = 0; j < animations.length; j++ ) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j *40, 64, 40);
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.moving = true;
        this.playerDir = direction;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // JComponent method

        //This will allow us to only see one image
        //subImg = img.getSubimage(0,0, 64,40); // row 1 column 1 image in sprite sheet
        //subImg = img.getSubimage(1*64, 2*40, 64,40); // row 2 column 3 image in sprite sheet
        g.drawImage(animations[this.playerAction][this.aniIndex], (int) xDelta, (int) yDelta, 256, 160, null);

    }

    private void updatePos() {
        if(this.moving) {
            switch (this.playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if( aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAnimation(this.playerAction)){
                aniIndex = 0;
            }
        }
    }

    private void setAnimations(){
        if(moving){
            this.playerAction = RUNNING;
        } else {
            this.playerAction = IDLE;
        }
    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try{
            img = ImageIO.read(is);
        }catch(IOException e ){
            e.printStackTrace();
        }finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimations();
        updatePos();
    }
}
