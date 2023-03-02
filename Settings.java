package scenes;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.GameStates;
import ui.MyButton;

import static main.GameStates.MENU;

public class Settings extends GameScene implements SceneMethods {

    private MyButton menuButton;

    public Settings(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        menuButton = new MyButton("Menu", 2, 2, 100, 30);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 640, 640);
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        menuButton.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (menuButton.getBounds().contains(x, y))
            SetGameState(MENU);
    }

    private void SetGameState(GameStates menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public void mouseMoved(int x, int y) {
        menuButton.setMouseOver(menuButton.getBounds().contains(x, y));
    }

    @Override
    public void mousePressed(int x, int y) {
        menuButton.setMousePressed(menuButton.getBounds().contains(x, y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    public void resetButtons() {
        menuButton.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (y >= 640) {
            // Do nothing
        } else {
            changeTile(x, y);
        }
    }

    private void changeTile(int x, int y) {
        // TODO: Implement tile changing logic
    }
}