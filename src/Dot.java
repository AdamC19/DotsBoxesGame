import javax.swing.*;
import java.awt.*;
//import java.awt.geom.*;

/**
 * 
 * @author Adam Cordingley
 *
 */
public class Dot extends JButton{
	
	private int width;
	private int height;
	private int x;
	private int y;
	private Color background;
	
	/* default width and height of a dot */
	public static final int DEFAULT_WIDTH 	= 6;
	public static final int DEFAULT_HEIGHT 	= 6;
	
	/**
	 * @param x the x-coordinate of the top left corner
	 * @param y the y-coordinate of the top left corner
	 */
	public Dot(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * @param x the x-coordinate of the top left corner
	 * @param y the y-coordinate of the top left corner
	 * @param width the width of the Dot
	 * @param height the height of the Dot
	 */
	public Dot(int x, int y, int width, int height) {
		super();
		this.x 		= x;
		this.y 		= y;
		this.width 	= width;
		this.height = height;
		this.setBackground(this.background = Color.BLACK);
		this.setBounds(x,y,width,height);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the background
	 */
	public Color getColor() {
		return background;
	}
	/**
	 * @param background the background to set
	 */
	public void setColor(Color background) {
		this.setBackground(this.background = background);
	}
	
	
	

}
