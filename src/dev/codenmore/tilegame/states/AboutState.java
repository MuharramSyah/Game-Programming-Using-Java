package dev.codenmore.tilegame.states;

import java.awt.Font;
import java.awt.Graphics;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.ui.ClickListener;
import dev.codenmore.tilegame.ui.UIImageButton;
import dev.codenmore.tilegame.ui.UIManager;

public class AboutState extends State{
	
	private UIManager uiManager;
	
	public AboutState(Handler handler) {
		
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		uiManager.addObject(new UIImageButton(10, 10, 228, 64, Assets.button_back, new ClickListener() {
		
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().menuState);
			}
			
		}));
	}

	public void tick() {
		uiManager.tick();
	}

	public void render(Graphics g) {
		g.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 22));
		g.drawString("Creator by :", 100, 100);
		g.drawString("=> Muharram Syah", 100, 130);
		g.drawString("=> Yusuf Fathony", 100, 160);
		g.drawString("=> Tirto Fales", 100, 190);
		g.drawString("=> Yuni Sartika", 100, 220);
		uiManager.render(g);
	}


}
