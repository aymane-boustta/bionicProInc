package bionicProInc.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "materials")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Material")
@XmlType(propOrder = { "name", "price", "amount" })
public class Material implements Serializable {

	private static final long serialVersionUID = 7510802237290550541L;
	@Id
	@GeneratedValue(generator = "materials")
	@TableGenerator(name = "materials", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "materials")

	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private float price;
	@XmlAttribute
	private int amount;
	@ManyToMany
	@XmlTransient
	private List<Product> products;

	public Material(int id, String name, float price, int amount, List<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.products = products;
	}

	public Material(int id, String name, float price, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.products = new ArrayList<Product>();
	}

	public Material(int id, String name, int amount) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.products = new ArrayList<Product>();
	}

	public Material(String name, float price, int amount) {
		super();
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.products = new ArrayList<Product>();
	}

	public Material(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
		this.products = new ArrayList<Product>();
	}

	public Material(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public List<Product> getProducts() {
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
		return "\n Material ID: " + id + ", Name: " + name + ", Price: " + price + ", Amount: " + amount;
	}

}
