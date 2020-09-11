package entity;

import java.util.HashMap;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Keyboard;

import dataStructure.Animation;
import dataStructure.DoAnimation;
import dataStructure.EntityTexture;
import dataStructure.Mesh;
import dataStructure.Texture;
import gameEntity.MouseOrtho;

public class Player extends Entity implements DoAnimation{

	private Vec2 walkDirection;
	private Vec2 speed;

	private boolean up = false ;
	private boolean down = false ;
	private boolean left = false ;
	private boolean right = false ;
	
	private int index ;
	
	public Player(Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(texture, position, rotation, scale, worldPosition);
		walkDirection = new Vec2(0, 0);
		index = 0 ;

		speed = new Vec2(100, 100);
		
		setAnimationInformation();

	}
	@Override
	public void setAnimationInformation() {
		Map<Integer, Integer> animationInformation = new HashMap<Integer, Integer>();
		animationInformation.put(0, 4) ;
		animationInformation.put(1, 5) ;
		
		animation.setAnimationInformation(animationInformation);
		
	}
	public void playerController() {
		/*
		 * Vec2 mousePosition = mouse.getMousePos2(); float radians = (float)
		 * Math.atan2(mousePosition.y - position.y, mousePosition.x - position.x) ;
		 * float degree = (float) (radians * 180 / Math.PI) ; rotation = degree - 90;
		 * Vec2 degreeVector = new Vec2((float)Math.cos(radians),
		 * (float)Math.sin(radians)); body.applyForceToCenter(new Vec2( speed.x*
		 * degreeVector.x , speed.y * degreeVector.y));
		 */
		/*
		 * System.out.println(Keyboard.get); switch(Keyboard.getEventKey()) { case
		 * Keyboard.KEY_W : System.out.println("W"); case Keyboard.KEY_S :
		 * System.out.println("S"); case Keyboard.KEY_A : System.out.println("A"); case
		 * Keyboard.KEY_D : System.out.println("D"); }
		 */
		while (Keyboard.next()) {

			if (Keyboard.getEventKeyState()) {
				switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_A : 
					walkDirection.x = -1;
					left = true ;
					break ;
				case Keyboard.KEY_S : 
					walkDirection.y = -1;
					down = true ;
					break ;
				case Keyboard.KEY_D : 
					walkDirection.x = 1;
					right = true ;
					break ;
				case Keyboard.KEY_W : 
					walkDirection.y = 1;
					up = true ;
					break ;
				
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_W && Keyboard.getEventKey() == Keyboard.KEY_D ) {
					System.out.println("adasda");
				}
			} else {
				switch(Keyboard.getEventKey()) {
				case Keyboard.KEY_A : 
					walkDirection.x = 0;
					left = false ;
					break ;
				case Keyboard.KEY_S : 
					walkDirection.y = 0;
					down = false ;
					break ;
				case Keyboard.KEY_D : 
					walkDirection.x = 0;
					right = false ;
					break ;
				case Keyboard.KEY_W : 
					walkDirection.y =0;
					up = false ;
					break ;
				}
			}
		}
		if(up) {
			rotation = 0;
		}
		if(down) {
			
			rotation = 180;
		}
		if(left) {
			rotation = 90;
		}
		if(right) {
			rotation = 270;
		}
		if(up && left) {
			rotation = 45;
		}
		if(up && right) {
			rotation = -45;
			
		}
		if(down && left) {
			rotation = 135;
			
		}
		if(down && right) {
			rotation = -135;
			
		}


		body.applyForceToCenter(new Vec2(speed.x * walkDirection.x, speed.y * walkDirection.y));

	}

	@Override
	public void update() {
		playerController();
		
		if(walkDirection.x != 0 || walkDirection.y != 0 ) {
			if(index != 0) {
				animation.clearAnimation((EntityTexture) texture);
				index = 0 ;
			}
			animation.doAnimation((EntityTexture) texture, true, index, 0.5f);
			
		}else if(Keyboard.isKeyDown(Keyboard.KEY_1) && walkDirection.x == 0 && walkDirection.y == 0) {
			if(index != 1) {
				animation.clearAnimation((EntityTexture) texture);
				index = 1 ;
			}
			animation.doAnimation((EntityTexture) texture, true, index, 0.2f);
		}else {
			animation.clearAnimation((EntityTexture) texture);
		}
		
		
		
		
		

	}

	@Override
	public void dead() {

	}



}
