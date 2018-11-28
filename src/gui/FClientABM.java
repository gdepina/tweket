package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import controller.Application;
import excepciones.FKException;
import observer.Observable;
import view.ClientView;
import view.ProductView;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class FClientABM implements Observable {

	JFrame frmClientes;
	JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FClientABM window = new FClientABM();
					window.frmClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FClientABM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Application.getInstancia().register(this);
		frmClientes = new JFrame();
		frmClientes.setTitle("Clientes");
		frmClientes.setBounds(100, 100, 359, 261);
		frmClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmClientes.getContentPane().setLayout(null);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FClient fClient = new FClient("new");
				fClient.frmCliente.setLocationRelativeTo(null);
				fClient.frmCliente.setVisible(true);
			}
		});
		btnNuevo.setBounds(17, 16, 117, 29);
		frmClientes.getContentPane().add(btnNuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentCli != null) {
				FClient fClient = new FClient("modify");
				fClient.frmCliente.setLocationRelativeTo(null);
				fClient.frmCliente.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null,"Seleccione un cliente para modificar","Producto",JOptionPane.INFORMATION_MESSAGE);
			}
			}
		});
		btnModificar.setBounds(17, 56, 117, 29);
		frmClientes.getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentCli != null) {
					try {
						Application.getInstancia().currentCli.remove();
					} catch (FKException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Error al eliminar cliente",JOptionPane.ERROR_MESSAGE);
					}
					Application.getInstancia().notifyObservables();
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un cliente para eliminarlo","Cliente",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(17, 97, 117, 29);
		frmClientes.getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 16, 189, 204);
		frmClientes.getContentPane().add(scrollPane);
		
		list = new JList(buildList());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Application.getInstancia().getClientByName((String) list.getSelectedValue());
			}
		});
	}
	
	private DefaultListModel buildList() {
		DefaultListModel listModel = new DefaultListModel<>();
		for (ClientView cli: Application.getInstancia().getClients()){
			listModel.addElement(cli.getName());
		}
		return listModel;
	}

	@Override
	public void update() {
		list.setModel(buildList());		
	}

}
