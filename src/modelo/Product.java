package modelo;

import dao.ProductDAO;
import view.ProductView;

import java.util.ArrayList;

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
		return new ProductView(this.productCode, this.title);
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

	// METODOS

	public void addProduct(Product product) {

	}

	public void removeProduct(Product product) {

	}

	public static ArrayList<Product> getProducts() {
		return ProductDAO.getInstancia().getProducts();
	}

}
