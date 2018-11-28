package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.Application;
import excepciones.PKDuplicadaException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FUser {

	JFrame frame;
	private JTextField txtName;
	private JTextField txtPass;
	String action;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FUser window = new FUser();
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
	public FUser() {
		initialize();
	}

	public FUser(String action) {
		this.action = action;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 288, 169);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtName = new JTextField();
		txtName.setBounds(101, 20, 159, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(101, 58, 159, 26);
		frame.getContentPane().add(txtPass);
		
		JLabel lblUsuario = new JLabel("Usuario*:");
		lblUsuario.setBounds(18, 25, 61, 16);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Password*:");
		lblContrasea.setBounds(18, 63, 81, 16);
		frame.getContentPane().add(lblContrasea);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (runValidations()) {
					String name = txtName.getText();
					String pass = txtPass.getText();					
					if (action.equals("new")) {	
						try {
							Application.getInstancia().saveUser(name, pass);
							frame.dispose();
						} catch (PKDuplicadaException e1) {
							JOptionPane.showMessageDialog(null,e1.getMessage(),"Error al guardar usuario",JOptionPane.ERROR_MESSAGE);							
						}	
					} else {					
						Application.getInstancia().currentUser.setUserName(name);
						Application.getInstancia().currentUser.setPass(pass);				
						Application.getInstancia().currentUser.update();
						Application.getInstancia().notifyObservables();
						frame.dispose();
					}
					
				}
			}
		});
		btnAceptar.setBounds(18, 96, 117, 29);
		frame.getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancelar.setBounds(143, 96, 117, 29);
		frame.getContentPane().add(btnCancelar);
		
		if (Application.getInstancia().currentUser != null && !action.equals("new")) {
			txtName.setText(Application.getInstancia().currentUser.getUserName());
			txtPass.setText(Application.getInstancia().currentUser.getPass());				
		}
		
	}
	
	protected boolean runValidations() {		
		if (txtName.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"El usuario es requerido.","Usuario",JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		if (txtPass.getText().isEmpty()) {			
			JOptionPane.showMessageDialog(null,"La contraseña es requerida.","Usuario",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	
		return true;
	}
	
}
