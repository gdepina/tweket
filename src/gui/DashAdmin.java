package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashAdmin {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashAdmin window = new DashAdmin();
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
	public DashAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 673, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cambiar de usuario");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login fLogin = new Login();
				fLogin.setLocationRelativeTo(null);
				fLogin.setVisible(true);
			}
		});
		mnOpciones.add(mntmCerrarSesin);
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login fLogin = new Login();
				fLogin.setLocationRelativeTo(null);
				fLogin.setVisible(true);						
			}
		});
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		mnOpciones.add(mntmSalir);
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				System.exit(1);
			}
		});
		
		
		JMenu mnNewMenu = new JMenu("Gestion");
		menuBar.add(mnNewMenu);
	
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Producto");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FProductABM fProduct = new FProductABM();
				fProduct.frmProductos.setLocationRelativeTo(null);
				fProduct.frmProductos.setVisible(true);
							
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FClientABM fClient = new FClientABM();
				fClient.frmClientes.setLocationRelativeTo(null);
				fClient.frmClientes.setVisible(true);
			}
		});
		mnNewMenu.add(mntmClientes);
		
		JMenuItem mntmRoles = new JMenuItem("Usuarios y roles");
		mntmRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FUserABM fUser = new FUserABM();
				fUser.frame.setLocationRelativeTo(null);
				fUser.frame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmRoles);
		frame.getContentPane().setLayout(null);		
		 
	}
}
