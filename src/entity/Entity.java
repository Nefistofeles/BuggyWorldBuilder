package entity;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;

import dataStructure.Mesh;
import dataStructure.Texture;
import renderer.Draw;
import utils.Color;
import utils.Matrix4;

public abstract class Entity {

	protected Texture texture ;
	protected Color color ;
	protected Vec2 position ;
	protected float rotation ;
	protected Vec2 scale ;
	protected float worldPosition ;
	
	protected Matrix4 transformation ;
	
	protected boolean isDead ;
	
	protected Body body ;
	protected Shape shape ;
	protected Fixture fixture ;
	
	public Entity(Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		this.worldPosition = worldPosition ;
		this.texture = texture;
		this.position = position; 
		this.rotation = rotation;
		this.scale =scale ;
		transformation = Matrix4.createTransformationMatrix(position, rotation, scale);
		//System.out.println("game object is created");
		color = null ;
		isDead = false ;
		
	}
	
	public Entity(Vec2 position, float rotation, Vec2 scale) {
		this.worldPosition = 0 ;
		this.texture = null;
		this.position = position; 
		this.rotation = rotation;
		this.scale =scale ;
		transformation = Matrix4.createTransformationMatrix(position, rotation, scale);
		//System.out.println("game physic part is created");
		color = null ;
		isDead = false ;
		
	}

	public float getWorldPosition() {
		return worldPosition;
	}


	public void setWorldPosition(float worldPosition) {
		this.worldPosition = worldPosition;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public abstract void update();
	public abstract void dead();
	
	public void createPhysics(Body body, Shape shape, Fixture fixture) {
		this.position = body.getPosition() ;
		this.rotation = body.getAngle() ;
		this.body = body ;
		this.shape = shape ;
		this.fixture = fixture ;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vec2 getPosition() {
		return position;
	}
	public void setPosition(Vec2 position) {
		this.position = position;
	}
	public void setPosition(float x , float y) {
		this.position.x = x ;
		this.position.y = y ;
		
	}
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	public Vec2 getScale() {
		return scale;
	}
	public void setScale(Vec2 scale) {
		this.scale = scale;
	}
	public Matrix4 getTransformation() {
		transformation = Matrix4.updateTransformationMatrix(transformation, position, rotation, scale);
		return transformation;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public Body getBody() {
		return body;
	}

	public Shape getShape() {
		return shape;
	}

	public Fixture getFixture() {
		return fixture;
	}
	
	
}
