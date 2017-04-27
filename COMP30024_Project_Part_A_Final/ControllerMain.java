import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Kendall McCormick (880456), Benjamin Taubenblatt (890808)
 * COMP30024: Project 1
 * Tutor: Matt De Bono 
 */
public class ControllerMain {

	/* This method extracts white spaces and new line characters from a String and returns a new String */
	public static String extractBadChar(String s){
		String newS = "";
		
		for(int j=0; j<s.length(); j++){
			if((int)s.charAt(j) == 32){ //if the character is a white space
				continue;
			}else if((int)s.charAt(j) == 10){ //if the character is a new line character
				continue;
			}else{
				newS = newS + s.charAt(j);
			}
		}
		
		return newS;
	}

	/* Main Method  */
	public static void main(String[] args) {		
		String FILE = args[0]; //reading and parsing input 
		BufferedReader br = null;
		FileReader fr = null; 
		int counter = 0;
		int dim = 0;
		int x = 0;
		String current;
		String temp;
		Piece[][] tempBoard = new Piece[dim][dim]; // temp board to create objects from 
		
		try{
			fr = new FileReader(FILE);
			br = new BufferedReader(fr);			
			
			br = new BufferedReader(new FileReader(FILE));
			
			while((current = br.readLine()) != null){				
				if(counter == 0){ //extract dimension on first iteration
					dim = Integer.parseInt(current); //convert String to int for dimension
					counter++;
					tempBoard = new Piece[dim][dim]; // make board of right dimension 
					continue;
				} 
				
				temp = extractBadChar(current); //extracts "bad" characters
				System.out.println(temp); // prints board for reference
				
				//This scans each string and determines which Piece we need to create, it then adds it to the Board
				for(int y=0; y<temp.length(); y++){
					// Horizontal Player
					if(temp.charAt(y) == 'H'){
						Position p = new Position(y,x);
						HorizontalPlayer h = new HorizontalPlayer(p);
						tempBoard[y][x] = h;
					// Blank Space	
					}else if(temp.charAt(y) == '+'){
						Position p = new Position(y,x);
						BlankSpace bs = new BlankSpace(p);
						tempBoard[y][x] = bs;
					// Block Character
					}else if(temp.charAt(y) == 'B'){
						Position p = new Position(y,x);
						Block bl = new Block(p);
						tempBoard[y][x] = bl;
					// Vertical Player 
					}else if(temp.charAt(y) == 'V'){
						Position p = new Position(y,x);
						VerticalPlayer v = new VerticalPlayer(p);
						tempBoard[y][x] = v;
						
					}
				}
				
				x++; //increment our x to next board section
				
			}
			
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if(br != null){
					br.close();
				}
				
				if(fr != null){
					fr.close();
				}
			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		Board world = new Board(dim,tempBoard); //create our world

		int vMoves = world.totalVerticalMoves(); //get total vertical moves
		int hMoves = world.totalHorizontalMoves(); //get total horizontal moves 
		
		System.out.println("Total Vertical Moves: " + vMoves );
		System.out.println("Total Horizontal Moves: " + hMoves );

		
	}

}
