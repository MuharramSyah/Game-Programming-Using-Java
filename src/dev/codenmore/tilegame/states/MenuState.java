package dev.codenmore.tilegame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;

public class MenuState extends State{

	private UIManager uiManager;
	
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		
		uiManager.addObject(new UIImageButton(300, 350, 228, 64, Assets.button_start, new ClickListener() {
			
			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().gameState);
			}
			
		}));
		
		uiManager.addObject(new UIImageButton(300, 430, 228, 64, Assets.button_about, new ClickListener() {
			
			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().aboutState);
			}
		}));
		
		uiManager.addObject(new UIImageButton(300, 530, 228, 64, Assets.button_exit, new ClickListener() {
			
			@Override
			public void onClick() {
				System.exit(0);
			}
		}));
		
	}
	public void tick() {
		uiManager.tick();
	}
	public void render(Graphics g) {
		g.drawImage(Assets.title,200, 220, 400, 100, null);
		uiManager.render(g);
	}
	
}
