package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import dataStructure.Mesh;
import dataStructure.Texture;
import entity.Enemy;
import entity.Entity;
import entity.EntityType;
import entity.Player;
import entity.Wall;
import entity.WorldPhysicParts;
import gui.GUI;
import gui.button.Button;
import gui.menu.Menu;
import loader.Loader;
import loader.TextureLoader;
import renderer.Renderer;
import text.FontCreator;
import text.Text;
import utils.Color;
import utils.Coordinates;

public class Creator {

	private World world ;
	private Renderer renderer ;
	private Loader loader ;

	private FontCreator font ;
	
	private Data data ;

	public Creator(World world, Renderer renderer,Loader loader,FontCreator font, Data data) {
		this.world = world ;
		this.renderer = renderer ;
		this.loader = loader ;
		this.font = font ;
		this.data = data ;

		
	}

	public Entity setEntityTexture(Entity entity, String textureName) {
		entity.setTexture(data.getTextures().get(textureName).clone());
		
		return entity ;
	}

	public Entity createEntity(EntityType entityType, String textureName, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		Entity entity = null ;
		switch(entityType) {
		case Wall :
			entity = new Wall(data.getTextures().get(textureName).clone(), position, rotation, scale, worldPosition) ; 
			//entity.setColor(new Color(1,0,1,0.2f));
			renderer.addEntity(entity);
			break ;
		case Player :
			entity = new Player(data.getTextures().get(textureName).clone(), position, rotation, scale, worldPosition) ; 
			renderer.addEntity(entity);
			break ;
		case Enemy :
			entity = new Enemy(data.getTextures().get(textureName).clone(), position, rotation, scale, worldPosition) ; 
			renderer.addEntity(entity);
			break ;
		default :
			System.out.println("entity is not added");
		}
		
		return entity ;
	}
	
	//TODO : 
/*	public Entity createEntity(EntityType entityType, String textureName,Vec2 offset,
			Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		Entity entity = null ;
		switch(entityType) {
		case Wall :
			entity = new Wall(data.getTextures().get(textureName).clone(), position, rotation, scale, worldPosition) ; 
			renderer.addEntity(entity);
			break ;
		case Player :
			entity = new Player(data.getTextures().get(textureName).clone(), position, rotation, scale, worldPosition) ; 
			renderer.addEntity(entity);
			break ;
		default :
			System.out.println("entity is not added");
		}
		
		return entity ;
	}*/
	
	
	
	
/*	public Menu createMenu(Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		Menu menu = new Menu(new Color(1,1,0,1), position, rotation, scale, worldPosition);
		renderer.addGUI(menu);
		
		return menu ;
	}*/
/*	public void loadText(FontCreator font, Text text) {
		font.loadText(text) ;
		renderer.addText(text);
		
	}*/
/*	public Button createButton(String string, boolean isCentered, Color buttonColor, Color textColor, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		Button button =  new Button(buttonColor, position,rotation, scale, worldPosition);
		button.setWorldPosition(worldPosition);
		renderer.addGUI(button);
		createText(string, button, isCentered, scale.x , 1, textColor, worldPosition) ;
		return button ;
	}
	public Button createButton(Color buttonColor, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		Button button =  new Button(buttonColor, position,rotation, scale, worldPosition);
		button.setWorldPosition(worldPosition);
		renderer.addGUI(button);
		return button ;
	}*/
/*	public Text createText(String string,Button button, boolean isCentered,float maxLineSize,float padding, Color color, float worldPosition) {
		Text text = button.writeText(string, isCentered,maxLineSize,padding, color, font);
		text.setWorldPosition(worldPosition);
		renderer.addText(text);
		return text ;
	}*/
	public void createTiledWorldPhysics(float[] vertices, Vec2 position, Vec2 scale) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.STATIC ; 
		bodydef.position = position ;
		bodydef.angle = 0 ;
		bodydef.fixedRotation = true ;
		bodydef.linearDamping = 0 ; 
		
		Shape shape = null ;
		Vec2[] newPosition = converter(vertices,scale, new Vec2(0,0)) ;
		shape = loadShape(position, newPosition) ;
		
		/*else if(shapeType == ShapeType.CIRCLE) {
			shape = loadShape(entity.getPosition(), entity.getScale().x) ;
		}*/
		Body body = world.createBody(bodydef) ;
		FixtureDef fixturedef = new FixtureDef();
		fixturedef.restitution = 0.0f;
		fixturedef.shape = shape ;
		fixturedef.density = 1 ;
		fixturedef.friction = 1;
		Fixture fixture = body.createFixture(fixturedef) ;
		
