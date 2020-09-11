package worldCreator;

import dataStructure.Texture;
import gui.button.Button;
import main.Creator;
import text.FontCreator;

public class TextureChange {
	
	private int id ;
	private Creator creator ;

	
	public TextureChange(Creator creator) {
		this.creator = creator ;
		id = 1 ;
	}
	
	public void changeID() {
		id++; 
		if(id >=creator.getData().getTextureID().size() + 1) {
			id = 1 ;
		}
		
		
	}

	public String getTextureName() {
		return creator.getData().getTextureID().get(id) ;
		
	}
	public int getID() {
		return id ;
	}
	
	public void setButtonText(Button button, FontCreator font) {
		button.getText().setText(getTextureName(), font);

	
	}
	
	public String getTextureName(int id) {
		return creator.getData().getTextureID().get(id) ;
	}
	
}
