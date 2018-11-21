package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import controller.Application;
import view.TicketView;

public class DashTicket {

	JFrame frame;	
	private JTable table;
	private String[] type;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DashTicket window = new DashTicket();
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
	public DashTicket() {
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	
	public DashTicket(String[] type) {
		this.type = type;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 803, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 803, 240);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		updateTable();
		scrollPane.setViewportView(table);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(36, 550, 61, 16);
		frame.getContentPane().add(lblEstado);
		
		JRadioButton rbRechazo = new JRadioButton("Rechazado");
		rbRechazo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		rbRechazo.setBounds(370, 546, 141, 23);
		frame.getContentPane().add(rbRechazo);
		
		JRadioButton rbEnCurso = new JRadioButton("En curso");
		rbEnCurso.setBounds(162, 546, 141, 23);
		frame.getContentPane().add(rbEnCurso);
		
		JRadioButton rbCerrado = new JRadioButton("Cerrado");
		rbCerrado.setBounds(632, 546, 141, 23);
		frame.getContentPane().add(rbCerrado);
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		
		rbRechazo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbEnCurso.setSelected(false);
				rbCerrado.setSelected(false);
				btnGuardar.setEnabled(true);
				
			}
		});
		
		rbEnCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbRechazo.setSelected(false);
				rbCerrado.setSelected(false);
				btnGuardar.setEnabled(true);
			}
		});
		
		rbCerrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rbRechazo.setSelected(false);
				rbEnCurso.setSelected(false);
				btnGuardar.setEnabled(true);
			}
		});
		
		JLabel lblDetalleDelTratamiento = new JLabel("Detalle del tratamiento");
		lblDetalleDelTratamiento.setBounds(36, 399, 213, 16);
		frame.getContentPane().add(lblDetalleDelTratamiento);
		
		JTextArea txtTratamiento = new JTextArea();
		txtTratamiento.setBounds(36, 427, 737, 111);
		frame.getContentPane().add(txtTratamiento);
		
			
	
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int state = 0;
				int ticketNumber = Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 0));		
                if (rbRechazo.isSelected()) {
                    state = 2;
                }
                if (rbEnCurso.isSelected()) {
                    state = 3;
                }
                if (rbCerrado.isSelected()) {
                    state = 4;
                }
                if (state == 0) {
                	JOptionPane.showMessageDialog(null, "Debe seleccionar un estado", "Estado invalido",
							JOptionPane.INFORMATION_MESSAGE);
                } else {
                	txtTratamiento.setText("");
	                Application.getInstancia().changeTicketState(state, ticketNumber, txtTratamiento.getText());                
	                updateTable();
                }
			}
		});
		btnGuardar.setBounds(36, 587, 117, 29);
		frame.getContentPane().add(btnGuardar);
		
		JTextArea txtDesc = new JTextArea();
		txtDesc.setWrapStyleWord(true);
		txtDesc.setLineWrap(true);
		txtDesc.setEditable(false);
		txtDesc.setBounds(36, 292, 737, 79);
		frame.getContentPane().add(txtDesc);
		
		JLabel lblDescripcinDelReclamo = new JLabel("Descripción del reclamo");
		lblDescripcinDelReclamo.setBounds(36, 264, 213, 16);
		frame.getContentPane().add(lblDescripcinDelReclamo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 383, 797, 12);
		frame.getContentPane().add(separator);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		JMenuItem mntmCerrarSesin = new JMenuItem("Cerrar sesión");
		mntmCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login fLogin = new Login();
				fLogin.setLocationRelativeTo(null);
				fLogin.setVisible(true);
				frame.dispose();
			}
		});
		mnOpciones.add(mntmCerrarSesin);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnOpciones.add(mntmSalir);
		
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {		   
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() > -1) {
					txtDesc.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
					String state = table.getValueAt(table.getSelectedRow(), 3).toString();
					if (state.toLowerCase().equals("en curso")) {
						rbEnCurso.setSelected(true);
						rbRechazo.setSelected(false);
						rbCerrado.setSelected(false);
					} else {
						rbEnCurso.setSelected(false);
					}
					
					if (state.toLowerCase().equals("rechazado")) {
						rbEnCurso.setSelected(false);
						rbRechazo.setSelected(true);
						rbCerrado.setSelected(false);
					} else {
						rbRechazo.setSelected(false);
					}
					
					if (state.toLowerCase().equals("cerrado")) {
						rbEnCurso.setSelected(false);
						rbRechazo.setSelected(false);
						rbCerrado.setSelected(true);
					} else {
						rbCerrado.setSelected(false);
					}
		        }
				
			}
		});
	}

	private void updateTable() {
		List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();
        
        columns.add("Reclamo #");
        columns.add("Tipo");
        columns.add("Cliente");
        columns.add("Estado");
        columns.add("Fecha creación");       
        columns.add("Producto");
        columns.add("Zona");       
        columns.add("Cantidad");       
        columns.add("Desc");
		
        for (TicketView tck : Application.getInstancia().getTickets(this.type)) {
        	values.add(new String[] {Integer.toString(tck.getTicketNumber()),tck.getType(), tck.getClient().getName(), tck.getStatus().getName(),
        			tck.getCreationDate().toString(), tck.getProduct().getTitle(), tck.getClient().getZone().getName(),Integer.toString(tck.getQuantity()), tck.getDescription()});
        }
        
        TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), columns.toArray()) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
		       
		table.setModel(tableModel);
		
		TableColumn idClmn= table.getColumn("Desc");
		idClmn.setMaxWidth(0);
		idClmn.setMinWidth(0);
		idClmn.setPreferredWidth(0);
	}
}
