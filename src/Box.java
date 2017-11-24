/**
 * 
 */
import javax.swing.*;
import java.awt.*;


/**
 * @author Adam Cordingley
 *
 */
public class Box extends JButton{

	private int width;
	private int height;
	private int x;
	private int y;
	private boolean owned;
	private Player owner;
	private Symbol symbol;
	
	private Line adjLineN;
	private Line adjLineS;
	private Line adjLineE;
	private Line adjLineW;
	
	/* default width and height of a Box */
	public static final int DEFAULT_WIDTH 	= 25;
	public static final int DEFAULT_HEIGHT 	= 25;
	
	/**
	 * 
	 */
	public Box(int x, int y) {
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * 
	 */
	public Box(int x, int y, int width, int height) {
		this.x 		= x;
		this.y 		= y;
		this.width 	= width;
		this.height = height;
		this.setBounds(x, y, width, height);
		this.setBorderPainted(false);
		this.setBackground(Color.WHITE);
	}
	
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * 
	 * @param owner the Player that occupies/owns this box
	 * increments owner's move
	 */
	public void setOwner(Player owner) {
		if(!isOwned()) {
			this.owner = owner;
			this.setSymbol(owner.getSymbol());
			owner.incrementMoves(); 		// increment move counter
			owner.incrementNumBoxes();
			this.owned = true;
		}
	}
	
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
		this.setIcon(symbol);
	}
	
	public boolean isOwned() {
		return owned;
	}

	/**
	 * @return the adjLineN
	 */
	public Line getAdjLineN() {
		return adjLineN;
	}

	/**
	 * @param adjLineN the adjLineN to set
	 */
	public void setAdjLineN(Line adjLineN) {
		this.adjLineN = adjLineN;
	}

	/**
	 * @return the adjLineS
	 */
	public Line getAdjLineS() {
		return adjLineS;
	}

	/**
	 * @param adjLineS the adjLineS to set
	 */
	public void setAdjLineS(Line adjLineS) {
		this.adjLineS = adjLineS;
	}

	/**
	 * @return the adjLineE
	 */
	public Line getAdjLineE() {
		return adjLineE;
	}

	/**
	 * @param adjLineE the adjLineE to set
	 */
	public void setAdjLineE(Line adjLineE) {
		this.adjLineE = adjLineE;
	}

	/**
	 * @return the adjLineW
	 */
	public Line getAdjLineW() {
		return adjLineW;
	}

	/**
	 * @param adjLineW the adjLineW to set
	 */
	public void setAdjLineW(Line adjLineW) {
		this.adjLineW = adjLineW;
	}
	
	public boolean isComplete() {
		boolean north = getAdjLineN().isSet();
		boolean east  = getAdjLineE().isSet();
		boolean south = getAdjLineS().isSet();
		boolean west  = getAdjLineW().isSet();
		
		return (north && east && south && west);
	}

}
