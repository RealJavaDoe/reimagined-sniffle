import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * @author Jean-Pierre PEIFFER
 * @edition 2019
 * @version 1
 * 
 *          This works fine with Windows 10 and Java 10 & GNU/Linux (Ubuntu
 *          18.10) and Java 8
 * 
 */

public class Calculator {

	private static final Color COLOR_1 = new Color(245, 245, 245);
	private static final Color COLOR_2 = new Color(160, 160, 160);
	private static final Color COLOR_3 = new Color(215, 215, 215);
	private static final Color COLOR_4 = new Color(230, 230, 230);
	private static final Color COLOR_5 = new Color(187, 187, 187);
	private static final Dimension DIM_1 = new Dimension(260, 30);
	private static final Dimension DIM_2 = new Dimension(300, 240);
	private static final Dimension DIM_3 = new Dimension(280, 40);
	private static final Dimension DIM_4 = new Dimension(165, 185);
	private static final Dimension DIM_5 = new Dimension(55, 185);
	private static final Dimension DIM_6 = new Dimension(50, 40);
	private static final Dimension DIM_7 = new Dimension(50, 85);
	private static final Dimension DIM_8 = new Dimension(310, 280);
	private static final Font FONT_1 = new Font("Arial", Font.BOLD, 20);
	private static final Font FONT_2 = new Font("Arial", Font.PLAIN, 16);
	private static final String[] TEXT = { "7", "8", "9", "4", "5", "6", "1", "2", "3", ".", "0", "\u00b1", "\u00f7",
			"\u00d7", "-", "+", "\u25c4", "C", "=", "Calculator" };
	private static final JButton[] BUTTON = new JButton[TEXT.length];
	private boolean clickedOnOperator = false, update = false;
	private double number;
	private int decimalPoint = 0;
	private JLabel display = new JLabel();
	private JPanel[] panel = new JPanel[5];
	private String operator = "";

