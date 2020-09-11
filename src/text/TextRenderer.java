package text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dataStructure.Texture;
import entity.Entity;
import renderer.Draw;
import renderer.EnableOpenGL;
import renderer.Renderer;

public class TextRenderer {

	private TextShader shader ;
	private Map<Texture, List<Text>> texts ;
	
	
	public TextRenderer() {
		shader = new TextShader();
		texts = new HashMap<Texture, List<Text>>();
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}
	public void addText(Text text) {
		List<Text> textlist = texts.get(text.getFont().getTexture()) ;
		if(textlist == null) {
			textlist = new ArrayList<Text>();
			textlist.add(text) ;
			texts.put(text.getFont().getTexture(), textlist) ;
		}else {
			textlist.add(text) ;
		}
		System.out.println("text is added");
	}
	public void update() {
		
	}
	public void render() {
		EnableOpenGL.blendFunc(true);
		EnableOpenGL.disableDepthTestWithMask(true);
	//	EnableOpenGL.enableDepthTest(false);
		shader.start();
		for(Texture texture : texts.keySet()) {
			List<Text> textlist = texts.get(texture) ;
			Draw.activateTexture(texture);
			for(Text t : textlist) {
				if(t.isShow()) {
					Draw.activate(t.getMesh(), 2);
					shader.loadWorldPosition(t.getWorldPosition());
					shader.loadTransformationMatrix(t.getTransformationMatrix());
					shader.loadColor(t.getColor());
					Draw.renderUnOptimizeTriangle(t.getMesh());
					Draw.finish(2);
				}

			}
		}
		shader.stop();
		EnableOpenGL.blendFunc(false);
		EnableOpenGL.disableDepthTestWithMask(false);
	//	EnableOpenGL.enableDepthTest(true);
	}
	public void delete(Text text) {
		Iterator<Texture> iter1= texts.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next();
			List<Text> textlist = texts.get(texture);
			Iterator<Text> iter = textlist.iterator();
			while (iter.hasNext()) {
				Text t = iter.next() ;
				if(t == text) {
					iter.remove();
				}
			}
			if (textlist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}
	
	public void clear() {
		Iterator<Texture> iter1= texts.keySet().iterator() ;
		while(iter1.hasNext()) {
			Texture texture = iter1.next();
			List<Text> textlist = texts.get(texture);
			Iterator<Text> iter = textlist.iterator();
			while (iter.hasNext()) {
				Text t = iter.next() ;
				iter.remove();
				
			}
			if (textlist.isEmpty()) {
				iter1.remove();
			}
			
		}
	}
	public void cleanUp() {
		shader.cleanUp();
	}
}
