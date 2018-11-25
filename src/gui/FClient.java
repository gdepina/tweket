package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Application;
import excepciones.PKDuplicadaException;
import modelo.ZoneLocation;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FClient {

	JFrame frmCliente;
	private JTextField txtName;
	private JTextField txtDni;
	private JTextField txtAddress;
	private JTextField txtPhone;
	private JTextField txtMail;
	String action;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FClient window = new FClient();
					window.frmCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FClient() {
		initialize();
	}

	public FClient(String action) {
		this.action = action;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCliente = new JFrame();
		frmCliente.setTitle("Cliente");
		frmCliente.setBounds(100, 100, 279, 330);
		frmCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCliente.getContentPane().setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(100, 178, 162, 26);
		frmCliente.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setBounds(100, 42, 162, 26);
		frmCliente.getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(100, 75, 162, 26);
		frmCliente.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(100, 108, 162, 26);
		frmCliente.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		txtMail = new JTextField();
		txtMail.setBounds(100, 141, 162, 26);
		frmCliente.getContentPane().add(txtMail);
		txtMail.setColumns(10);
		
		JComboBox cbZone = new JComboBox();
		cbZone.setBounds(100, 216, 162, 27);
		frmCliente.getContentPane().add(cbZone);
		
		JLabel lblNombre = new JLabel("Nombre*:");
		lblNombre.setBounds(27, 183, 61, 16);
		frmCliente.getContentPane().add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("DNI*:");
		lblNewLabel.setBounds(27, 47, 61, 16);
		frmCliente.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dirección*:");
		lblNewLabel_1.setBounds(27, 80, 83, 16);
		frmCliente.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tel.*:");
		lblNewLabel_2.setBounds(27, 113, 61, 16);
		frmCliente.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Mail*:");
		lblNewLabel_3.setBounds(27, 146, 61, 16);
		frmCliente.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Zona:");
		lblNewLabel_4.setBounds(27, 220, 61, 16);
		frmCliente.getContentPane().add(lblNewLabel_4);
		
		for (ZoneLocation zl : Application.getInstancia().getZones()) {
			cbZone.addItem(zl.getName());
		}
		

		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (runValidations()) {
					String dni = txtDni.getText();
					String address = txtAddress.getText();
					String phone = txtPhone.getText();
					String mail = txtMail.getText();
					String name = txtName.getText();
					String zone = cbZone.getSelectedItem().toString();
					if (action.equals("new")) {	
						try {
							Application.getInstancia().saveClient(name, dni, address, phone, mail, zone);
							frmCliente.dispose();
						} catch (PKDuplicadaException e1) {
							JOptionPane.showMessageDialog(null,e1.getMessage(),"Error al guardar cliente",JOptionPane.ERROR_MESSAGE);							
						}	
					} else {					
						Application.getInstancia().currentCli.setDni(dni);
						Application.getInstancia().currentCli.setHomeAddress(address);
						Application.getInstancia().currentCli.setPhone(phone);
						Application.getInstancia().currentCli.setMail(mail);
						Application.getInstancia().currentCli.setName(name);
						Application.getInstancia().currentCli.setZone(Application.getInstancia().getZoneByName(zone));
						Application.getInstancia().currentCli.update();
						Application.getInstancia().notifyObservables();
						frmCliente.dispose();
					}
					
				}
			}
		});
		btnAceptar.setBounds(6, 261, 117, 29);
		frmCliente.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCliente.dispose();
			}
		});
		btnCancelar.setBounds(156, 261, 117, 29);
		frmCliente.getContentPane().add(btnCancelar);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setBounds(100, 6, 83, 26);
		frmCliente.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		if (Application.getInstancia().currentCli != null && !action.equals("new")) {
			txtId.setText(Integer.toString(Application.getInstancia().currentCli.getId()));
			txtDni.setText(Application.getInstancia().currentCli.getDni());
			txtAddress.setText(Application.getInstancia().currentCli.getHomeAddress());
			txtPhone.setText(Application.getInstancia().currentCli.getPhone());
			txtMail.setText(Application.getInstancia().currentCli.getMail());
			txtName.setText(Application.getInstancia().currentCli.getName());
			cbZone.setSelectedItem(Application.getInstancia().currentCli.getZone().getName());
		}
		
		
		JLabel lblNewLabel_5 = new JLabel("ID:");
		lblNewLabel_5.setBounds(27, 11, 61, 16);
		frmCliente.getContentPane().add(lblNewLabel_5);
		
		if (action.equals("new")) {
			txtId.setVisible(false);
			lblNewLabel_5.setVisible(false);
		} else {
			txtId.setVisible(true);
			lblNewLabel_5.setVisible(true);
		}
	
	}
	
	protected boolean runValidations() {				
		if (txtDni.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"DNI es requerido.","Cliente",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (txtAddress.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"Dirección es requerido.","Cliente",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtPhone.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"Telefono es requerido.","Cliente",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (txtMail.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"Mail es requerido.","Cliente",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (!isValidEmailAddress(txtMail.getText())) {
			JOptionPane.showMessageDialog(null,"Email invalido, por favor coloque uno nuevo.","Cliente",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (txtName.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"Nombre es requerido.","Cliente",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	
	   public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }

}
