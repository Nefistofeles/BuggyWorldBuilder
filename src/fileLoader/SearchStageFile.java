package fileLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SearchStageFile {

	public static List<String> searchStage(String name){
		List<String > list = new ArrayList<String>();
		BufferedReader reader = null ;
		
		try {
			reader = new BufferedReader(new FileReader("res/" + name + ".txt"));
			String line ;
			while((line = reader.readLine()) != null) {
				list.add(line) ;
			}
			reader.close();
		}catch(Exception e) {
			System.out.println("stage is not readed");
		}
		return  list ;
	}
}
