import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * 
 */

/**
 * @author Adam Cordingley
 *
 */
public class Symbol implements Icon {
	
	private final static int width 	= Box.DEFAULT_WIDTH;
	private final static int height = Box.DEFAULT_HEIGHT;
	
	private Color color;
	
	public static final Color[] availableColors = {
			Color.BLUE,	Color.GREEN,		Color.MAGENTA,
			Color.RED,	Color.YELLOW,		Color.PINK,
			Color.ORANGE,Color.LIGHT_GRAY,	Color.BLACK};
	
	/**
	 * 
	 */
	public Symbol() {
		this(availableColors[0]);
	}
	
	public Symbol(Color color) {
		this.color = color;
	}

	/** (non-Javadoc)
	 * @see javax.swing.Icon#getIconHeight()
	 */
	public int getIconHeight() {
		return height;
	}

	/** (non-Javadoc)
	 * @see javax.swing.Icon#getIconWidth()
	 */
	public int getIconWidth() {
		return width;
	}
	
	public Color getBackground() {
		return color;
	}

	/** (non-Javadoc)
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
	 */
	public void paintIcon(Component c, Graphics g, int xDim, int yDim) {
		Graphics2D g2D = (Graphics2D)g.create();
		g2D.setColor(getBackground());
		g2D.fillRect(0, 0, getIconWidth(), getIconHeight());
		

	}

}
