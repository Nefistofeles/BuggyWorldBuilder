package text;

import java.util.List;

import org.jbox2d.common.Vec2;

import dataStructure.Mesh;
import utils.Color;
import utils.Matrix4;

public class Text {

	private String text ;
	private FontCreator font ;
	private Vec2 position ;
	private float rotation ;
	private Vec2 scale ;
	private	double maxLineLength ;
	private Matrix4 transformationMatrix ;
	private List<Line> lines ;
	private boolean isCentered ;
	private Color color ;
	private Mesh mesh ;
	private boolean isShow ;
	private float worldPosition ;
	
	public Text(String text,FontCreator font, Vec2 position, float rotation, Vec2 scale, double maxLineLength, boolean isCentered) {
		this.font = font ;
		this.text = text;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.maxLineLength = maxLineLength;
		this.isCentered = isCentered;
		this.color = new Color(0,0,0,1);
		
		transformationMatrix = Matrix4.createTransformationMatrix(position, rotation, scale);
		mesh = null ;
		isShow = true ;
		
		worldPosition = 8 ;
	}
	
	public Text createText() {
		font.loadText(this) ;
		return this ;
	}
	public void setText(String text, FontCreator font) {
		this.text = text;
		lines = null ;
		font.updateText(this);
	}
	
	public float getWorldPosition() {
		return worldPosition;
	}


	public void setWorldPosition(float worldPosition) {
		this.worldPosition = worldPosition;
	}


	public FontCreator getFont() {
		return font;
	}


	public void setFont(FontCreator font) {
		this.font = font;
	}


	public void loadMesh(Mesh mesh) {
		this.mesh = mesh ;
	}
	public Mesh getMesh() {
		return mesh ;
	}

	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}
	public void setPosition(float x, float y) {
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

	public String getText() {
		return text;
	}

	public double getMaxLineLength() {
		return maxLineLength;
	}

	public Matrix4 getTransformationMatrix() {
		transformationMatrix = Matrix4.updateTransformationMatrix(transformationMatrix, position, rotation, scale);
		return transformationMatrix;
	}

	public List<Line> getLines() {
		return lines;
	}

	public boolean isCentered() {
		return isCentered;
	}

	public Color getColor() {
		return color;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}



	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	
	
}
