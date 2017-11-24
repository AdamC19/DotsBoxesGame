/**
 * 
 */
import javax.swing.*;
import java.awt.*;


/**
 * @author Adam Cordingley
 *
 */
public class Line extends JButton{
	
	private int width;
	private int length;
	private int xDim;
	private int yDim;
	private int x;
	private int y;
	private char orientation;
	private Color background;
	private boolean set;
	
	private Box adjBoxN;
	private Box adjBoxE;
	private Box adjBoxS;
	private Box adjBoxW;
	
	/* default width and height of a Line */
	public static final int DEFAULT_WIDTH 	= Dot.DEFAULT_WIDTH;
	public static final int DEFAULT_LENGTH = 25;
	/**
	 * 
	 */
	public Line(int x, int y, char orientation) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_LENGTH, orientation);
	}
	
	/**
	 * 
	 */
	public Line(int x, int y, int width, int length, char orientation) {
		super();
		this.x 		= x;
		this.y 		= y;
		this.orientation = orientation;
		this.width 	= width;
		this.length = length;
		this.setBorderPainted(false);
		
		// set the x and y dimensions based on the orientation
		if( Math.abs(orientation-'V') <= Math.abs(orientation-'H') ) {
			// Vertical case
			this.xDim = width;
			this.yDim = length;
		}else{
			// Horizontal case
			this.yDim = width;
			this.xDim = length;
		}
		
		this.setBounds(x,y,xDim,yDim);						// set location & size 
		this.setBackground(this.background = Color.WHITE);	// starts out white
		this.set = false;
	}
	
	
	public boolean isSet() {
		return set;
	}
	
	public void set() {
		if(!isSet()) {
			set = true;
			setColor(Color.BLACK);
		}
	}
	
	/**
	 * @param background the background to set
	 */
	private void setColor(Color background) {
		this.setBackground(this.background = background);
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
	public Color getBackground() {
		return background;
	}

	/**
	 * @return the adjBoxN
	 */
	public Box getAdjBoxN() {
		return adjBoxN;
	}

	/**
	 * @param adjBoxN the adjBoxN to set
	 */
	public void setAdjBoxN(Box adjBoxN) {
		this.adjBoxN = adjBoxN;
	}

	/**
	 * @return the adjBoxE
	 */
	public Box getAdjBoxE() {
		return adjBoxE;
	}

	/**
	 * @param adjBoxE the adjBoxE to set
	 */
	public void setAdjBoxE(Box adjBoxE) {
		this.adjBoxE = adjBoxE;
	}

	/**
	 * @return the adjBoxS
	 */
	public Box getAdjBoxS() {
		return adjBoxS;
	}

	/**
	 * @param adjBoxS the adjBoxS to set
	 */
	public void setAdjBoxS(Box adjBoxS) {
		this.adjBoxS = adjBoxS;
	}

	/**
	 * @return the adjBoxW
	 */
	public Box getAdjBoxW() {
		return adjBoxW;
	}

	/**
	 * @param adjBoxW the adjBoxW to set
	 */
	public void setAdjBoxW(Box adjBoxW) {
		this.adjBoxW = adjBoxW;
	}

	/**
	 * @return the orientation
	 */
	public char getOrientation() {
		return orientation;
	}

	
	

}
