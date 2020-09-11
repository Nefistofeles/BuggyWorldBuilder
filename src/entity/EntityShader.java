package entity;

import org.jbox2d.common.Vec2;

import shaderProgram.ShaderProgram;
import utils.Color;
import utils.Matrix4;

public class EntityShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/shader/entityVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/shader/entityFragmentShader.frag" ;
	
	private int location_projectionMatrix ;
	private int location_viewMatrix ;
	private int location_transformationMatrix ;
	private int location_color ;
	private int location_useColor ;
	private int location_worldPosition ;
	private int location_textureOffset ;
	private int location_numberOfRows ;
	private int location_numberOfColumns ;
	
	public EntityShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1,"textureCoords");
		
	}

	@Override
	protected void getUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix") ;
		location_viewMatrix = super.getUniformLocation("viewMatrix") ;
		location_projectionMatrix = super.getUniformLocation("projectionMatrix") ;
		location_color = super.getUniformLocation("color") ;
		location_useColor = super.getUniformLocation("useColor") ;
		location_worldPosition = super.getUniformLocation("worldPosition") ;
		location_textureOffset = super.getUniformLocation("textureOffset") ;
		location_numberOfRows = super.getUniformLocation("numberOfRows") ;
		location_numberOfColumns = super.getUniformLocation("numberOfColumns") ;
	}
	
	public void loadProjectionMatrix(Matrix4 projection) {
		super.loadMatrix4(location_projectionMatrix, projection);
	}
	public void loadViewMatrix(Matrix4 view) {
		super.loadMatrix4(location_viewMatrix, view);
	}
	public void loadTransformationMatrix(Matrix4 transformation) {
		super.loadMatrix4(location_transformationMatrix, transformation);
	}
	public void loadColor(Color color, boolean useColor) {
		super.loadVector4f(location_color, color.r, color.g, color.b, color.alpha);
		super.loadBoolean(location_useColor, useColor);
	}
	public void loadWorldPosition(float worldPosition) {
		super.loadFloat(location_worldPosition, worldPosition);
	}
	public void loadTextureOffset(Vec2 textureOffset, int numberOfRows, int numberOfColumns) {
		super.loadVector2f(location_textureOffset, textureOffset);
		super.loadFloat(location_numberOfRows, numberOfRows);
		super.loadFloat(location_numberOfColumns, numberOfColumns);
	}
	
}