		/*Entity entity = new WorldPhysicParts(position,0 , new Vec2(1,1)) ;
		entity.createPhysics(body, shape, fixture);*/
		renderer.addWorldPhysicParts(body);
	
	}
	//linear damping = sürtünme olmadan durma
	public void createPhysics(Entity entity, float[] vertices,Vec2 scale,BodyType bodyType, boolean fixedRotation,
			ShapeType shapeType, float restitution, float density, float friction) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = bodyType ; 
		bodydef.position = entity.getPosition() ;
		bodydef.fixedRotation = fixedRotation ;
		bodydef.linearDamping = 5 ; 
		bodydef.userData = entity ;
		
		Shape shape = null ;
		Vec2[] newPosition = converter(vertices,scale ,new Vec2(0,0)) ;
		if(shapeType == ShapeType.POLYGON) {
			shape = loadShape(entity.getPosition(), newPosition) ;
		}else if(shapeType == ShapeType.CIRCLE) {
			shape = loadShape(entity.getPosition(), scale.x) ;
		}
		Body body = world.createBody(bodydef) ;
		FixtureDef fixturedef = new FixtureDef();
		fixturedef.restitution = restitution;
		fixturedef.shape = shape ;
		fixturedef.density = density ;
		fixturedef.friction = friction;
		Fixture fixture = body.createFixture(fixturedef) ;
		
		entity.createPhysics(body, shape, fixture);
		
	}
	//linear damping = sürtünme olmadan durma
	public void createPhysics(Entity entity,Vec2 correctionPosition, float[] vertices,Vec2 scale,BodyType bodyType, boolean fixedRotation,
			ShapeType shapeType, float restitution, float density, float friction) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = bodyType ; 
		bodydef.position = entity.getPosition();
		bodydef.angle = entity.getRotation() ;
		bodydef.fixedRotation = true ;
		bodydef.linearDamping = 5 ; 
		bodydef.userData = entity ;
		
		Shape shape = null ;
		Vec2[] newPosition = converter(vertices,scale, correctionPosition) ;
		if(shapeType == ShapeType.POLYGON) {
			shape = loadShape(entity.getPosition(), newPosition) ;
		}else if(shapeType == ShapeType.CIRCLE) {
			shape = loadShape(entity.getPosition(), scale.x) ;
		}
		Body body = world.createBody(bodydef) ;
		FixtureDef fixturedef = new FixtureDef();
		fixturedef.restitution = restitution;
		fixturedef.shape = shape ;
		fixturedef.density = density ;
		fixturedef.friction = friction;
		Fixture fixture = body.createFixture(fixturedef) ;
		
		entity.createPhysics(body, shape, fixture);
		
	}
	public void createPhysics(Entity entity, float[] vertices,BodyType bodyType, boolean fixedRotation,
			ShapeType shapeType, float restitution, float density, float friction) {
		BodyDef bodydef = new BodyDef();
		bodydef.type = bodyType ; 
		bodydef.position = entity.getPosition() ;
		bodydef.angle = entity.getRotation() ;
		bodydef.fixedRotation = fixedRotation ;
		bodydef.linearDamping = 5 ; 
		bodydef.userData = entity ;
		
		Shape shape = null ;
		Vec2[] newPosition = converter(vertices,entity.getScale(), new Vec2(0,0)) ;
		if(shapeType == ShapeType.POLYGON) {
			shape = loadShape(entity.getPosition(), newPosition) ;
		}else if(shapeType == ShapeType.CIRCLE) {
			shape = loadShape(entity.getPosition(), entity.getScale().x) ;
		}
		Body body = world.createBody(bodydef) ;
		FixtureDef fixturedef = new FixtureDef();
		fixturedef.restitution = restitution;
		fixturedef.shape = shape ;
		fixturedef.density = density ;
		fixturedef.friction = friction;
		Fixture fixture = body.createFixture(fixturedef) ;
		
		entity.createPhysics(body, shape, fixture);
		
	}
	public Shape loadShape(Vec2 centerPos, Vec2[] vertices) {
		PolygonShape shape = new PolygonShape();
		shape.m_centroid.set(centerPos) ; 
		shape.set(vertices, vertices.length);
		return shape ;
	}
	public Shape loadShape(Vec2 shapePos, float radius) {
		CircleShape shape = new CircleShape();
		shape.m_p.set(shapePos) ;
		shape.m_radius = radius ;
		return shape ;
	}
	private Vec2[] converter(float[] vertices, Vec2 scale, Vec2 correctionPosition) {
		Vec2[] newPosition = new Vec2[vertices.length / 2] ;
		int k = 0 ;
		for(int i = 0 ; i < vertices.length ; i+=2) {
			Vec2 p = new Vec2(vertices[i] * scale.x + correctionPosition.x, vertices[i +1]* scale.y + correctionPosition.y) ;
			newPosition[k] = p ;
			k++ ;
		}

		return newPosition ;
	}
	public FontCreator getFont() {
		return font;
	}
	public void setFont(FontCreator font) {
		this.font = font;
	}

	public Data getData() {
		return data;
	}

	
	

}
