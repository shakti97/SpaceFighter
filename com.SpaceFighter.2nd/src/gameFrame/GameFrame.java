package gameFrame;

import javax.swing.JFrame;

import board.Board;
import gameUtils.GameConstant;

public class GameFrame extends JFrame implements GameConstant{
	public GameFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setLocationRelativeTo(null);
		setTitle(TITLE);
		Board board = new Board();
		add(board);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		GameFrame gameframe = new GameFrame();

	}

}
