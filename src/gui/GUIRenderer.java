package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dataStructure.Mesh;
import dataStructure.Texture;
import gameEntity.MouseOrtho;
import loader.Loader;
import renderer.Draw;
import renderer.EnableOpenGL;
import renderer.Renderer;
import utils.Coordinates;
import utils.DisplayManager;

public class GUIRenderer {

	private GUIShader shader;
	private List<GUI> guis;
	private final Mesh mesh;
	private GUIIntersection intersection;
	private MouseOrtho mouse;

	public GUIRenderer(Loader loader, MouseOrtho mouse) {
		this.mouse = mouse;
		shader = new GUIShader();
		mesh = loader.getMeshLoader().createMesh(Coordinates.triangleStripVertex, Coordinates.TextureCoords);
		guis = new ArrayList<GUI>();
		intersection = new GUIIntersection();
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}

	public void update() {
		
		Iterator<GUI> iter1 = guis.iterator();
		while (iter1.hasNext()) {
			GUI t = iter1.next();
			if (t.isShow()) {
				t.setIntersect(intersection.isIntersect(t, mouse.getGUIMasterPosition()));
				
			}
		}
		
		

	}

	public void addGUI(GUI gui) {
		guis.add(gui);
	}

	public void render() {
		EnableOpenGL.blendFunc(true);
		EnableOpenGL.disableDepthTestWithMask(true);
		// EnableOpenGL.enableDepthTest(false);
		shader.start();
		Draw.activate(mesh, 1);

		for (GUI g : guis) {
			if (g.isShow) {
				shader.loadWorldPosition(g.getWorldPosition());
				shader.loadTransformationMatrix(g.getTransformationMatrix());
				shader.loadColor(g.getColor());
				Draw.renderTriangleStrip(mesh);
			}
		}

		Draw.finish(2);
		shader.stop();
		EnableOpenGL.blendFunc(false);
		EnableOpenGL.disableDepthTestWithMask(false);
		// EnableOpenGL.enableDepthTest(true);
	}

	public void delete(GUI guit) {
		Iterator<GUI> iter1 = guis.iterator();
		while (iter1.hasNext()) {

			GUI t = iter1.next();
			if (t.equals(guit))
				iter1.remove();

		}
	}

	public void clear() {
		Iterator<GUI> iter1 = guis.iterator();
		while (iter1.hasNext()) {

			GUI t = iter1.next();
			iter1.remove();

		}
	}

	public void cleanUp() {
		shader.cleanUp();
	}
}
