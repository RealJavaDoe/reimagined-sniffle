import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author Jean-Pierre PEIFFER
 * @edition 2019
 * @version 1.00
 * 
 *          This code works fine with Windows 10 and Java 10 or GNU/Linux (Ubuntu
 *          18.10) and Java 8
 *
 */

@SuppressWarnings("serial")
public class MyPuzzle extends JFrame {

	private static final Border BORDER = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK);
	private static final Color COLOR_1 = new Color(245, 245, 245);
	private static final Color COLOR_2 = new Color(255, 230, 230);
	private static final Dimension DIM_1 = new Dimension(360, 360);
	private static final Dimension DIM_2 = new Dimension(90, 90);
	private static final Dimension DIM_3 = new Dimension(160, 50);
	private static final Dimension DIM_4 = new Dimension(160, 50);
	private static final Dimension DIM_5 = new Dimension(400, 460);
	private static final Font FONT_1 = new Font("Arial", Font.BOLD, 40);
	private static final Font FONT_2 = new Font("Arial", Font.PLAIN, 18);
	private static final Font FONT_3 = new Font("Arial", Font.PLAIN, 26);
	private static final String[] TEXT = { "Scramble", "Score: ", "Press the OK key to play another game.",
			"Congratulations!", "15-puzzle" };
	private JButton tileButton;
	private JButton optionButton = new JButton(TEXT[0]);
	private JFrame frame;
	private JLabel label;
	private JLabel emptyTileLabel;
	private JPanel container = new JPanel();
	private JPanel[] panel = new JPanel[3];
	int numberOfMoves = 0;
	int[][] position = new int[][] { { 0, 1, 2, 3 }, { 4, 5, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };
	int[] newOrder = new int[16];

	public static int[] getRandomNumbers() {
		int[] randomNumbers = new int[15];
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < randomNumbers.length; i++) {
			arrayList.add(i);
		}
		Random random = new Random();
		for (int i = 0; i < randomNumbers.length; i++) {
			int index = random.nextInt(arrayList.size());
			randomNumbers[i] = arrayList.get(index);
			arrayList.remove(index);
		}
		return randomNumbers;
	}

	public int[] getSolubleGame() {
		boolean test = false;
		int[] randomNumbers = getRandomNumbers();
		while (test == false) {
			int counter = 0;
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < i; j++) {
					if (randomNumbers[j] > randomNumbers[i])
						counter++;
				}
			}
			if (counter % 2 == 0)
				test = true;
			else
				randomNumbers = getRandomNumbers();
		}
		return randomNumbers;
	}

	public JPanel getDisplay() {
		container.setBackground(COLOR_1);
		panel[0] = new JPanel();
		panel[0].setPreferredSize(DIM_1);
		panel[0].setBackground(Color.WHITE);
		panel[0].setLayout(new GridLayout(4, 4, 0, 0));
		panel[0].setBorder(BORDER);
		int[] randomNumbers = getSolubleGame();
		for (int i = 0; i < 15; i++) {
			newOrder[i] = randomNumbers[i];
		}
		newOrder[15] = 15;
		int counter = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 3 && j == 3) {
					emptyTileLabel = new JLabel("");
					panel[0].add(emptyTileLabel);
				} else {
					label = new JLabel(String.valueOf(newOrder[counter] + 1));
					tileButton = new JButton(label.getText());
					tileButton.setPreferredSize(DIM_2);
					tileButton.setFont(FONT_1);
					tileButton.setBackground(COLOR_2);
					tileButton.setFocusPainted(false);
					tileButton.addActionListener(new TileListener());
					panel[0].add(tileButton);
					counter++;
				}
			}
		}
		panel[1] = new JPanel();
		panel[1].setPreferredSize(DIM_3);
		panel[1].setBackground(COLOR_1);
		optionButton.setFont(FONT_2);
		optionButton.setFocusPainted(false);
		optionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				container.removeAll();
				numberOfMoves = 0;
				frame.add(getDisplay());
				frame.validate();
			}
		});
		panel[1].add(optionButton);
		panel[2] = new JPanel();
		panel[2].setBackground(COLOR_1);
		panel[2].setPreferredSize(DIM_4);
		panel[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel movesLabel = new JLabel();
		movesLabel.setFont(FONT_3);
		movesLabel.setText(TEXT[1] + numberOfMoves);
		panel[2].add(movesLabel);
		container.add(panel[0], BorderLayout.NORTH);
		container.add(panel[1], BorderLayout.CENTER);
		container.add(panel[2], BorderLayout.SOUTH);
		return container;
	}

	public void switchNumbers(int indexStartTile, int indexFinishTile) {
		int temp;
		temp = newOrder[indexStartTile];
		newOrder[indexStartTile] = newOrder[indexFinishTile];
		newOrder[indexFinishTile] = temp;
	}

	public boolean isOver() {
		int counter = 0;
		boolean test = false;
		for (int i = 0; i < 15; i++) {
			if (newOrder[i] < newOrder[i + 1])
				counter++;
		}
		if (counter == 15)
			test = true;
		return test;
	}

	class TileListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton startTileButton = (JButton) e.getSource();
			Dimension dimStartTile = startTileButton.getSize();
			int startTileX = startTileButton.getX();
			int startTileY = startTileButton.getY();
			int finishTileX = emptyTileLabel.getX();
			int finishTileY = emptyTileLabel.getY();
			int indexStartTile = position[startTileY / dimStartTile.height][startTileX / dimStartTile.width];
			int indexFinishTile;
			if (finishTileX == startTileX && finishTileY == startTileY + dimStartTile.height) {
				indexFinishTile = indexStartTile + 4;
			} else if (finishTileX == startTileX && finishTileY == startTileY - dimStartTile.height) {
				indexFinishTile = indexStartTile - 4;
			} else if (finishTileX == startTileX + dimStartTile.width && finishTileY == startTileY) {
				indexFinishTile = indexStartTile + 1;
			} else if (finishTileX == startTileX - dimStartTile.width && finishTileY == startTileY) {
				indexFinishTile = indexStartTile - 1;
			} else
				return;
			numberOfMoves++;
			panel[0].add(emptyTileLabel, indexStartTile);
			panel[0].add(startTileButton, indexFinishTile);
			panel[0].validate();
			panel[2].removeAll();
			JLabel movesLabel = new JLabel();
			movesLabel.setFont(FONT_3);
			movesLabel.setText(TEXT[1] + numberOfMoves);
			panel[2].add(movesLabel);
			panel[2].validate();
			switchNumbers(indexStartTile, indexFinishTile);
			if (isOver()) {
				JOptionPane.showMessageDialog(null, TEXT[2], TEXT[3], JOptionPane.PLAIN_MESSAGE);
				container.removeAll();
				numberOfMoves = 0;
				frame.add(getDisplay());
				frame.validate();
			}
		}

	}

	public void start() {
		frame = new JFrame();
		frame.setTitle(TEXT[4]);
		frame.setSize(DIM_5);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(COLOR_1);
		frame.add(getDisplay());
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new MyPuzzle().start();
	}

}
