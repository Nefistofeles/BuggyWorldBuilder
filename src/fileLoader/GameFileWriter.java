package fileLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbox2d.common.Vec2;

import worldCreator.EntityChange;
import worldCreator.TextureChange;
import worldCreator.Tile;

public class GameFileWriter {

	private void clearFile(String fileName, String fileType) {
		
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/res/" + fileName + fileType));
			bufferedWriter.write("");
			bufferedWriter.flush();
			bufferedWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void writeFile(String fileName, String fileType, Tile[][] tiles, List<Vec2[]> savePhysics, EntityChange entityChange, TextureChange textureChange) {
		File file = new File("src/res/" + fileName + fileType);
		if (SearchFile.search(fileName, ".txt")) {
			System.out.println("res/" + fileName + fileType + "is already exists");
			clearFile(fileName, fileType);
		} else {
			try {
				if (file.createNewFile()) {
					System.out.println("res/" + fileName + fileType + " File Created");
				} else {
					System.out.println("File " + "res/" + fileName + fileType + " already exists");
					clearFile(fileName, fileType);
					
				}
					
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(-1);
			}
		}
		
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/res/" + fileName + fileType,true))) {
			bufferedWriter.write(tiles.length + " " + tiles[0].length);
			bufferedWriter.newLine();

			Map<Integer, String> entityIDs = new HashMap<Integer, String>();
			Map<Integer, String> textureIDs = new HashMap<Integer, String>();
			
			for(int j = 0 ; j < tiles.length ; j++) {
				for(int i = 0 ; i < tiles[0].length ; i++) {
					Tile tile = tiles[tiles.length - 1 - j][i] ;
					String id = tile.getId() ;
					String[] parser2 = id.split("/") ;
					String[] parserx = parser2[0].split("_") ;
					if(tile.getEntity() != null) {
						if(entityIDs.get(Integer.parseInt(parserx[0])) == null) {
							entityIDs.put(Integer.parseInt(parserx[0]), entityChange.getEntityType(Integer.parseInt(parserx[0])).toString()) ;
							
							System.out.println(parserx[0] + " " + entityChange.getEntityType(Integer.parseInt(parserx[0])).toString());
						}
						if(textureIDs.get(Integer.parseInt(parserx[1])) == null) {
							textureIDs.put(Integer.parseInt(parserx[1]), textureChange.getTextureName(Integer.parseInt(parserx[1])).toString()) ;
							
							System.out.println(parserx[1] + " " + textureChange.getTextureName(Integer.parseInt(parserx[1])).toString());
						}
						
						
						
					}
					
				}
				
			}
			bufferedWriter.write("->E");
			bufferedWriter.newLine();
			int a = 0 ;
			for(Integer i : entityIDs.keySet()) {
				
				if(a == entityIDs.size() - 1)
					bufferedWriter.write(i +"=" + entityIDs.get(i));
				else
					bufferedWriter.write(i +"=" + entityIDs.get(i) + " ");
				
				a++ ;
				
			}
			bufferedWriter.newLine();
			bufferedWriter.write("<-E");
			bufferedWriter.newLine();
			bufferedWriter.write("->T");
			bufferedWriter.newLine();
			a = 0 ;
			for(Integer i : textureIDs.keySet()) {
				
				if(a == textureIDs.size() - 1)
					bufferedWriter.write(i +"=" + textureIDs.get(i));
				else
					bufferedWriter.write(i +"=" + textureIDs.get(i) + " ");
				
				a++ ;
				
			}
			bufferedWriter.newLine();
			bufferedWriter.write("<-T");
			
			bufferedWriter.newLine();
			bufferedWriter.write("->A");
			bufferedWriter.newLine();
			
			for(int j = 0 ; j < tiles.length ; j++) {
				for(int i = 0 ; i < tiles[0].length ; i++) {
					Tile tile = tiles[tiles.length - 1 - j][i] ;
					String id = ""+tile.getId() ;
					if(i != tiles.length-1)
						bufferedWriter.write(id + " ");
					else
						bufferedWriter.write(id);
					
				}
				bufferedWriter.newLine();
			}
			bufferedWriter.write("<-A");
			bufferedWriter.newLine();

			bufferedWriter.write("->P");
			bufferedWriter.newLine();
			for(Vec2[] x : savePhysics) {
				
				bufferedWriter.write(x[0].x + " " + x[0].y+ " " + x[1].x + " " + x[1].y);
				bufferedWriter.newLine();
			}
			bufferedWriter.write("<-P");
			bufferedWriter.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(fileName + " is not saved");
			System.exit(-1);
		}
		
		
	}
}
