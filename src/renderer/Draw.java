package renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dataStructure.Mesh;
import dataStructure.Texture;


public class Draw {


	public static void activate(Mesh mesh, int index) {
		GL30.glBindVertexArray(mesh.getVaoID());
		for(int i = 0 ; i < index; i++) {
			GL20.glEnableVertexAttribArray(i);
		}
		
		
	}
	public static void finish(int index) {
		for(int i = 0 ; i < index; i++) {
			GL20.glDisableVertexAttribArray(i);
		}
		GL30.glBindVertexArray(0);
		
	}

	public static void activateTexture(Texture texture) {
		GL13.glActiveTexture(texture.getTextureID());
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
	}
	public static void drawOptimizeTriangle(Mesh mesh) {
		GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount() , GL11.GL_UNSIGNED_INT, 0);
		
	}
	public static void renderTriangleStrip(Mesh mesh) {
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, mesh.getVertexCount());
	}
	public static void renderUnOptimizeTriangle(Mesh mesh) {
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, mesh.getVertexCount());
	}
}
