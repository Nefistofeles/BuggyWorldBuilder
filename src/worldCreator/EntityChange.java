package worldCreator;

import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;

import entity.EntityType;
import gui.button.Button;
import main.Data;
import text.FontCreator;

public class EntityChange {

	private int id ;
	private Data data ;
	private Vec2 scale;
	private float rotate ;
	
	public EntityChange(Data data, Vec2 scale) {
		id = 1 ;
		this.data = data ;
		this.scale = scale ;
		rotate = 0 ;
	}
	
	
	public void changeEntity() {
		id++;
		if(id >= data.getEntityID().size() + 1) {
			id = 1 ;
		}
		
		
	}
	
	public EntityType getEntityType() {
		return data.getEntityID().get(id) ;
	}
	public int getID() {
		return id ;
	}
	
	public EntityType getEntityType(int id) {
		return data.getEntityID().get(id) ;
	}
	
	public void setButton(Button button, FontCreator font) {
		button.getText().setText(getEntityType().toString(), font);
	}
	public void setScale(Vec2 s) {
		scale.x += s.x ;
		scale.y += s.y ;
		
	}

	public Vec2 getScale() {
		return scale;
	}


	public float getRotate() {
		return rotate;
	}


	public void setRotate(float rotate) {
		this.rotate = rotate;
	}
	
	
	
	
}
