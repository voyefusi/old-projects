package game;
import puzzleLib.*;
import java.util.Arrays;
import puzzleLib.Dictionary131;

public class Jumble implements Puzzle {
	/*  Students:  In the line above, you may wonder what is meant by
	 *  "implements Puzzle".  You will learn about it soon!  Here
	 *  is the idea:  "Puzzle" is an interface, containing the prototypes
	 *  of many methods.  The class we are writing (Jumble) must contain
	 *  implementations of ALL of the methods found in the Puzzle
	 *  interface.  You'll learn more about interfaces soon in the
	 *  lectures this week.
	 *  
	 *  Remove this comment and implement the class!  See the project
	 *  description and the JavaDoc for specific details.
	 */

	private char solutionWord[];
	private char currentWord[];
	private int numberOfGeneratedTransformations;
	private int initTrans;
	private int transTotal;

	public void generatePuzzle(java.lang.String wordToUse){ 
		//Initializes current puzzle so that it represents the sequence of characters contained in the parameter.
		solutionWord = new char[wordToUse.length()]; 
		currentWord =  new char[wordToUse.length()];

		for (int index = 0; index < wordToUse.length();index++){
			solutionWord[index] = wordToUse.charAt(index);
			currentWord[index] = wordToUse.charAt(index);
		}
	}

	public void generateRandomScrambledPuzzle(int numLetters, int numTransformations){ 
		//Initializes current puzzle based on a random word selected from the dictionary.
		initTrans = numTransformations;
		String word = Dictionary131.getRandomWord(numLetters);
		int range = 3;	
		generatePuzzle(word);

		for(int count = 0;count < numTransformations;count++){

			int randomIntR = Random131.getRandomInteger(range);
			int firstIndex = Random131.getRandomInteger(word.length());
			int secondIndex = Random131.getRandomInteger(word.length());

			if (randomIntR == 0){
				swap(firstIndex,secondIndex);
			}
			if (randomIntR == 1){
				insert(firstIndex,secondIndex);
			}
			if (randomIntR == 2){
				multipleSwap(firstIndex,secondIndex);
			}
		}
	}

	public void generateScrambledPuzzle(java.lang.String wordToUse, int numTransformations){
		//Initializes current puzzle so that it represents the sequence of characters contained in the parameter.

		int range = 3;

		initTrans = numTransformations;

		generatePuzzle(wordToUse);

		for(int count = 0;count < numTransformations;count++){

			int randomIntR = Random131.getRandomInteger(range);
			int firstIndex = Random131.getRandomInteger(wordToUse.length());
			int secondIndex = Random131.getRandomInteger(wordToUse.length());

			if (randomIntR == 0){
				swap(firstIndex,secondIndex);
			}
			if (randomIntR == 1){
				insert(firstIndex,secondIndex);
			}
			if (randomIntR == 2){
				multipleSwap(firstIndex,secondIndex);
			}
		}
	}
	
	public int getAttempts(){ 
		//Returns the number of user attempts at solving the puzzle.
		transTotal = numberOfGeneratedTransformations-initTrans;
		return transTotal;
	}

	public int getNumOfInitTransformations(){
		//Returns the number of random transformations applied to the word while the puzzle was being generated.
		return initTrans;
	}

	public int getPuzzleLength(){ 
		//Returns the length (i.e., number of characters) of the puzzle solution.
		return solutionWord.length;
	}
	
	public String getSolution(){ 
		//Returns a String representing the word the user is expected to guess.
		String word = "";
		for(int index = 0;index < solutionWord.length; index++){
			word += solutionWord[index];
		}
		return word;
	}

	public void insert(int firstIndex, int secondIndex){ 
		//Performs an "insert move" on the cells with indices specified.
		char temp;

		if(firstIndex < secondIndex){
			temp = currentWord[firstIndex];
			for(int index = firstIndex;index < secondIndex; index++){
				currentWord[index] = currentWord[index+1];
			}
			currentWord[secondIndex]=temp;
		}

		if(firstIndex > secondIndex){
			temp = currentWord[firstIndex];
			for(int index = firstIndex; index > secondIndex; index--){
				currentWord[index] = currentWord[index-1];
			}
			currentWord[secondIndex]=temp;
		}
		numberOfGeneratedTransformations++;
	}
	
