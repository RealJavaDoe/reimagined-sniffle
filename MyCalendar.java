import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Jean-Pierre PEIFFER
 * @edition 2019
 * @version 1.0
 * 
 *          This works fine with Windows 10 and Java 10 or GNU/Linux (Ubuntu
 *          18.10) and Java 8
 * 
 */

public class MyCalendar {

	private static final Color COLOR = new Color(245, 245, 245);
	private static final Dimension DIM_1 = new Dimension(300, 225);
	private static final Dimension DIM_2 = new Dimension(300, 345);
	private static final Font FONT = new Font("Arial", Font.PLAIN, 14);
	private static final String[] DAY = { "Mo", "Tu", "We", "Th", "Fr", "Sa", "Su" };
	private static final String[] TEXT_BUTTON = { "\u22b2 Prev.", "Now", "Next \u22b3" };
	private static final String[] TEXT_MONTH = { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	private static final Calendar CALENDAR = Calendar.getInstance();
	private static final int CURRENT_DAY = CALENDAR.get(Calendar.DAY_OF_MONTH);
	private static final int CURRENT_MONTH = CALENDAR.get(Calendar.MONTH);
	private static final int CURRENT_YEAR = CALENDAR.get(Calendar.YEAR);
	private static final int ROW_HEIGHT = 30;
	private static final int TABLE_WIDTH = 270;

	private JButton[] button = new JButton[3];
	private JFrame frame;
	private JLabel label;
	private JPanel container = new JPanel();
	private JPanel[] panel = new JPanel[2];

	private int year = CURRENT_YEAR;
	private int month = CURRENT_MONTH;

	public int getNumberOfDays(int month, int year) {
		int numberOfDays;
		switch (month) {
		case 1:
			if (year % 4 == 0 && year % 100 != 0)
				numberOfDays = 29;
			else
				numberOfDays = 28;
			break;
		case 3:
		case 5:
		case 8:
		case 10:
			numberOfDays = 30;
			break;
		default:
			numberOfDays = 31;
			break;
		}
		return numberOfDays;
	}

	public int getFisrtDayOfTheMonth(int month, int year) {
		month++;
		int firstDayOfTheMonth;
		if (month >= 3)
			firstDayOfTheMonth = ((23 * month) / 9 + (1 + 4) + year + (year / 4) - (year / 100) + (year / 400) - 2) % 7;
		else
			firstDayOfTheMonth = ((23 * month) / 9 + (1 + 4) + year + ((year - 1) / 4) - ((year - 1) / 100)
					+ ((year - 1) / 400)) % 7;
		if (firstDayOfTheMonth == 0)
			firstDayOfTheMonth = 7;
		return firstDayOfTheMonth;
	}

	public boolean isCurrentDate(int day, int month, int year) {
		boolean test = false;
		if (day == CURRENT_DAY && month == CURRENT_MONTH && year == CURRENT_YEAR)
			test = true;
		return test;
	}

	public JPanel getCalendar(int month, int year) {
		String[] header = { DAY[0], DAY[1], DAY[2], DAY[3], DAY[4], DAY[5], DAY[6] };
		String[][] data = new String[6][7];
		int day = 1;
		int numberOfDays = getNumberOfDays(month, year);
		int firstDayOfTheMonth = getFisrtDayOfTheMonth(month, year);
		for (int i = 0; i <= 5; i++) {
			if (i == 0) {
				for (int j = (firstDayOfTheMonth - 1); j <= 6; j++) {
					if (isCurrentDate(day, month, year))
						data[i][j] = String.valueOf("*" + day + "*");
					else
						data[i][j] = String.valueOf(day);
					day++;
				}
			} else {
				for (int j = 0; j <= 6; j++) {
					if (day <= numberOfDays) {
						if (isCurrentDate(day, month, year))
							data[i][j] = String.valueOf("(" + day + ")");
						else
							data[i][j] = String.valueOf(day);
						day++;
					}
				}
			}
		}
		@SuppressWarnings("serial")
		JTable table = new JTable(data, header) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setRowHeight(ROW_HEIGHT);
		table.getTableHeader().setPreferredSize(new Dimension(TABLE_WIDTH, ROW_HEIGHT));
		table.getTableHeader().setReorderingAllowed(false);
		table.setPreferredSize(new Dimension(TABLE_WIDTH, table.getRowHeight() * table.getRowCount()));
		table.setCellSelectionEnabled(false);
		table.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Table.gridColor")));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
		tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(tableCellRenderer);
		}
		JPanel panel = new JPanel();
		panel.setPreferredSize(DIM_1);
		panel.setBackground(COLOR);
		panel.add(table.getTableHeader());
		panel.add(table);
		return panel;
	}

	public JPanel getDisplay(int month, int year) {
		container.setBackground(COLOR);
		panel[0] = new JPanel();
		panel[0].setBackground(COLOR);
		label = new JLabel(TEXT_MONTH[month] + " " + year);
		label.setFont(FONT);
		panel[0].add(label);
		panel[1] = new JPanel();
		panel[1].setBackground(COLOR);
		button[0] = new JButton(TEXT_BUTTON[0]);
		button[0].addActionListener(new Previous());
		button[1] = new JButton(TEXT_BUTTON[1]);
		button[1].addActionListener(new CurrentMonth());
		button[2] = new JButton(TEXT_BUTTON[2]);
		button[2].addActionListener(new Next());
		panel[1].add(button[0]);
		panel[1].add(button[1]);
		panel[1].add(button[2]);
		Box box = Box.createVerticalBox();
		box.add(panel[0]);
		box.add(getCalendar(month, year));
		box.add(panel[1]);
		container.add(box);
		return container;
	}

	class Previous implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (year >= 1806) {
				if (month > 0)
					month--;
				else {
					if (year == 1806) {
						month = 0;
						year = 1806;
					} else {
						month = 11;
						year--;
					}
				}
				container.removeAll();
				frame.add(getDisplay(month, year));
				frame.validate();
			} else
				return;
		}

	};

	class CurrentMonth implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			container.removeAll();
			month = CURRENT_MONTH;
			year = CURRENT_YEAR;
			frame.add(getDisplay(month, year));
			frame.validate();
		}

	}

	class Next implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (year < 2100) {
				if (month < 11)
					month++;
				else {
					month = 0;
					year++;
				}
				container.removeAll();
				frame.add(getDisplay(month, year));
				frame.validate();
			} else
				return;
		}

	};

	public void start(int month, int year) {
		frame = new JFrame();
		frame.setTitle("Calendar");
		frame.setSize(DIM_2);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBackground(COLOR);
		frame.add(getDisplay(month, year));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new MyCalendar().start(CURRENT_MONTH, CURRENT_YEAR);
	}

}
