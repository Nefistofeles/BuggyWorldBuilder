package worldCreator;

import org.jbox2d.common.Vec2;

import entity.Entity;

public class Tile {

	public static final Vec2 min = new Vec2(-1,-1) ;
	public static final Vec2 max = new Vec2(1,1) ;
	private String id ;
	private float x ;
	private float y ;
	private float width ;
	private float height ;
	private Entity entity ;
	private boolean isSelected ;
	
	public Tile(String id) {
		this.id = id ;
		entity = null ;
		isSelected = false ;
	}
	public void setProperties(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Vec2 getScale() {
		return new Vec2(width,height) ;
	}
	public Vec2 getCenter() {
		return new Vec2(x + width / 2 , y + height / 2) ;
	}


	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	public String getId() {
		return id;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	
	
	
}
