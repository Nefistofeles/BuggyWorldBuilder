package gui.panel;

import org.jbox2d.common.Vec2;

import gui.GUI;
import utils.Color;

public class Panel extends GUI{

	public Panel(Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(position, rotation, scale, worldPosition);
		
	}
	
	@Override
	public void setPosition(Vec2 position) {
		position.set(position) ;
		
	}

	@Override
	public void setPosition(float x, float y) {
		position.set(x,y) ;
		
	}

	@Override
	public void update() {
		;
		
	}

}
