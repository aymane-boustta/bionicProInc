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
@Table(name = "characteristics")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Characteristic")
@XmlType(propOrder = { "length", "width", "height", "weight", "joint_numb", "flexibility_scale" })
public class Characteristic implements Serializable {

	private static final long serialVersionUID = -8095684428083579778L;
	@Id
	@GeneratedValue(generator = "characteristics")
	@TableGenerator(name = "characteristics", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "characteristics")

	@XmlTransient
	private int id;
	@XmlAttribute
	private float length;
	@XmlAttribute
	private float width;
	@XmlAttribute
	private float height;
	@XmlAttribute
	private float weight;
	@XmlAttribute
	private int joint_numb;
	@XmlAttribute
	private int flexibility_scale;
	@ManyToMany
	@XmlTransient
	private List<Product> products;

	public Characteristic(int id, float length, float width, float height, float weight, int joint_numb,
			int flexibilty_scale, List<Product> products) {
		this.id = id;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.joint_numb = joint_numb;
		this.flexibility_scale = flexibilty_scale;
		this.products = products;
	}

	public Characteristic(int id, float length, float width, float height, float weight, int joint_numb,
			int flexibilty_scale) {
		this.id = id;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.joint_numb = joint_numb;
		this.flexibility_scale = flexibilty_scale;
		this.products = new ArrayList<Product>();
	}

	public Characteristic(float length, float width, float height, float weight, int joint_numb, int flexibilty_scale) {
		super();
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.joint_numb = joint_numb;
		this.flexibility_scale = flexibilty_scale;
		this.products = new ArrayList<Product>();
	}

	public Characteristic(int id, float length) {
		super();
		this.id = id;
		this.length = length;
		this.products = new ArrayList<Product>();
	}

	public Characteristic() {
		super();
		this.products = new ArrayList<Product>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public int getJoint_numb() {
		return joint_numb;
	}

	public void setJoint_numb(int joint_numb) {
		this.joint_numb = joint_numb;
	}

	public int getFlexibilty_scale() {
		return flexibility_scale;
	}

	public void setFlexibilty_scale(int flexibilty_scale) {
		this.flexibility_scale = flexibilty_scale;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
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
		Characteristic other = (Characteristic) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n Characteristic ID: " + id + ", Length: " + length + ", Width: " + width + ", Height: " + height
				+ ", Weight: " + weight + ", Number of joints: " + joint_numb + ", Flexibility scale (1-10): "
				+ flexibility_scale;
	}

}