package main;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import entity.Entity;
import entity.EntityType;
import gameEntity.Camera;
import gameEntity.MouseOrtho;
import gui.GUI;
import gui.button.Button;
import gui.menu.Menu;
import renderer.Renderer;
import text.FontCreator;
import text.Text;
import utils.Color;
import utils.DisplayManager;
import utils.Maths;
import worldCreator.Tile;
import worldCreator.WorldCreator;

public class WorldCreatorState extends StateManager{

	private Creator creator ;
	private Renderer renderer ;
	private World world ;
	private Camera camera ;

	private WorldCreator worldCreator ;
	private MouseOrtho mouse ;
	
	private float time ;
	private boolean isContinue ;

	private List<Entity> entityList ;
	private FontCreator font ;
	
	private Menu menu ;

	private Button back ;
	private Button addEntity ;
	private Button selectEntity ;
	private Button changeEntityTexture ;
	private Button changeEntityAttribute ;
	private Button createPhysics ;
	private Button save ;
	
	private Menu entityMenu ;
	private Button changeEntity ;
	/*private Button applyEntity ;
	private Button entityName ;*/
	private Button changeScaleplus ;
	private Button changeScaleMinus ;
	private Button scale ;
	private Button changeRotateplus ;
	private Button changeRotateMinus ;
	private Button rotate ;
	
	private Menu textureMenu ;
	private Button changeTexture ;
	//private Button applyTexture ;
	//private Button textureName ;

	
	public WorldCreatorState(Creator creator, FontCreator font, Renderer renderer, World world, Camera camera, MouseOrtho mouse,WorldCreator worldCreator) {
		this.font = font ;
		this.renderer = renderer ;
		this.world = world ;
		this.camera = camera ;
		this.mouse = mouse ;
		this.creator = creator ;
		this.worldCreator = worldCreator ;
		time = 0 ;
		isContinue = true ;

		
	}

	@Override
	public void init() {
		camera.setPosition(0, 0);
		
		
		menu = new Menu(new Vec2(59,58),0,new Vec2(2,3),5,1);
		back = menu.addButton("Back", true, font,renderer);
		addEntity = menu.addButton("Add Entity", true, font,renderer);
		selectEntity = menu.addButton("Select Entity", true, font, renderer);
		changeEntityTexture = menu.addButton("Texture", true, font,renderer);
		changeEntityAttribute = menu.addButton("Entity Attribute", true, font,renderer);
		createPhysics = menu.addButton("Create Physics", true, font, renderer);
		save = menu.addButton("Save", true, font, renderer);
		
		
		entityMenu = new Menu(new Vec2(59,28),0,new Vec2(2,3),5,1);
		changeEntity = entityMenu.addButton("Change", true, font, renderer);
		/*entityName = entityMenu.addButton(worldCreator.getEntityChange().getEntityType() +"", true, font, renderer);
		applyEntity = entityMenu.addButton("Apply", true, font, renderer);*/
		changeScaleplus = entityMenu.addButton("Scale add 1", true, font, renderer);
		changeScaleMinus = entityMenu.addButton("Scale add -1", true, font, renderer);
		scale = entityMenu.addButton("S = " + worldCreator.getEntityChange().getScale().toString(), true, font, renderer);
		changeRotateplus = entityMenu.addButton("Rotate add 45", true, font, renderer);
		changeRotateMinus = entityMenu.addButton("Rotate add -45", true, font, renderer);
		rotate = entityMenu.addButton("R = 0", true, font, renderer);
		
		textureMenu = new Menu(new Vec2(59,28),0,new Vec2(2,3),5,1);
		changeTexture = textureMenu.addButton("Change", true, font, renderer);
	//	textureName = textureMenu.addButton(creator.getData().getTextureID().get(1), true, font, renderer);
	//	applyTexture = textureMenu.addButton("Apply", true, font, renderer);
		

		worldCreator.loadGrid();
		
		menu.setShow(true);
		entityMenu.setShow(false);
		textureMenu.setShow(false);
	

		entityList = new ArrayList<Entity>();

		isContinue = false ;
		time = 0 ;
		
		
	}

