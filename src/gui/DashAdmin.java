package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

//FALTA LIMPIAR EL COMBO EN EL QUITAR, ARREGLAR EL OBTERNER ROLES, QUE NO REPITA.
public class DashAdmin extends javax.swing.JFrame {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JRadioButton radio_agregar;
	private JButton boton_agregar;
	private JButton botonObtener;
	private JComboBox<String> roles_combo;
	private JLabel jLabel1;
	private JLabel jlabel1;
	private JComboBox<String> usuarios_combo;
	private JRadioButton quitar_radio;

	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DashAdmin inst = new DashAdmin();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public DashAdmin() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				radio_agregar = new JRadioButton();
				getContentPane().add(radio_agregar);
				radio_agregar.setText("Agregar Rol");
				radio_agregar.setBounds(12, 10, 99, 20);
				radio_agregar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (event.getSource()== radio_agregar){
							quitar_radio.setSelected(false);
							roles_combo.removeAllItems();
							hacerVisibles();
							botonObtener.setVisible(false);
							usuarios_combo.setEnabled(true);
							
						}
					}

					private void hacerVisibles() {
						roles_combo.addItem("DashAdmin");
						roles_combo.addItem("CallCenter");
						roles_combo.addItem("Consulta");
						roles_combo.addItem("Distribucion");
						roles_combo.addItem("Facturacion");
						roles_combo.addItem("Zona Entrega");
					}
				});
				
			}
			{
				quitar_radio = new JRadioButton();
				getContentPane().add(quitar_radio);
				quitar_radio.setText("Quitar Rol");
				quitar_radio.setBounds(128, 10, 110, 20);
				quitar_radio.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (event.getSource()== quitar_radio){
							radio_agregar.setSelected(false);
							roles_combo.removeAllItems();
							botonObtener.setVisible(true);
							usuarios_combo.setEnabled(true);
						}
						}

					
					
				});
				
			}
			{//anda la agregacion de los String. ya probado.
				ComboBoxModel usuarios_comboModel = 
						new DefaultComboBoxModel(new String[] { "Elija un usuario..." });
				
				usuarios_combo = new JComboBox();
				getContentPane().add(usuarios_combo);
				usuarios_combo.setModel(usuarios_comboModel);
				usuarios_combo.setBounds(10, 73, 211, 23);
				usuarios_combo.setEnabled(false);
				usuarios_combo.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent event) {
		               
		                JComboBox comboBox = (JComboBox) event.getSource();

		                Object selected = comboBox.getSelectedItem();
		                if(selected.toString()=="Elija un usuario..."){
		                	roles_combo.setEnabled(false);
		                }
		                else{
		                	roles_combo.setEnabled(true);
		                }
		                	
		                }
				});
					
			}
			for (UsuarioView user: Controlador.getInstancia().buscarUsuarios()){
				usuarios_combo.addItem(Integer.toString(user.getIdUsuario())+"    "+user.getNombre()+" "+user.getApellido());
			}
			{
				jlabel1 = new JLabel();
				getContentPane().add(jlabel1);
				jlabel1.setText("Usuarios");
				jlabel1.setBounds(10, 53, 55, 14);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Roles");
				jLabel1.setBounds(12, 142, 41, 14);
			}
			{ 
				ComboBoxModel roles_comboModel = 
						new DefaultComboBoxModel(
								new String[] { "Elija un rol..." });
				roles_combo = new JComboBox();
				getContentPane().add(roles_combo);
				roles_combo.setModel(roles_comboModel);
				roles_combo.setBounds(6, 168, 211, 20);
				roles_combo.setEnabled(false);
			}
			{
				//el agregar ya probado, anda
				//el quitar ya probado, anda
				boton_agregar = new JButton();
				getContentPane().add(boton_agregar);
				boton_agregar.setText("Aceptar");
				boton_agregar.setBounds(146, 207, 71, 23);
				boton_agregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
	
						if (evt.getSource()== boton_agregar && (roles_combo.isEnabled()==false || (quitar_radio.isSelected()==false && radio_agregar.isSelected()==false)|| usuarios_combo.getSelectedItem().toString()=="Elija un usuario...")){
							mensaje();
						}else {
						if(evt.getSource()== boton_agregar && radio_agregar.isSelected()){
							Controlador.getInstancia().agregarRolAUsuario(roles_combo.getSelectedItem().toString(), Integer.parseInt(usuarios_combo.getSelectedItem().toString().substring(0,2).trim()));
						}else{
							if (evt.getSource()== boton_agregar && quitar_radio.isSelected()){
								Controlador.getInstancia().quitarRolAUsuario(roles_combo.getSelectedItem().toString(), Integer.parseInt(usuarios_combo.getSelectedItem().toString().substring(0,2).trim()));
								
							}
						}
							
						}
					}
				});
				
			}
			{
				botonObtener = new JButton();
				getContentPane().add(botonObtener);
				botonObtener.setVisible(false);
				botonObtener.setText("Obtener roles...");
				botonObtener.setBounds(74, 108, 138, 28);
				botonObtener.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (event.getSource() == botonObtener && quitar_radio.isSelected()) {
							for (String tipoRol : Controlador.getInstancia().obtenerRolesDeUsuario(Integer.parseInt(usuarios_combo.getSelectedItem().toString().substring(0,2).trim()))){
								roles_combo.addItem(tipoRol);
							}
						} 
					}
				});
			}
			pack();
			this.setSize(264, 280);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mensaje() {
		JOptionPane.showMessageDialog(null,
				"Hay campos que no fueron seleccionados", "Error",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
