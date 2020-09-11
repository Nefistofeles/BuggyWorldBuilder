package loader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dataStructure.Mesh;

public class MeshLoader {
	
	private static final int FLOAT_SIZE = 4 ;
	private static final int INTEGER_SIZE = 4 ;
	
	private List<Integer> vaos ;
	private List<Integer> vbos ;
	
	public MeshLoader() {
		vaos = new ArrayList<Integer>();
		vbos= new ArrayList<Integer>();
	}
	
	public Mesh createMesh(float[] vertices, float[] textureCoords, int[] indices) {
		int vaoID = GL30.glGenVertexArrays() ;
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		
		bindIndices(indices);
		storeDataVBO(0, 2, vertices);
		storeDataVBO(1, 2, textureCoords);
		
		GL30.glBindVertexArray(0);
		return new Mesh(vaoID, indices.length) ;
		
	}
	public Mesh createMesh(float[] vertices, float[] textureCoords) {
		int vaoID = GL30.glGenVertexArrays() ;
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		
		storeDataVBO(0, 2, vertices);
		storeDataVBO(1, 2, textureCoords);
		
		GL30.glBindVertexArray(0);
		System.out.println("mesh is loaded");
		return new Mesh(vaoID, vertices.length / 2) ;
		
	}
	private void storeDataVBO(int index, int size, float[] data) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = storeFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, size * FLOAT_SIZE ,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
	}
	private void bindIndices(int[] indices) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
		IntBuffer buffer = storeIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	private IntBuffer storeIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length) ;
		buffer.put(data) ;
		buffer.flip();
		return buffer ;
	}
	private FloatBuffer storeFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length) ;
		buffer.put(data) ;
		buffer.flip();
		return buffer ;
	}

	public void destroy() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
	}
	
	
	
	public int createVAO() {
		int vaoID = GL30.glGenVertexArrays() ;
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID ;
	}
	public void closeVAO() {
		GL30.glBindVertexArray(0);
		
	}
	public int createVBO(int index, int size, float[] data, int drawType) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = storeFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, drawType);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, size * FLOAT_SIZE ,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		return vbo ;
	}
	public void updateVBO(int vbo, float[] data, int drawType) {
		FloatBuffer buffer = storeFloatBuffer(data);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * Float.BYTES, drawType);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	
}
