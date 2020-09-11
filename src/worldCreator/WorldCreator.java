package worldCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Mouse;

import dataStructure.Texture;
import entity.Entity;
import entity.EntityType;
import fileLoader.GameFileWriter;
import fileLoader.LevelLoader;
import fileLoader.SearchFile;
import gameEntity.Camera;
import gameEntity.MouseOrtho;
import main.Creator;
import renderer.Renderer;
import utils.Color;

public class WorldCreator {

	private Tile[][] tiles;
	private List<Tile> selectedEntityList;
	private List<Vec2[]> savePhysics;
	private Creator creator;
	private MouseOrtho mouse;
	private Camera camera;
	private Renderer renderer;
	private GameFileWriter gameWriter;
	private Vec2 startPos;
	private Vec2 size;
	private Vec2 scale;
	private LevelLoader levelLoader;
	
	private TextureChange textureChange ;
	private EntityChange entityChange ;
	private EntityCreate entityCreate ;
	
	public WorldCreator(Creator creator, MouseOrtho mouse, Camera camera, Renderer renderer, Vec2 startPos, Vec2 size,
			Vec2 scale) {
		this.creator = creator;
		this.mouse = mouse;
		this.camera = camera;
		this.renderer = renderer;
		this.startPos = startPos;
		this.size = size;
		this.scale = scale;
		gameWriter = new GameFileWriter();
		selectedEntityList = new ArrayList<Tile>();
		savePhysics = new ArrayList<Vec2[]>();
		
		createGrid();
		textureChange = new TextureChange(creator);
		entityChange = new EntityChange(creator.getData(), scale.clone()) ;
		entityCreate = new EntityCreate(creator, this, entityChange, textureChange);
		levelLoader = new LevelLoader(entityCreate);
		
	}

	
	public TextureChange getTextureChange() {
		return textureChange;
	}
	public EntityChange getEntityChange() {
		return entityChange;
	}
	
	public void createGrid() {
		tiles = new Tile[(int) size.y][(int) size.x];
		for (int j = (int) (size.y - 1); j >= 0; j--) {
			for (int i = 0; i < size.x; i++) {
				Tile t = new Tile("0");
				t.setProperties(i * scale.x * 2 - 0.5f * scale.x, j * scale.y * 2 - 0.5f * scale.y, scale.x, scale.y);
				tiles[j][i] = t;

			}
		}

	}

	public void loadGrid() {
		for (int j = (int) (tiles.length - 1); j >= 0; j--) {
			for (int i = 0; i < tiles[0].length; i++) {
				creator.createEntity(EntityType.Wall, "particle", new Vec2(tiles[j][i].getCenter()), 0, scale, -1);
			}

		}
	}
	public void textureSettingForEntity(String textureName) {
		for(int i = 0 ; i < selectedEntityList.size() ; i++) {
			Tile t = selectedEntityList.get(i) ;
			creator.setEntityTexture(t.getEntity(), textureName) ;
		}
			
		
	}

	public Entity isIntersect() {
	
		Tile tile = null;
		Entity entity = null ;
		for (int j = 0; j < tiles.length; j++) {
			for (int i = 0; i < tiles[0].length; i++) {
				tile = tiles[j][i];
				if (isInside(tile, scale) && Mouse.isButtonDown(0)) {
					if (tile.getEntity() == null) {
						entity = giveMeEntity(entityChange.getEntityType(), textureChange.getTextureName(), tile);
						return tile.getEntity();
					}

				} else if (isInside(tile, scale) && tile.getEntity() != null && Mouse.isButtonDown(1)) {
					renderer.deleteEntity(tile.getEntity());
					tile.setEntity(null);
					tile.setId("0");
				}

			}
		}

		return entity;

	}
	private Entity giveMeEntity(EntityType entityType ,String textureName, Tile tile) {
		Entity entity =  creator.createEntity(entityType, textureName, tile.getCenter(), entityChange.getRotate(), entityChange.getScale().clone(), 1) ;
		tile.setEntity(entity);
		tile.setId(entityChange.getID() + "_"+ textureChange.getID() + "/" + entityChange.getScale().x + "_"+ entityChange.getScale().y + "/"+entityChange.getRotate());
		
		if(entityType == EntityType.Player || entityType == EntityType.Enemy) {
			if(entity.getColor() != null)
				entity.setColor(new Color(0,0,0,0));
		}

		return entity ;
	}

