package view;

public class ProductView {
	private String name;
	private String id;
	public ProductView(String title, String productCode) {
		super();
		this.name = title;
		this.id = productCode;
	}
	public String getName() {
		return name;
	}
	public String getId() {
		return id;
	}
	
	
}
