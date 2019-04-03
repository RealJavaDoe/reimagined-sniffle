import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

import java.util.Date;

/**
 * @author Jean-Pierre PEIFFER
 * @edition 2019
 * @version 1.00
 * 
 *          This works fine with Windows 10 and Java 10 & GNU/Linux (Ubuntu
 *          18.10) and Java 8
 *
 */

public class Clock {

	private static final Color COLOR = new Color(245, 245, 245);
	private static final Dimension DIM = new Dimension(300, 140);
	private static final Font FONT = new Font("Arial", Font.PLAIN, 60);
	private static final String NAME = "Clock";
	private JLabel clock;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

	public JPanel getClock() {
		clock = new JLabel(simpleDateFormat.format(new Date()));
		clock.setFont(FONT);
		Timer timer = new Timer(1000, new ClockListener());
		timer.start();
		JPanel panel = new JPanel();
		panel.removeAll();
		panel.add(clock);
		panel.revalidate();
		return panel;
	}

	class ClockListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			clock.setText(simpleDateFormat.format(new Date()));
		}

	}

	public void start() {
		JFrame frame = new JFrame();
		frame.setTitle(NAME);
		frame.setSize(DIM);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(COLOR);
		frame.add(getClock());
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Clock().start();
	}

}
