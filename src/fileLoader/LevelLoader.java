package fileLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbox2d.collision.shapes.ShapeType;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.lwjgl.util.vector.Vector3f;

import entity.Entity;
import entity.EntityType;
import main.Creator;
import renderer.Renderer;
import utils.Coordinates;
import worldCreator.EntityCreate;
import worldCreator.WorldCreator;

public class LevelLoader {
	
	private EntityCreate entityCreate ;
	
	public LevelLoader(EntityCreate entityCreate) {
		this.entityCreate = entityCreate ;
	}

	
	// TODO : something pls
	public void loadLevel(String fileName, Renderer renderer, Creator creator, Vec2 startPos, Vec2 scale, WorldCreator worldCreator) {

		try {
			InputStream in = Class.class.getResourceAsStream("/res/levels.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			String sizeString = reader.readLine();
			String[] parse = sizeString.split(" ");
			String[] parser = null ;
			String[] parser2 = null ;
			Vec2 size = new Vec2(Integer.parseInt(parse[0]), Integer.parseInt(parse[1]));
			
			String line = null;

			float xpos = 0;
			float ypos = 0;

			while ((line = reader.readLine()) != null) {
				
				if (line.startsWith("->A")) {
					//line = reader.readLine();
					for (int j = (int) (size.y- 1); j >= 0; j--) {
						ypos = j * scale.x * 2;
						line = reader.readLine();
						parse = line.split(" ");
						
						for (int i = 0; i < size.x; i++) {
							xpos = i * scale.y * 2;
							
							if(!parse[i].startsWith("0")) {
								parser2 = parse[i].split("/") ;
								
								parser = parser2[0].split("_") ;
								int entity = Integer.parseInt(parser[0]) ;
								int texture = Integer.parseInt(parser[1]) ;
								
								parser = parser2[1].split("_") ;
								float scalex = Float.parseFloat(parser[0] + "f") ;
								float scaley = Float.parseFloat(parser[1] + "f") ;
								
								Entity entityE = entityCreate.getEntity(texture, entity, new Vec2(xpos,ypos),Float.parseFloat(parser2[2]+ "f"), new Vec2(scalex, scaley)) ;
								
							}


						}
						
					}
					

				}

				if (line.startsWith("->T")) {
					line = reader.readLine();
					Map<Integer, String> textures = new HashMap<Integer, String>();
					while ((line = reader.readLine()) != null && !line.startsWith("<-T")) {
						parse = line.split("=");
						textures.put(Integer.parseInt(parse[0]), parse[1]);

					}
				}
				if (line.startsWith("->P")) {

					while ((line = reader.readLine()) != null && !line.startsWith("<-P")) {
						parse = line.split(" ");

						float a = Float.parseFloat(parse[0] + "f");
						float b = Float.parseFloat(parse[1] + "f");
						float c = Float.parseFloat(parse[2] + "f");
						float d = Float.parseFloat(parse[3] + "f");
						
					/*	Entity e = creator.createEntity(EntityType.Wall, "particle", new Vec2(a*scale.x, b* scale.y ), 0,
								new Vec2(c * scale.x, d * scale.y), 1);
						
						System.out.println("pos " + e.getPosition());*/
						creator.createTiledWorldPhysics(Coordinates.Vertex, new Vec2(a*scale.x, b* scale.y ), new Vec2(c * scale.x, d * scale.y));
					}

				}

			}

			/*
			 * for(int j = 0 ; j < tile.length ; j++) { yy++ ; ypos += yy * 2 ; for(int i =
			 * 0 ; i < tile[0].length ; i++) { xpos += i * 2 ;
			 * 
			 * if(tile[j][i] == 1) { creator.createEntity(EntityType.Wall, "awesomeface1",
			 * new Vec2(xpos, ypos), 0, new Vec2(2,2), 0); }
			 * 
			 * } xpos = 0;
			 * 
			 * }
			 */

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(fileName + " is not loaded");
			System.exit(-1);
		}

	}

	private void putVectorToFloatArray(Vec2 data, float[] data2, int k) {
		data2[k] = data.x;
		k++;
		data2[k] = data.y;
		k++;
		System.out.println("data " + data.toString());

	}
}
