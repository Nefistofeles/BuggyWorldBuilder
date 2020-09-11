package main;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import dataStructure.Texture;
import gameEntity.MouseOrtho;
import gui.button.Button;
import gui.menu.Menu;
import loader.Loader;
import loader.TextureLoader;
import renderer.Renderer;
import text.FontCreator;
import text.Text;
import utils.Color;
import utils.DisplayManager;

public class MenuState extends StateManager{

	private Loader loader ;
	private Renderer renderer ;
	private Creator creator ; 
	private MouseOrtho mouse ;
	private World world ;
	private Menu menu ;
	private Button start ;
	private Button worldCreator ;
	private boolean isContinue ;
	private float time ;
	
	private FontCreator font ;
	public MenuState(Loader loader, Renderer renderer, FontCreator font, MouseOrtho mouse, World world) {
		this.loader = loader ;
		this.renderer = renderer ;
		this.font = font ;
		this.mouse = mouse ;
		this.world = world ;
		
	}
	
	@Override
	public void init() {
		menu = new Menu(new Vec2(-6,6),0,new Vec2(3,3),4,1);
		start = menu.addButton("START", true, font,renderer);
		worldCreator = menu.addButton("World Creator", true, font, renderer);

		isContinue = false ;
		time = 0 ;
	}
	
	@Override
	public void update() {
		if(start.isIntersect()&& isContinue && Mouse.isButtonDown(0)) {
			isContinue = false ;
			StateManager.setCurrentState(StateManager.StateEnum.GameState);
		}
		else if(worldCreator.isIntersect() && isContinue&& Mouse.isButtonDown(0)) {
			isContinue = false ;
			StateManager.setCurrentState(StateManager.StateEnum.WorldCreator);
		}
		if(!isContinue) {
			time += DisplayManager.getFrameTime() ;
			if(time > 0.5f) {
				time = 0 ;
				isContinue = true ;
			}
		}
	//	menu.setPosition(mouse.getGUIMasterPosition());
		renderer.update();
		
	}

	@Override
	public void clear() {
		renderer.clearGame(world);
		
	}



}
