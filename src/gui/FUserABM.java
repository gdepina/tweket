package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import controller.Application;
import excepciones.FKException;
import observer.Observable;
import view.ClientView;
import view.UserView;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class FUserABM implements Observable {

	JFrame frame;
	JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FUserABM window = new FUserABM();
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
	public FUserABM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Application.getInstancia().register(this);
		frame = new JFrame();
		frame.setBounds(100, 100, 351, 285);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FUser fUser = new FUser("new");
				fUser.frame.setLocationRelativeTo(null);
				fUser.frame.setVisible(true);
			}
		});
		btnNuevo.setBounds(17, 21, 117, 29);
		frame.getContentPane().add(btnNuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentUser != null) {
					FUser fUser = new FUser("modify");
					fUser.frame.setLocationRelativeTo(null);
					fUser.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un usuario para modificar","Usuarios",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnModificar.setBounds(17, 64, 117, 29);
		frame.getContentPane().add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentUser != null) {
					try {
						Application.getInstancia().currentUser.remove();
					} catch (FKException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),"Error al eliminar usuario",JOptionPane.ERROR_MESSAGE);						
					}
					Application.getInstancia().notifyObservables();
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un usuario para eliminarlo","Usuarios",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(17, 105, 117, 29);
		frame.getContentPane().add(btnEliminar);
		
		list = new JList(this.buildList());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Application.getInstancia().getUser((String) list.getSelectedValue());
			}
		});
		list.setBounds(165, 18, 166, 210);
		frame.getContentPane().add(list);
		
		JButton btnRoles = new JButton("Roles");
		btnRoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Application.getInstancia().currentUser != null) {
					FRole fRole = new FRole();
					fRole.frame.setLocationRelativeTo(null);
					fRole.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un usuario para asignarle roles","Usuarios",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnRoles.setBounds(17, 146, 117, 29);
		frame.getContentPane().add(btnRoles);
	}
	
	private DefaultListModel buildList() {
		DefaultListModel listModel = new DefaultListModel<>();
		for (UserView user: Application.getInstancia().getUsers()){
			listModel.addElement(user.getName());
		}
		return listModel;
	}

	@Override
	public void update() {
		list.setModel(buildList());
		
	}
}
