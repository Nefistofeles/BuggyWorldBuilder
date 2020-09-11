package gui;

import org.jbox2d.common.Vec2;

import dataStructure.Texture;
import utils.Color;
import utils.Matrix4;

public abstract class GUI {
	
	protected Vec2 position ;
	protected float rotation ;
	protected Vec2 scale ;
	protected Color color ;
	protected Color color2 ;
	protected Matrix4 transformationMatrix ;
	protected boolean intersect ;
	protected boolean isShow ;
	protected boolean isActive ;
	protected float worldPosition ;
	
	public GUI(Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		this.color = new Color(1,1,0,1) ;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		transformationMatrix = Matrix4.createTransformationMatrix(position, rotation, scale) ;
		intersect = false ;
		isShow = true;
		isActive = false ;
		this.worldPosition = worldPosition ;
		color2 = null ;
	}

	public Color getColor2() {
		return color2;
	}

	public abstract void setPosition(Vec2 position);
	public abstract void setPosition(float x, float y);
	public abstract void update();

	public Vec2 getPosition() {
		return position;
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
	public void setScale(float x, float y) {
		this.scale.set(x,y) ;
	}


	public Matrix4 getTransformationMatrix() {
		transformationMatrix = Matrix4.updateTransformationMatrix(transformationMatrix, position, rotation, scale);
		return transformationMatrix;
	}

	public Color getColor() {
		return color;
	}

	public void setIntersect(boolean intersect) {
		this.intersect = intersect;
	}

	public boolean isIntersect() {
		return intersect;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public float getWorldPosition() {
		return worldPosition;
	}

	public void setWorldPosition(float worldPosition) {
		this.worldPosition = worldPosition;
	}
	
	
	
}
