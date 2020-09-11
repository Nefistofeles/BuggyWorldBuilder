package dataStructure;

import org.jbox2d.common.Vec2;

public class EntityTexture extends Texture{

	public EntityTexture(int textureID) {
		super(textureID);
		
	}
	
	public Vec2 getOffset() {
		int column = indexX % numberOfRows ;
		float x = (float)column / (float) numberOfRows ;
		
		int row = indexY % numberOfColumn ;
		float y = (float) row / (float) numberOfColumn ;
		
		return new Vec2(x,y) ;
	}
	@Override
	public Vec2 getOffset(int indexx, int indeyy) {
		int column = indexx % numberOfRows ;
		float x = (float)column / (float) numberOfRows ;
		
		int row = indeyy % numberOfColumn ;
		float y = (float) row / (float) numberOfColumn ;
		
		return new Vec2(x,y) ;
	}
	@Override
	public Texture clone() {
		Texture texture = new EntityTexture(this.textureID) ;
		texture.setIndex(this.indexX, this.indexY);
		texture.set(this.numberOfRows, this.numberOfColumn, this.textureSize);
		texture.setStuck(this.isStuck);
		return texture ;
	}

	@Override
	public void set(int numberOfRows, int numberOfColumn, int textureSize) {
		this.numberOfRows = numberOfRows ;
		this.numberOfColumn = numberOfColumn ;
		this.textureSize = textureSize ;
	}

	@Override
	public void setIndex(int x, int y) {
		this.indexX = x ;
		this.indexY = y ;
		
	}



}
