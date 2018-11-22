package modelo;

import dao.ProductDAO;
import excepciones.PKDuplicadaException;
import view.ProductView;

public class Product {

	private String productCode;
	private String title;
	private String description;
	private float price;

	public Product(String productCode, String title, String description, float price) {
		super();
		this.productCode = productCode;
		this.title = title;
		this.description = description;
		this.price = price;
	}

	public ProductView toView() {
		return new ProductView(this.title, this.productCode);
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void save() throws PKDuplicadaException {
		ProductDAO.getInstancia().addProduct(this);
	}

	public void remove() {
		ProductDAO.getInstancia().removeProduct(this);
	}

	public void update() {
		ProductDAO.getInstancia().updateProduct(this);		
	}

}
