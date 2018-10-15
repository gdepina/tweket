package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import controller.Application;

public class Login extends JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JButton btn_roles;
	private JTextField t_usuario;
	private JTextField t_password;
	private JButton botonIngresar;
	private JComboBox comboRoles;
	private JLabel Contrasenia;
	private JLabel Jlabel1;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login inst = new Login();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public Login() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Login");
			this.setMaximumSize(new java.awt.Dimension(279, 269));
			this.setMinimumSize(new java.awt.Dimension(279, 269));
			this.setVisible(false);
			{
				btn_roles = new JButton();
				getContentPane().add(btn_roles);
				btn_roles.setText("Obtener Roles");
				btn_roles.setBounds(111, 116, 118, 21);
				btn_roles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (event.getSource() == btn_roles) {
							if ((Application.getInstancia()).checkUser(Integer.parseInt(t_usuario.getText()),
									t_password.getText()) == true) {
								for (String type : Application.getInstancia()
										.getRolesByUser(Integer.parseInt(t_usuario.getText()))) {
									comboRoles.addItem(type);
								}
							} else
								JOptionPane.showMessageDialog(null, "Usuario y Contrase�a incorrectos", "Error",
										JOptionPane.INFORMATION_MESSAGE);

						}
					}

				});
			}
			{
				t_usuario = new JTextField();
				getContentPane().add(t_usuario);
				t_usuario.setBounds(111, 26, 116, 30);
			}
			{
				t_password = new JTextField();
				getContentPane().add(getT_password());
				t_password.setBounds(111, 68, 118, 29);
			}
			{
				Jlabel1 = new JLabel();
				getContentPane().add(Jlabel1);
				Jlabel1.setText("ID");
				Jlabel1.setBounds(18, 33, 88, 14);
			}
			{
				Contrasenia = new JLabel();
				getContentPane().add(Contrasenia);
				Contrasenia.setText("Contrasenia");
				Contrasenia.setBounds(12, 73, 94, 14);
			}
			{
				ComboBoxModel<String> comboRolesModel = new DefaultComboBoxModel<String>(
						new String[] { "Elija un Rol..." });
				comboRoles = new JComboBox();
				getContentPane().add(comboRoles);
				comboRoles.setModel(comboRolesModel);
				comboRoles.setBounds(18, 149, 219, 20);
			}
			{
				botonIngresar = new JButton();
				getContentPane().add(botonIngresar);
				botonIngresar.setText("Ingresar");
				botonIngresar.setBounds(139, 187, 90, 23);
				botonIngresar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (event.getSource() == botonIngresar) {

						}
					}

				});
			}
			pack();
			this.setSize(279, 229);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextField getT_password() {
		return t_password;
	}

}
