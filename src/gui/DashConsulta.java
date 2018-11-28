package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import controller.Application;
import observer.Observable;

public class DashConsulta implements Observable {

	JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashConsulta window = new DashConsulta();
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
	public DashConsulta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Application.getInstancia().register(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 795, 717);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cambiar de usuario");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login fLogin = new Login();
				fLogin.setLocationRelativeTo(null);
				fLogin.setVisible(true);				
			}
		});
		mnNewMenu.add(mntmCerrarSesin);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		mnNewMenu.add(mntmSalir);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Reclamos por mes");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel.setBounds(19, 19, 165, 22);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMes = new JLabel("Mes:");
		lblMes.setBounds(19, 67, 61, 16);
		frame.getContentPane().add(lblMes);
		
		JLabel lblQty = new JLabel("");
		lblQty.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblQty.setBounds(92, 94, 92, 16);
		frame.getContentPane().add(lblQty);
		
		String[] comboModel = new String[] { "Seleccionar","1", "2", "3", "4", "5","6","7","8","9","10","11","12"};
		
		JComboBox comboBox = new JComboBox(comboModel);
		comboBox.setBounds(80, 63, 135, 27);
		frame.getContentPane().add(comboBox);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblQty.setText(Integer.toString(Application.getInstancia().getTicketsByMonth(Integer.parseInt(comboBox.getSelectedItem().toString()))));				
			}
		});
		btnMostrar.setBounds(212, 62, 117, 29);
		frame.getContentPane().add(btnMostrar);
		
		JLabel lblNewLabel_1 = new JLabel("Cantidad:");
		lblNewLabel_1.setBounds(19, 95, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(19, 154, 772, 16);
		frame.getContentPane().add(separator);
		
		JLabel lblRankingDeTratamientos = new JLabel("Ranking de tratamientos");
		lblRankingDeTratamientos.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblRankingDeTratamientos.setBounds(19, 177, 258, 22);
		frame.getContentPane().add(lblRankingDeTratamientos);
		
		JLabel lblReclamosPorCliente = new JLabel("Reclamos por cliente");
		lblReclamosPorCliente.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblReclamosPorCliente.setBounds(421, 182, 258, 22);
		frame.getContentPane().add(lblReclamosPorCliente);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(399, 169, 10, 225);
		frame.getContentPane().add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 211, 370, 181);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable(Application.getInstancia().getTicketRankLogs());
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(421, 211, 342, 181);
		frame.getContentPane().add(scrollPane_1);
		
		table_1 = new JTable(Application.getInstancia().getTicketRankByClients());
		table_1.setEnabled(false);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setRowSelectionAllowed(false);
		scrollPane_1.setViewportView(table_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(19, 404, 772, 16);
		frame.getContentPane().add(separator_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(19, 455, 370, 181);
		frame.getContentPane().add(scrollPane_2);
		
		table_2 = new JTable(Application.getInstancia().getAvgResponseTime());
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblTiempoDeResputa = new JLabel("Tiempo de respuesta promedio en dias");
		lblTiempoDeResputa.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblTiempoDeResputa.setBounds(19, 421, 370, 22);
		frame.getContentPane().add(lblTiempoDeResputa);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(399, 421, 10, 225);
		frame.getContentPane().add(separator_3);
		

	}

	@Override
	public void update() {
		table.setModel(Application.getInstancia().getTicketRankLogs());
		table_1.setModel(Application.getInstancia().getTicketRankByClients());
		table_2.setModel(Application.getInstancia().getAvgResponseTime());
	}
}
