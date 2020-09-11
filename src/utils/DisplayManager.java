package utils;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static int WIDTH = 1920 ;
	private static int HEIGHT = 1080 ;
	private static int FPS = 120 ;
	
	private static float delta ;
	private long lastTime ;
	private long now ;

	public static final Orthographics orthographics = new Orthographics(80,60);
	public static final Matrix4 projectionMatrix = orthographics.getProjectionMatrix();

	public DisplayManager() {
		System.setProperty("org.lwjgl.opengl.Display.enableHighDPI", "true");
		
	}
	public void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3,3).withForwardCompatible(true).withProfileCore(true) ;
		PixelFormat pixel = new PixelFormat();
		
		pixel.withDepthBits(24) ;
		pixel.withStencilBits(8) ;
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(pixel, attribs);
			Display.setTitle("Shooter");
			Display.setFullscreen(false);
			Display.setVSyncEnabled(true);
			Display.setResizable(false);
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastTime = getTime() ;
		
		System.out.println(GL11.glGetString(GL11.GL_VENDOR));
		System.out.println(GL11.glGetString(GL11.GL_RENDERER)) ;
	
	}
	public void updateDisplay() {
	///	Display.sync(FPS);
		Display.update();
		
		now = getTime();
		delta = (now - lastTime) / 1000f ;
		lastTime = now ;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		}else if(Keyboard.isKeyDown(Keyboard.KEY_M)) {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL );
		}else if(Keyboard.isKeyDown(Keyboard.KEY_B)) {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_POINT );
		}
	
		
		if(Display.wasResized()) {
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}

	}
	public static float getFrameTime() {
		return delta ;
	}
	
	public void closeDisplay() {
		Display.destroy();
	}
	
	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution() ;
	}
}
