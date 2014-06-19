package studentCode;
import java.awt.Color;
import CMSC131GridTools.DrawingGrid;


public class LetterMaker {
	private final static int STROKE_THICKNESS  = 3
	; 

	public static void drawLetter(DrawingGrid grid, String letter, Color color) {
		/*The parameter "letter"
		 * will be equal to one of the following characters:
		 *  "C", "U", "H", "O", "I", "N", "Z" or "error"		
		 */
		if (letter.equals("C")){
			letterC(grid,color);
		}else if (letter.equals("U")){
			letterU(grid,color);
		}else if (letter.equals("H")){
			letterH(grid,color);
		}else if (letter.equals("O")){
			letterO(grid,color);
		}else if (letter.equals("I")){
			letterI(grid,color);
		}else if (letter.equals("N")){
			letterN(grid,color);
		}else if (letter.equals("Z")){
			letterZ(grid,color);
		}else{
			letter.equals("error");
			letterError(grid,color); 
		}
	}


	public static void letterZ(DrawingGrid grid, Color color){
		int count = -(STROKE_THICKNESS/2);


		do{
			int b = (grid.getGridSize()-1);  //Slash
			int length = grid.getGridSize();
			for(int h=1; h<(STROKE_THICKNESS/2)+1; h++){
			for(int a= 1;a <length - h; a++){
				
					//grid.setColor()
					grid.setColor(length-a-1, a+1, color);
					grid.setColor(length-a-1,a, color);
					grid.setColor(length-a-2,a,color);
					//b--;
				//}

			}}
			count++;

		}while(count <= (STROKE_THICKNESS/2));			



		//Top Horizontal Line
		for(int j=0, length1 = grid.getGridSize(); j<STROKE_THICKNESS; j++){
			for(int i = 0 ; i <= length1 - 1; i++){
				grid.setColor(j, i, color); 
			}
		}
		 


		//bottom horizontal line
		for(int length3 = grid.getGridSize(),thickness = length3-STROKE_THICKNESS, x = length3 -1;x>=thickness; x--){  
			for(int y = length3 - 1 ; y >= 0; y--){
				grid.setColor(x, y, color);

			}
		}

		 
	}




