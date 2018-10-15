package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import controller.Application;
import modelo.Client;
import modelo.Product;
import view.ClientView;
import view.ProductView;
import views.ClienteView;
import views.ProductoView;
import negocio.Cliente;
import negocio.Controlador;
import negocio.Producto;


public class DashCallCenter extends javax.swing.JFrame  {

	{
		try {
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JComboBox comboProductos;
	private JLabel jLabel6;
	private JComboBox comboCliente;
	private JLabel Cliente;
	private JRadioButton radioCompuesto;
	private JRadioButton radioSimple;
	private JButton btn_ingresar;
	private JTextField text_decripcion;
	private JComboBox jComboBox4;
	private JComboBox jComboBox3;
	private JComboBox jComboBox2;
	private JComboBox jComboBox1;
	private JComboBox combo1;
	private JLabel jLabel5;
	private JLabel jLabel4;
	


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DashCallCenter inst = new DashCallCenter();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public DashCallCenter() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
		
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Tipo Reclamo:");
				jLabel1.setBounds(13, 43, 155, 14);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Tipo Reclamo:");
				jLabel2.setBounds(12, 74, 113, 14);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Tipo Reclamo:");
				jLabel4.setBounds(12, 140, 126, 14);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Tipo Reclamo:");
				jLabel5.setBounds(12, 173, 113, 14);
			}
			{
				ComboBoxModel combo1Model = 
						new DefaultComboBoxModel(
								new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega"});
				combo1 = new JComboBox();
				getContentPane().add(combo1);
				combo1.setModel(combo1Model);
				combo1.setBounds(162, 40, 127, 21);
			}
			{
				ComboBoxModel jComboBox1Model = 
						new DefaultComboBoxModel(
								new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega" });
				jComboBox1 = new JComboBox();
				getContentPane().add(jComboBox1);
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(162, 71, 127, 21);
			}
			{
				ComboBoxModel jComboBox2Model = 
						new DefaultComboBoxModel(
								new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega" });
				jComboBox2 = new JComboBox();
				getContentPane().add(jComboBox2);
				jComboBox2.setModel(jComboBox2Model);
				jComboBox2.setBounds(162, 104, 127, 21);
			}
			{
				ComboBoxModel jComboBox3Model = 
						new DefaultComboBoxModel(
								new String[] {"Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega"});
				jComboBox3 = new JComboBox();
				getContentPane().add(jComboBox3);
				jComboBox3.setModel(jComboBox3Model);
				jComboBox3.setBounds(162, 137, 127, 21);
			}
			{
				ComboBoxModel jComboBox4Model = 
						new DefaultComboBoxModel(
								new String[] { "Seleccionar","Cantidad", "Facturacion", "Faltante", "Producto", "Zona Entrega" });
				jComboBox4 = new JComboBox();
				getContentPane().add(jComboBox4);
				jComboBox4.setModel(jComboBox4Model);
				jComboBox4.setBounds(162, 170, 127, 21);
			}
			{
				text_decripcion = new JTextField();
				getContentPane().add(text_decripcion);
				text_decripcion.setBounds(11, 309, 272, 105);
			}
			{
				btn_ingresar = new JButton();
				getContentPane().add(btn_ingresar);
				btn_ingresar.setText("Ingresar");
				btn_ingresar.setBounds(179, 426, 104, 21);
				btn_ingresar.addActionListener(new ActionListener(){
				ArrayList <String> tipoReclamos= new ArrayList <String>();
				Client cli= new Client();
				Product pro= new Product();
					public void actionPerformed(ActionEvent event) {		
						
						if (event.getSource() == btn_ingresar && radioSimple.isSelected()) {
							tipoReclamos.add(combo1.getSelectedItem().toString());
							Application.getInstancia().saveTicket(tipoReclamos, Integer.parseInt(comboCliente.getSelectedItem().toString().substring(0,2).trim()),text_decripcion.getText(), Integer.parseInt(comboProductos.getSelectedItem().toString().substring(0,2).trim()));
							

						} else if (event.getSource() == btn_ingresar && radioCompuesto.isSelected()){
							if (combo1.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0 ){
								tipoReclamos.add(combo1.getSelectedItem().toString());
							} 
							if (jComboBox1.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0){
								tipoReclamos.add(jComboBox1.getSelectedItem().toString());
							} 
							if (jComboBox2.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0){
								tipoReclamos.add(jComboBox2.getSelectedItem().toString());
							} 
							if (jComboBox3.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") != 0){
								tipoReclamos.add(jComboBox3.getSelectedItem().toString());
							} 
							if (jComboBox4.getSelectedItem().toString().compareToIgnoreCase("Seleccionar") !=0){
								tipoReclamos.add(jComboBox4.getSelectedItem().toString());
							}
							Application.getInstancia().saveTicket(tipoReclamos, Integer.parseInt(comboCliente.getSelectedItem().toString().substring(0,2).trim()),text_decripcion.getText(), Integer.parseInt(comboProductos.getSelectedItem().toString().substring(0,2).trim()));
						}
						tipoReclamos= new ArrayList <String>();
					}				
				});
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Tipo Reclamo:");
				jLabel3.setBounds(12, 107, 126, 14);
			}
			{
				radioSimple = new JRadioButton();
				getContentPane().add(radioSimple);
				radioSimple.setText("Simple");
				radioSimple.setBounds(12, 10, 91, 18);
				jComboBox1.setEnabled(false);
				jComboBox2.setEnabled(false);
				jComboBox3.setEnabled(false);
				jComboBox4.setEnabled(false);
				jLabel3.setVisible(true);
				jLabel2.setVisible(true);
				jLabel4.setVisible(true);
				jLabel5.setVisible(true);
				radioSimple.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (event.getSource()== radioSimple){
							radioCompuesto.setSelected(false);		
							jComboBox1.setEnabled(false);
							jComboBox2.setEnabled(false);
							jComboBox3.setEnabled(false);
							jComboBox4.setEnabled(false);	
						}
					}
				});
			}
			{
				radioCompuesto = new JRadioButton();
				getContentPane().add(radioCompuesto);
				radioCompuesto.setText("Compuesto");
				radioCompuesto.setBounds(138, 10, 109, 18);
				radioCompuesto.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (e.getSource() == radioCompuesto){
							radioSimple.setSelected(false);		
							jComboBox1.setEnabled(true);
							jComboBox2.setEnabled(true);
							jComboBox3.setEnabled(true);
							jComboBox4.setEnabled(true);							
						}
					}
				});
			}
			{
				Cliente = new JLabel();
				getContentPane().add(Cliente);
				Cliente.setText("Cliente");
				Cliente.setBounds(12, 223, 66, 16);
			}
			{
				ComboBoxModel comboClienteModel = 
						new DefaultComboBoxModel(
								new String[] {  "Elija un cliente..."});
				comboCliente = new JComboBox();
				getContentPane().add(comboCliente);
				comboCliente.setModel(comboClienteModel);
				comboCliente.setBounds(125, 218, 158, 26);
				for (ClientView cli: Application.getInstancia().getClients()){
					comboCliente.addItem(Integer.toString(cli.getId())+"    "+cli.getName());
				}
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Producto");
				jLabel6.setBounds(12, 265, 61, 16);
			}
			{
				ComboBoxModel comboProductosModel = 
						new DefaultComboBoxModel(
								new String[] { "Elija un producto..." });
				comboProductos = new JComboBox();
				getContentPane().add(comboProductos);
				comboProductos.setModel(comboProductosModel);
				comboProductos.setBounds(125, 260, 158, 26);
				for (ProductView pro: Application.getInstancia().getProducts()){
					comboProductos.addItem(pro.getId()+"    "+pro.getName());
				}
			}
			pack();
			this.setSize(315, 495);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}