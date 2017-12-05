package dev.codenmore.tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.gfx.ImageLoader;
import dev.codenmore.tilegame.gfx.SpriteSheet;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.input.MouseManager;
import dev.codenmore.tilegame.states.AboutState;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.MenuState;
import dev.codenmore.tilegame.states.State;

public class Game implements Runnable{
	
	final int NODE = 20;
	final int WIDTH_NODE = 30;
	final int HEIGHT_NODE = 30;
	
	public String title;
	private int width, height;
	
	private Display display;
	private Thread thread;
	
	private boolean isRunning = false;
	
	private BufferStrategy bs;
	private Graphics g;

	private int fps = 60;
	private double timerPerTick = 1000000000 / fps;
	private double delta = 0;
	private long now;
	private long lastTime = System.nanoTime();
	private long timer = 0;
	private int ticks = 0;
	
	// States
	public State gameState;
	public State menuState;
	public State aboutState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// GameCamera
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	int randomWithRange(int NODE) {
		return (int)(Math.random()*NODE) + 1;
	}
	
	private Color setColorNode(int map) {
		return (map == 1) ? Color.GREEN : Color.GRAY; 
	}
	
	
	
	private void init() {
		
		display = new Display(title,width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		gameCamera = new GameCamera(0, 0);
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		aboutState = new AboutState(handler);
		menuState = new MenuState(handler);
		
		
		State.setState(menuState);
		
	}
	
	private void tick() {
		
		keyManager.tick();
		
		if(State.getState() != null) 
			State.getState().tick();
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		
		// Clear Screen
		g.clearRect(0, 0, width, height);
		
		// DRAW HERE!
		g.translate(15, 15);
		
		
		if(State.getState() != null)
			State.getState().render(g);
		
		// END DRAWING
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		
		while(isRunning) {
			
			now  = System.nanoTime();
			delta += (now - lastTime) / timerPerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " +ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public synchronized void start() {
		if(isRunning) return;
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!isRunning) return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
