package main;

import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Mouse;

import entity.Entity;
import entity.EntityType;
import entity.Player;
import gameEntity.Camera;
import gameEntity.MouseOrtho;
import gui.button.Button;
import gui.menu.Menu;
import loader.Loader;
import renderer.Renderer;
import text.FontCreator;
import text.Text;
import utils.Color;
import utils.Coordinates;
import utils.DisplayManager;
import worldCreator.WorldCreator;

public class GameState extends StateManager{
	
	private Loader loader ;
	private Renderer renderer ;
	private Creator creator ;
	private World world ;
	private Camera camera ;

	private MouseOrtho mouse ;
	private float time ;
	private boolean isEnable ;
	private Button back ;
	private FontCreator font ;
	private WorldCreator worldCreator; 
	private Player player ;
	
	public GameState(Loader loader, Renderer renderer, World world, FontCreator font, MouseOrtho mouse, Camera camera, WorldCreator worldCreator, Creator creator ) {
		this.loader = loader ;
		this.renderer = renderer ;
		this.world = world ;
		this.font = font ;
		this.mouse = mouse ;
		time = 0 ;
		isEnable= false ;
		this.camera = camera ;
		this.worldCreator = worldCreator ;
		this.creator = creator ;
	}
	@Override
	public void init() {
		
		camera.setPosition(0, 0);
		
		Menu menu = new Menu(new Vec2(-80,59),0,new Vec2(2,3),2,1);
		back = menu.addButton("Back", true, font,renderer);
		worldCreator.loadWorld("levels"); 
		
		
	/*	player = (Player) creator.createEntity(EntityType.Player, "playerpack", new Vec2(-7, 24), 0, new Vec2(8,8), 1) ;
		creator.createPhysics(player,new Vec2(0,-0), Coordinates.Vertex,new Vec2(2,2), BodyType.DYNAMIC, true, ShapeType.POLYGON, 0.1f, 0.1f, 0.1f);
		*/
		
	}
	
	@Override
	public void update() {
		
		if(back.isIntersect() && Mouse.isButtonDown(0)) {
			StateManager.setCurrentState(StateManager.StateEnum.MenuState);
		}
		if(isEnable) {
			time+= DisplayManager.getFrameTime() ;
			
			if(time > 0.3f) {
				isEnable = false ;
				time = 0 ;
			}
		}
		//player.playerController(mouse);
		camera.move();
		renderer.update();
		
	}

	@Override
	public void clear() {
		renderer.clearGame(world);
	}
}


