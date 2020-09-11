package dataStructure;

import org.jbox2d.common.Vec2;

public abstract class Texture {

	protected int textureID ;
	protected String textureName ;
	
	protected int numberOfRows ;
	protected int numberOfColumn ;
	
	protected int indexX ;
	protected int indexY ;
	protected int textureSize ;
	
	protected boolean isStuck ;
	
	public Texture(int textureID) {
		textureName = "" ;
		this.textureID = textureID;
		numberOfRows = 1 ;
		numberOfColumn = 1 ;
		indexX = 0 ;
		indexY = 0 ; 
		textureSize = 1 ;
		isStuck = false ;
	}
	public abstract Texture clone();
	public abstract Vec2 getOffset();
	public abstract Vec2 getOffset(int x , int y);
	public abstract void set(int numberOfRows, int numberOfColumn, int textureSize);
	public abstract void setIndex(int x , int y) ; 

	/*public float getTextureXoffSet() {
		int column = textureIndexX % numberOfRows ;
		return (float)column / (float) numberOfRows ;
	}
	public float getTextureYoffSet() {
		int row = textureIndexY % numberOfColumn ;
		return (float) row / (float) numberOfColumn ;
	}
	public float getTextureYoffsetForOneSolX() {
		int row = (int)Math.floor(textureIndexY / numberOfRows) ;
		return (float) row / (float) numberOfRows ;
	}
	*/
	public int getTextureID() {
		return textureID;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getNumberOfColumn() {
		return numberOfColumn;
	}

	public void setNumberOfColumn(int numberOfColomn) {
		this.numberOfColumn = numberOfColomn;
	}
	
	public int getTextureSize() {
		textureSize = numberOfColumn * numberOfRows - 1;
		return textureSize;
	}

	public void setTextureSize(int textureSize) {
		this.textureSize = textureSize;
	}
	public int getIndexX() {
		return indexX;
	}
	public int getIndexY() {
		return indexY;
	}
	public String getTextureName() {
		return textureName;
	}
	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}
	public boolean isStuck() {
		return isStuck;
	}
	public void setStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

	
	
	
	
	
}
