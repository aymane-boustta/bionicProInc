package bionicProInc.db.pojos;

import java.io.Serializable;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5234210426301713165L;
	private int id;
	private int customer_id;
	private int product_id;

	public Order(int id, int customer_id, int product_id) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.product_id = product_id;
	}
	

	public Order() {
		super();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customer_id;
		result = prime * result + id;
		result = prime * result + product_id;
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
		if (customer_id != other.customer_id)
			return false;
		if (id != other.id)
			return false;
		if (product_id != other.product_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer_id=" + customer_id + ", product_id=" + product_id + "]";
	}

}
