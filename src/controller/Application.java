package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import modelo.Client;
import modelo.Product;
import modelo.Role;
import modelo.Ticket;
import modelo.User;
import view.ClientView;

public class Application {
	private ArrayList<Client> clients;
	private ArrayList<Product> products;
	private ArrayList<User> users;
	private ArrayList<Ticket> tickets;
	private static Application instancia;

	public static Application getInstancia() {
		if (instancia == null) {
			instancia = new Application();
		}
		return instancia;
	}
	public Application() {
		this.clients = new ArrayList<Client>();
		this.products = new ArrayList<Product>();
		this.users = new ArrayList<User>();
		this.tickets = new ArrayList<Ticket>();
	
	}
	
	public ArrayList<ClientView> getClients(){
		ArrayList <ClientView> clientesView= new ArrayList <ClientView>();
		for (Client cli: Client.buscarClientes()){
			clientesView.add(cli.toView());
		}
		return clientesView;
	}
	public ArrayList<ReclamoView> buscarReclamos(){
		ArrayList <ReclamoView> reclamosView= new ArrayList <ReclamoView>();
		for (Reclamo rec:Reclamo.buscarReclamos()){
			reclamosView.add(rec.toView());
		}
		return reclamosView;
	}
	
	public ArrayList<UsuarioView> buscarUsuarios(){
		ArrayList <UsuarioView> usuariosView= new ArrayList <UsuarioView>();
		for (Usuario user:  Usuario.buscarUsuarios()){
			usuariosView.add(user.toView());
		}
		return usuariosView;
	}
	
	public ArrayList<ProductoView> buscarProductos(){
		ArrayList <ProductoView> productosView= new ArrayList <ProductoView>();
		for (Producto pro: Producto.buscarProductos()){
			productosView.add(pro.toView());
		}
		return productosView;
	}
	
	public ArrayList<String> obtenerRolesDeUsuario(int idUsuario){
		Usuario user = buscarUsuario(idUsuario);
		ArrayList<String> roles= new ArrayList<String>();
		for (Rol rol: user.getRoles()){
			roles.add(rol.getTipoRol());
		}
		return roles;
	}

	
	public Tratamiento buscarTratamiento(int idTratamiento){
		Tratamiento res=null;
		for (Tratamiento tra: tratamientos){
			if (tra.sosElTratamiento(idTratamiento)==true)
				res=tra;
		}
		if (res==null)
			res=Tratamiento.buscarTratamiento(idTratamiento);
		
		return res;
	}

	

	public Producto buscarProducto(int codigo){
		Producto res=null;
		for (Producto pro: productos){
			if (pro.sosElProducto(codigo)==true)
				res=pro;
		}
		if (res==null)
			res=Producto.buscarProducto(codigo);
		
		return res;
	}


	public Reclamo buscarReclamo(int idReclamo){
		Reclamo res=null;
		for (Reclamo rec: reclamos){
			if (rec.sosElReclamo(idReclamo)==true)
				res=rec;
		}
		if (res==null)
			res=Reclamo.buscarReclamo(idReclamo);
		
		return res;
	}
	public Usuario buscarUsuario(int idUsuario){
		Usuario res=null;
		for (Usuario user: usuarios){
			if (user.sosElUsuario(idUsuario)==true){
				res=user;
			}
		}
		if (res==null){
			res=Usuario.buscarUsuario(idUsuario);
		}
		return res;
	}
	
	public Cliente buscarCliente(int idCliente){
		Cliente res=null;
		for (Cliente cli: clientes){
			if (cli.sosElCliente(idCliente)==true)
				res=cli;
		}
		if (res==null)
			res=Cliente.buscarCliente(idCliente);
		
		return res;
	}
	
	
	public Role makeRol(String tipoRol) {
		Role rol = null;
		if (tipoRol.compareToIgnoreCase("Zona Entrega") == 0)
			rol = new RolZonaEntrega();
		else if (tipoRol.compareToIgnoreCase("CallCenter") == 0)
			rol = new RolCallCenter();
		else if (tipoRol.compareToIgnoreCase("Distribucion") == 0)
			rol = new RolDistribucion();
		else if (tipoRol.compareToIgnoreCase("Consulta") == 0)
			rol = new RolConsulta();
		else if (tipoRol.compareToIgnoreCase("Administrador") == 0)
			rol = new RolAdministrador();
		else if (tipoRol.compareToIgnoreCase("Facturacion") == 0)
			rol = new RolFacturacion();
		return rol;

	}

