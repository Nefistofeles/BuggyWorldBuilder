package main;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import renderer.EnableOpenGL;
import utils.DisplayManager;
import utils.Maths;

public class Game implements Runnable{

	private Thread thread ;
	private boolean isRun;
	private DisplayManager display ;
	private GameManager gameManager ;
	private World world ;
	
	public Game() {
		thread = null ;
		isRun = false ;
		
		
	}
	private void init() {
		display = new DisplayManager();
		display.createDisplay();
		world = new World(new Vec2(0,0)) ;
		gameManager = new GameManager(world);
		gameManager.init();
		
		EnableOpenGL.culling(true);
		EnableOpenGL.blendFunc(false);
		EnableOpenGL.enableDepthTest(true);
		EnableOpenGL.enableStencilTest(true);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1);
	}
	@Override
	public void run() {
		init();
		while(!Display.isCloseRequested()) {
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT );
			world.step(DisplayManager.getFrameTime(), 6, 2);
			gameManager.update();
			gameManager.render();
			display.updateDisplay();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		destroy();
		display.closeDisplay();
		stop();
		
		
	}
	
	public synchronized void start() {
		if(isRun)
			return ;
		isRun = true ;
		if(thread == null)
			thread = new Thread(this);
		
		thread.start();
		System.out.println("thread start");
		
	}
	
	public synchronized void stop() {
		if(!isRun) 
			return ;
		try {
			System.out.println("thread stop");
			isRun = false ;
			thread.interrupt();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
			
		}
	}
	private void destroy() {
		gameManager.destroy();
	}

	
}
