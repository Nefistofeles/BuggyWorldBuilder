package text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.Display;
import utils.Maths;

public class FontTypeLoader {

	private Map<String, String> data ;
	private Map<Integer, Character> characters ;
	private BufferedReader reader ;
	private int ownPadding ;
	private int padding ;
	private int lineHeight ;
	private int imageScaleWH ; 
	private String textureName ;
	private double verticalPerPixelSize ; 
	private double horizontalPerPixelSize ; 
	private double aspectRatio ;
	
	public FontTypeLoader(int ownPadding) {
		aspectRatio = Display.getWidth() / Display.getHeight() ;
		this.ownPadding = ownPadding ;
		data = new HashMap<String, String>();
		characters = new HashMap<Integer, Character>();
		
	}
	
	public void loadFont(String name) throws Exception{
		InputStream in = Class.class.getResourceAsStream("/res/" + name + ".fnt") ;
		reader = new BufferedReader(new InputStreamReader(in)) ;
		loadFile();
		reader.close();
		
	}
	private void loadFile() {
		parser();
		String[] paddingString = data.get("padding").split(",") ;
		padding= Integer.parseInt(paddingString[0]) ;
		
		parser();
		lineHeight = Integer.parseInt(data.get("lineHeight")) ;
		imageScaleWH = Integer.parseInt(data.get("scaleW")) ;
		parser();
		textureName = data.get("file") ;
		int lineHeightPixels = lineHeight - 2 * padding;
		verticalPerPixelSize = Maths.LINE_HEIGHT / (double) lineHeightPixels;
		horizontalPerPixelSize = verticalPerPixelSize / aspectRatio;
		parser();
		int count = Integer.parseInt(data.get("count")) ;
		for(int i = 0 ; i < count; i++) {
			parser();
			loadCharacter();
		}
		
	}
	
	private void loadCharacter() {
		int id = Integer.parseInt(data.get("id")) ;
		double width = Integer.parseInt(data.get("width")) - (2 * padding - (2 * ownPadding));
		double height = Integer.parseInt(data.get("height")) - (2 * padding - (2 * ownPadding));

		//textureda harfi bulup resim olarak yazdýrmak icin
		double x = ((double) Integer.parseInt(data.get("x"))+ (padding - ownPadding)) / imageScaleWH;
		double y = ((double) Integer.parseInt(data.get("y")) + (padding - ownPadding)) / imageScaleWH;
		double xTexSize = (double) width / imageScaleWH;
		double yTexSize = (double) height / imageScaleWH;
		
		//ekrana çizme için gereken bilgiler
		double quadWidth = width * (double)horizontalPerPixelSize;
		double quadHeight = height * (double)verticalPerPixelSize;
		double xoffset = (Integer.parseInt((data.get("xoffset"))) - (padding - ownPadding) )* (double)horizontalPerPixelSize;
		double yoffset = (Integer.parseInt(data.get("yoffset")) - (padding - ownPadding) )* (double)verticalPerPixelSize;
		double xadvance = (Integer.parseInt(data.get("xadvance")) - (2 * padding )) * (double)horizontalPerPixelSize;
		
		Character c = new Character(id, quadWidth, quadHeight, x, y, xTexSize, yTexSize, xoffset, yoffset, xadvance);
		characters.put(id, c) ;
	}

	private void parser() {
		String line = null ;
		try {
			line = reader.readLine();
		} catch (IOException e) {
			System.out.println("line is not found");
			e.printStackTrace();
			System.exit(-1);
		}
	
		if(line == null) {
			return ;
		}
		String[] parser1 = line.split(" ") ;
		for(int i = 0 ; i < parser1.length ; i++) {
			String[] parser2 = parser1[i].split("=") ;
			if(parser2.length == 2) {
				data.put(parser2[0], parser2[1]) ;
			}

		}
	}

	public Map<Integer, Character> getCharacters() {
		return characters;
	}

	public String getTextureName() {
		return textureName;
	}
	
}