	public boolean isSolutionCorrect(){ 
		//Determines if the current puzzle is in a correct solution state.
		for (int index = 0; index < solutionWord.length;index++){
			if(currentWord[index] != solutionWord[index]){
				return false;
			}
		}
		return true;
	}
	
	public void	multipleSwap(int firstIndex, int secondIndex){ 
		//Performs a "multiple-swap move" on the cells with indices specified.
		for(int index1 = firstIndex, index2 = secondIndex;(index1 < index2);index1++,index2--){
			char temp1 = currentWord[index1];
			char temp2 = currentWord[index2];
			currentWord[index1]=temp2;
			currentWord[index2]=temp1;	
		}

		for(int index1 = firstIndex, index2 = secondIndex;(index1 > index2);index1--,index2++){
			char temp1 = currentWord[index1];
			char temp2 = currentWord[index2];
			currentWord[index1]=temp2;
			currentWord[index2]=temp1;

		}
		numberOfGeneratedTransformations++;

	} 
	
	public void	swap(int firstIndex, int secondIndex){ 
		//Performs a "swap move" on the cells with indices specified.

		char newWord[] = new char[currentWord.length];
		for(int index = 0;index < currentWord.length; index++){
			if (index == firstIndex){
				newWord[secondIndex] = currentWord[firstIndex];
			}else if(index==secondIndex){
				newWord[firstIndex] = currentWord[secondIndex];
			}else{ 
				newWord[index] = currentWord[index];
			}
		}
		for (int index2 = 0; index2 < newWord.length;index2++){
			currentWord[index2] = newWord[index2];
		}
		numberOfGeneratedTransformations++;
	}
	
	public String toString(){
		//"char[] a", you can call Arrays.toString(a)
		//String solution = Arrays.toString(solutionWord)
		String word = "";
		for(int index = 0;index < currentWord.length; index++){
			word += currentWord[index];
		}
		return word;
	}
	
	public puzzleLib.Cell[] getCurrentContentsAsCells() {
		//Returns the number of random transformations applied to the word while the puzzle was being generated.
		Cell[] celly;
		celly =	new Cell[solutionWord.length];
		for(int index = 0;index < solutionWord.length;index++){

			if (index == 0){
				if (currentWord[index]==solutionWord[index]){
					celly[index] = new Cell(currentWord[index],  Cell.WHITE);
				}
				else if (currentWord[index]==solutionWord[solutionWord.length-1]){
					celly[index] = new Cell(currentWord[index],  Cell.YELLOW);
				}
				else if (currentWord[index]==solutionWord[index+1]){
					celly[index] = new Cell(currentWord[index],  Cell.YELLOW);
				}else{
					celly[index] = new Cell(currentWord[index],  Cell.WHITE);
				}
			}else if((index>0)&&(index<(solutionWord.length-1))){
				if (currentWord[index]==solutionWord[index]){
					celly[index] = new Cell(currentWord[index],  Cell.WHITE);
				}
				else if (currentWord[index]==solutionWord[(index-1)]){
					celly[index] = new Cell(currentWord[index],  Cell.YELLOW);
				}
				else if (currentWord[index]==solutionWord[(index+1)]){
					celly[index] = new Cell(currentWord[index],  Cell.YELLOW);
				}else{
					celly[index] = new Cell(currentWord[index],  Cell.WHITE);
				}
			}else if(index == solutionWord.length-1){
				if (currentWord[index]==solutionWord[index]){
					celly[index] = new Cell(currentWord[index],  Cell.WHITE);
				}else if (currentWord[index]==solutionWord[(0)]){
					celly[index] = new Cell(currentWord[index],  Cell.YELLOW);
				}else if (currentWord[index]==solutionWord[(index-1)]){
					celly[index] = new Cell(currentWord[index],  Cell.YELLOW);
				}else{
					celly[index] = new Cell(currentWord[index],  Cell.WHITE);
				}
			}

		}
		return celly;
	}

}