	public static void letterN(DrawingGrid grid, Color color){
		//left vertical line
		for(int a=0, length2 = grid.getGridSize(); a<STROKE_THICKNESS; a++){
			for(int b = 0 ; b <= length2 - 1; b++){
				grid.setColor(b, a, color);
			}
		}
		//right vertical line
		for(int length4 = grid.getGridSize()-1,thickness = length4-STROKE_THICKNESS,g = length4;g>thickness; g--){
			for(int h = 0 ; h <= length4; h++){
				grid.setColor(h, g, color);
			}
		}
		int y = 0;  //Slash
		int length3 = grid.getGridSize();

		
			for(int x = 0; x <= length3 -1; x++){
					grid.setColor((length3+x)-length3, x, color);
			}
			for(int h=1; h<(STROKE_THICKNESS/2)+1; h++){
				for(int a= 1;a <length3; a++){
					
						
						grid.setColor((length3+a)-length3, a-1, color);
						
					

				}}for(int h=1; h<(STROKE_THICKNESS/2)+1; h++){
				for(int a= 1;a <length3-1; a++){
						grid.setColor((length3+a)-length3, a+1, color);
				}}


	}
	public static void letterI(DrawingGrid grid, Color color){
		//Top Horizontal Line
		for(int j=0, length1 = grid.getGridSize(); j<STROKE_THICKNESS; j++){
			for(int i = 0 ; i <= length1 - 1; i++){
				grid.setColor(j, i, color); 
			}
		}
		//bottom horizontal line
		for(int length3 = grid.getGridSize(),thickness = length3-STROKE_THICKNESS, x = length3 -1;x>=thickness; x--){  
			for(int y = length3 - 1 ; y >= 0; y--){
				grid.setColor(x, y, color);

			}

		}

		//middle vertical line

		for( int length3 = ((grid.getGridSize()-1)/2),g = length3, thickness = g+(STROKE_THICKNESS/2); g<=thickness; g++){ 
			for(int h = 0 ; h <= (length3 * 2) ; h++){
				grid.setColor(h, g, color);
			}	
		}
		for( int length3 = ((grid.getGridSize()-1)/2),g = length3, thickness = g-(STROKE_THICKNESS/2); g>=thickness; g--){ 
			for(int h = 0 ; h <= (length3 * 2) ; h++){
				grid.setColor(h, g, color);
			}	
		}
	}
	public static void letterO(DrawingGrid grid, Color color){
		//Top Horizontal Line
		for(int j=0, length1 = grid.getGridSize(); j<STROKE_THICKNESS; j++){
			for(int i = 0 ; i <= length1 - 1; i++){
				grid.setColor(j, i, color); 
			}
		}
		//left vertical line
		for(int a=0, length2 = grid.getGridSize(); a<STROKE_THICKNESS; a++){
			for(int b = 0 ; b <= length2 - 1; b++){
				grid.setColor(b, a, color);
			}
		}

		//bottom horizontal line
		for(int length3 = grid.getGridSize(),thickness = length3-STROKE_THICKNESS, x = length3 -1;x>=thickness; x--){  
			for(int y = length3 - 1 ; y > 0; y--){
				grid.setColor(x, y, color);

			}

		}
		//right vertical line
		for(int length4 = grid.getGridSize()-1,thickness = length4-STROKE_THICKNESS,g = length4;g>thickness; g--){
			for(int h = 0 ; h <= length4 - 1; h++){
				grid.setColor(h, g, color);
			}
		}

	}
	public static void letterH(DrawingGrid grid, Color color){
		//middle horizontal line
		for( int length1 = grid.getGridSize(),x = ((length1 - 1)/2), thickness = x+(STROKE_THICKNESS/2); x<=thickness; x++){ 

			for(int y = length1 - 1 ; y >= 0; y--){
				grid.setColor(x, y, color);
				;
			}

		}
		for( int length1 = grid.getGridSize(),x = ((length1 - 1)/2), thickness = x-(STROKE_THICKNESS/2); x>=thickness; x--){ 

			for(int y = length1 - 1 ; y >= 0; y--){
				grid.setColor(x, y, color);
				;
			}
		}
		//right vertical line
		for(int length4 = grid.getGridSize()-1,thickness = length4-STROKE_THICKNESS,g = length4;g>thickness; g--){
			for(int h = 0 ; h <= length4; h++){
				grid.setColor(h, g, color);
			}
		}


		//left vertical line
		for(int a=0, length2 = grid.getGridSize(); a<STROKE_THICKNESS; a++){
			for(int b = 0 ; b <= length2 - 1; b++){
				grid.setColor(b, a, color);
			}
		}




	}
	public static void letterU(DrawingGrid grid, Color color){
		//bottom horizontal line
		for(int length3 = grid.getGridSize(),thickness = length3-STROKE_THICKNESS, x = length3 -1;x>=thickness; x--){  
			for(int y = length3 - 1 ; y > 0; y--){
				grid.setColor(x, y, color);

			}

		}
		//right vertical line
		for(int length4 = grid.getGridSize()-1,thickness = length4-STROKE_THICKNESS,g = length4;g>thickness; g--){
			for(int h = 0 ; h <= length4 - 1; h++){
				grid.setColor(h, g, color);
			}
		}
		//left vertical line
		for(int a=0, length2 = grid.getGridSize(); a<STROKE_THICKNESS; a++){
			for(int b = 0 ; b <= length2 - 1; b++){
				grid.setColor(b, a, color);
			}
		}

	}
	public static void letterC(DrawingGrid grid, Color color){
		//Top Horizontal Line
		for(int j=0, length1 = grid.getGridSize(); j<STROKE_THICKNESS; j++){
			for(int i = 0 ; i <= length1 - 1; i++){
				grid.setColor(j, i, color); 
			}
		}
		//left vertical line
		for(int a=0, length2 = grid.getGridSize(); a<STROKE_THICKNESS; a++){
			for(int b = 0 ; b <= length2 - 1; b++){
				grid.setColor(b, a, color);
			}
		}

		//bottom horizontal line
		for(int length3 = grid.getGridSize(),thickness = length3-STROKE_THICKNESS, x = length3 -1;x>=thickness; x--){  
			for(int y = length3 - 1 ; y > 0; y--){
				grid.setColor(x, y, color);

			}

		}

	}


	public static void letterError(DrawingGrid grid, Color color){
		int y = grid.getGridSize()-1;
		do{
			for(int x = grid.getGridSize()-1,thickness = x-STROKE_THICKNESS;x > thickness; x--){
				grid.setColor(x, y, color);
			}
			y--;
		}while(y >= (grid.getGridSize()- STROKE_THICKNESS));


	}

}
