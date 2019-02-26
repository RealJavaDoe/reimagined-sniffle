import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * @author Jean-Pierre PEIFFER
 * @edition 2019
 * @version 1.0
 * 
 *          This works fine with Windows 10 and Java 10 & GNU/Linux (Ubuntu
 *          18.10) and Java 8
 * 
 */

public class PasswordGenerator {

	private static final Border BORDER_1 = BorderFactory.createEtchedBorder();
	private static final Border BORDER_2 = BorderFactory.createLoweredBevelBorder();
	private static final Border BORDER_3 = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY);
	private static final Color COLOR = new Color(245, 245, 245);
	private static final Dimension DIM_1 = new Dimension(325, 30);
	private static final Dimension DIM_2 = new Dimension(415, 90);
	private static final Dimension DIM_3 = new Dimension(460, 470);
	private static final Font FONT_1 = new Font("Arial", Font.PLAIN, 20);
	private static final Font FONT_2 = new Font("Arial", Font.PLAIN, 14);
	private static final int MAXIMUM_PASSWORD_LENGTH = 20;
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String[] TEXT = { "PARAMETERS", "Password length:", "Requirements", "Lowercase characters",
			"Uppercase characters", "Digits", "Special characters (e.g. #&*/+-=%!?,;:()[]{}@_$<>)", "Option",
			"Exclude similar characters (e.g. 0 o O 1 l I)", "Generate", "DETAILS", " lowercase characters;",
			" uppercase characters;", " digits;", " special characters.", " lowercase character;",
			" uppercase character;", " digit;", " special character.", "The password is ",
			" characters long and contains: ", "Password generator" };
	private Box[] box = new Box[3];
	private JButton button = new JButton();
	private JCheckBox[] checkBox = new JCheckBox[5];
	private JComboBox<String> comboBox;
	private JFrame frame;
	private JLabel label = new JLabel();
	private JPanel container = new JPanel();
	private JPanel[] panel = new JPanel[6];
	private JTextArea[] textArea = new JTextArea[2];
	private String lowercaseCharacters = "abcdefghijklmnopqrstuvwxyz";
	private String uppercaseCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String digits = "0123456789";
	private String specialCharacters = "#&*/+-=%!?,;:()[]{}@_$<>";

	public JPanel getContainer(int index, boolean test1, boolean test2, boolean test3, boolean test4, String str1,
			String str2) {
		container.setBackground(COLOR);
		panel[0] = new JPanel();
		panel[0].setBackground(COLOR);
		panel[0].setBorder(BorderFactory.createTitledBorder(BORDER_1, TEXT[0], TitledBorder.LEFT, TitledBorder.TOP));
		panel[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		panel[1] = new JPanel();
		panel[1].setBackground(COLOR);
		panel[1].setLayout(new FlowLayout(FlowLayout.LEFT));
		label = new JLabel(TEXT[1]);
		panel[1].add(label);
		comboBox = new JComboBox<String>();
		for (int i = 6; i < MAXIMUM_PASSWORD_LENGTH + 1; i++) {
			comboBox.addItem(String.valueOf(i));
		}
		comboBox.setSelectedIndex(index);
		panel[1].add(comboBox);
		panel[2] = new JPanel();
		panel[2].setBackground(COLOR);
		panel[2].setBorder(BorderFactory.createTitledBorder(BORDER_3, TEXT[2], TitledBorder.LEFT, TitledBorder.TOP));
		panel[2].setLayout(new FlowLayout(FlowLayout.LEFT));
		checkBox[0] = new JCheckBox(TEXT[3]);
		checkBox[0].setBackground(COLOR);
		checkBox[0].setSelected(true);
		checkBox[0].setEnabled(false);
		checkBox[1] = new JCheckBox(TEXT[4]);
		checkBox[1].setBackground(COLOR);
		checkBox[1].setSelected(test1);
		checkBox[2] = new JCheckBox(TEXT[5]);
		checkBox[2].setBackground(COLOR);
		checkBox[2].setSelected(test2);
		checkBox[3] = new JCheckBox(TEXT[6]);
		checkBox[3].setBackground(COLOR);
		checkBox[3].setSelected(test3);
		box[0] = Box.createVerticalBox();
		box[0].add(checkBox[0]);
		box[0].add(checkBox[1]);
		box[0].add(checkBox[2]);
		box[0].add(checkBox[3]);
		panel[2].add(box[0]);
		panel[3] = new JPanel();
		panel[3].setBackground(COLOR);
		panel[3].setBorder(BorderFactory.createTitledBorder(BORDER_3, TEXT[7], TitledBorder.LEFT, TitledBorder.TOP));
		panel[3].setLayout(new FlowLayout(FlowLayout.LEFT));
		checkBox[4] = new JCheckBox(TEXT[8]);
		checkBox[4].setBackground(COLOR);
		checkBox[4].setSelected(test4);
		panel[3].add(checkBox[4]);
		box[1] = Box.createVerticalBox();
		box[1].add(panel[1]);
		box[1].add(panel[2]);
		box[1].add(panel[3]);
		panel[0].add(box[1]);
		panel[4] = new JPanel();
		panel[4].setBackground(COLOR);
		panel[4].setLayout(new FlowLayout(FlowLayout.LEFT));
		button = new JButton(TEXT[9]);
		button.addActionListener(new Generate());
		panel[4].add(button);
		textArea[0] = new JTextArea(str1);
		textArea[0].setPreferredSize(DIM_1);
		textArea[0].setBackground(COLOR);
		textArea[0].setFont(FONT_1);
		textArea[0].setBorder(BORDER_2);
		textArea[0].setEditable(false);
		panel[4].add(textArea[0]);
		panel[5] = new JPanel();
		panel[5].setBackground(COLOR);
		panel[5].setBorder(BorderFactory.createTitledBorder(BORDER_1, TEXT[10], TitledBorder.LEFT, TitledBorder.TOP));
		panel[5].setLayout(new FlowLayout(FlowLayout.LEFT));
		textArea[1] = new JTextArea(str2);
		textArea[1].setPreferredSize(DIM_2);
		textArea[1].setBackground(COLOR);
		textArea[1].setFont(FONT_2);
		textArea[1].setBorder(BORDER_2);
		textArea[1].setEditable(false);
		panel[5].add(textArea[1]);
		box[2] = Box.createVerticalBox();
		box[2].add(panel[0]);
		box[2].add(panel[4]);
		box[2].add(panel[5]);
		container.add(box[2]);
		return container;
	}

	class Generate implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			boolean testUppercaseCharacters = false;
			boolean testDigits = false;
			boolean testSpecialCharacters = false;
			boolean testSimilarCharacters = false;
			int numberOfUppercaseCharacters, numberOfDigits, numberOfSpecialCharacters;
			int passwordLength = Integer.valueOf((String) comboBox.getSelectedItem());
			int halfLength = passwordLength / 2;
			if (checkBox[1].isSelected()) {
				numberOfUppercaseCharacters = (int) (Math.random() * halfLength) + 1;
				testUppercaseCharacters = true;
			} else
				numberOfUppercaseCharacters = 0;
			if (checkBox[2].isSelected()) {
				numberOfDigits = (int) (Math.random() * (halfLength - numberOfUppercaseCharacters)) + 1;
				testDigits = true;
			} else
				numberOfDigits = 0;
			if (checkBox[3].isSelected()) {
				numberOfSpecialCharacters = (int) (Math.random()
						* (halfLength - numberOfUppercaseCharacters - numberOfDigits)) + 1;
				testSpecialCharacters = true;
			} else
				numberOfSpecialCharacters = 0;
			if (checkBox[4].isSelected()) {
				lowercaseCharacters = "abcdefghijkmnpqrstuvwxyz";
				uppercaseCharacters = "ABCDEFGHJKLMNPQRSTUVWXYZ";
				digits = "23456789";
				testSimilarCharacters = true;
			}
			char[] allRandomizedCharacters = new char[passwordLength];
			int value = 0;
			int numberOfLowercaseCharacters = passwordLength - numberOfUppercaseCharacters - numberOfDigits
					- numberOfSpecialCharacters;
			for (int i = 0; i < numberOfLowercaseCharacters; i++) {
				int j = (int) Math.floor(Math.random() * lowercaseCharacters.length());
				allRandomizedCharacters[value] = lowercaseCharacters.charAt(j);
				value++;
			}
			for (int i = 0; i < numberOfUppercaseCharacters; i++) {
				int j = (int) Math.floor(Math.random() * uppercaseCharacters.length());
				allRandomizedCharacters[value] = uppercaseCharacters.charAt(j);
				value++;
			}
			for (int i = 0; i < numberOfDigits; i++) {
				int j = (int) Math.floor(Math.random() * digits.length());
				allRandomizedCharacters[value] = digits.charAt(j);
				value++;
			}
			for (int i = 0; i < numberOfSpecialCharacters; i++) {
				int j = (int) Math.floor(Math.random() * specialCharacters.length());
				allRandomizedCharacters[value] = specialCharacters.charAt(j);
				value++;
			}
			ArrayList<String> shuffledList = new ArrayList<String>();
			for (int i = 0; i < passwordLength; i++) {
				shuffledList.add(String.valueOf(allRandomizedCharacters[i]));
			}
			Collections.shuffle(shuffledList);
			String password = String.join("", shuffledList);
			String lowercaseChar = TEXT[11];
			String uppercaseChar = TEXT[12];
			String digits = TEXT[13];
			String specialCharacters = TEXT[14];
			if (numberOfLowercaseCharacters == 0 || numberOfLowercaseCharacters == 1)
				lowercaseChar = TEXT[15];
			if (numberOfUppercaseCharacters == 0 || numberOfUppercaseCharacters == 1)
				uppercaseChar = TEXT[16];
			if (numberOfDigits == 0 || numberOfDigits == 1)
				digits = TEXT[17];
			if (numberOfSpecialCharacters == 0 || numberOfSpecialCharacters == 1)
				specialCharacters = TEXT[18];
			String details = TEXT[19] + passwordLength + TEXT[20] + LINE_SEPARATOR + " - " + numberOfLowercaseCharacters
					+ lowercaseChar + LINE_SEPARATOR + " - " + numberOfUppercaseCharacters + uppercaseChar
					+ LINE_SEPARATOR + " - " + numberOfDigits + digits + LINE_SEPARATOR + " - "
					+ numberOfSpecialCharacters + specialCharacters;
			container.removeAll();
			frame.add(getContainer(passwordLength - 6, testUppercaseCharacters, testDigits, testSpecialCharacters,
					testSimilarCharacters, password, details));
			frame.validate();
		}
		
	}

	public void start() {
		frame = new JFrame();
		frame.setTitle(TEXT[21]);
		frame.setSize(DIM_3);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(COLOR);
		frame.add(getContainer(4, false, false, false, false, null, null));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new PasswordGenerator().start();
	}

}
