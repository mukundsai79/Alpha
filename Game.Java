package main;

import javax.swing.JFrame;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {

	private GameDisplay gameDisplay;
	private Thread gameThread;

	private final double FPS_SET = 120.0;
	private final double UPS_SET = 60.0;

	// Classes
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;

	private boolean running;

	public Game() {

		initClasses();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(gameDisplay);
		pack();
		setVisible(true);

	}

	private void initClasses() {
		render = new Render(this);
		gameDisplay = new GameDisplay(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);

	}

	private void start() {
		gameThread = new Thread(this);
		running = true;
		gameThread.start();
	}

	private void updateGame() {

		// System.out.println("Game Updated!");
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.gameDisplay.initInputs();
		game.start();

	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		long lastFrame = System.nanoTime();
		long lastUpdate = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();

		int frames = 0;
		int updates = 0;

		long now;

		while (running) {
			now = System.nanoTime();
			
			// Render
			if (now - lastFrame >= timePerFrame) {
				repaint();
				lastFrame += timePerFrame;
				frames++;
			}

			// Update
			if (now - lastUpdate >= timePerUpdate) {
				updateGame();
				lastUpdate += timePerUpdate;
				updates++;
			}

			if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
				lastTimeCheck = System.currentTimeMillis();
			}

			// Limit frame rate
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	// Getters and setters
	public Render getRender() {
		return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

}
