package dataStructure;

import java.util.Map;

import org.jbox2d.common.Vec2;

import utils.DisplayManager;

public class Animation {

	private float time;
	private int x;
	private int y;
	
	private Map<Integer, Integer> animationInformation ;

	public Animation() {
		time = 0;
		x = 0;
		y = 0;
		
	}
	public void setAnimationInformation(Map<Integer, Integer> animationInformation) {
		this.animationInformation = animationInformation ;
	}

	public void doAnimation(EntityTexture texture,boolean doAnimate, int offset, float timeS) {
		y = offset ;

		time += DisplayManager.getFrameTime();
		if (time >= timeS && doAnimate) {
			time = 0;
			texture.setIndex(x, y);
			
			if(x < animationInformation.get(y))
				x++;
			System.out.println(x + " " + y);
			
			if (x >= animationInformation.get(y)) {
				x = 0;
				
			}
			if (y >= animationInformation.get(y)) {
				y = 0;
			}
		}

	}
	public void clearAnimation(EntityTexture texture) {
		x = 0 ;
		y = 0 ;
		texture.setIndex(x, y);
	}
}
