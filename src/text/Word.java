package text;

import java.util.ArrayList;
import java.util.List;

public class Word {

	private List<Character> characters ;
	private double width ;
	
	public Word() {
		width = 0 ;
		
	}
	
	public void addCharacterToWord(Character character) {
		if(characters == null) {
			characters = new ArrayList<Character>();
		}
		characters.add(character);
		width += character.getXadvance() ;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public double getWidth() {
		return width;
	}
	
	
}
