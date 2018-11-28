package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Application;
import excepciones.PKDuplicadaException;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FProduct {

	JFrame frmProducto;
	private JTextField txtCode;
	private JTextField txtTitle;
	private JTextField txtPrice;
	String action;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FProduct window = new FProduct();
					window.frmProducto.setVisible(true);
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

	public FProduct(String action) {
		this.action = action;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProducto = new JFrame();
		frmProducto.setTitle("Producto");
		frmProducto.setBounds(100, 100, 368, 262);
		frmProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProducto.getContentPane().setLayout(null);
		
		txtCode = new JTextField();
		txtCode.setBounds(119, 19, 130, 26);
		frmProducto.getContentPane().add(txtCode);		
		txtCode.setColumns(10);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(119, 50, 130, 26);
		frmProducto.getContentPane().add(txtTitle);
		txtTitle.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(119, 82, 130, 26);
		frmProducto.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		JTextArea txtDesc = new JTextArea();
		txtDesc.setLineWrap(true);
		txtDesc.setBounds(119, 120, 233, 52);
		frmProducto.getContentPane().add(txtDesc);
		
		JLabel lblCdigo = new JLabel("Codigo*:");
		lblCdigo.setBounds(26, 24, 61, 16);
		frmProducto.getContentPane().add(lblCdigo);
		
		JLabel lblNombre = new JLabel("Nombre*:");
		lblNombre.setBounds(26, 55, 61, 16);
		frmProducto.getContentPane().add(lblNombre);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(26, 87, 61, 16);
		frmProducto.getContentPane().add(lblPrecio);
		
		JLabel lblDescripcin = new JLabel("Descripcion:");
		lblDescripcin.setBounds(26, 120, 85, 16);
		frmProducto.getContentPane().add(lblDescripcin);
		
		if (Application.getInstancia().currentProd != null && !action.equals("new")) {
			txtTitle.setText(Application.getInstancia().currentProd.getTitle());
			txtDesc.setText(Application.getInstancia().currentProd.getDescription());
			txtPrice.setText(Float.toString(Application.getInstancia().currentProd.getPrice()));
			txtCode.setText(Application.getInstancia().currentProd.getProductCode());		
		}
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if (runValidations()) {
					String title = txtTitle.getText();
					String desc = txtDesc.getText();
					String productCode = txtCode.getText();
					float price = Float.parseFloat(txtPrice.getText());
					if (action.equals("new")) {	
						try {
							Application.getInstancia().saveProduct(productCode, title, desc, price);
							frmProducto.dispose();
						} catch (PKDuplicadaException e1) {
							JOptionPane.showMessageDialog(null,e1.getMessage(),"Error al guardar producto",JOptionPane.ERROR_MESSAGE);							
						}	
					} else {					
						Application.getInstancia().currentProd.setTitle(title);
						Application.getInstancia().currentProd.setDescription(desc);
						Application.getInstancia().currentProd.setPrice(price);
						Application.getInstancia().currentProd.setProductCode(productCode);
						Application.getInstancia().currentProd.update();
						Application.getInstancia().notifyObservables();
						frmProducto.dispose();
					}
					
				}
				
			}
		});
		btnNewButton.setBounds(26, 184, 117, 29);
		frmProducto.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmProducto.dispose();
			}
		});
		btnNewButton_1.setBounds(155, 184, 117, 29);
		frmProducto.getContentPane().add(btnNewButton_1);
	}

	protected boolean runValidations() {		
		if (!txtPrice.getText().matches("[-+]?[0-9]*\\.?[0-9]+")) {		
			JOptionPane.showMessageDialog(null,"Precio invalido, por favor coloque un valor numerico.","Producto",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtCode.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"El código de producto es requerido.","Producto",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (txtTitle.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"El nombre de producto es requerido.","Producto",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	
		return true;
	}

}