	public Reclamo generarTipoReclamo(String tipoReclamo) {
		Reclamo rec = null;

		if (tipoReclamo.compareToIgnoreCase("Zona Entrega") == 0)
			rec = new ReclamoSimpleZona();
		else if (tipoReclamo.compareToIgnoreCase("faltante") == 0)
			rec = new ReclamoSimpleFaltante();
		else if (tipoReclamo.compareToIgnoreCase("Cantidad") == 0)
			rec = new ReclamoSimpleCantidad();
		else if (tipoReclamo.compareToIgnoreCase("Producto") == 0)
			rec = new ReclamoSimpleProducto();
		else if (tipoReclamo.compareToIgnoreCase("Facturacion") == 0)
			rec = new ReclamoSimpleFacturacion();
		return rec;

	}
	//modificado despues de la entrga
	public int obtenerIdTratamiento(){
		int respuesta=0;
		if (Tratamiento.getIdEstatico()==0)
			respuesta= Tratamiento.buscarIdEstatico();
		else {
			respuesta= Tratamiento.getIdEstatico();
		}
		respuesta++;
		Tratamiento.setIdEstatico(respuesta);
		return respuesta;
	}
	
	//hasta aca
	public int obtenerIdReclamo(){
		int respuesta=0;
		if (Reclamo.getIdEstatico()==0)
			respuesta= Reclamo.buscarIdEstatico();
		else {
			respuesta= Reclamo.getIdEstatico();
		}
		respuesta++;
		Reclamo.setIdEstatico(respuesta);
		return respuesta;
	}
	public void insertarReclamo(ArrayList<String> tipoReclamos, int idCliente,String descrip, int codigoProducto) {
		Reclamo rec = null;
		int id= obtenerIdReclamo();
		ArrayList<Reclamo> conjunto = new ArrayList<Reclamo>();
		int cantReclamos = tipoReclamos.size();
		for (String tipo : tipoReclamos) {
			rec = this.generarTipoReclamo(tipo);
			conjunto.add(rec);
		}
		if (cantReclamos == 1) {
			Cliente cli= buscarCliente(idCliente);
			rec.setCliente(cli);
			Producto pro= buscarProducto(codigoProducto);
			rec.setProducto(pro);
			rec.setDescripcion(descrip);
			rec.setIdReclamo(id);
			((ReclamoSimple) rec).insertarReclamo(); 
		} else {
			rec = new ReclamoCompuesto(conjunto);
			Cliente cli= buscarCliente(idCliente);
			rec.setCliente(cli);
			Producto pro= buscarProducto(codigoProducto);
			rec.setProducto(pro);
			rec.setDescripcion(descrip);
			rec.setIdReclamo(id);
			((ReclamoCompuesto) rec).insertarReclamo();
			
		}		
		
		reclamos.add(rec);
	}

	
	public void cerrarReclamo(int idReclamo) {
		Reclamo rec = buscarReclamo(idReclamo);
		rec.cerrar();
	}

	public void tratarReclamo(String detalle, int idReclamo) {
		Tratamiento tra = new Tratamiento(detalle);
		int id= obtenerIdTratamiento();
		tra.setIdTratamiento(id);
		Reclamo rec = buscarReclamo(idReclamo);
		Tratamiento.insertarTratamientoEnBD(tra);
		rec.insertarTratamientoEnReclamo(tra);
		tratamientos.add(tra);
		Tratamiento.insertarTratamientoEnBD(tra);
	}

	
	public void agregarRolAUsuario(String tipoRol, int idUsuario) {
		Usuario user = buscarUsuario(idUsuario);
		Rol rol = generarTipoRol(tipoRol);
		user.agregarRol(rol);
	}

	
	public void quitarRolAUsuario(String tipoRol, int idUsuario) {
		Usuario user = buscarUsuario(idUsuario);
		Rol rol = generarTipoRol(tipoRol);
		user.quitarRol(rol);
	}



