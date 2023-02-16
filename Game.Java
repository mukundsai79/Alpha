package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

    private GameDisplay gameDisplay;
    private BufferedImage img;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    public Game() {
        importImg();

        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gameDisplay = new GameDisplay(img);
        add(gameDisplay);
        setVisible(true);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/TowerdefenseImage.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void updateGame() {
        // System.out.println("Game Updated!");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0, updates = 0;
        while (true) {
            long now = System.nanoTime();
            long elapsedFrameTime = now - lastFrame;
            long elapsedUpdateTime = now - lastUpdate;
            if (elapsedFrameTime >= timePerFrame) {
                lastFrame = now;
                gameDisplay.repaint();
                frames++;
            }
            if (elapsedUpdateTime >= timePerUpdate) {
                lastUpdate = now;
                updateGame();
                updates++;
            }
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

}