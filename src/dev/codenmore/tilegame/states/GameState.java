package dev.codenmore.tilegame.states;

import java.awt.Graphics;

import dev.codenmore.tilegame.Game;
import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.entities.statics.Heal;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.tiles.Tile;
import dev.codenmore.tilegame.worlds.World;

public class GameState extends State{

	private Player player;
	private World world;
	private Heal heal;
	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/World/world1.txt");
		handler.setWorld(world);
		player = new Player(handler,30,30);
	}
	
	public void tick() {
		world.tick();
		// IF USING CAMERA FOLLOWING PLAYER
//		game.getGameCamera().move(1, 1);
	}

	public void render(Graphics g) {
		world.render(g);
	}
	
}
