package entity;

import org.jbox2d.common.Vec2;

import dataStructure.Mesh;
import dataStructure.Texture;

public class Wall extends Entity{

	public Wall(Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(texture, position, rotation, scale,worldPosition);
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void dead() {
		
		
	}



	
}
