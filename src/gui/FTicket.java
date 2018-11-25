package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Application;
import observer.Observable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FTicket implements Observable {

	JFrame frame;
	private JTextField txtProductName;
	private JTextField txtClient;
	private JTextField txtProductCode;
	private JTextField txtZone;
	private JTextField txtAddress;
	private JTextField txtContact;
	private JTextField txtCantidad;
	private JTextField txtBillId;
	JComboBox cbTypes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FTicket window = new FTicket();
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
	public FTicket() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Application.getInstancia().register(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 423, 475);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtProductName = new JTextField();
		txtProductName.setEditable(false);
		txtProductName.setBounds(206, 41, 130, 26);
		frame.getContentPane().add(txtProductName);
		txtProductName.setColumns(10);
		
		txtClient = new JTextField();
		txtClient.setEditable(false);
		txtClient.setBounds(160, 79, 176, 26);
		frame.getContentPane().add(txtClient);
		txtClient.setColumns(10);
		
		txtProductCode = new JTextField();
		txtProductCode.setEditable(false);
		txtProductCode.setBounds(160, 41, 44, 26);
		frame.getContentPane().add(txtProductCode);
		txtProductCode.setColumns(10);
		
		txtZone = new JTextField();
		txtZone.setEditable(false);
		txtZone.setBounds(160, 117, 176, 26);
		frame.getContentPane().add(txtZone);
		txtZone.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setBounds(160, 155, 176, 26);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtContact = new JTextField();
		txtContact.setEditable(false);
		txtContact.setColumns(10);
		txtContact.setBounds(160, 193, 176, 26);
		frame.getContentPane().add(txtContact);
		
		JLabel lblselec = new JLabel("Tipo de reclamo:");
		lblselec.setBounds(45, 10, 118, 16);
		frame.getContentPane().add(lblselec);
		
		txtBillId = new JTextField();
		txtBillId.setColumns(10);
		txtBillId.setBounds(160, 231, 176, 26);
		frame.getContentPane().add(txtBillId);
		
		String[] comboModel = new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega"};
		cbTypes = new JComboBox(comboModel);
		
	
				

		cbTypes.setBounds(160, 6, 176, 27);
		frame.getContentPane().add(cbTypes);
		
		JLabel lblProduct = new JLabel("Producto:");
		lblProduct.setBounds(45, 46, 118, 16);
		frame.getContentPane().add(lblProduct);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(45, 84, 118, 16);
		frame.getContentPane().add(lblCliente);
		
		JLabel lblZona = new JLabel("Zona:");
		lblZona.setBounds(45, 122, 118, 16);
		frame.getContentPane().add(lblZona);
		
		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setBounds(45, 160, 118, 16);
		frame.getContentPane().add(lblDireccin);
		
		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setBounds(45, 198, 118, 16);
		frame.getContentPane().add(lblContacto);
		
		JButton btnSelectProduct = new JButton("+");
		btnSelectProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HProduct fProduct = new HProduct();
				fProduct.frame.setLocationRelativeTo(null);
				fProduct.frame.setVisible(true);
			}
		});
		btnSelectProduct.setBounds(334, 41, 40, 29);
		frame.getContentPane().add(btnSelectProduct);
		
		JLabel lblFactura = new JLabel("Factura #:");
		lblFactura.setBounds(45, 236, 118, 16);
		frame.getContentPane().add(lblFactura);
		
		JLabel label = new JLabel("Descripción e Información adicional:");
		label.setBounds(45, 278, 305, 16);
		frame.getContentPane().add(label);
		
		JTextArea txtDesc = new JTextArea();
		txtDesc.setWrapStyleWord(true);
		txtDesc.setLineWrap(true);
		txtDesc.setBounds(45, 306, 342, 98);
		frame.getContentPane().add(txtDesc);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (runValidations()) {
					int clientId = Application.getInstancia().currentCli.getId();
					String productCode = Application.getInstancia().currentProd.getProductCode();
					int qty = txtCantidad.getText().equals("") ? 0 : Integer.parseInt(txtCantidad.getText());
					String billId = txtBillId.getText();
					Application.getInstancia().addTicket(cbTypes.getSelectedItem().toString(), clientId, txtDesc.getText(), productCode, qty, billId);
					frame.dispose();
				}
			}
		});
		btnAgregar.setBounds(45, 416, 117, 29);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(269, 416, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		
		lblFactura.setVisible(false);
		txtBillId.setVisible(false);
		lblCantidad.setVisible(false);
		
		
		lblCantidad.setBounds(45, 236, 118, 16);
		frame.getContentPane().add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(160, 231, 176, 26);
		frame.getContentPane().add(txtCantidad);
		txtCantidad.setVisible(false);
		cbTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFactura.setVisible(false);
				txtBillId.setVisible(false);
				lblCantidad.setVisible(false);
				txtCantidad.setVisible(false);
				
				if (cbTypes.getSelectedItem().toString().equals("Facturacion")) {
					
					lblFactura.setVisible(true);
					txtBillId.setVisible(true);
				}
				
				if (cbTypes.getSelectedItem().toString().equals("Faltante") || cbTypes.getSelectedItem().toString().equals("Cantidad")) {
					lblCantidad.setVisible(true);
					txtCantidad.setVisible(true);
				}	
			}
		});
		if (Application.getInstancia().currentCli !=  null) { 
			txtClient.setText(Application.getInstancia().currentCli.getName());
			txtZone.setText(Application.getInstancia().currentCli.getZone().getName());
			txtAddress.setText(Application.getInstancia().currentCli.getHomeAddress());
			txtContact.setText(Application.getInstancia().currentCli.getMail());			
		}
	}
	
	protected boolean runValidations() {		
		if (cbTypes.getSelectedItem().toString().equals("Seleccionar")){		
			JOptionPane.showMessageDialog(null,"Seleccione un tipo de reclamo.","Reclamo",JOptionPane.WARNING_MESSAGE);
			return false;
		} else if (Application.getInstancia().checkTicketTypeExist(cbTypes.getSelectedItem().toString())) {
			JOptionPane.showMessageDialog(null,"El tipo de reclamo:"+cbTypes.getSelectedItem().toString()+" ya esta presente entre los reclamos asignados","Reclamo",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtProductCode.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"Seleccione un producto.","Producto",JOptionPane.WARNING_MESSAGE);
			return false;
		}	
	
		return true;
	}


	@Override
	public void update() {
		if (Application.getInstancia().currentProd !=  null) {
			txtProductCode.setText(Application.getInstancia().currentProd.getProductCode());
			txtProductName.setText(Application.getInstancia().currentProd.getTitle());
			}
		
	}
}
