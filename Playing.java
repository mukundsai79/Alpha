package scenes;

import java.awt.Graphics;

import helperMethods.LevelBuild;
import main.Game;
import managers.TileManager;
import objects.Tile;
import ui.BottomBar;

public class Playing extends GameScene implements SceneMethods {

    private int[][] level;
    private TileManager tileManager;
    private Tile selectedTile;
    private BottomBar bottomBar;
    private int mouseX, mouseY;
    private boolean drawSelectedTile;

    public Playing(Game game) {
        super(game);
        level = LevelBuild.getLevelData();
        tileManager = new TileManager();
        bottomBar = new BottomBar(0, 640, 640, 100, this);
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int id = level[y][x];
                g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
            }
        }
        bottomBar.draw(g);
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelectedTile) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelectedTile = true;
    }

    public TileManager getTileManager() {
        return tileManager; 
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(y >= 640) {
            bottomBar.mouseClicked(x, y);
        } else {
            changeTile(x, y);
        }
    }

    private void changeTile(int x, int y) {
        if(selectedTile != null) {
            int tileX = x / 32;
            int tileY = y / 32;
            if(level[tileY][tileX] != selectedTile.getId()) {
                level[tileY][tileX] = selectedTile.getId();
            }
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(y >= 640) {
            bottomBar.mouseMoved(x, y);
            drawSelectedTile = false;
        } else {
            drawSelectedTile = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(y >= 640) {
            bottomBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(y >= 640) {
            
        } else {
            changeTile(x, y);
        }
    }
}
