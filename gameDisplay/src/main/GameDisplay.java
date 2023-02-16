package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel; 

public class GameDisplay extends JPanel {

    private Random random;
    private BufferedImage img;
    private ArrayList<BufferedImage> sprites = new ArrayList<>();
    private int[][] spriteIndices;

    public GameDisplay(BufferedImage img) {
        this.img = img;
        loadSprites();
        random = new Random();
        spriteIndices = new int[20][20];
        for (int i = 0; i < spriteIndices.length; i++) {
            for (int j = 0; j < spriteIndices[i].length; j++) {
                spriteIndices[i][j] = getRndInt();
            }
        }
    }

    private void loadSprites() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                sprites.add(img.getSubimage(x * 32, y * 32, 32, 32));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < spriteIndices.length; y++) {
            for (int x = 0; x < spriteIndices[y].length; x++) {
                g.drawImage(sprites.get(spriteIndices[y][x]), x * 32, y * 32, null);
            }
        }
    }

    private int getRndInt() {
        return random.nextInt(100);
    }

    private Color getRndColor() {
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
}