	public Entity getEntityTheLocation() {

		for (int j = 0; j < tiles.length; j++) {
			for (int i = 0; i < tiles[0].length; i++) {
				Tile tile = tiles[j][i];
				if (isInside(tile, scale) && Mouse.isButtonDown(0) && !tile.isSelected()) {
					if (tile.getEntity() != null) {
						tile.getEntity().setColor(new Color(0, 1, 1, 1));
						tile.setSelected(true);
						selectedEntityList.add(tile);
						System.out.println(tiles[j][i].getCenter());

					}
				} else if (isInside(tile, scale) && Mouse.isButtonDown(0) && tile.isSelected()) {
					if (tile.getEntity() != null) {
						tile.getEntity().setColor(new Color(1, 1, 1, 1));
						tile.setSelected(false);
						Iterator<Tile> it = selectedEntityList.iterator();
						while (it.hasNext()) {
							Tile e = it.next();
							if (e == tile) {
								it.remove();
							}
						}
					}
				}

			}
		}
		return null;
	}

	public void save(String fileName, String fileType) {
		// if(SearchFile.search(fileName, fileType)) {
		gameWriter.writeFile(fileName, fileType, tiles, savePhysics, entityChange, textureChange);
		// }

	}

	public void createPhysics() {
		
		if (!selectedEntityList.isEmpty()) {

			Vec2 pos = new Vec2(0,0);
			Vec2 min = new Vec2(Float.MAX_VALUE	,Float.MAX_VALUE);
			Vec2 max = new Vec2(-Float.MAX_VALUE,-Float.MAX_VALUE);
			
			for (int i = 0; i < selectedEntityList.size(); i++) {
				pos = selectedEntityList.get(i).getCenter();
				
				if (pos.x < min.x) {
					min.x = pos.x;

				}
				if (pos.y < min.y) {
					min.y = pos.y;
				}
				if (pos.x > max.x) {
					max.x = pos.x;

				}
				if (pos.y > max.y) {
					max.y = pos.y;
				}

			}
			
			Vec2[] ax = new Vec2[2];
			Vec2 x = new Vec2((max.x + min.x) / (2) , (max.y + min.y) / (2)) ;
			
			ax[0] = new Vec2(x.x / scale.x, x.y / scale.y);
			ax[1] = new Vec2((scale.x + (max.x - min.x) / 2)/ scale.x, (scale.y+ (max.y - min.y) / 2) / scale.y);
			
			savePhysics.add(ax);

			for (int i = 0; i < selectedEntityList.size(); i++) {
				selectedEntityList.get(i).getEntity().getColor().setColor(1, 0, 0, 1);
			}

			selectedEntityList.clear();
		}

	}

	public void loadWorld(String name) {
		levelLoader.loadLevel(name, renderer, creator, startPos, scale, this);
	}
	/*
	 * private void putVectorToFloatArray(Vec2 data, float[] data2, int k) {
	 * data2[k] = data.x ; k += 1; data2[k] = data.y ; k+=1;
	 * 
	 * }
	 */
	/*
	 * float[] vertex = new float[8] ; int k = 0 ; putVectorToFloatArray(new
	 * Vec2(min.x, max.y), vertex, k); putVectorToFloatArray(new Vec2(max.x, max.y),
	 * vertex, k); putVectorToFloatArray(new Vec2(max.x, min.y), vertex, k);
	 * putVectorToFloatArray(new Vec2(min.x, min.y), vertex, k);
	 */
	/*
	 * for(int i = 0 ; i < selectedEntityList.size() ; i++) {
	 * selectedEntityList.get(i).setColor(new Color(1,0,0,0.6f));
	 * 
	 * tile.setSelected(false); Iterator<Entity> it = selectedEntityList.iterator();
	 * while(it.hasNext()) { Entity e = it.next() ; if(e == tile.getEntity()) {
	 * it.remove(); } }
	 * 
	 * }
	 */

	private boolean isInside(Tile tile, Vec2 scale) {
		Vec2 mousePos = mouse.getMousePos2();
		float x1 = tile.getCenter().x + Tile.min.x * scale.x;
		float y1 = tile.getCenter().y + Tile.min.y * scale.y;
		float x2 = tile.getCenter().x + Tile.max.x * scale.x;
		float y2 = tile.getCenter().y + Tile.max.x * scale.y;

		if (mousePos.x < x1 || mousePos.y < y1) {
			return false;
		}
		if (mousePos.x > x2 || mousePos.y > y2) {
			return false;
		}
		return true;
	}

	public List<Tile> getSelectedEntityList() {
		return selectedEntityList;
	}

}
