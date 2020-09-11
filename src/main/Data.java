package main;

import java.util.HashMap;
import java.util.Map;

import dataStructure.Texture;
import entity.EntityType;
import loader.Loader;
import loader.TextureLoader;

public class Data {

	private Loader loader ;
	
	private Map<String, Texture> textures ;
	private Map<Integer, EntityType> entityID ;
	private Map<Integer, String> textureID ;
	
	public Data(Loader loader) {
		this.loader = loader ;
		
		textures = new HashMap<String, Texture>();
		textureID = new HashMap<Integer, String>();
		entityID = new HashMap<Integer, EntityType>() ;
		
		
		entityID = new HashMap<Integer, EntityType>();
		entityID.put(1, EntityType.Player) ;
		entityID.put(2, EntityType.Wall) ;
		entityID.put(3, EntityType.Enemy) ;
		
		
	}
	
	public Texture createTexture(String name, int id) {
		try {
			Texture texture = textures.get(name) ;
			if(texture == null) {
				texture = loader.getTextureLoader().loadTexture(name,TextureLoader.TextureType.Entity_Texture,
						TextureLoader.TextureNearest, TextureLoader.DEFAULT_BIAS) ;
				textureID.put(id, name) ;
				textures.put(name, texture) ;
				return texture ;
			}else {
				return textures.get(name) ;
			}
			
		}catch(Exception e) {
			System.out.println(name + " texture is not found");
			e.printStackTrace(); 
			System.exit(-1);
		}
		return null ;
	}
	public Texture getTexture(String name) {
		try {
			Texture texture = textures.get(name) ;
			if(texture == null) {
				texture = loader.getTextureLoader().loadTexture(name,TextureLoader.TextureType.Entity_Texture,
						TextureLoader.TextureNearest, TextureLoader.DEFAULT_BIAS) ;
				textures.put(name, texture) ;
				return texture.clone() ;
			}else {
				return textures.get(name).clone() ;
			}
			
		}catch(Exception e) {
			System.out.println(name + " texture is not found");
			e.printStackTrace(); 
			System.exit(-1);
		}
		return null ;
	}


	public Map<String, Texture> getTextures() {
		return textures;
	}


	public Map<Integer, EntityType> getEntityID() {
		return entityID;
	}


	public Map<Integer, String> getTextureID() {
		return textureID;
	}
	
	
	
	
}
