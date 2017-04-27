/**
 *
 * @author Kendall McCormick (880456), Benjamin Taubenblatt (890808)
 * COMP30024: Project 1
 * Tutor: Matt De Bono 
 */

 //piece is the parent class of all game pieces, contains a position object for its location 
 //board is made up of pieces 
public abstract class Piece {
	private Position pos;
	
	
	public Piece(Position pos){
		this.pos = pos;
	}
	
	
	public Position getPosition(){
		return pos; 
	}
	
	
}
