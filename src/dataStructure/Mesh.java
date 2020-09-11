package dataStructure;

public class Mesh {

	private final int vaoID ;
	private int vertexCount ;
	private int[] vbo ;
	
	public Mesh(int vaoID, int vertexCount) {
		
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public Mesh(int vaoID, int vertexCount, int[] vbo) {
		
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
		this.vbo = vbo;
	}

	public int[] getVbo() {
		return vbo;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	
	
	
	
}
