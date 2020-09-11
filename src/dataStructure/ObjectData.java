package dataStructure;

public class ObjectData {

	private float[] vertices ;
	private float[] textureCoors ;
	
	public ObjectData(float[] vertices, float[] textureCoors) {
		
		this.vertices = vertices;
		this.textureCoors = textureCoors;
	}

	public float[] getVertices() {
		return vertices;
	}

	public float[] getTextureCoors() {
		return textureCoors;
	}
	
	
	
	
}
