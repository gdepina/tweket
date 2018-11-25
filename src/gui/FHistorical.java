package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.Application;

public class FHistorical {

	JFrame frame;
	private JTable table;
	int ticketNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FHistorical window = new FHistorical();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FHistorical() {
		initialize();
	}

	public FHistorical(int ticketNumber) {
		this.ticketNumber = ticketNumber;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 266);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(Application.getInstancia().getTicketHistorical(ticketNumber));
		scrollPane.setViewportView(table);
	}

}
