package gui.menu;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Mouse;

import dataStructure.Texture;
import gui.GUI;
import gui.button.Button;
import renderer.Renderer;
import text.FontCreator;
import utils.Color;

public class Menu extends GUI{

	private List<Button> buttons ;
	private int size ;
	private float maxLineLength ;
	
	public Menu(Vec2 position, float rotation, Vec2 scale,float maxLineLength, float worldPosition) {
		super(position, rotation, scale, worldPosition);
		buttons = new ArrayList<Button>();
		this.maxLineLength = maxLineLength ;
	}
	@Override
	public void setPosition(Vec2 position) {
		this.position = position ;
		for(int i = 0 ; i < buttons.size(); i++) {
			buttons.get(i).setPosition(position.x ,position.y - i * buttons.get(i).getScale().y);
		}
		
	}

	@Override
	public void setPosition(float x, float y) {
		this.position.x = x ;
		this.position.y = y ;
		for(int i = 0 ; i < buttons.size(); i++) {
			buttons.get(i).setPosition(position.x,position.y - i * buttons.get(i).getScale().y);
		}
		
	}

	@Override
	public void update() {
	
		
	}
	public Button addButton(String string, boolean isCentered, FontCreator font,Renderer renderer) {
		Button button = new Button(new Vec2(position.x,position.y),rotation,new Vec2(scale.x, scale.y),worldPosition);
		button.writeText(string, isCentered,maxLineLength, font); 
		renderer.addButton(button);
		buttons.add(button) ;
		setPosition(position);
		
		
		return button ;
	}

	
/*	public void reCalculatePosition() {
		//setScale(scale.x + 2 * padding.x, scale.y + 2 * padding.y);
		//setPosition(position);
		/*Vec2 startPos = new Vec2(-1, 1) ;
		float x = startPos.x * scale.x + position.x ;
		float y = startPos.y * scale.y + position.y ;
		for(int i = 0 ; i < buttons.size() ; i++) {
			buttons.get(i).setPosition(x + i * scale.x *2 + padding.x , y + padding.y);
		}
		
	}*/

	public float getMaxLineLength() {
		return maxLineLength;
	}
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public List<Button> getButtons() {
		return buttons;
	}
	public void setShow(boolean show) {
		this.isShow = show ;
		for(int i = 0 ; i < buttons.size(); i++) {
			buttons.get(i).setShow(show);
		}
	}
	


}
