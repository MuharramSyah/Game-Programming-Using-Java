package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static final int width = 90, height = 110;
	
	public static BufferedImage enemy, wall, grass, heal, title;
	
	public static BufferedImage[] player_down,player_up,player_left,player_right, button_start, button_exit, button_back, button_about;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/tanks.png"));
		SpriteSheet button = new SpriteSheet(ImageLoader.loadImage("/textures/button.png"));
		
		player_up = new BufferedImage[1];
		player_down = new BufferedImage[1];
		player_left = new BufferedImage[1];
		player_right = new BufferedImage[1];
		
		button_start = new BufferedImage[3];
		button_exit = new BufferedImage[3];
		button_back = new BufferedImage[3];
		button_about = new BufferedImage[3];
	
		player_up[0] = sheet.crop(width * 0, 0, width, height);
		player_down[0] = sheet.crop(width * 1, 0, width, height);
		player_left[0] = sheet.crop(width * 0, height * 1, width, height);
		player_right[0] = sheet.crop(width * 2, 0, width, height);
		
		button_start[0] = button.crop(0, 0, 140, 50);
		button_start[1] = button.crop(0, 98, 140, 50);
		button_exit[0] = button.crop(155, 0, 140, 50);
		button_exit[1] = button.crop(155, 98, 140, 50);
		button_about[0] = button.crop(0, 146, 140, 50);
		button_about[1] = button.crop(0, 244, 140, 50);
		
		button_back[0] = button.crop(155, 146,140,50);
		button_back[1] = button.crop(155, 244, 140, 50);
		
		grass = ImageLoader.loadImage("/textures/grass.png");
		wall = ImageLoader.loadImage("/textures/wall.png");
		heal = ImageLoader.loadImage("/textures/heart.png");
		title = ImageLoader.loadImage("/textures/title.png");
	}

}
