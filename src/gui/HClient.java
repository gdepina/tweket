package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import controller.Application;
import view.ClientView;

public class HClient {

	JFrame frame;
	private DefaultListModel listModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HClient window = new HClient();
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
	public HClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 287, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSeleccioneUnCliente = new JLabel("Seleccione un cliente:");
		lblSeleccioneUnCliente.setBounds(25, 18, 232, 16);
		frame.getContentPane().add(lblSeleccioneUnCliente);
		
		
		
		
		listModel = new DefaultListModel<>();
		for (ClientView cli: Application.getInstancia().getClients()){
			listModel.addElement(cli.getName());
		}
		
		JList list = new JList(listModel);
		
		
		list.setBorder(UIManager.getBorder("EditorPane.border"));
		list.setToolTipText("Clientes");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(25, 46, 213, 213);
		list.setVisible(true);	
		
		frame.getContentPane().add(list);
		
		JButton btnConfirm = new JButton("Aceptar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Application.getInstancia().currentCli = Application.getInstancia().getClientByName((String) list.getSelectedValue());
				Application.getInstancia().notifyObservables();		
				frame.dispose();
			}
		});
		btnConfirm.setBounds(23, 272, 117, 29);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancelar.setBounds(136, 271, 117, 29);
		frame.getContentPane().add(btnCancelar);
	}
}
