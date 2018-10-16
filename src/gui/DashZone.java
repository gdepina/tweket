package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Application;
import observer.Observable;
import view.TicketView;


public class DashZone extends JFrame implements Observable {
	private JLabel jLabel1;
	private JComboBox<String> combo_reclamos;
	private JRadioButton jRadioButton1;
	private JTextArea area_notificaiones;
	private JTextArea desc_reclamo;
	private JLabel jLabel3;
	private JRadioButton jRadioButton3;
	private JRadioButton jRadioButton2;
	private JButton jButton1;
	private JLabel jLabel2;
	private JEditorPane tratamientoText;
	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				DashZone inst = new DashZone();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public DashZone() {
		super();
		initGUI();
		
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			getContentPane().setLayout(null);
			this.setTitle("Reclamos de Zona");
			this.setMaximumSize(new java.awt.Dimension(879, 669));
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Reclamos:");
				jLabel1.setBounds(10, 97, 74, 14);
			}
			{
				ComboBoxModel combo_reclamosModel = new DefaultComboBoxModel(
						new String[] { "Seleccionar" });
				combo_reclamos = new JComboBox();
				getContentPane().add(combo_reclamos);
				combo_reclamos.setModel(combo_reclamosModel);
				combo_reclamos.setBounds(94, 94, 332, 20);
				for (TicketView tck : Application.getInstancia().getTickets("Zona Entrega")) {
					combo_reclamos.addItem(Integer.toString(tck.getTicketNumber())
							+ "  " +"cliente: "+ tck.getClient().toView().getName() + ", "
						+"estado: "+ tck.getStatus().getName());
				}
				combo_reclamos.addActionListener(new ActionListener() {//add actionlistner to listen for change
					@Override
					public void actionPerformed(ActionEvent e) {

						JComboBox comboBox = (JComboBox) e.getSource();

						Object selected = comboBox.getSelectedItem();
						if (selected.toString() != "Seleccionar") {

							TicketView rec = Application.getInstancia().getTicket(Integer.parseInt(combo_reclamos.getSelectedItem().toString().substring(0, 2).trim())).toView();
							desc_reclamo.setText(rec.getDescription());
						}
					}

					});
			}
			{
				tratamientoText = new JEditorPane();
				getContentPane().add(tratamientoText);
				tratamientoText.setBounds(10, 322, 416, 152);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Detalle del Tratamiento:");
				jLabel2.setBounds(10, 297, 115, 14);
			}
			{
				jButton1 = new JButton();
				getContentPane().add(jButton1);
				jButton1.setText("Actualizar estado");
				jButton1.setBounds(267, 485, 164, 23);
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
	
						if (evt.getSource()== jButton1){
//						Application.getInstancia().tratarReclamo(tratamientoText.getText(), Integer.parseInt(combo_reclamos.getSelectedItem().toString().substring(0,2).trim()));
					}
					}
				
				});
				
			}
			
			{
				jRadioButton1 = new JRadioButton();
				getContentPane().add(jRadioButton1);
				jRadioButton1.setText("ingresado");
				jRadioButton1.setBounds(73, 265, 73, 23);
			}
			{
				jRadioButton2 = new JRadioButton();
				getContentPane().add(jRadioButton2);
				jRadioButton2.setText("en tratamiento");
				jRadioButton2.setBounds(183, 265, 110, 23);
			}
			{
				jRadioButton3 = new JRadioButton();
				getContentPane().add(jRadioButton3);
				jRadioButton3.setText("cerrado");
				jRadioButton3.setBounds(313, 265, 100, 23);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Estado:");
				jLabel3.setBounds(10, 267, 57, 19);
			}
			{
				desc_reclamo = new JTextArea();
				getContentPane().add(desc_reclamo);
				desc_reclamo.setText("Descripcion del Tratamiento");
				desc_reclamo.setBounds(15, 133, 416, 123);
				desc_reclamo.setEditable(false);
			}
			{
				area_notificaiones = new JTextArea();
				getContentPane().add(area_notificaiones);
				area_notificaiones.setBounds(10, 11, 421, 77);
				area_notificaiones.setEditable(false);
			}
			pack();
			this.setSize(445, 567);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update() {
		System.out.println("bocot: zona notifico");
//		area_notificaiones.setText("Ultimo Reclamo ingresado: "+rec.getFechaIngreso());
	}

//	public void notify(Reclamo rec) {
//		System.out.println("bocot: zona notifico");
//		area_notificaiones.setText("Ultimo Reclamo ingresado: "+rec.getFechaIngreso());
//	}
	/**
	* This method should return an instance of this class which does 
	* NOT initialize it's GUI elements. This method is ONLY required by
	* Jigloo if the superclass of this class is abstract or non-public. It 
	* is not needed in any other situation.
	 */
	public static Object getGUIBuilderInstance() {
		return new DashZone(Boolean.FALSE);
	}
	
	/**
	 * This constructor is used by the getGUIBuilderInstance method to
	 * provide an instance of this class which has not had it's GUI elements
	 * initialized (ie, initGUI is not called in this constructor).
	 */
	public DashZone(Boolean initGUI) {
		super();
	}



}
