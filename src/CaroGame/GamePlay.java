package CaroGame;

public class GamePlay {
	private int[][] lineDirections = {
			{0,1},	//horizontal
			{1,0},	//vertical
			{1,-1},	//diagonal 1
			{1,1}	//diagonal 2
	};
	
	private int requiredLineLength = 5; // Win condition
	
	private boolean legalSquare(int[] square, int boardSize) {
	    return square[0] < boardSize && square[1] < boardSize && square[0] >= 0 && square[1] >= 0;
	}

	public boolean checkWin(int[][] board, int row , int col, Player currentPlayer) {
		int[] lastMove = {row,col};
		boolean isWon = false;
		for (int i = 0; i < lineDirections.length;i++) {
			int[] shift = lineDirections[i];
			int[] currentSquare = {lastMove[0] + shift[0], lastMove[1] + shift[1]};
			int lineLength = 1;
			while (lineLength < requiredLineLength && legalSquare(currentSquare,board.length) && board[currentSquare[0]][currentSquare[1]] == currentPlayer.getId()) {

		            lineLength++;
		            currentSquare[0] += shift[0];
		            currentSquare[1] += shift[1];
		        } 
		        // Check the opposite side of the direction
		        currentSquare = new int[] {lastMove[0] - shift[0], lastMove[1] - shift[1]};
		        
		        while (lineLength < requiredLineLength && legalSquare(currentSquare,board.length) && board[currentSquare[0]][currentSquare[1]] == currentPlayer.getId()) {
		            lineLength++;
		            currentSquare[0] -= shift[0];
		            currentSquare[1] -= shift[1];
		        }
		        if (lineLength >= requiredLineLength)
		            isWon = true;
		    }
		    return isWon;

	}
	
}
