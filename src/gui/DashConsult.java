package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import negocio.Controlador;
import negocio.Reclamo;
import views.ClienteView;
import views.ReclamoView;
import views.UsuarioView;

public class DashConsult extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JComboBox combo_reclamos;
	private JTextArea desc_reclamo;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JLabel ranking_clientesReclamos;
	private JLabel Reportes_label;
	private JTextArea jTextArea2;
	private JLabel jLabel2;
	private JTextArea jTextArea1;
	private JLabel jLabel3;
	private JButton btn_reclamosPorMes;
	private JButton ver4;
	private JLabel jLabel7;
	private JButton btn_rankingTratados;
	private JLabel jLabel6;
	private JButton btn_mayorCantReclamo;

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager
					.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				DashConsult inst = new DashConsult();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public DashConsult() {
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
				jLabel1.setText("Reclamos:");
				jLabel1.setBounds(6, 14, 74, 14);
			}
			{
				ComboBoxModel combo_reclamosModel = new DefaultComboBoxModel(
						new String[] { "Elija un reclamo" });
				combo_reclamos = new JComboBox();
				getContentPane().add(combo_reclamos);
				combo_reclamos.setModel(combo_reclamosModel);
				combo_reclamos.setBounds(84, 11, 184, 20);
				for (ReclamoView rec : Controlador.getInstancia()
						.buscarReclamos()) {
					combo_reclamos
							.addItem(Integer.toString(rec.getIdReclamo()));
				}
				combo_reclamos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {

						JComboBox comboBox = (JComboBox) event.getSource();

						Object selected = comboBox.getSelectedItem();
						if (selected.toString() != "Elija un reclamo") {
							ReclamoView rec = Controlador
									.getInstancia()
									.buscarReclamo(
											Integer.parseInt(combo_reclamos
													.getSelectedItem()
													.toString().trim()))
									.toView();
							desc_reclamo.setText(rec.getDescripcion());
							jTextArea1.setText(rec.getEstado());
							jTextArea2.setText(rec.getFecha());
						}

					}
				});

			}

			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Estado:");
				jLabel3.setBounds(10, 176, 93, 19);
			}
			{
				desc_reclamo = new JTextArea();
				getContentPane().add(desc_reclamo);
				desc_reclamo.setText("Descripcion del Reclamo");
				desc_reclamo.setBounds(10, 42, 416, 123);
				desc_reclamo.setEditable(false);
			}
			{
				jTextArea1 = new JTextArea();
				getContentPane().add(jTextArea1);
				jTextArea1.setText("Estado");
				jTextArea1.setBounds(165, 173, 201, 29);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Fecha de Ingreso:");
				jLabel2.setBounds(10, 206, 131, 14);
			}
			{
				jTextArea2 = new JTextArea();
				getContentPane().add(jTextArea2);
				jTextArea2.setText("fecha");
				jTextArea2.setBounds(165, 201, 201, 29);
			}
			{
				Reportes_label = new JLabel();
				getContentPane().add(Reportes_label);
				Reportes_label.setText("Reportes:");
				Reportes_label.setBounds(10, 245, 63, 14);
			}
			{
				ranking_clientesReclamos = new JLabel();
				getContentPane().add(ranking_clientesReclamos);
				ranking_clientesReclamos
						.setText("Ranking de clientes con mayor reclamos ");
				ranking_clientesReclamos.setBounds(10, 270, 324, 14);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Cantidad de reclamos tratados por mes");
				jLabel4.setBounds(10, 305, 188, 14);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Cantidad de reclamos tratados por mes");
				jLabel5.setBounds(10, 305, 257, 14);
			}
			{
				btn_mayorCantReclamo = new JButton();
				getContentPane().add(btn_mayorCantReclamo);
				btn_mayorCantReclamo.setText("Ver");
				btn_mayorCantReclamo.setBounds(346, 266, 49, 23);
				btn_mayorCantReclamo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						if (evt.getSource() == btn_mayorCantReclamo) {
							String mensaje = " ";
							ArrayList<ClienteView> clientes = Controlador
									.getInstancia().mayorCantidadReclamos();
							for (ClienteView cli : clientes) {
								mensaje = mensaje + "id: " + cli.getIdCliente()
										+ ", nombre: " + cli.getNombre() + "\n";
							}

							JOptionPane.showMessageDialog(null, mensaje, "Top",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Ranking de tratamientos de reclamos");
				jLabel6.setBounds(10, 339, 279, 14);
			}
			{
				btn_rankingTratados = new JButton();
				getContentPane().add(btn_rankingTratados);
				btn_rankingTratados.setText("Ver");
				btn_rankingTratados.setBounds(346, 335, 49, 23);
				btn_rankingTratados.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						if (evt.getSource() == btn_rankingTratados) {
							String mensaje = " ";
							ArrayList<UsuarioView> usuarios = Controlador
									.getInstancia().MayorCantidadTratamientos();
							for (UsuarioView user : usuarios) {
								mensaje = mensaje + "id: "
										+ user.getIdUsuario() + ", nombre: "
										+ user.getNombre() + ", apellido: "
										+ user.getApellido() + "\n";
							}

							JOptionPane.showMessageDialog(null, mensaje, "Top",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});

			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Tiempo promedio de respuesta por responsable");
				jLabel7.setBounds(10, 370, 290, 14);
			}
			{
				ver4 = new JButton();
				getContentPane().add(ver4);
				ver4.setText("Ver");
				ver4.setBounds(346, 366, 49, 23);
				ver4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						if (evt.getSource() == ver4) {

							PromedioUsuario frame = new PromedioUsuario();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
						}
					}
				});
			}
			{
				btn_reclamosPorMes = new JButton();
				getContentPane().add(btn_reclamosPorMes);
				btn_reclamosPorMes.setText("Ver");
				btn_reclamosPorMes.setBounds(346, 301, 49, 23);
				btn_reclamosPorMes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						if (evt.getSource() == btn_reclamosPorMes) {

							ReclamosPorMes frame = new ReclamosPorMes();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
						}
					}
				});

			}
			pack();
			this.setSize(457, 435);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
