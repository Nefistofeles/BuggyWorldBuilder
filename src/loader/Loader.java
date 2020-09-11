package loader;

public class Loader {

	private MeshLoader meshLoader ;
	private TextureLoader textureLoader ;
	
	public Loader() {
		meshLoader = new MeshLoader();
		textureLoader = new TextureLoader();
	}

	public void destroy() {
		meshLoader.destroy();
		textureLoader.destroy();
	}
	public MeshLoader getMeshLoader() {
		return meshLoader;
	}

	public TextureLoader getTextureLoader() {
		return textureLoader;
	}
	
	
}
