package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Application;
import excepciones.FKException;
import observer.Observable;
import view.ProductView;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FProductABM  implements Observable {

	JFrame frmProductos;
	JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FProductABM window = new FProductABM();
					window.frmProductos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FProductABM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Application.getInstancia().register(this);
		frmProductos = new JFrame();
		frmProductos.setTitle("Productos");
		frmProductos.setBounds(100, 100, 321, 264);
		frmProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProductos.getContentPane().setLayout(null);
		
	
		
		list = new JList(buildList());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Application.getInstancia().getProductByName((String) list.getSelectedValue());											
			}
		});
		
		list.setBounds(135, 22, 161, 194);
		frmProductos.getContentPane().add(list);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FProduct fProd = new FProduct("new");
				fProd.frmProducto.setLocationRelativeTo(null);
				fProd.frmProducto.setVisible(true);
			}
		});
		btnNuevo.setBounds(6, 17, 117, 29);
		frmProductos.getContentPane().add(btnNuevo);
		
		JButton btnGuardar = new JButton("Modificar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentProd != null) {
					FProduct fProd = new FProduct("modify");
					fProd.frmProducto.setLocationRelativeTo(null);
					fProd.frmProducto.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un producto para modificar","Producto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(6, 58, 117, 29);
		frmProductos.getContentPane().add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentProd != null) {
					try {
						Application.getInstancia().currentProd.remove();
					} catch (FKException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Error al eliminar producto",JOptionPane.ERROR_MESSAGE);						
					}
					Application.getInstancia().notifyObservables();
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un producto para eliminarlo","Producto",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(6, 99, 117, 29);
		frmProductos.getContentPane().add(btnEliminar);
	}

	private DefaultListModel buildList() {
		DefaultListModel listModel = new DefaultListModel<>();
		for (ProductView pro: Application.getInstancia().getProducts()){
			listModel.addElement(pro.getName());
		}
		return listModel;
	}

	@Override
	public void update() {		
		list.setModel(buildList());
	}
}
