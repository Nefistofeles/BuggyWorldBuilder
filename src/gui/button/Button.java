package gui.button;

import java.util.List;

import org.jbox2d.common.Vec2;

import dataStructure.Texture;
import gui.GUI;
import gui.menu.Menu;
import loader.Loader;
import renderer.Renderer;
import text.FontCreator;
import text.FontTypeLoader;
import text.Text;
import text.Word;
import utils.Color;
import utils.Coordinates;
import utils.DisplayManager;
import utils.Maths;
import text.Character;

public class Button extends GUI{
	
	private Text text ;
	private float padding ;
	private Vec2 greatest ;


	public Button(Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(position, rotation, scale, worldPosition);
		text = null ;
		
	}

	public Text writeText(String string, boolean isCentered,float maxLineLength, FontCreator fontCreator) {
		text = new Text(string,fontCreator, new Vec2(position), rotation, new Vec2(scale.x, scale.y),maxLineLength, isCentered) ;
		fontCreator.loadText(text);
		setTextSize();
		return text ;
	}

	public Text getText() {
		return text ;
	}
	public void update() {
		
		
	}
	public void clear(Renderer renderer) {
		if(text != null)
			renderer.deleteText(text);
		renderer.deleteGUI(this);
	}

	public void setTextSize() {
		if(text != null) {
			//	Character c = text.getLines().get(0).getWords().get(0).getCharacters().get(0) ;
			greatest = findMostGreatest(text.getLines().get(0).getWords().get(0)) ;
//			float x = (float) (0.5f + (offset.x)) ;
//			float y = (float) (0.5f + (offset.y)) ;
			float x = greatest.x * scale.x ;
			float y = greatest.y * scale.y ;
			greatest.set(x, y);
			x -= padding/2 ;
			float width = (float) (text.getScale().x *2 * text.getMaxLineLength());
			float height = (float) (2*text.getLines().size() * text.getScale().y * Maths.LINE_HEIGHT ) ;
			this.setPosition(text.getPosition().x + x , text.getPosition().y + y);
			this.setScale(width, height);
		}

	}
	private Vec2 findMostGreatest(Word word) {
		double bigonex = Integer.MIN_VALUE ;
		double bigoney = Integer.MIN_VALUE ;
		for(int i = 0 ; i < word.getCharacters().size() ; i++) {
			if(bigonex < word.getCharacters().get(i).getOffsetx()) {
				bigonex = word.getCharacters().get(i).getOffsetx() ;
			}
			if(bigoney < word.getCharacters().get(i).getOffsety()) {
				bigoney = word.getCharacters().get(i).getOffsety() ;
			}
			
		}
		return new Vec2((float) bigonex, (float) bigoney) ;
	}
	@Override
	public void setPosition(float x, float y) {
		this.position.x = x ;
		this.position.y = y ;
		if(text != null)
			text.setPosition(position.x + greatest.x , position.y - greatest.y);
		
		
	}
	@Override
	public void setPosition(Vec2 position) {
		this.position = position ;
		if(text != null)
			text.setPosition(position.x + greatest.x , position.y - greatest.y);
		
		//this.setPosition(position.x, position.y);
	}

	public boolean isIntersect() {
		return intersect;
	}

	public void setIntersect(boolean intersect) {
		this.intersect = intersect;
	}


	public boolean isShow() {
		return isShow;
	}
	

	public void setShow(boolean isShow) {
		text.setShow(isShow);
		this.isShow = isShow;
	}

	@Override
	public Color getColor() {
		if(!this.isActive()) {
			if(this.isIntersect()) {
				color.setColor(0, 1,0, 1);
			}else {
				if(color2 == null)
					color.setColor(1, 1,0, 1);
				else
					color.setColor(0, 1,0, 1);
			}
		}else {
			if(this.isIntersect()) {
				color.setColor(1, 0,0, 1);
			}
		}
		
		return color;
	}


	
}
