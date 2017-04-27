
/**
 *
 * @author Kendall McCormick (880456), Benjamin Taubenblatt (890808)
 * COMP30024: Project 1
 * Tutor: Matt De Bono 
 */

 //This class sets up a board of a certain dimension and keeps track of all pieces. 
public class Board {
	private int dimension;
	private Piece[][] myWorld; 
	
	public Board(int dimension, Piece[][] myWorld){
		this.dimension = dimension; 
		this.myWorld = myWorld; 	
	}
	
	public void setDimension(int vl){
		this.dimension = vl;
	}
	
	public int getDimension(){
		return dimension; 
	}
	
	public Piece[][] getWorld(){
		//we clone this for well-formed code 
		Piece[][] clonie = this.myWorld.clone();
		return clonie; 
	}
	
	public boolean checkHorizontalSpot(Position ps){
		//this method checks all potential horizontal spots generated by the piece class 
		//to make sure that they are valid moves (ie still on the board and not blocked)
		//returns true if spot at a specific position is both in the board and empty

		int tempX = ps.getX();
		int tempY = ps.getY();
		
		//the following if statements check that the spot to move is a valid board spot 
		if(tempY >= dimension){ 
			return false; 
		}else if(tempX==dimension){ //if the spot is 1 spot off the board, still valid 
			return true; 
		}
		else if(tempX < 0 || tempY < 0){
			return false;
		}else if(myWorld[tempX][tempY] instanceof BlankSpace){//if the spot is a blank space character
			return true;
		}else{
			return false; 
		}
	}
	
	public boolean checkVerticalSpot(Position ps){
		//this is the same as checkHorizontal, except for pieces of type Vertical 

		int tempX = ps.getX();
		int tempY = ps.getY();
		
		if(tempX >= dimension || tempY >= dimension || tempX < 0){
			return false; 
		}else if(tempY < 0){
			return true; 
		}else if(myWorld[tempX][tempY] instanceof BlankSpace){
			return true;
		}else{
			return false; 
		}
	}
	
	public int totalHorizontalMoves(){
	// This method iterates through the Board and counts the total number of horizontal moves for all Horizontal Players
	// It utilizes checkHorizontalSpot() to determine if a Player can move. 
	// returns the total number (int) of possible Horizontal moves for all Horizontal Players. 		

		int counter = 0;
		
		for(int i=0; i<dimension; i++){
			for(int j=0; j<dimension; j++){
				Piece temp = myWorld[i][j];
				
				if(temp instanceof HorizontalPlayer){
					Position[] pArr = ((HorizontalPlayer) temp).hypotheticalMovesH(); 
					for(int k=0; k<3; k++){ //each piece of type horizontal has 3 hypothetical moves (forward, up, down),
											//we have to loop through these and validate each
						boolean result = checkHorizontalSpot(pArr[k]);
						if(result == true){
							counter++;
						}
					}
				}
				
			}
		}
		return counter; 
	}
	
	public int totalVerticalMoves(){
		//same method but for vertical pieces 

		int counter = 0;
		
		for(int i=0; i<dimension; i++){
			for(int j=0; j<dimension; j++){
				Piece temp = myWorld[i][j];
				
				if(temp instanceof VerticalPlayer){
					Position[] pArr = ((VerticalPlayer) temp).hypotheticalMovesV(); 
					for(int k=0; k<3; k++){
						boolean result = checkVerticalSpot(pArr[k]);
						if(result == true){
							counter++;
						}
					}
				}
				
			}
		}
		return counter; 
	}
	
}
