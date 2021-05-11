package bionicProInc.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5234210426301713165L;
	private int id;
	private int customer_id;
	private int product_id;
	private List<Product> products;

	public Order(int customer_id, int product_id, List<Product> products) {
		super();
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.products = products;
	}

	public Order(int customer_id, int product_id) {
		super();
		this.customer_id = customer_id;
		this.product_id = product_id;
		this.products = new ArrayList<Product>();
	}

	public Order() {
		super();
		this.products = new ArrayList<Product>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product prod) {
		if (!products.contains(prod)) {
			products.add(prod);
		}
	}

	public void removeProduct(int id) {
		for (int i = 1; i <= this.products.size() - 1; i++) {
			int pId = this.products.get(i).getId();
			if (pId == id) {
				this.products.remove(i);
			}
		}
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
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", product_id=" + product_id + ", products="
				+ products + "]";
	}

}
