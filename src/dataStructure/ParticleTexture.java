package dataStructure;

import org.jbox2d.common.Vec2;

public class ParticleTexture extends Texture{

	public ParticleTexture(int textureID) {
		super(textureID);
		
	}
	
	@Override
	public Texture clone() {
		Texture texture = new ParticleTexture(textureID) ;
		texture.setNumberOfRows(this.getNumberOfRows());
		texture.setNumberOfColumn(this.getNumberOfColumn());
		texture.setIndex(indexX, indexY);
		return texture ;
	}

	@Override
	public Vec2 getOffset() {
		int column = indexX % numberOfRows ;
		float x = (float)column / (float) numberOfRows ;
		
		int row = (int)Math.floor(indexX / numberOfColumn) ;
		float y =  (float) row / (float) numberOfColumn ;
		
		return new Vec2(x,y) ;
		
	}
	@Override
	public Vec2 getOffset(int indexx, int indeyy) {
		int column = indexx % numberOfRows ;
		float x = (float)column / (float) numberOfRows ;
		
		int row = (int)Math.floor(indexx / numberOfColumn) ;
		float y =  (float) row / (float) numberOfColumn ;
		
		return new Vec2(x,y) ;
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
