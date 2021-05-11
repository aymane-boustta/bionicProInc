package bionicProInc.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;

public class Material implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7510802237290550541L;
	private int id;
	private String name;
	private float price;
	private int amount;
	private ArrayList<Product> products;

	public Material(String name, float price, int amount, ArrayList<Product> products) {
		super();
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.products = products;
	}

	public Material(String name, float price, int amount) {
		super();
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.products = new ArrayList<Product>();
	}

	public Material() {
		super();
		this.products = new ArrayList<Product>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + ", price=" + price + ", amount=" + amount + "]";
	}

}
