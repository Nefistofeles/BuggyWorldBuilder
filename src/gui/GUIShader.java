package gui;

import shaderProgram.ShaderProgram;
import utils.Color;
import utils.Matrix4;

public class GUIShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER ="/shader/GuiVertexShader.vert" ;
	private static final String FRAGMENT_SHADER ="/shader/GuiFragmentShader.frag" ;

	private int location_transformationMatrix ;
	private int location_projectionMatrix ;
	private int location_color ;
	private int location_worldPosition ;
	
	public GUIShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		
	}

	@Override
	protected void getUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix") ;
		location_projectionMatrix = super.getUniformLocation("projectionMatrix") ;
		location_color = super.getUniformLocation("color") ;
		location_worldPosition = super.getUniformLocation("worldPosition") ;
		
	}
	public void loadTransformationMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_transformationMatrix, matrix);
	}
	public void loadProjectionMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_projectionMatrix, matrix);
	}
	public void loadColor(Color color) {
		super.loadVector4f(location_color, color.r, color.g, color.b, color.alpha);
	}
	public void loadWorldPosition(float worldPosition) {
		super.loadFloat(location_worldPosition, worldPosition);
	}


}
