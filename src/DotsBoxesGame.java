/**
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("unused")
/**
 * @author Adam Cordingley
 *
 */
public class DotsBoxesGame extends JFrame implements ActionListener {
	
	private int nPlayers;
	private int frameWidth;
	private int frameHeight;
	private int cols;
	private int rows;
	private int spacing;		// spacing between components
	private int edgePadding;	// spacing around edge
	private Player[] players;
	private int currentPlayer;
	private boolean done;
	private static String titleBase = "Dots and Boxes";
	private static int DEFAULT_NUM_PLAYERS 	= 2;
	private static int DEFAULT_NUM_BOXES 	= 10;
	private static int DEFAULT_COLS 		= 10;
	private static int DEFAULT_ROWS 		= 10;
	private static int DEFAULT_SPACING 		= 1;
	private static int DEFAULT_EDGE_PADDING = 10;
	private JButton[][] board;
	
	
	public DotsBoxesGame() {
		this(
				DEFAULT_NUM_PLAYERS, 
				DEFAULT_COLS, 
				DEFAULT_ROWS, 
				DEFAULT_SPACING, 
				DEFAULT_EDGE_PADDING	
				);
	}
	
	public DotsBoxesGame(int nPlayers, int boxes) {
		this(nPlayers, boxes, boxes);
	}
	
	public DotsBoxesGame(int nPlayers, int cols, int rows) {
		this(
				nPlayers, 
				cols, 
				rows, 
				DEFAULT_SPACING, 
				DEFAULT_EDGE_PADDING	
				);
	}
	
	public DotsBoxesGame(int nPlayers, int cols, int rows, int spacing, int edgePadding) {
		super(titleBase);
		this.nPlayers 		= nPlayers;			//
		this.cols 			= cols;				//
		this.rows 			= rows;				//
		this.spacing 		= spacing;			//
		this.edgePadding 	= edgePadding; 		//
		this.board 			= new JButton[2*cols + 1][2*rows + 1];

		setupPane(this.getContentPane());		// do the work of adding stuff
		
		//CONFIGURE PLAYERS
		players = new Player[nPlayers];
		for(int i=0; i<nPlayers; i++) {
			players[i] = new Player(i+1);
		}
		players[0].incrementMoves();			// give the first player a move
		
		updateAllPlayers();
		updateTitle();
		
		setVisible(true); 						// display the window
	}
	
	
	/**
	 * Takes user input and returns a new game with the desired configuration
	 * @return DotsBoxesGame new game with the desired configuration.
	 */
	private static DotsBoxesGame setupGame() {
		int numPlayers 	= DEFAULT_NUM_PLAYERS;
		int nBoxes 		= DEFAULT_NUM_BOXES;
		String title 		= "Configure Game Options";
		Object[] options 	= {2,3,4};
		Object selected = JOptionPane.showInputDialog(null,
	             "Select number of players", 
	             title,
	             JOptionPane.QUESTION_MESSAGE, 
	             null,
	             options, 
	             options[0]);
		
		Object response = JOptionPane.showInputDialog(
				null,
				"Board Size",
				title,
				JOptionPane.QUESTION_MESSAGE);
		
		if(selected instanceof Integer) {
			numPlayers = ((Integer)selected).intValue();
		}// else{ do nothing }
		
		if(response instanceof String) {
			try {
				nBoxes = Integer.parseInt(((String)response));
			}catch(Exception e) {
				nBoxes = 10;
			}
		}
		
		return new DotsBoxesGame(numPlayers, nBoxes);
	}

