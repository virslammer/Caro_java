package CaroGame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;




public class Board {
	private int size=16;
	private JTable board;
	private JFrame f;
	
	private int[][] boardArray = new int[size][size];
	private Player player1 = new Player(" X ","player 1");
	private Player player2 = new Player(" O ","player 2");
	private Player currentPlayer = player1;
	private GamePlay control = new GamePlay(); 
	
	private JButton startBtn = new JButton("Start");
	private JButton resetBtn = new JButton("Reset");
	private JLabel gameStatusLb = new JLabel("Press start to play");
	
	
	public void createAndShow() {
		f = new JFrame("Simple caro game ");
		board = new JTable(this.size,this.size);
		JScrollPane boardContainer = new JScrollPane(board);
		
		JPanel controlPanel = new JPanel();
		board.setFillsViewportHeight(true);
		board.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		board.setTableHeader(null);
		board.setRowHeight(25);
		
		for (int i=0; i < this.size ; i++) {
			board.getColumnModel().getColumn(i).setPreferredWidth(25);
		}
		
		gameStatusLb.setBounds(130, 100, 100, 40);
		gameStatusLb.setAlignmentX(Component.CENTER_ALIGNMENT);
		startBtn.setBounds(130, 100, 100, 40);
		startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		resetBtn.setBounds(130, 100, 100, 40);
		resetBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		controlPanel.add(gameStatusLb);
		controlPanel.add(startBtn);
		controlPanel.add(resetBtn);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		startBtn.addMouseListener(startBtnClick);
		resetBtn.addMouseListener(resetBtnClick);
		board.setEnabled(false);

		
		f.add(controlPanel);
		f.setResizable(false);
		f.add(boardContainer, BorderLayout.CENTER);
		f.setLayout(new GridLayout(1,2));
		f.setSize(1000,300);
		f.setVisible(true);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void gameStart() {
		resetBoard();
		board.addMouseListener(clickCell);
	}
	private void gameStop() {
		board.removeMouseListener(clickCell);
		gameStatusLb.setText(currentPlayer.getPlayerName() + " win . " +"Press start to play new game ");
	}
	private void resetBoard() {
		for (int row=0; row < size ; row++) {
			for(int col=0; col < size; col++) {
				boardArray[row][col] = 0;
				board.setValueAt(null, row, col);
			}
		}
	}
	private MouseAdapter clickCell = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=board.rowAtPoint(e.getPoint());
				int col= board.columnAtPoint(e.getPoint());
				if (board.getValueAt(row, col) == null) {
					if (currentPlayer == player1) {
						System.out.println("Clicked on " + col + "-" + row);
						board.setValueAt(currentPlayer.getSym(),row,  col);
						System.out.println(currentPlayer.getId());
						boardArray[row][col] = currentPlayer.getId();
						printBoard();
						if (control.checkWin(boardArray, row, col, currentPlayer)) {
							gameStop();
							return;
						}
						currentPlayer = player2;
						turnOfPlayer();
					} else {
						System.out.println("Clicked on " + col + "-" + row);
						board.setValueAt(currentPlayer.getSym(),row,  col);
						System.out.println(currentPlayer.getId());
						boardArray[row][col] = currentPlayer.getId();
						printBoard();
						System.out.println(control.checkWin(boardArray, row, col, currentPlayer));
						if (control.checkWin(boardArray, row, col, currentPlayer)) {
							gameStop();
							return;
						}
						currentPlayer = player1;
						turnOfPlayer();
					}
					
				} else {
					System.out.println("Cell filled");
				}
			}
		};
		void printBoard() {
			for (int i= 0; i < this.boardArray.length; i++) {
				for (int j=0; j < this.boardArray[i].length; j++) {
					System.out.print(boardArray[i][j]+ " ");
				}
				System.out.print("\n");
			}
		}
	
	private MouseAdapter startBtnClick = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			
			gameStart();
			gameStatusLb.setText("Turn of player : " + currentPlayer.getPlayerName());
			
		}
	};
	private MouseAdapter resetBtnClick = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			resetBoard();
			gameStart();
			
		}
	};
	private void turnOfPlayer() {
		gameStatusLb.setText("turn of player : " + currentPlayer.getPlayerName());
		System.out.println("turn of player : " + currentPlayer.getPlayerName());
	}

}
