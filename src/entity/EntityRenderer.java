package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import dataStructure.Mesh;
import dataStructure.Texture;
import gameEntity.Camera;
import loader.Loader;
import renderer.Draw;
import renderer.Renderer;
import utils.Color;
import utils.Coordinates;
import utils.DisplayManager;

public class EntityRenderer {

	public static final Vec2 min = new Vec2(-1,-1) ;
	public static final Vec2 max = new Vec2(1,1) ;
	
	private EntityShader shader ;
	private Map<Texture, List<Entity>> entities ;
	private List<Body> worldPhysics ;
	private Camera camera ;
	private Mesh mesh ;
	
	public EntityRenderer(Camera camera, Loader loader) {
		this.camera = camera ;
		shader = new EntityShader();
		entities = new HashMap<Texture, List<Entity>>();
		worldPhysics = new ArrayList<Body>();
		mesh = loader.getMeshLoader().createMesh(Coordinates.Vertex, Coordinates.TextureCoords, Coordinates.indices) ;
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}
	public void addWorldPhysics(Body body) {
		worldPhysics.add(body) ;
		
	}
	public void addEntity(Entity entity) {
		List<Entity> entitylist = entities.get(entity.getTexture()) ;
		
		if(entitylist == null) {
			entitylist = new ArrayList<Entity>();
			entitylist.add(entity) ;
			entities.put(entity.getTexture(), entitylist) ;
		}else {
			entitylist.add(entity) ;
		}
		
		
	}
	
	
	public void update() {
		for(Texture texture : entities.keySet()) {
			List<Entity> entitylist = entities.get(texture) ;
			for(Entity entity : entitylist) {
				entity.update();
			}
		}
	}
	
	public void render() {
		shader.start();
		shader.loadViewMatrix(camera.getViewMatrix());
		
		Draw.activate(mesh, 2);
		
		for(Texture texture : entities.keySet()) {
			List<Entity> entitylist = entities.get(texture) ;
			Draw.activateTexture(texture);
			
			for(Entity entity : entitylist) {
				boolean render = canRender(entity) ;
				if(render) {
					
					if(entity.getColor() != null) {
						shader.loadColor(entity.getColor(), true);
					}else {
						shader.loadColor(new Color(0,0,0,0), false);
					}
					shader.loadWorldPosition(entity.getWorldPosition());
					shader.loadTransformationMatrix(entity.getTransformation());
					shader.loadTextureOffset(texture.getOffset(), 
							texture.getNumberOfRows(), texture.getNumberOfColumn());
					
					Draw.drawOptimizeTriangle(mesh);
					
					
				}

			}
			
		}
		Draw.finish(2);
		shader.stop();
	}
	private boolean canRender(Entity entity) {
		if(entity.getPosition().x + min.x * entity.getScale().x> camera.getPosition().x + 80 ||
				entity.getPosition().x + max.x * entity.getScale().x < camera.getPosition().x - 80) {
			return false ;
		}
		if(entity.getPosition().y + min.y * entity.getScale().y> camera.getPosition().y + 60 ||
				entity.getPosition().y + max.y * entity.getScale().y< camera.getPosition().y - 60) {
			return false ;
		}
		return true ;
	}
	public void delete(Entity entityDelete, World world) {
		Iterator<Texture> iter1= entities.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next();
			List<Entity> entitylist = entities.get(texture);
			Iterator<Entity> iter = entitylist.iterator();
			while (iter.hasNext()) {
				Entity entity = iter.next() ;
				if(entity == entityDelete) {
					if(entity.getBody() != null) {
						world.destroyBody(entity.getBody());
					}
					
					iter.remove();
				}
			}
			if (entitylist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}
	public void clear(World world) {
		Iterator<Texture> iter1= entities.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next();
			List<Entity> entitylist = entities.get(texture);
			Iterator<Entity> iter = entitylist.iterator();
			while (iter.hasNext()) {
				Entity entity = iter.next() ;
				if(entity.getBody() != null)
					world.destroyBody(entity.getBody());
				iter.remove();
				
			}
			if (entitylist.isEmpty()) {
				iter1.remove();
			}
			
		}
		for(int i = 0 ; i < worldPhysics.size(); i++) {
			world.destroyBody(worldPhysics.get(i));
		}
	}
	public void cleanUp() {
		entities.clear();
		shader.cleanUp();
	}
}
