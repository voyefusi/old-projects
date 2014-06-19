
import game.Game;
import game.GameDef;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * A simple graphical driver of the Tron game.
 * April 2010
 * @author Ben Bederson
 */
@SuppressWarnings("serial")
public class TronGraphicalDriver extends JFrame {
	final private int WIDTH = 500;
	final private int HEIGHT = 500;
	final private int MIN_DIM = 5;
	final private int MAX_DIM = 40;
	final private int DEFAULT_DIM = 10;

	private JTextField dimField;
	private int dim = DEFAULT_DIM;

	public static void main(String[] args) {
		TronGraphicalDriver driver = new TronGraphicalDriver();
		driver.createAndShowGUI();
	}

	void createAndShowGUI() {
		final TronGame tronGame = new TronGame();		
		tronGame.initialize(dim);

		// Configure control panel
		JPanel controlPanel = new JPanel();
		JLabel label = new JLabel("Enter integer board dimension:");
		controlPanel.add(label);
		dimField = new JTextField(Integer.toString(DEFAULT_DIM));
		dimField.setPreferredSize(new Dimension(40, 20));
		dimField.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {
				try {
					dim = Integer.parseInt(dimField.getText());
					dim = (dim < MIN_DIM) ? MIN_DIM : dim;
					dim = (dim > MAX_DIM) ? MAX_DIM : dim;
				} catch (Exception ex) {
					dim = DEFAULT_DIM;
				}
			}
		});
		controlPanel.add(dimField);
		final JButton setButton = new JButton("Set");
		setButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tronGame.initialize(dim);
			}
		});
		controlPanel.add(setButton);
		
		final JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tronGame.togglePlayStop(playButton, dim);
			}
		});
		controlPanel.add(playButton);

		// Configures and displays window
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(controlPanel, BorderLayout.NORTH);
		cp.add(tronGame, BorderLayout.CENTER);
		setTitle("Tron Driver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setVisible(true);
	}
}

@SuppressWarnings("serial")
class TronGame extends JPanel {
	final int cellInset = 2;
	final int delayBetweenMoves = 100;

	private int dim = 20;
	private Game[] trons;
	private TronDef tronDef;
	private TronState state;
	private int playerNum = 0;
	private GameDef.gameStatus gameStatus;
	private boolean gamePlaying = false;
	private boolean stopRequested = false;
	private boolean initialized = false;
	private JButton playButton;

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		int width = this.getWidth();
		int height = this.getHeight();
		int cellWidth = width / dim;
		int cellHeight = height / dim;

		String boardString = state.dump();
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				char cell = boardString.charAt(row * (dim + 1) + col);
				switch (cell) {
				case '.':
					g2.setPaint(Color.lightGray);
					g2.drawRect(cellInset + (col * cellWidth), cellInset + (row * cellHeight), cellWidth - (2 * cellInset), cellHeight - (2 * cellInset));
					break;
				case '*':
					g2.setPaint(Color.lightGray);
					g2.fillRect(cellInset + (col * cellWidth), cellInset + (row * cellHeight), cellWidth - (2 * cellInset), cellHeight - (2 * cellInset));
					break;
				case 'x':
					g2.setPaint(Color.black);
					g2.fillRect(cellInset + (col * cellWidth), cellInset + (row * cellHeight), cellWidth - (2 * cellInset), cellHeight - (2 * cellInset));
					break;
				default:
					if (cell == '0') {
						g2.setPaint(Color.red);
					} else {
						g2.setPaint(Color.blue);						
					}
					g2.fillRect(cellInset + (col * cellWidth), cellInset + (row * cellHeight), cellWidth - (2 * cellInset), cellHeight - (2 * cellInset));
					break;
				}
			}
		}
	}

	public void togglePlayStop(JButton playButton, int dim) {
		this.playButton = playButton;
		if (gamePlaying) {
			playButton.setText("Play");
			stopPlay();
		} else {
			playButton.setText("Stop");
			if (!initialized) {
				initialize(dim);
			}
			startPlay();
		}
		gamePlaying = !gamePlaying;
	}
	
	public void initialize(int dim) {
		this.dim = dim;
		trons = new Game[2];
		trons[0] = Utils.createSoln();
		trons[0].setPlayerNum(0);
		trons[1] = Utils.createSoln();
		trons[1].setPlayerNum(1);

		tronDef = new TronDef();
		state = (TronState)tronDef.initialize(dim);
		state.addObstacles(2);
		playerNum = 0;
		stopRequested = false;
		initialized = true;

		repaint();
	}

	public void startPlay() {
		playTurn();
	}

	public void stopPlay() {
		stopRequested = true;
		initialized = false;
	}

	public void playTurn() {
		if (!stopRequested) {
			trons[playerNum].move(state);
			gameStatus = tronDef.getGameStatus(state);
			if (!tronDef.playerGoesAgain()) {
				playerNum = (playerNum + 1) % 2;
			}

			if (gameStatus == GameDef.gameStatus.ONGOING) {
				try {
					Thread.sleep(delayBetweenMoves);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						playTurn();
					}
				});
			} else {
				playButton.setText("Play");
				initialized = false;
				gamePlaying = false;
			}
		}

		repaint();
	}
}