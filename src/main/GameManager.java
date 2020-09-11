package main;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;

import dataStructure.Texture;
import gameEntity.Camera;
import gameEntity.MouseOrtho;
import loader.Loader;
import loader.TextureLoader;
import renderer.Renderer;
import text.FontCreator;
import utils.DisplayManager;
import utils.Maths;
import worldCreator.WorldCreator;

public class GameManager {
	 
	private StateManager gameState ;
	private StateManager menuState ;
	private StateManager worldCreatorState ;
	
	private Camera camera ;
	private Renderer renderer ;
	private Loader loader ;

	private Creator creator ;
	private World world ;
	private MouseOrtho mouse ;
	private WorldCreator worldCreator ;
	private Data data ;
	
	public GameManager(World world) {
		this.world = world ;
	}
	public void init() {
		camera = new Camera();
		loader = new Loader();
		mouse = new MouseOrtho(camera) ;
		
		renderer = new Renderer(camera, loader, world, mouse);
		
		FontCreator font = new FontCreator("candaraTurkish", 3, loader);
		data = new Data(loader);
		creator = new Creator(world,renderer,loader,font, data);
		
		worldCreator = new WorldCreator(creator, mouse, camera,renderer,new Vec2(-80,-60), new Vec2(50,50), new Vec2(2,2));
		
		gameState = new GameState(loader,renderer, world, font, mouse, camera,worldCreator,creator);
		menuState = new MenuState(loader,renderer,font, mouse, world);
		worldCreatorState = new WorldCreatorState(creator,font, renderer, world, camera, mouse,worldCreator) ;
		
		StateManager.addState(StateManager.StateEnum.GameState, gameState);
		StateManager.addState(StateManager.StateEnum.MenuState, menuState);
		StateManager.addState(StateManager.StateEnum.WorldCreator, worldCreatorState);

		Texture texture = data.createTexture("particle",1) ;
		texture.setTextureName("particle");
		Texture texture1 = data.createTexture("ground",2) ;
		texture1.setTextureName("ground");
		Texture texture2 = data.createTexture("awesomeface1",3) ;
		texture2.setTextureName("awesomeface1");
		Texture texture3 = data.createTexture("backgroundAtlas",4) ;
		texture3.setTextureName("backgroundAtlas");
		texture3.set(4, 1, 4);
		
		Texture texture4 = data.createTexture("zombie",5) ;
		texture4.setTextureName("zombie");
		texture4.set(3, 2, 6);
		
		Texture texture5 = data.createTexture("playerpack",6) ;
		texture5.setTextureName("playerpack");
		texture5.set(5, 2, 10);
		
		StateManager.setCurrentState(StateManager.StateEnum.MenuState);
		System.out.println("Menu State");
		
		
	}
	public void update() {
		
		StateManager.getCurrentState().update();
		renderer.update();
	}
	public void render() {
		renderer.render();
	}
	public void destroy() {
		renderer.destroy();
		loader.destroy();
	}
}
