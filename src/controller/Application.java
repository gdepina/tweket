package controller;

import java.util.ArrayList;
import java.util.List;

import modelo.*;
import view.ClientView;
import view.ProductView;
import view.TicketView;

public class Application {
    public List<Client> clients;
    public List<Product> products;
    public List<User> users;
    public List<Ticket> tickets;
    public static Application instancia;

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

    public Role makeRol(String type) {

//		if (tipoRol.compareToIgnoreCase("Zona Entrega") == 0)
//			rol = new Role(tipoRol);
//		else if (tipoRol.compareToIgnoreCase("CallCenter") == 0)
//			rol = new RolCallCenter();
//		else if (tipoRol.compareToIgnoreCase("Distribucion") == 0)
//			rol = new RolDistribucion();
//		else if (tipoRol.compareToIgnoreCase("Consulta") == 0)
//			rol = new RolConsulta();
//		else if (tipoRol.compareToIgnoreCase("DashAdmin") == 0)
//			rol = new RolAdministrador();
//		else if (tipoRol.compareToIgnoreCase("Facturacion") == 0)
//			rol = new RolFacturacion();
        return new Role(type);

    }

    public ArrayList<TicketView> getTickets(String type) {
        ArrayList<TicketView> ticketView = new ArrayList<TicketView>();
        for (Ticket tck : Ticket.getTickets(type)) {
            ticketView.add(tck.toView());
        }
        return ticketView;
    }

    public ArrayList<String> getRolesByUser(String userName) {
        User user = getUser(userName);
        ArrayList<String> roles = new ArrayList<String>();
        for (Role rol : user.getRoles()) {
            roles.add(rol.getType());
        }
        return roles;
    }


    public Ticket getTicket(int ticketNumber) {
        Ticket res = null;
        for (Ticket tck : tickets) {
            if (tck.getTicketNumber() == ticketNumber) {
                res = tck;
            }
        }
        if (res == null)
            res = Ticket.getTicket(ticketNumber);
        return res;
    }


    public ArrayList<ClientView> getClients() {
        ArrayList<ClientView> clientesView = new ArrayList<ClientView>();
        for (Client cli : Client.getClients()) {
            clientesView.add(cli.toView());
        }
        return clientesView;
    }

    public Product getProduct(String code) {
        Product res = null;
        for (Product pro : products) {
            if (pro.getProductCode() == code) {
                res = pro;
            }
        }
        if (res == null)
            res = Product.getProduct(code);

        return res;
    }


    public ArrayList<ProductView> getProducts() {
        ArrayList<ProductView> productViews = new ArrayList<ProductView>();
        for (Product prod : Product.getProducts()) {
            productViews.add(prod.toView());
        }
        return productViews;
    }


    public Client getClient(int clientId) {
        Client res = null;
        for (Client client : clients) {
            if (client.getId() == clientId) {
                res = client;
            }
        }
        if (res == null) {
            res = Client.getClient(clientId);
        }
        return res;
    }


    public User getUser(String userName) {
        User res = null;
        for (User user : users) {
            if (user.getUserName() == userName) {
                res = user;
            }
        }
        if (res == null) {
            res = User.getUser(userName);
        }
        return res;
    }

    public boolean checkUser(String userId, String pass) {
        return User.logIn(userId, pass);
    }

    public Ticket makeTicketByType(String type) {
        Ticket tck = null;

        if (type.equals("Zona Entrega"))
            tck = new ZoneTicket(type);
        else if (type.equals("faltante") || type.equals("Cantidad") || type.equals("Producto"))
            tck = new ProductTicketContext(type);
        else if (type.equals("Facturacion"))
            tck = new BillingTicket(type);
        return tck;

    }

    public void saveTicket(ArrayList<String> tipoReclamos, int clientId, String descrip, String productCode) {
        Ticket tck = null;
        if (tipoReclamos.size() == 1) {
            tck = this.makeTicketByType(tipoReclamos.get(0));
            tck.setClient(getClient(clientId));
            tck.setProduct(getProduct(productCode));
            tck.setDescription(descrip);
            tck.addTicket(tck);
        } else {
            ArrayList<Ticket> tmpTickets = new ArrayList<Ticket>();
            for (String type : tipoReclamos) {
                tmpTickets.add(this.makeTicketByType(type));
            }
            tck= new TicketComposite(tmpTickets);
            tck.setClient(getClient(clientId));
            tck.setProduct(getProduct(productCode));
            tck.setDescription(descrip);
            tck.addTicket(tck);

        }

        tickets.add(tck);
    }


}
