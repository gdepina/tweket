package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import controller.Application;
import view.ProductView;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.AbstractListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class FProduct {

	JFrame frame;
	private DefaultListModel listModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FProduct window = new FProduct();
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
	public FProduct() {
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
		
		JLabel lblSeleccioneUnProducto = new JLabel("Seleccione un producto:");
		lblSeleccioneUnProducto.setBounds(25, 18, 232, 16);
		frame.getContentPane().add(lblSeleccioneUnProducto);
		
		
		
		listModel = new DefaultListModel<>();
		for (ProductView pro: Application.getInstancia().getProducts()){
			listModel.addElement(pro.getName());
		}
		
		JList list = new JList(listModel);
		
		
		list.setBorder(UIManager.getBorder("EditorPane.border"));
		list.setToolTipText("Producto");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(25, 46, 213, 213);
		list.setVisible(true);	
		
		frame.getContentPane().add(list);
		
		JButton btnConfirm = new JButton("Aceptar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Application.getInstancia().currentProd = Application.getInstancia().getProductByName((String) list.getSelectedValue());
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
