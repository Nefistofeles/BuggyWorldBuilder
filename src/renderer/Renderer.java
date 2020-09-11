package renderer;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import entity.Entity;
import entity.EntityRenderer;
import gameEntity.Camera;
import gameEntity.MouseOrtho;
import gui.GUI;
import gui.GUIRenderer;
import gui.button.Button;
import loader.Loader;
import text.FontCreator;
import text.Text;
import text.TextRenderer;
import utils.Maths;
import utils.Matrix4;
import utils.Orthographics;

public class Renderer {

	public static final Orthographics orthographics = new Orthographics(80,60);
	public static final Matrix4 projectionMatrix = orthographics.getProjectionMatrix();
	
	private EntityRenderer entityRenderer ;
	private TextRenderer textRenderer ;
	private GUIRenderer guiRenderer ;
	private World world ;
	
	public Renderer(Camera camera, Loader loader, World world, MouseOrtho mouse) {
		this.world = world ;
		entityRenderer = new EntityRenderer(camera, loader);
		textRenderer = new TextRenderer() ;
		guiRenderer = new GUIRenderer(loader, mouse);
	}
	
	public void update() {
		entityRenderer.update();
		guiRenderer.update();
		textRenderer.update();
		
	}
	public void render() {
		entityRenderer.render();
		guiRenderer.render();
		textRenderer.render();
	}

	public void addEntity(Entity entity) {
		entityRenderer.addEntity(entity);
	}
	public void addWorldPhysicParts(Body body) {
		entityRenderer.addWorldPhysics(body);
	}
	public void addText(Text text) {
		textRenderer.addText(text);
	}
	public void addGUI(GUI gui) {
		guiRenderer.addGUI(gui);
	}
	public void addButton(Button button) {
		guiRenderer.addGUI(button);
		textRenderer.addText(button.getText());
	}
	public void deleteButton(Button button) {
		textRenderer.delete(button.getText());
		guiRenderer.delete(button);
	}
	public void deleteEntity(Entity entity) {
		entityRenderer.delete(entity, world);
	}
	public void deleteGUI(GUI gui) {
		guiRenderer.delete(gui);
	}
	public void deleteText(Text text) {
		textRenderer.delete(text);
	}
	public void clearGame(World world) {
		entityRenderer.clear(world);
		textRenderer.clear();
		guiRenderer.clear();
	}
	public void destroy() {
		entityRenderer.cleanUp();
		textRenderer.cleanUp();
		guiRenderer.cleanUp();
	}
}
