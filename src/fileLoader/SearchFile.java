package fileLoader;

import java.io.File;

public class SearchFile {
	
	public static boolean search(String name,String type) {
		File x = null ;
		try {
			x = new File("src/res/" + name +type);
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		System.out.println(x.getAbsolutePath());
		if(x.exists() && !x.isDirectory()) {
			return true ;
		}else {
			return false ;
		}
	}
}