	@Override
	public void update() {
		
		for(Button b : menu.getButtons()) {
			
			if(b != back && b != changeEntityTexture && b != changeEntityAttribute) {
				if(b.isIntersect() && isContinue && Mouse.isButtonDown(0) ) {
					isContinue = false ;
					b.setActive(!b.isActive());
						
				}
			}
			if(back.isIntersect() && isContinue && Mouse.isButtonDown(0)) {
				isContinue = false ;
				StateManager.setCurrentState(StateManager.StateEnum.MenuState);
			}
			
			if(changeEntityTexture.isIntersect() && !entityMenu.isShow()&& isContinue && Mouse.isButtonDown(0)) {
				changeEntityTexture.setActive(!changeEntityTexture.isActive());
				isContinue = false ;
				textureMenu.setShow(changeEntityTexture.isActive());
			}
			if(changeEntityAttribute.isIntersect() && !textureMenu.isShow() &&isContinue && Mouse.isButtonDown(0)) {
				changeEntityAttribute.setActive(!changeEntityAttribute.isActive());
				isContinue = false ;
				entityMenu.setShow(changeEntityAttribute.isActive());
			}
		
			
		}
		if(entityMenu.isShow()) {
			for(Button b : entityMenu.getButtons() ) {
				if(b != back && b != changeEntityTexture && b != changeEntityAttribute) {
					if(b.isIntersect() && isContinue && Mouse.isButtonDown(0) ) {
						isContinue = false ;
						b.setActive(!b.isActive());
							
					}
				}
			}
		}
		if(textureMenu.isShow()) {
			for(Button b : textureMenu.getButtons() ) {
				if(b != back && b != changeEntityTexture && b != changeEntityAttribute) {
					if(b.isIntersect() && isContinue && Mouse.isButtonDown(0) ) {
						isContinue = false ;
						b.setActive(!b.isActive());
							
					}
				}
			}
		}
		

		if(StateManager.getCurrentState() == this && addEntity.isActive() && !addEntity.isIntersect()&&!selectEntity.isIntersect()) {
			isContinue = false ;
			Entity entity =worldCreator.isIntersect();
			
		}
		if(selectEntity.isActive() && isContinue&& Mouse.isButtonDown(0) ) {
			isContinue = false ;
			worldCreator.getEntityTheLocation();
		}
		if(createPhysics.isActive() && isContinue ) {
			isContinue = false ;
			worldCreator.createPhysics();
			createPhysics.setActive(false);
		}
		
		if(save.isActive() && isContinue) {
			isContinue = false ;
			save.setActive(!save.isActive());
			worldCreator.save("levels",".txt");
			System.out.println("save");
			
		}

		if(changeTexture.isActive() && isContinue) {
			isContinue = false ;
			changeTexture.setActive(!changeTexture.isActive());
			worldCreator.getTextureChange().changeID();
			worldCreator.getTextureChange().setButtonText(changeTexture, font);
		}
	/*	if(applyTexture.isActive() && isContinue) {
			isContinue = false ;
			applyTexture.setActive(!applyTexture.isActive());
			worldCreator.getTextureChange().setButtonText(textureName, font);
		}*/
		if(changeEntity.isActive() && isContinue) {
			isContinue = false ;
			changeEntity.setActive(!changeEntity.isActive());
			worldCreator.getEntityChange().changeEntity();
			worldCreator.getEntityChange().setButton(changeEntity, font);
		}
		/*if(applyEntity.isActive() && isContinue) {
			isContinue = false ;
			applyEntity.setActive(!applyEntity.isActive());
			
		}*/
		if(changeScaleplus.isActive() && isContinue) {
			isContinue = false ;
			changeScaleplus.setActive(!changeScaleplus.isActive());
			worldCreator.getEntityChange().setScale(new Vec2(1,1));
			scale.getText().setText("S = " + worldCreator.getEntityChange().getScale().toString(), font);
		}
		if(changeScaleMinus.isActive() && isContinue) {
			isContinue = false ;
			changeScaleMinus.setActive(!changeScaleMinus.isActive());
			worldCreator.getEntityChange().setScale(new Vec2(-1,-1));
			scale.getText().setText("S = " + worldCreator.getEntityChange().getScale().toString(), font);
		}
		if(changeRotateplus.isActive() && isContinue) {
			isContinue = false ;
			changeRotateplus.setActive(!changeRotateplus.isActive());
			worldCreator.getEntityChange().setRotate(worldCreator.getEntityChange().getRotate() + 90);
			rotate.getText().setText("R = " +  worldCreator.getEntityChange().getRotate(), font);
		}
		if(changeRotateMinus.isActive() && isContinue) {
			isContinue = false ;
			changeRotateMinus.setActive(!changeRotateMinus.isActive());
			worldCreator.getEntityChange().setRotate(worldCreator.getEntityChange().getRotate() - 90);
			rotate.getText().setText("R = " + worldCreator.getEntityChange().getRotate(), font);
		}

		if(!isContinue) {
			time += DisplayManager.getFrameTime() ;
			if(time > 0.2f) {
				isContinue = true ;
				time = 0 ;
			}
		}
		camera.move();
		renderer.update();
	}
	

	@Override
	public void clear() {
		renderer.clearGame(world);
		
	}


}

/*if(textureName.isActive()) {
while(Keyboard.next() && Keyboard.getEventKeyState()) {
	
	name+= Keyboard.getEventCharacter() ;
	System.out.println(name);
	
}
}
if(change) {
textureName.getText().setText(name, font);
change = false ;

}*/
