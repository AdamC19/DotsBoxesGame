import javax.swing.*;
import java.awt.*;

/**
 * 
 */

/**
 * @author Adam Cordingley
 *
 */
public class Player {

	private String name;
	private Symbol symbol;
	private int moves;
	private int movesMade;
	private int boxesOwned;
	private int playerID;
	private InfoPane infoPane;
	
	
	/**
	 * 
	 * @author Adam Cordingley
	 *
	 */
	private class InfoPane extends JFrame{
		private Player player;
		public static final int WIDTH 	= 250;
		public static final int HEIGHT 	= 100;
		private int xCoord = 100;
		private int yCoord = 100;
		private JLabel scoreLabel;
		private JLabel movesLabel;
		private JLabel movesLeft;
		private Box playerBox;
		
		public InfoPane(Player player) {
			super(player.getName());
			this.player = player;
			this.setSize(WIDTH,HEIGHT);
			addComponentsToPane(this.getContentPane());
			this.update();
			this.setVisible(true);
			this.yCoord = this.yCoord + (player.getPlayerID()-1)*(HEIGHT+50);
			this.setLocation(new Point(xCoord,yCoord));
		}
		public void addComponentsToPane(Container pane) {

			pane.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			playerBox = new Box(0,0);
			playerBox.setSymbol(player.getSymbol());
			c.weightx = 0.5;
			c.weighty = 0.5;
			c.gridx = 0;
		    c.gridy = 0;
			c.ipady = 10;
			c.ipadx = 10;
			pane.add(playerBox, c);
			
			
			scoreLabel = new JLabel();
			c.gridx = 0;
		    c.gridy = 1;
		    c.ipady = 10;
		    c.ipadx = 10;
			pane.add(this.scoreLabel,c);
			
			movesLabel = new JLabel();
			c.gridx = 0;
			c.gridy = 2;
			c.ipady = 10;
			c.ipadx = 10;
//			c.fill 	= GridBagConstraints.HORIZONTAL;
			pane.add(this.movesLabel,c);
			
			movesLeft = new JLabel();
			c.gridx = 0;
			c.gridy = 3;
			c.ipady = 10;
			c.ipadx = 10;
			pane.add(this.movesLeft,c);
		}
		
		/**
		 * 
		 */
		public void update() {
			playerBox.setIcon(player.getSymbol());
			scoreLabel.setText("Boxes Occupied: " + player.getNumBoxes());
			movesLabel.setText("Moves Made: "+ player.getMovesMade());
			movesLeft.setText("Moves Left: " + player.getMoves());
		}
		
	}
	public Player(int ID) {
		super();
		this.playerID = ID;
		setupPlayer();
		this.infoPane = new InfoPane(this);
	}
	
	public Player(int ID, String name) {
		this(ID, name, null);
	}
	/**
	 * 
	 */
	public Player(int ID, String name, Symbol symbol) {
		super();
		this.playerID 	= ID;
		this.symbol 	= symbol;
		this.name 		= name;
		this.infoPane 	= new InfoPane(this);
	}
	
	
	private void setupPlayer() {
		String name = " ";
		Symbol sym 	= null;
		
		String title = "Configure Player";
		
		Object response = JOptionPane.showInputDialog(
				null,
				"Name:",
				title,
				JOptionPane.QUESTION_MESSAGE);
		
		if(response instanceof String) {
			try {
				name = (String)response;
			}catch(Exception e) {}
		}else {
			name = "Anon";
		}
		
		Object[] symbols = new Object[Symbol.availableColors.length]; 
		for(int i = 0; i<symbols.length; i++) {
			symbols[i] = new Symbol(Symbol.availableColors[i]);
		}
		Object selection = JOptionPane.showInputDialog(
				null,
				name+ ", select your symbol",
				title,
				JOptionPane.QUESTION_MESSAGE,
				null,
				symbols,
				symbols[0]
				);
		

		if(selection instanceof Symbol) {
			sym = (Symbol)selection;
		}else {
			sym = new Symbol(Symbol.availableColors[5]);
		}
		
		this.setName(name);
		this.setSymbol(sym);
	}
	
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public boolean hasMove() {
		return moves>0;
	}
	public int getMovesMade(){
		return movesMade;
	}
	public void incrementMoves() {
		moves++;
	}
	public void decrementMoves() {
		moves--;
	}
	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}

	public int getNumBoxes() {
		return boxesOwned;
	}
	public void incrementNumBoxes() {
		boxesOwned++;
	}
	public void incrementScore() {
		boxesOwned++;
	}
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * @param playerID the playerID to set
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * @return the infoPane
	 */
	public void updateInfoPane() {
		infoPane.update();
	}
}
