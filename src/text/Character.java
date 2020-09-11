package text;

public class Character {

	private int id ;
	private double width ;
	private double height ;
	private double textureX ;
	private double textureY ;
	private double textureWidth ;
	private double textureHeight ;
	private double offsetx ;
	private double offsety ;
	private double xadvance ;
	
	public Character(int id, double width, double height, double textureX, double textureY,
			double textureWidth, double textureHeight,double offsetx,double offsety, double xadvance) {
		
		this.id = id;
		this.width = width;
		this.height = height;
		this.textureX = textureX;
		this.textureY = textureY;
		this.textureWidth = textureWidth;
		this.textureHeight = textureHeight;
		this.offsetx = offsetx ;
		this.offsety = offsety ;
		this.xadvance = xadvance;
	}

	public int getId() {
		return id;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getTextureX() {
		return textureX;
	}

	public double getTextureY() {
		return textureY;
	}

	public double getTextureWidth() {
		return textureWidth;
	}

	public double getTextureHeight() {
		return textureHeight;
	}

	public double getOffsetx() {
		return offsetx;
	}

	public double getOffsety() {
		return offsety;
	}

	public double getXadvance() {
		return xadvance;
	}
	
	@Override
	public String toString() {
		return "id = "+ id +"\nwidth = " + width + " height =  " + height + "\ntextureX =  " +textureX +
				" textureY = " + textureY +"\ntextureWidth =  " +textureWidth+ " textureHeight =  " + textureHeight + "\noffsetx =  " +offsetx 
				+" offsety =  " +offsety +"\nxadvance =  " +xadvance ;
	}
	
	
	
}
