package dev.codenmore.tilegame;

import dev.codenmore.tilegame.display.Display;

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("Battle Tank!", 850, 690);
		game.start();
	}

}
