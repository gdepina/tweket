package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import view.TicketView;

import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

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
	private JTextField txtCantidad;
	private JTextField txtBillId;
	private JTable table;
	JButton button_1;
	JRadioButton rdbtnCompuesto;

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
		frame.setBounds(100, 100, 782, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 776, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnOptions = new JMenu("Opciones");
		menuBar.add(mnOptions);
		
		JMenuItem mnClose = new JMenuItem("Cambiar de usuario");		
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
		txtCantidad.setBounds(436, 271, 165, 26);		
		frame.getContentPane().add(txtCantidad);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(363, 276, 61, 16);
		frame.getContentPane().add(lblCantidad);		
		
		JRadioButton rbSimple = new JRadioButton("Simple");

		rbSimple.setBounds(30, 34, 141, 23);
		frame.getContentPane().add(rbSimple);
		
		rdbtnCompuesto = new JRadioButton("Compuesto");
		
		rdbtnCompuesto.setBounds(163, 34, 141, 23);
		frame.getContentPane().add(rdbtnCompuesto);
		
		String[] comboModel = new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega"};
		
		cbTicket1 = new JComboBox(comboModel);

		cbTicket1.setBounds(163, 69, 172, 27);
		frame.getContentPane().add(cbTicket1);
		
		JLabel lblTipoDeReclamo = new JLabel("Tipo de reclamo:");
		lblTipoDeReclamo.setBounds(30, 73, 105, 16);
		frame.getContentPane().add(lblTipoDeReclamo);
		

		
		JTextArea txtAreaDesc = new JTextArea();
		txtAreaDesc.setLineWrap(true);
		txtAreaDesc.setWrapStyleWord(true);
		txtAreaDesc.setBounds(30, 358, 727, 71);
		frame.getContentPane().add(txtAreaDesc);
		
		JLabel lblInformacinAdicional = new JLabel("Descripción e Información adicional:");
		lblInformacinAdicional.setBounds(30, 330, 305, 16);
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
		
		button_1 = new JButton("+");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HClient fClient = new HClient();
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
				String billId = txtBillId.getText();
				
				if (rbSimple.isSelected()) {
					tipoReclamos.add(cbTicket1.getSelectedItem().toString());							
					Application.getInstancia().saveTicket(tipoReclamos,clientId, txtAreaDesc.getText(), productCode, qty, billId);					

				} else if (rdbtnCompuesto.isSelected()){
					Application.getInstancia().saveTicketComposite(clientId);					
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
				txtAreaDesc.setText("");
				txtCantidad.setText("");
				txtBillId.setText("");				
				Application.getInstancia().currentCli = null;
				Application.getInstancia().currentProd = null;
				Application.getInstancia().tickets = null;
				table.setModel(new DefaultTableModel());
				
				
			}
		});
		btnGuardar.setBounds(30, 441, 117, 29);
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
		
		JLabel lblDireccin = new JLabel("Direccion:");
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
		separator.setBounds(342, 34, 20, 312);
		frame.getContentPane().add(separator);
		
		txtBillId = new JTextField();
		txtBillId.setBounds(436, 271, 165, 26);
		frame.getContentPane().add(txtBillId);
		txtBillId.setColumns(10);
		
		JLabel lblFactura = new JLabel("Factura #:");
		lblFactura.setBounds(363, 276, 68, 16);
		frame.getContentPane().add(lblFactura);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 101, 305, 183);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnAgregarReclamo = new JButton("Agregar");
		btnAgregarReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!txtClientTitle.getText().isEmpty()) {
					FTicket fTicket = new FTicket();
					fTicket.frame.setLocationRelativeTo(null);
					fTicket.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null,"Seleccione un cliente para agregar un nuevo reclamo","Reclamo",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnAgregarReclamo.setBounds(30, 289, 117, 29);
		frame.getContentPane().add(btnAgregarReclamo);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (!table.getSelectionModel().isSelectionEmpty()) {
					String type = table.getValueAt(table.getSelectedRow(), 0).toString();
					Application.getInstancia().removeTicket(type);		
				} else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar un reclamo para quitarlo","Reclamo",JOptionPane.INFORMATION_MESSAGE);
				}
				
				if (table.getRowCount() == 0) {
					button_1.setEnabled(true);
				}
			}
		});
		btnQuitar.setBounds(218, 289, 117, 29);
		frame.getContentPane().add(btnQuitar);
		
		JLabel lblNewLabel_2 = new JLabel("Reclamos asociados:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel_2.setBounds(30, 73, 219, 16);
		frame.getContentPane().add(lblNewLabel_2);
					
		scrollPane.setVisible(false);					
		lblNewLabel_2.setVisible(false);
		btnAgregarReclamo.setVisible(false);
		btnQuitar.setVisible(false);
		lblFactura.setVisible(false);
		txtBillId.setVisible(false);
		lblCantidad.setVisible(false);
		txtCantidad.setVisible(false);
		
		
		rdbtnCompuesto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					rbSimple.setSelected(false);	
					scrollPane.setVisible(true);
					cbTicket1.setEnabled(true);
					lblTipoDeReclamo.setVisible(false);
					cbTicket1.setVisible(false);
					lblNewLabel_2.setVisible(true);
					btnAgregarReclamo.setVisible(true);
					btnQuitar.setVisible(true);
					txtProduct.setVisible(false);
					lblNewLabel.setVisible(false);
					txtProductTitle.setVisible(false);
					button.setVisible(false);
					lblInformacinAdicional.setVisible(false);
					txtAreaDesc.setVisible(false);
					
					lblCantidad.setVisible(false);
					lblFactura.setVisible(false);
					txtCantidad.setVisible(false);
					txtBillId.setVisible(false);
			}
		});
		
		rbSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCompuesto.setSelected(false);		
				scrollPane.setVisible(false);
				cbTicket1.setEnabled(true);
				lblTipoDeReclamo.setVisible(true);
				cbTicket1.setVisible(true);
				lblNewLabel_2.setVisible(false);
				btnAgregarReclamo.setVisible(false);
				btnQuitar.setVisible(false);
				
				txtProduct.setVisible(true);
				lblNewLabel.setVisible(true);
				txtProductTitle.setVisible(true);
				button.setVisible(true);
				lblInformacinAdicional.setVisible(true);
				txtAreaDesc.setVisible(true);
			}
		});
		
		
		rbSimple.setSelected(true);	
		
		cbTicket1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFactura.setVisible(false);
				txtBillId.setVisible(false);
				lblCantidad.setVisible(false);
				txtCantidad.setVisible(false);
				
				if (cbTicket1.getSelectedItem().toString().equals("Facturacion")) {
					
					lblFactura.setVisible(true);
					txtBillId.setVisible(true);
				}
				
				if (cbTicket1.getSelectedItem().toString().equals("Faltante") || cbTicket1.getSelectedItem().toString().equals("Cantidad") || cbTicket1.getSelectedItem().toString().equals("Producto")) {
					lblCantidad.setVisible(true);
					txtCantidad.setVisible(true);
				}				
			}
		});		
			
	}
	
	private void updateTable() {
		List<String> columns = new ArrayList<String>();
        List<String[]> values = new ArrayList<String[]>();
                
        columns.add("Tipo");                            
        columns.add("Producto");                      
		
        for (TicketView tck : Application.getInstancia().getTickets()) {
        	values.add(new String[] {tck.getType(), tck.getProduct().getTitle()});
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
		
		if (Application.getInstancia().tickets != null && rdbtnCompuesto.isSelected()) {
			button_1.setEnabled(false);
			updateTable();		
		}
		
	}
}