	public boolean existeUsuario(int idUsuario, String contrasenia) {
		boolean respuesta= false;
		Usuario user= buscarUsuario(idUsuario);
		if (user.getUserPassword().compareToIgnoreCase(contrasenia)== 0){// = 0 significa que es igual
			respuesta=true;
		}
		return respuesta;
	}
	
	public ArrayList<ClientView> mayorCantidadReclamos(){
		
		ArrayList <ClientView> respuesta= new ArrayList <ClientView>();
		ArrayList <Reclamo> vacio= new ArrayList<Reclamo>();
		Cliente primero=new Cliente();
		Cliente segundo=new Cliente();
		Cliente tercero=new Cliente();
		primero.setReclamos(vacio);
		segundo.setReclamos(vacio);
		tercero.setReclamos(vacio);
		clientes= Cliente.buscarClientes();
		for (Cliente cli: clientes){
			if (cli.getReclamos().size()>=primero.getReclamos().size()){
				tercero=segundo;
				segundo=primero;
				primero=cli;
			} else {
				if (cli.getReclamos().size()>=segundo.getReclamos().size()){
				tercero=segundo;
				segundo=cli;
				
			}else {
				if (cli.getReclamos().size()>=tercero.getReclamos().size()){
					tercero=cli;
				}
					
				}
		}
		}
		if (primero.getReclamos().size()>0)
			respuesta.add(primero.toView());
		if (segundo.getReclamos().size()>0)
			respuesta.add(segundo.toView());
		if (tercero.getReclamos().size()>0)
			respuesta.add(tercero.toView());
		return respuesta;
	}
	
	public int cantidadReclamosPorMes(String mes){
		reclamos= Reclamo.buscarReclamos();
		int contador=0;
		for (Reclamo rec:reclamos){
			if (mes.compareTo(rec.getFechaIngreso().substring(4,7))==0)
				contador++;
		}
		return contador;
	}

	public ArrayList<UsuarioView> MayorCantidadTratamientos(){
		
		ArrayList <UsuarioView> respuesta= new ArrayList <UsuarioView>();
		ArrayList <Tratamiento> vacio= new ArrayList<Tratamiento>();
		Usuario primero=new Usuario();
		Usuario segundo=new Usuario();
		Usuario tercero=new Usuario();
		primero.setTratamientos(vacio);
		segundo.setTratamientos(vacio);
		tercero.setTratamientos(vacio);
		usuarios= Usuario.buscarUsuarios();
		for (Usuario user: usuarios){
			if (user.getTratamientos().size()>=primero.getTratamientos().size()){
				tercero=segundo;
				segundo=primero;
				primero=user;
			} else {
				if (user.getTratamientos().size()>=segundo.getTratamientos().size()){
				tercero=segundo;
				segundo=user;
				
			}else {
				if (user.getTratamientos().size()>=tercero.getTratamientos().size()){
					tercero=user;
				}
					
				}
		}
		}
		if (primero.getTratamientos().size()>0)
			respuesta.add(primero.toView());
		if (segundo.getTratamientos().size()>0)
			respuesta.add(segundo.toView());
		if (tercero.getTratamientos().size()>0)
			respuesta.add(tercero.toView());
		return respuesta;
	}
	public float tiempoPromedioPorUsuario(int idUsuario){
		Usuario user= Controlador.getInstancia().buscarUsuario(idUsuario);
		SimpleDateFormat parseador = new SimpleDateFormat("dd-MM-yy hh:mm:ss");
	
		Date fechaReclamo= null;
		Date fechaTratamiento=null;
		int contador=0;
		float total=0;
		for (Tratamiento tra: user.getTratamientos()){
			for (ReclamoView rec: Controlador.getInstancia().buscarReclamos()){
				if (rec.getTratamiento().getIdTratamiento()== tra.getIdTratamiento()){
					float minutos=0;
					try {
						fechaReclamo= parseador.parse(rec.getFecha());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						
						fechaTratamiento= parseador.parse(tra.getFechaTratamiento());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					minutos =(fechaTratamiento.getTime() - fechaReclamo.getTime());
					total=total + minutos;
					contador++;
				}
			}
		}
		total= (total/contador);
		return (total/(1000*60));
	}

}
