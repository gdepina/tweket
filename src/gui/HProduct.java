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
import view.ProductView;
import javax.swing.JScrollPane;

public class HProduct {

	JFrame frame;
	private DefaultListModel listModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HProduct window = new HProduct();
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
	public HProduct() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 287, 335);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSeleccioneUnProducto = new JLabel("Seleccione un producto:");
		lblSeleccioneUnProducto.setBounds(25, 18, 232, 16);
		frame.getContentPane().add(lblSeleccioneUnProducto);
		
		
		
		listModel = new DefaultListModel<>();
		for (ProductView pro: Application.getInstancia().getProducts()){
			listModel.addElement(pro.getName());
		}
		
		JButton btnConfirm = new JButton("Aceptar");

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 46, 213, 213);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				Application.getInstancia().currentProd = Application.getInstancia().getProductByName((String) list.getSelectedValue());
				Application.getInstancia().notifyObservables();		
				frame.dispose();
			}
		});
		
		
		list.setBorder(UIManager.getBorder("EditorPane.border"));
		list.setToolTipText("Producto");
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setVisible(true);
	}
}
