package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Application;
import observer.Observable;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JMenuItem;

public class DashCallCenter extends JFrame implements Observable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField txtProduct;
	private JTextField txtProductTitle;
	private JTextField txtClientTitle;
	private JTextField txtZoneName;
	private JTextField txtAddress;
	private JTextField txtMail;
	private JTextField txtTel;
	private JComboBox cbTicket1;
	private JComboBox cbTicket2;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JTextField txtCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashCallCenter window = new DashCallCenter();
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
	public DashCallCenter() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Application.getInstancia().register(this);
		frame = new JFrame();
		frame.setTitle("Tablero Call Center");
		frame.setResizable(false);
		frame.setBounds(100, 100, 782, 464);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 776, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnOptions = new JMenu("Opciones");
		menuBar.add(mnOptions);
		
		JMenuItem mnClose = new JMenuItem("Cerrar sesión");		
		mnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login fLogin = new Login();
				fLogin.setLocationRelativeTo(null);
				fLogin.setVisible(true);						
			}
		});
		mnOptions.add(mnClose);
		
		JMenuItem mnSalir = new JMenuItem("Salir");
		mnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				System.exit(1);
			}
		});
		mnOptions.add(mnSalir);
		
		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(163, 233, 172, 26);		
		frame.getContentPane().add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(30, 238, 61, 16);
		frame.getContentPane().add(lblCantidad);		
		
		JRadioButton rbSimple = new JRadioButton("Simple");

		rbSimple.setBounds(30, 34, 141, 23);
		frame.getContentPane().add(rbSimple);
		
		JRadioButton rdbtnCompuesto = new JRadioButton("Compuesto");
		
		rdbtnCompuesto.setBounds(163, 34, 141, 23);
		frame.getContentPane().add(rdbtnCompuesto);
		
		String[] comboModel = new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega"};
		
		cbTicket1 = new JComboBox(comboModel);
		cbTicket1.setBounds(163, 69, 172, 27);
		frame.getContentPane().add(cbTicket1);
		
		JLabel lblTipoDeReclamo = new JLabel("Tipo de reclamo:");
		lblTipoDeReclamo.setBounds(30, 73, 105, 16);
		frame.getContentPane().add(lblTipoDeReclamo);
		
		cbTicket2 = new JComboBox(comboModel);
		cbTicket2.setBounds(163, 104, 172, 27);
		frame.getContentPane().add(cbTicket2);
		
		JLabel label = new JLabel("Tipo de reclamo:");
		label.setBounds(30, 108, 105, 16);
		frame.getContentPane().add(label);
		
		comboBox = new JComboBox(comboModel);
		comboBox.setBounds(163, 137, 172, 27);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox(comboModel);
		comboBox_1.setBounds(163, 170, 172, 27);
		frame.getContentPane().add(comboBox_1);
		
		comboBox_2 = new JComboBox(comboModel);
		comboBox_2.setBounds(163, 202, 172, 27);
		frame.getContentPane().add(comboBox_2);
		
		rdbtnCompuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					rbSimple.setSelected(false);		
					cbTicket1.setEnabled(true);
					cbTicket2.setEnabled(true);
					comboBox.setEnabled(true);
					comboBox_1.setEnabled(true);
					comboBox_2.setEnabled(true);
			}
		});
		
		rbSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCompuesto.setSelected(false);		
				cbTicket1.setEnabled(true);
				cbTicket2.setEnabled(false);
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
			}
		});
		
		JLabel label_1 = new JLabel("Tipo de reclamo:");
		label_1.setBounds(30, 141, 105, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Tipo de reclamo:");
		label_2.setBounds(30, 174, 105, 16);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Tipo de reclamo:");
		label_3.setBounds(30, 206, 105, 16);
		frame.getContentPane().add(label_3);
		
		JTextArea txtAreaDesc = new JTextArea();
		txtAreaDesc.setLineWrap(true);
		txtAreaDesc.setWrapStyleWord(true);
		txtAreaDesc.setBounds(30, 313, 727, 71);
		frame.getContentPane().add(txtAreaDesc);
		
		JLabel lblInformacinAdicional = new JLabel("Descripción e Información adicional:");
		lblInformacinAdicional.setBounds(30, 285, 305, 16);
		frame.getContentPane().add(lblInformacinAdicional);
		
		JLabel lblNewLabel = new JLabel("Producto:");
		lblNewLabel.setBounds(363, 73, 68, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(363, 108, 68, 16);
		frame.getContentPane().add(lblCliente);
		
		txtProduct = new JTextField();
		txtProduct.setEditable(false);
		txtProduct.setBounds(436, 68, 40, 26);
		frame.getContentPane().add(txtProduct);
		txtProduct.setColumns(10);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HProduct fProduct = new HProduct();
				fProduct.frame.setLocationRelativeTo(null);
				fProduct.frame.setVisible(true);
			}
		});
		button.setBounds(597, 68, 40, 28);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("+");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FClient fClient = new FClient();
				fClient.frame.setLocationRelativeTo(null);
				fClient.frame.setVisible(true);
			}
		});
		button_1.setBounds(597, 103, 40, 29);
		frame.getContentPane().add(button_1);
		
		JButton btnGuardar = new JButton("Guardar");	
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <String> tipoReclamos= new ArrayList <String>();
				int clientId = Application.getInstancia().currentCli.getId();
				String productCode = Application.getInstancia().currentProd.getProductCode();
				int qty = txtCantidad.getText().equals("") ? 0 : Integer.parseInt(txtCantidad.getText());
				
				if (rbSimple.isSelected()) {
					tipoReclamos.add(cbTicket1.getSelectedItem().toString());							
					Application.getInstancia().saveTicket(tipoReclamos,clientId, txtAreaDesc.getText(), productCode, qty);
					

				} else if (rdbtnCompuesto.isSelected()){
					if (cbTicket1.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0 ){
						tipoReclamos.add(cbTicket1.getSelectedItem().toString());
					} 
					if (cbTicket2.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0){
						tipoReclamos.add(cbTicket2.getSelectedItem().toString());
					} 
					if (comboBox.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0){
						tipoReclamos.add(comboBox.getSelectedItem().toString());
					} 
					if (comboBox_1.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") != 0){
						tipoReclamos.add(comboBox_1.getSelectedItem().toString());
					} 
					if (comboBox_2.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0){
						tipoReclamos.add(comboBox_2.getSelectedItem().toString());
					}
					Application.getInstancia().saveTicket(tipoReclamos, clientId, txtAreaDesc.getText(), productCode, qty);					
				}
				this.cleanForm();
				
				
			}

			private void cleanForm() {
				txtProduct.setText("");
				txtProductTitle.setText("");
				txtClientTitle.setText("");
				txtZoneName.setText("");
				txtAddress.setText("");
				txtMail.setText("");
				txtTel.setText("");
				cbTicket1.setSelectedIndex(0);
				cbTicket2.setSelectedIndex(0);
				comboBox.setSelectedIndex(0);
				comboBox_1.setSelectedIndex(0);
				comboBox_2.setSelectedIndex(0);
				txtAreaDesc.setText("");
				txtCantidad.setText("");
				Application.getInstancia().currentCli = null;
				Application.getInstancia().currentProd = null;
				
			}
		});
		btnGuardar.setBounds(30, 396, 117, 29);
		frame.getContentPane().add(btnGuardar);
		
		txtProductTitle = new JTextField();
		txtProductTitle.setEditable(false);
		txtProductTitle.setColumns(10);
		txtProductTitle.setBounds(471, 68, 130, 26);
		frame.getContentPane().add(txtProductTitle);
		
		txtClientTitle = new JTextField();
		txtClientTitle.setEditable(false);
		txtClientTitle.setColumns(10);
		txtClientTitle.setBounds(436, 103, 165, 26);
		frame.getContentPane().add(txtClientTitle);
		
		JLabel lblZona = new JLabel("Zona:");
		lblZona.setBounds(363, 141, 68, 16);
		frame.getContentPane().add(lblZona);
		
		txtZoneName = new JTextField();
		txtZoneName.setEditable(false);
		txtZoneName.setColumns(10);
		txtZoneName.setBounds(436, 136, 165, 26);
		frame.getContentPane().add(txtZoneName);
		
		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setBounds(363, 174, 68, 16);
		frame.getContentPane().add(lblDireccin);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setColumns(10);
		txtAddress.setBounds(436, 169, 165, 26);
		frame.getContentPane().add(txtAddress);
		
		JLabel lblContacto = new JLabel("Contacto:");
		lblContacto.setBounds(363, 206, 68, 16);
		frame.getContentPane().add(lblContacto);
		
		txtMail = new JTextField();
		txtMail.setEditable(false);
		txtMail.setColumns(10);
		txtMail.setBounds(436, 201, 165, 26);
		frame.getContentPane().add(txtMail);
		
		txtTel = new JTextField();
		txtTel.setEditable(false);
		txtTel.setColumns(10);
		txtTel.setBounds(436, 233, 165, 26);
		frame.getContentPane().add(txtTel);
		
		JLabel lblNewLabel_1 = new JLabel("Tel:");
		lblNewLabel_1.setBounds(363, 238, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(336, 34, 12, 267);
		frame.getContentPane().add(separator);
		
	}
	

	@Override
	public void update() {
		if (Application.getInstancia().currentProd !=  null) {
		txtProduct.setText(Application.getInstancia().currentProd.getProductCode());
		txtProductTitle.setText(Application.getInstancia().currentProd.getTitle());
		}
		if (Application.getInstancia().currentCli !=  null) { 
			txtClientTitle.setText(Application.getInstancia().currentCli.getName());
			txtZoneName.setText(Application.getInstancia().currentCli.getZone().getName());
			txtAddress.setText(Application.getInstancia().currentCli.getHomeAddress());
			txtMail.setText(Application.getInstancia().currentCli.getMail());
			txtTel.setText(Application.getInstancia().currentCli.getPhone());
		}
		
	}
}
