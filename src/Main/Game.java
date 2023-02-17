package Main;

public class Game implements Runnable{

    private Thread gameThread;
    private final int FPS_SET =120;
    private GameWindow gameWindow;
    //testing
    private GamePanel gamePanel;
    public Game(){

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus(); // gets input focus
        startGameLoop();

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;

        long lastFrame = System.nanoTime();
        long now;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while(true){
            now = System.nanoTime();
            if(System.nanoTime() - lastFrame >= timePerFrame)
            {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }


            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
    }
}
