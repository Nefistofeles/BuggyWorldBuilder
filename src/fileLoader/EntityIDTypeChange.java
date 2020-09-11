package fileLoader;

public class EntityIDTypeChange {

	private int entityID ;
	private int textureID ;
	
	public EntityIDTypeChange(int entityID, int textureID) {
		
		this.entityID = entityID;
		this.textureID = textureID;
	}

	public int getEntityID() {
		return entityID;
	}

	public void setEntityID(int entityID) {
		this.entityID = entityID;
	}

	public int getTextureID() {
		return textureID;
	}

	public void setTextureID(int textureID) {
		this.textureID = textureID;
	}
	
	
	
	
}
