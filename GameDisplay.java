package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
public class GameDisplay extends JPanel {
    private static final Dimension PANEL_SIZE = new Dimension(640, 740);

    private final Game game;
    private final MyMouseListener mouseListener;
    private final KeyboardListener keyboardListener;

    public GameDisplay(Game game) {
        this.game = game;
        this.mouseListener = new MyMouseListener(game);
        this.keyboardListener = new KeyboardListener();
        setPreferredSize(PANEL_SIZE);
        setMinimumSize(PANEL_SIZE);
        setMaximumSize(PANEL_SIZE);
    }

    public void initInputs() {
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener);
        requestFocus();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
    }
}