	private void setupPane(Container pane) {
		
		//SIZE THE WINDOW
		int lineW 			= Line.DEFAULT_WIDTH;
		int boxW 			= Box.DEFAULT_WIDTH;
		this.frameWidth 	= cols*(2*spacing+lineW+boxW+2)+lineW+2*edgePadding;	//
		this.frameHeight 	= rows*(2*spacing+lineW+boxW+4)+lineW+2*edgePadding;	//
		this.setSize(frameWidth, frameHeight);	// 
		
		//SET THE LOCATION OF GAME BOARD
		double[] dims = getScreenDims();
		int x = ((int)dims[0]-frameWidth)/2;		// puts the board at the center of the screen
		int y = ((int)dims[1]-frameHeight)/2;	//
		this.setTitle(titleBase);
		this.setLocation(new Point(x,y));
		
		int width 	= (int) pane.getSize().getWidth();	// width of the pane
		int height 	= (int) pane.getSize().getHeight();	//height of the pane
		
		// CREATE THE OBJECTS IN AN ARRAY
		populateArray(board);
		
		pane.setLayout(null);
		
		// POPULATE THE PANE
		for(int col = 0; col<board.length; col++) {
			
			for(int row = 0; row<board[col].length; row++) {
				pane.add(board[col][row]);
			}
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	private void populateArray(JButton[][] arr) {
		int xLen = arr.length;
		int yLen = arr[0].length; 
		int xPix = edgePadding;
		int yPix = edgePadding;
		
		int dotInc 	= Dot.DEFAULT_WIDTH;
		int lineInc = Line.DEFAULT_WIDTH;
		int boxInc 	= Box.DEFAULT_WIDTH;
		
		// place lines and boxes
		for(int x=0; x<xLen; x++) {
			yPix = edgePadding;
			
			for(int y=0; y<yLen; y++) {
				if(x%2 == 0) {
					if(y%2 != 0) { 	// VERTICAL line
						(arr[x][y] = new Line(xPix,yPix,'V')).addActionListener(this);
						yPix+=boxInc;
					}else{ 			// DOT
						arr[x][y] = new Dot(xPix,yPix);
						yPix+=dotInc;
					}
				}else if(y%2 == 0){
					if(x%2 != 0) {	//HORIZONTAL Line
						(arr[x][y] = new Line(xPix,yPix,'H')).addActionListener(this);
						yPix+=dotInc;
					}else {			// DOT
						arr[x][y] = new Dot(xPix,yPix);
						yPix+=dotInc;
					}
				}else {				// BOX
					arr[x][y] = new Box(xPix,yPix);
					yPix+=boxInc;
				}
				yPix+=spacing;
			}
			
			if(x%2==0) {
				xPix+=dotInc;
			}else {
				xPix+=boxInc;
			}
			xPix+=spacing;
		}
		
		// ADD POINTERS BETWEEN LINES <-> BOXES
		Box b;				// ptr to the box at hand
		Line N, E, S, W;	// ptrs to Lines for all 4 sides of the box
		for(int x=1; x<arr.length; x+=2) {
			for(int y=1; y<arr[x].length; y+=2) {
				if(arr[x][y] instanceof Box) {
					b = (Box)arr[x][y];
					N = (Line)arr[x][y-1];
					W = (Line)arr[x-1][y];
					S = (Line)arr[x][y+1];
					E = (Line)arr[x+1][y];
					
					b.setAdjLineN(N);		//North line
					b.setAdjLineE(E);
					b.setAdjLineS(S);
					b.setAdjLineW(W);
					
					N.setAdjBoxS(b);		//
					E.setAdjBoxW(b);
					S.setAdjBoxN(b);
					W.setAdjBoxE(b);
				}
			}
		}
	}
	
	/**
	 * This method is useful for centering dialog boxs/frames on the screen.
	 * @return the dimensions of the screen. This is used for centering the window.
	 */
	private static double[] getScreenDims(){
		double[] array =  {Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
			Toolkit.getDefaultToolkit().getScreenSize().getHeight()};
		return array;
	}
	
	@Override
	/**
	 * this handles the actual game-play
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		Line line;
		Player player = players[this.currentPlayer];
		if(e.getSource() instanceof Line) {
			line = (Line)e.getSource();
			if(!line.isSet() && player.hasMove()) {
				line.set();
				player.decrementMoves();
			}
			// check if the adjacent boxes are complete now
			if(line.getOrientation() == 'V') {
				Box A = line.getAdjBoxE();
				Box B = line.getAdjBoxW();
				if(A != null) {
					if(A.isComplete()) 
						A.setOwner(player);
				}
				if(B != null) {
					if(B.isComplete())
						B.setOwner(player);
				}
			}else {
				Box C = line.getAdjBoxN();
				Box D = line.getAdjBoxS();
				if(C != null) {
					if(C.isComplete()) 
						C.setOwner(player);
				}
				if(D != null) {
					if(D.isComplete())
						D.setOwner(player);
				}
			}
			
			player.updateInfoPane();
			
			// check if player has more moves, 
			// if not, goto next player
			if(!player.hasMove()) {
				nextPlayer();
			}
			
		}//else{do nothing}
		
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean b) {
		this.done = b;
	}
	

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	
	/**
	 * @return the nPlayers
	 */
	public int getNumPlayers() {
		return nPlayers;
	}

	/**
	 * @param nPlayers the nPlayers to set
	 */
	public void setNumPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public void nextPlayer() {
		currentPlayer = (++currentPlayer)%getNumPlayers();
		players[currentPlayer].incrementMoves(); 	// give them a move
		updateAllPlayers();
		updateTitle();
	}
	
	private void updateTitle() {
		Player player = players[currentPlayer];
		this.setTitle(titleBase + " - "+player.getName()+"'s turn");
	}
	
	private void updateAllPlayers() {
		for(int i=0; i<players.length; i++) {
			players[i].updateInfoPane();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//SETUP STATE
		DotsBoxesGame Game = setupGame();
		
		// GAME PLAY STATE
		while(!Game.isDone()) {}
		
	}

}
