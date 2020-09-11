package xmlLoader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLLoader {

	private static final Pattern DATA = Pattern.compile(">(.+?)<");
	private static final Pattern START_TAG = Pattern.compile("<(.+?)>");
	private static final Pattern ATTR_NAME = Pattern.compile("(.+?)=");
	private static final Pattern ATTR_VAL = Pattern.compile("\"(.+?)\"");
	private static final Pattern CLOSED = Pattern.compile("(</|/>)");
	
	private BufferedReader reader ;
	private int width ;
	private int height ;
	
	
	public XMLNode loadFile(String name) {
		
		try {
			InputStream in = Class.class.getResourceAsStream(name) ;
			reader = new BufferedReader(new InputStreamReader(in)) ;
			reader.readLine() ;
			XMLNode node = load();
			reader.close();
			return node ;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(name + " xml file is not loaded");
			System.exit(-1);
		}
		return null ;
	}
	
	private XMLNode load() throws Exception{
		String line = reader.readLine().trim()  ;
		if(line.startsWith("</")) {
			return null ;
		}
		String[] parser = start(line).split(" ") ;
		XMLNode node = new XMLNode(parser[0]) ;
		splitAttribute(node, parser);
		
		if(node.getName().equals("layer")) {
 			width = Integer.parseInt(node.getAttribute("width")) ;
			height = Integer.parseInt(node.getAttribute("height")) ;
			
		}

		if(node.getName().equals("data")) {
			String data = "";
			for(int i = 0 ; i < height ; i++) {
				data += reader.readLine() ;
			
			}
			node.setData(data);
		}
		

		if(CLOSED.matcher(line).find()) {
			return node ;
		}
		XMLNode child = null ;
		while((child = load()) != null) {
			node.addChild(child);
		}
		return node ;
		
	}
	private void splitAttribute(XMLNode node, String[] parser) {
		for(int i = 1 ; i < parser.length ; i++) {
			Matcher matcher1 = ATTR_NAME.matcher(parser[i]) ;
			Matcher matcher2 = ATTR_VAL.matcher(parser[i]) ;
			matcher1.find() ;
			matcher2.find(); 
			node.addAttribute(matcher1.group(1), matcher2.group(1));
		}
	}
	private String start(String line) {
		Matcher matcher = START_TAG.matcher(line) ;
		matcher.find();
		return matcher.group(1) ;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
