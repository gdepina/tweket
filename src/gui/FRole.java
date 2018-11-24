package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.Application;
import modelo.Role;
import view.ProductView;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FRole {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FRole window = new FRole();
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
	public FRole() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 421, 286);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList listRoles = new JList(this.buildRolesList());
		
		listRoles.setBounds(20, 66, 134, 165);
		frame.getContentPane().add(listRoles);
		
		JLabel lblRoles = new JLabel("Roles");
		lblRoles.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoles.setBounds(20, 26, 134, 16);
		frame.getContentPane().add(lblRoles);
		
		JList listAsigned = new JList(this.buildAisgnedList());
		listAsigned.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			}
		});
		listAsigned.setBounds(251, 66, 134, 165);
		frame.getContentPane().add(listAsigned);
		
		JLabel lblAsignados = new JLabel("Asignados");
		lblAsignados.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsignados.setBounds(251, 26, 134, 16);
		frame.getContentPane().add(lblAsignados);
		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				if (!listRoles.isSelectionEmpty()) {
					Application.getInstancia().currentUser.saveRole(Application.getInstancia().getRoleByName((String) listRoles.getSelectedValue()));
					listAsigned.setModel(buildAisgnedList());
					listRoles.setModel(buildRolesList());
				} else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar un rol para asginar","Roles",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(166, 89, 73, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnQuitar = new JButton("-");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!listAsigned.isSelectionEmpty()) {
					Application.getInstancia().currentUser.removeRole(Application.getInstancia().getRoleByName((String) listAsigned.getSelectedValue()));
					Application.getInstancia().getUser(Application.getInstancia().currentUser.getUserName());
					listAsigned.setModel(buildAisgnedList());
					listRoles.setModel(buildRolesList());
				} else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar un rol asignado para eliminar","Roles",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnQuitar.setBounds(166, 151, 73, 29);
		frame.getContentPane().add(btnQuitar);
		listRoles.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnQuitar.setEnabled(false);
			}
		});
		listAsigned.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnQuitar.setEnabled(true);
			}
		});
	}
	
	private DefaultListModel buildAisgnedList() {
		DefaultListModel listModel = new DefaultListModel<>();
		for (Role role: Application.getInstancia().currentUser.getRoles()){
			listModel.addElement(role.getType());			
		}
		return listModel;
	}
	
	private DefaultListModel buildRolesList() {
		DefaultListModel listModel = new DefaultListModel<>();
		for (Role role: Application.getInstancia().getAvailableRoles()){
			listModel.addElement(role.getType());
		}
		return listModel;
	}

}
