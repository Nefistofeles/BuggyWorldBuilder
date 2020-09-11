package fileLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import entity.Entity;
import entity.Player;
import loader.Loader;
import main.Creator;

public class WriterAndReader {


	/*private Loader loader;
	private Creator creator ;

	public WriterAndReader(Loader loader,Creator creator) {
		this.loader = loader;
		this.creator = creator ;
	}
	public void clearFile(String fileName) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("res/" + fileName + ".txt"));
			bufferedWriter.write("");
			bufferedWriter.flush();
			bufferedWriter.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeEntity(List<Entity> entities , String fileName) {
		File file = new File("res/" + fileName + ".txt");
		if (SearchFile.search(fileName, ".txt")) {
			System.out.println("res/" + fileName + ".txt " + "is already exists");
			clearFile(fileName);
		} else {
			try {
				if (file.createNewFile()) {
					System.out.println("res/" + fileName + ".txt" + " File Created");
				} else {
					System.out.println("File " + "res/" + fileName + ".txt" + " already exists");
					clearFile(fileName);
					
				}
					
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("res/" + fileName + ".txt",true))) {
			for(Entity entity : entities) {
				if(entity instanceof Player) {
					System.out.println("player is not writed");
				}else {
					String tag = "e " + entity.getId(); //id
					String rawName = "n " + entity.getRawName();
					String texName = "t " + entity.getTexName();
					String position = "p " + entity.getPosition().x + " " + entity.getPosition().y + " " + entity.getPosition().z;
					String rotation = "r " + entity.getRotation().x + " " + entity.getRotation().y + " " + entity.getRotation().z;
					String scale = "s " + entity.getScale();
					
					
				
					bufferedWriter.write(tag);
					bufferedWriter.newLine();
					bufferedWriter.write(rawName);
					bufferedWriter.newLine();
					bufferedWriter.write(texName);
					bufferedWriter.newLine();
					bufferedWriter.write(position);
					bufferedWriter.newLine();
					bufferedWriter.write(rotation);
					bufferedWriter.newLine();
					bufferedWriter.write(scale);
					bufferedWriter.newLine();
					bufferedWriter.write("f ");
					bufferedWriter.newLine();
					
				}

				
			}
			bufferedWriter.close();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(fileName + " is not loaded");
			
		}
		


		
		
		
	}

	public void writeEntity(Entity entity, String fileName) {
		File file = new File("res/" + fileName + ".txt");
		if (SearchFile.search(fileName, ".txt")) {
			System.out.println("res/" + fileName + ".txt " + "is already exists");
			
		} else {
			try {
				if (file.createNewFile()) {
					System.out.println("res/" + fileName + ".txt" + " File Created");
				} else {
					System.out.println("File " + "res/" + fileName + ".txt" + " already exists");
					
					
				}
					
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	/*	try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/" + fileName + ".txt"))) {
			String line;
			int i = 0 ;
			while((line =  bufferedReader.readLine()) != null) {
				String[] currentLine = line.split(" ");
				if(line.startsWith("e ")) {
					if(currentLine[1] == entity.getId()) {
						return ;
					}
				}
			}
			bufferedReader.close();
		}catch(Exception e) {
			System.out.println(fileName + " file is not found");
		}
		
		String tag = "e " + entity.getId(); //id
		String rawName = "n " + entity.getRawName();
		String texName = "t " + entity.getTexName();
		String position = "p " + entity.getPosition().x + " " + entity.getPosition().y + " " + entity.getPosition().z;
		String rotation = "r " + entity.getRotation().x + " " + entity.getRotation().y + " " + entity.getRotation().z;
		String scale = "s " + entity.getScale();

		
		 String fileContent = new String("E:" + entity.getRawName() + ":" +
		 entity.getTexName() + ":" + entity.getPosition() + ":"+entity.getRotX()+
		 ":"+entity.getRotY()+ ":"+entity.getRotZ()+":"+entity.getScale());
		 

		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("res/" + fileName + ".txt",true))) {
			bufferedWriter.write(tag);
			bufferedWriter.newLine();
			bufferedWriter.write(rawName);
			bufferedWriter.newLine();
			bufferedWriter.write(texName);
			bufferedWriter.newLine();
			bufferedWriter.write(position);
			bufferedWriter.newLine();
			bufferedWriter.write(rotation);
			bufferedWriter.newLine();
			bufferedWriter.write(scale);
			bufferedWriter.newLine();
			bufferedWriter.write("f ");
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("entity is not read");
			e.printStackTrace();
		}

	}

	public List allReadEntity(String fileName ) {
		List<String> entityID = new ArrayList<String>();
		List<String> entityRawName = new ArrayList<String>();
		List<String> entityTexName = new ArrayList<String>();
		List<Vector3f> entityPosition = new ArrayList<Vector3f>();
		List<Vector3f> entityRotation = new ArrayList<Vector3f>();
		List<Float> entityScale = new ArrayList<Float>();
		List<Entity> entities = new ArrayList<Entity>();
		System.out.println("file found " + SearchFile.search(fileName, ".txt"));
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/" + fileName + ".txt"))) {
			
			String line;
			int i = -1 ;
			while((line =  bufferedReader.readLine()) != null) {
				String[] currentLine = line.split(" ");
				System.out.println(line);
			//	for(String s : currentLine) {

					if(line.startsWith("cleared")) {
						continue ;
					}
					if(line.startsWith("e ")) {
						i++;
						
					}			
					if (line.startsWith("e")) {
						entityID.add(currentLine[1]);
					} else if (line.startsWith("n")) {
						entityRawName.add(currentLine[1]);
					} else if (line.startsWith("t")) {
						entityTexName.add(currentLine[1]);
					} else if (line.startsWith("p")) {
						entityPosition.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
								Float.parseFloat(currentLine[3])));
					} else if (line.startsWith("r")) {
						entityRotation.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
								Float.parseFloat(currentLine[3])));
					} else if (line.startsWith("s")) {
						entityScale.add(Float.parseFloat(currentLine[1]));
					} else if (line.startsWith("f")) {
						System.out.println(entityRawName.get(0) + " " + entityTexName.get(0) + " " + entityPosition.get(0)
							+" " +entityRotation.get(i)+" "+ entityScale.get(0)+" " +entityID.get(0)+" " +" " );
						Entity entity = creator.createEnemy(entityRawName.get(i), entityTexName.get(i),
								entityPosition.get(i), entityRotation.get(i), entityScale.get(i),entityID.get(i) );
						
						entities.add(entity);
						
						
					}
			//	}
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("xxfile is not found");
			
		}
		int i = 0;
		for (Entity entity : entities) {
			i++;
			System.out.println(entity.getRawName());
		}
		entityID.clear();
		entityRawName.clear();
		entityTexName.clear();
		entityPosition.clear();
		entityRotation.clear();
		entityScale.clear();
		return entities;
		

	}
	public List readOneEntity(String fileName , String name) {
		List<String> entityID = new ArrayList<String>();
		List<String> entityRawName = new ArrayList<String>();
		List<String> entityTexName = new ArrayList<String>();
		List<Vector3f> entityPosition = new ArrayList<Vector3f>();
		List<Vector3f> entityRotation = new ArrayList<Vector3f>();
		List<Float> entityScale = new ArrayList<Float>();
		List<Entity> entities = new ArrayList<Entity>();
		
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("res/" + fileName + ".txt"))) {
			String line;
			int i = 0 ;
			while((line =  bufferedReader.readLine()) != null) {
				String[] currentLine = line.split(" ");
			//	for(String s : currentLine) {

					if(line.startsWith("e ")) {
						if(currentLine[1] == name) {
							if (line.startsWith("e ")) {
								entityID.add(currentLine[1]);
								System.out.println(entityID.get(0));
							} else if (line.startsWith("n ")) {
								entityRawName.add(currentLine[1]);
							} else if (line.startsWith("t ")) {
								entityTexName.add(currentLine[1]);
							} else if (line.startsWith("p ")) {
								entityPosition.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
										Float.parseFloat(currentLine[3])));
							} else if (line.startsWith("r ")) {
								entityRotation.add(new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
										Float.parseFloat(currentLine[3])));
							} else if (line.startsWith("s ")) {
								entityScale.add(Float.parseFloat(currentLine[1]));
							} else if (line.startsWith("f ")) {
								Entity entity = creator.createEnemy(entityRawName.get(0), entityTexName.get(0),
										entityPosition.get(0), entityRotation.get(0), entityScale.get(0),entityID.get(0) );
								entities.add(entity);
								
							}
						}
						
					}else {
						System.out.println("entity");
					}

			//	}
			}

			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("file is not found");
			
		}
		entityID.clear();
		entityRawName.clear();
		entityTexName.clear();
		entityPosition.clear();
		entityRotation.clear();
		entityScale.clear();
		return entities;
	}*/
}