	public JPanel getCalculator() {
		display = new JLabel("0");
		display.setFont(FONT_1);
		display.setHorizontalAlignment(JLabel.RIGHT);
		display.setPreferredSize(DIM_1);
		panel[0] = new JPanel();
		panel[0].setPreferredSize(DIM_2);
		panel[0].setBackground(COLOR_1);
		panel[0].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, COLOR_2));
		panel[1] = new JPanel();
		panel[1].setPreferredSize(DIM_3);
		panel[2] = new JPanel();
		panel[2].setPreferredSize(DIM_4);
		panel[2].setBackground(COLOR_1);
		panel[3] = new JPanel();
		panel[3].setPreferredSize(DIM_5);
		panel[3].setBackground(COLOR_1);
		panel[4] = new JPanel();
		panel[4].setPreferredSize(DIM_5);
		panel[4].setBackground(COLOR_1);
		for (int i = 0; i < TEXT.length; i++) {
			BUTTON[i] = new JButton(TEXT[i]);
			BUTTON[i].setPreferredSize(DIM_6);
			BUTTON[i].setBackground(COLOR_3);
			BUTTON[i].setFont(FONT_2);
			BUTTON[i].setUI(new Button());
			switch (i) {
			case 0:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 1:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 2:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 3:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 4:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 5:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 6:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 7:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 8:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 9:
				panel[2].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_4);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 10:
				panel[2].add(BUTTON[i]);
				BUTTON[i].addActionListener(new NumberListener());
				break;
			case 11:
				panel[2].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_4);
				BUTTON[i].addActionListener(new ChangeSignListener());
				break;
			case 12:
				panel[3].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_5);
				BUTTON[i].addActionListener(new DivisionListener());
				break;
			case 13:
				panel[3].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_5);
				BUTTON[i].addActionListener(new MultiplicationListener());
				break;
			case 14:
				panel[3].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_5);
				BUTTON[i].addActionListener(new SubtractionListener());
				break;
			case 15:
				panel[3].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_5);
				BUTTON[i].addActionListener(new AdditionListener());
				break;
			case 16:
				panel[4].add(BUTTON[i]);
				BUTTON[i].setBackground(COLOR_4);
				BUTTON[i].addActionListener(new BackListener());
				break;
			case 17:
				panel[4].add(BUTTON[i]);
				BUTTON[i].setForeground(Color.RED);
				BUTTON[i].setBackground(COLOR_4);
				BUTTON[i].addActionListener(new AllClearListener());
				break;
			default:
				panel[4].add(BUTTON[i]);
				BUTTON[i].setPreferredSize(DIM_7);
				BUTTON[i].setBackground(COLOR_4);
				BUTTON[i].addActionListener(new ResultListener());
				break;
			}
		}
		panel[1].add(display);
		panel[1].setBackground(Color.WHITE);
		panel[1].setBorder(BorderFactory.createLoweredBevelBorder());
		panel[0].add(panel[1], BorderLayout.NORTH);
		panel[0].add(panel[2], BorderLayout.CENTER);
		panel[0].add(panel[3], BorderLayout.EAST);
		panel[0].add(panel[4], BorderLayout.EAST);
		return panel[0];
	}

	private void calculate() {
		if (operator.equals("+")) {
			number = number + Double.valueOf(display.getText()).doubleValue();
			display.setText(String.valueOf(number));
		}
		if (operator.equals("-")) {
			number = number - Double.valueOf(display.getText()).doubleValue();
			display.setText(String.valueOf(number));
		}
		if (operator.equals("*")) {
			number = number * Double.valueOf(display.getText()).doubleValue();
			display.setText(String.valueOf(number));
		}
		if (operator.equals("/")) {
			try {
				number = number / Double.valueOf(display.getText()).doubleValue();
				display.setText(String.valueOf(number));
			} catch (ArithmeticException e) {
				display.setText("0");
			}
		}
	}

	private void finalZero() {
		String str = display.getText();
		if (str.endsWith(".0"))
			str = str.substring(0, str.length() - 2);
		display.setText(str);
	}

	class NumberListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton) e.getSource()).getText();
			if (update)
				update = false;
			else {
				if (!display.getText().equals("0"))
					str = display.getText() + str;
			}
			display.setText(str);
		}
		
	}

	class DecimalPointListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			decimalPoint++;
			String str = ((JButton) e.getSource()).getText();
			if (update)
				update = false;
			else {
				if (decimalPoint > 1)
					return;
				else {
					if (!display.getText().equals("0"))
						str = display.getText() + str;
				}
			}
			display.setText(str);
		}
		
	}

	class AdditionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			if (clickedOnOperator) {
				calculate();
				display.setText(String.valueOf(number));
			} else {
				number = Double.valueOf(display.getText()).doubleValue();
				clickedOnOperator = true;
			}
			operator = "+";
			update = true;
			decimalPoint = 0;
		}
		
	}

	class SubtractionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			if (clickedOnOperator) {
				calculate();
				display.setText(String.valueOf(number));
			} else {
				number = Double.valueOf(display.getText()).doubleValue();
				clickedOnOperator = true;
			}
			operator = "-";
			update = true;
			decimalPoint = 0;
		}
		
	}

	class MultiplicationListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			if (clickedOnOperator) {
				calculate();
				display.setText(String.valueOf(number));
			} else {
				number = Double.valueOf(display.getText()).doubleValue();
				clickedOnOperator = true;
			}
			operator = "*";
			update = true;
			decimalPoint = 0;
		}
		
	}

	class DivisionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			if (clickedOnOperator) {
				calculate();
				display.setText(String.valueOf(number));
			} else {
				number = Double.valueOf(display.getText()).doubleValue();
				clickedOnOperator = true;
			}
			operator = "/";
			update = true;
			decimalPoint = 0;
		}
		
	}

	class AllClearListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			clickedOnOperator = false;
			update = true;
			decimalPoint = 0;
			number = 0;
			operator = "";
			display.setText("0");
		}
		
	}

	class BackListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String str = display.getText();
			if (str.length() > 0)
				str = str.substring(0, str.length() - 1);
			if (str.length() == 0)
				str = "0";
			if (!str.contains("."))
				decimalPoint = 0;
			display.setText(str);
		}
		
	}

	class ChangeSignListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String str = display.getText();
			if (str == "0")
				return;
			else {
				if (!str.contains("-"))
					str = "-" + str;
				else
					str = str.substring(1);
			}
			display.setText(str);
		}
		
	}

	class ResultListener implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			calculate();
			finalZero();
			update = true;
			decimalPoint = 0;
			clickedOnOperator = false;
		}
		
	}

	class Button extends BasicButtonUI {
		
		@Override
		public void installUI(JComponent component) {
			super.installUI(component);
			AbstractButton abstractButton = (AbstractButton) component;
			abstractButton.setOpaque(false);
			abstractButton.setBorder(new EmptyBorder(5, 15, 5, 15));
		}

		public void paint(Graphics graphics, JComponent component) {
			AbstractButton abstractButton = (AbstractButton) component;
			paintBackground(graphics, abstractButton, abstractButton.getModel().isPressed() ? 2 : 0);
			super.paint(graphics, component);
		}

		private void paintBackground(Graphics graphics, JComponent component, int yOffset) {
			Dimension size = component.getSize();
			Graphics2D graphics2D = (Graphics2D) graphics;
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setColor(component.getBackground().darker());
			graphics.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
			graphics.setColor(component.getBackground());
			graphics.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
		}
		
	}

	public void start() {
		JFrame frame = new JFrame();
		frame.setTitle(TEXT[19]);
		frame.setSize(DIM_8);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(COLOR_1);
		frame.add(getCalculator());
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Calculator().start();
	}

}
