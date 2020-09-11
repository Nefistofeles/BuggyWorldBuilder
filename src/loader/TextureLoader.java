package loader;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import dataStructure.EntityTexture;
import dataStructure.ParticleTexture;
import dataStructure.Texture;
import dataStructure.TextureData;
import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class TextureLoader {
	
	private List<Integer> textures ;
	
	public static final int TextureNearest = GL11.GL_NEAREST_MIPMAP_NEAREST ;
	public static final int TextureLinear = GL11.GL_LINEAR_MIPMAP_LINEAR ;
	public static final int TextureWrapping = GL11.GL_NEAREST ;
	public static final int DEFAULT_BIAS = 0x01; 
	
	public static enum TextureType {
		Entity_Texture,
		Particle_Texture
	};
	
	public TextureLoader() {
		textures = new ArrayList<Integer>();
	}
	public Texture loadTexture(String name,TextureType textureType, int nearest, float bias ) {
		Texture texture  = null ;
		TextureData data = loadTextureDecomposition("/res/" + name + ".png") ;
		int textureID = GL11.glGenTextures() ;
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 
				0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getBuffer());
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, TextureWrapping);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, TextureWrapping);
	    
	    if(nearest != 0) {
	        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, nearest);
	        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, nearest);
	        
	    }
	    if(bias == DEFAULT_BIAS) {
	    	 GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -1.4f);
	    	 
	    }else {
	    	GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, bias);
	    }
	   
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        if(textureType == TextureType.Entity_Texture)
        	texture = new EntityTexture(textureID) ;
        else if(textureType == TextureType.Particle_Texture)
        	texture = new ParticleTexture(textureID) ;
        textures.add(textureID) ;
        System.out.println(name + " texture is loaded");
        return texture ;
	}

	public Texture loadTextureForFont(String name) {
		TextureData data = loadTextureDecomposition(name) ;
		int textureID = GL11.glGenTextures() ;
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getBuffer());

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D	, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D	, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0);
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
        textures.add(textureID);
        return new EntityTexture(textureID);
	}
	private TextureData loadTextureDecomposition(String textureName) {
		TextureData data = null ;
		
		try {
			InputStream in = Class.class.getResourceAsStream(textureName) ;
			PNGDecoder decoder = new PNGDecoder(in);
			int width = decoder.getWidth() ;
			int height = decoder.getHeight() ;
			ByteBuffer buffer = ByteBuffer.allocateDirect(4 * width * height);
			decoder.decode(buffer, width * 4, Format.RGBA);
			buffer.flip();
			data = new TextureData(width, height, buffer) ;
			in.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(textureName + " is not loaded");
			System.exit(-1);
		}
		
		return data ;
	}
	public void destroy() {
		for(Integer t : textures) {
			GL11.glDeleteTextures(t);
		}
	}
}
