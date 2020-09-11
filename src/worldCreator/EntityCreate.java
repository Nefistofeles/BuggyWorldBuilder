package worldCreator;

import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import entity.Entity;
import entity.EntityType;
import main.Creator;
import utils.Coordinates;

public class EntityCreate {

	private EntityChange entityChange ;
	private TextureChange textureChange ;
	private WorldCreator worldCreator ;
	private Creator creator ;
	
	public EntityCreate(Creator creator, WorldCreator worldCreator, EntityChange entityChange, TextureChange textureChange) {
		this.creator = creator ;
		this.entityChange = entityChange;
		this.textureChange = textureChange;
		this.worldCreator = worldCreator ;
	}
	
	public Entity getEntity(int textureID, int entityType, Vec2 position,float rot, Vec2 scale) {
		Entity e = creator.createEntity(entityChange.getEntityType(entityType), textureChange.getTextureName(textureID), position,rot, scale, 0);
		if(entityChange.getEntityType(entityType) == EntityType.Player ||entityChange.getEntityType(entityType) == EntityType.Enemy ) {
			creator.createPhysics(e, Coordinates.Vertex	, BodyType.DYNAMIC, true, ShapeType.POLYGON, 0.1f, 0.01f, 0.1f);
		
		}
		return e ;
	}

	
}
