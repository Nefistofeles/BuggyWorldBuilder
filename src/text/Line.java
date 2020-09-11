package text;

import java.util.ArrayList;
import java.util.List;

public class Line {

	private List<Word> words ;
	private double maxLineLength ;
	private double currentLineLength ;
	
	public Line(double maxLineLength) {
		this.maxLineLength = maxLineLength ;
		currentLineLength = 0 ;
	}
	
	public boolean addWordToLine(Word word) {
		if(words == null) {
			words = new ArrayList<Word>();	
		}
		double length = currentLineLength + word.getWidth() ;
		if(length <= maxLineLength) {
			words.add(word) ;
			currentLineLength += word.getWidth() ;
			return true ;
		}
		return false ;
	}

	public List<Word> getWords() {
		return words;
	}

	public double getMaxLineLength() {
		return maxLineLength;
	}

	public double getCurrentLineLength() {
		return currentLineLength;
	}
	
	
	
}
