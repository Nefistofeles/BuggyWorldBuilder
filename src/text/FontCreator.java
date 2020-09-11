package text;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import dataStructure.Mesh;
import dataStructure.ObjectData;
import dataStructure.Texture;
import loader.Loader;
import utils.Maths;

public class FontCreator {

	private Texture texture;
	private FontTypeLoader fontLoader;
	private Loader loader;

	public FontCreator(String fontName, int padding, Loader loader) {
		this.fontLoader = new FontTypeLoader(padding);
		try {
			fontLoader.loadFont(fontName);
		} catch (Exception e) {
			System.out.println(fontName + " is not loaded");
			e.printStackTrace();
			System.exit(-1);
		}
		texture = loader.getTextureLoader().loadTextureForFont("/res/" + fontName + ".png");
		this.loader = loader;

	}

	public void loadText(Text text) {
		createSkeletalStructure(text);
		ObjectData data = loadSkeletalStructure(text);

		int vao = loader.getMeshLoader().createVAO();
		int vbo1 = loader.getMeshLoader().createVBO(0, 2, data.getVertices(), GL15.GL_DYNAMIC_DRAW);
		int vbo2 = loader.getMeshLoader().createVBO(1, 2, data.getTextureCoors(), GL15.GL_DYNAMIC_DRAW);
		int[] vbos = new int[2];
		vbos[0] = vbo1;
		vbos[1] = vbo2;
		loader.getMeshLoader().closeVAO();
		text.loadMesh(new Mesh(vao, data.getVertices().length / 2, vbos));
	}

	private ObjectData loadSkeletalStructure(Text text) {
		List<Float> vertices = new ArrayList<Float>();
		List<Float> textureCoords = new ArrayList<Float>();

		List<Line> lines = text.getLines();

		Character correction = lines.get(0).getWords().get(0).getCharacters().get(0);

		double curserX = 0.5f - correction.getOffsetx();
		double curserY = 0.5f - correction.getOffsety();
		System.out.println(lines.size());
		System.out.println(lines.get(0).getWords().size());
		for (Line line : lines) {
			if (text.isCentered()) {
				curserX += (line.getMaxLineLength() - line.getCurrentLineLength()) / 2;
			}
			for (Word word : line.getWords()) {
				for (Character c : word.getCharacters()) {
					
					meshAndTextureLoad(curserX, curserY, c, vertices, textureCoords);
					curserX += c.getXadvance();

				}
			}
			curserX = 0.5f - correction.getOffsetx();
			curserY += Maths.LINE_HEIGHT;
		}
		float[] verticesArray = Maths.listToArray(vertices);
		float[] textureCoordsArray = Maths.listToArray(textureCoords);

		return new ObjectData(verticesArray, textureCoordsArray);

	}

	private void updateMeshData(Text text, float[] vertices, float[] textureCoords) {
		// GL30.glBindVertexArray(text.getMesh().getVaoID());

		loader.getMeshLoader().updateVBO(text.getMesh().getVbo()[0], vertices, GL15.GL_DYNAMIC_DRAW);
		loader.getMeshLoader().updateVBO(text.getMesh().getVbo()[1], textureCoords, GL15.GL_DYNAMIC_DRAW);
		text.getMesh().setVertexCount(vertices.length / 2);
		// GL30.glBindVertexArray(0);

	}

	public void updateText(Text text) {
		createSkeletalStructure(text);
		ObjectData object = loadSkeletalStructure(text);
		updateMeshData(text, object.getVertices(), object.getTextureCoors());
	}

	private void meshAndTextureLoad(double curserX, double curserY, Character character, List<Float> vertices,
			List<Float> textureCoords) {

		addVerticesCharacterConvert(curserX, curserY, character, vertices);
		Maths.addMeshAttachment(textureCoords, character.getTextureX(), character.getTextureY(),
				character.getTextureWidth() + character.getTextureX(),
				character.getTextureHeight() + character.getTextureY());

	}

	private void addVerticesCharacterConvert(double curserX, double curserY, Character character,
			List<Float> vertices) {

		double x = curserX + (character.getOffsetx());
		double y = curserY + (character.getOffsety());
		double width = x + (character.getWidth());
		double height = y + (character.getHeight());

		double xCorrection = (2 * x) - 1;
		double yCorrection = (-2 * y) + 1;
		double widthCorrection = (2 * width) - 1;
		double heightCorrection = (-2 * height) + 1;

		Maths.addMeshAttachment(vertices, xCorrection, yCorrection, widthCorrection, heightCorrection);

	}

	private void createSkeletalStructure(Text text) {
		char[] data = text.getText().toCharArray();

		List<Line> lines = new ArrayList<Line>();
		Line currentLine = new Line(text.getMaxLineLength());
		Word currentWord = new Word();

		for (char c : data) {

			Character character = fontLoader.getCharacters().get((int) c);
			currentWord.addCharacterToWord(character);
			
			if ((int) c == 32) {

				boolean isadded = currentLine.addWordToLine(currentWord);
				if (!isadded) {
					lines.add(currentLine);
					currentLine = new Line(text.getMaxLineLength());
					currentLine.addWordToLine(currentWord);
				}
				currentWord = new Word();
			}
		}

		boolean isadded = currentLine.addWordToLine(currentWord);
		if (!isadded) {
			lines.add(currentLine);
			currentLine = new Line(text.getMaxLineLength());
			currentLine.addWordToLine(currentWord);
		}
		lines.add(currentLine);

		text.setLines(lines);

	}

	public Texture getTexture() {
		return texture;
	}

}
