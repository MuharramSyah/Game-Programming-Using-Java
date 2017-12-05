package dev.codenmore.tilegame.tiles;

import java.awt.image.BufferedImage;

import dev.codenmore.tilegame.gfx.Assets;

public class WallTile extends Tile{

	public WallTile(int id) {
		super(Assets.wall, id);
		// TODO Auto-generated constructor stub
	}

	public boolean isSolid() {
		return true;
	}
}
