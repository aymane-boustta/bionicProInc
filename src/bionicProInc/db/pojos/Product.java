package bionicProInc.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bionicProInc.db.xml.utils.SQLDateAdapter;

@Entity
@Table(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Product")
@XmlType(propOrder = { "name", "bodypart", "price", "date_creation", "photo", "characteristics", "materials",
		"engineers" })
public class Product implements Serializable {

	private static final long serialVersionUID = -2448117025953730410L;
	@Id
	@GeneratedValue(generator = "products")
	@TableGenerator(name = "products", table = "sqlite_sequence", pkColumnName = "name", valueColumnName = "seq", pkColumnValue = "products")

	@XmlTransient
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String bodypart;
	@XmlAttribute
	private Float price;
	@XmlAttribute
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date date_creation;
	@XmlAttribute
	private byte[] photo;
	@ManyToMany
	@JoinTable(name = "products_characteristics", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "characteristic_id", referencedColumnName = "id") })
	@XmlElement(name = "characteristics")
	@XmlElementWrapper(name = "characteristics")
	private List<Characteristic> characteristics;
	@ManyToMany
	@JoinTable(name = "engineers_products", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "engineer_id", referencedColumnName = "id") })
	@XmlElement(name = "engineers")
	@XmlElementWrapper(name = "engineers")
	private List<Engineer> engineers;
	@ManyToMany
	@JoinTable(name = "products_materials", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "material_id", referencedColumnName = "id") })
	@XmlElement(name = "materials")
	@XmlElementWrapper(name = "materials")
	private List<Material> materials;

	public Product(int id, String name, String bodypart, Float price, LocalDate date_creation, byte[] photo,
			List<Characteristic> characteristics, List<Engineer> engineer, List<Material> materials) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.setDate_creation(date_creation);
		this.photo = photo;
		this.characteristics = characteristics;
		this.engineers = engineer;
		this.materials = materials;
	}

	public Product(int id, String name, String bodypart, Float price, LocalDate date_creation, byte[] photo,
			List<Characteristic> characteristics, List<Material> materials) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.setDate_creation(date_creation);
		this.photo = photo;
		this.characteristics = characteristics;
		this.engineers = new ArrayList<Engineer>();
		this.materials = materials;
	}

	public Product(int id, String name, String bodypart, Float price, LocalDate date_creation, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.setDate_creation(date_creation);
		this.photo = photo;
		this.characteristics = new ArrayList<Characteristic>();
		this.engineers = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();
	}

	public Product(String name, String bodypart, Float price, LocalDate date_creation, byte[] photo) {
		super();
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.setDate_creation(date_creation);
		this.photo = photo;
		this.characteristics = new ArrayList<Characteristic>();
		this.engineers = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();

	}

	public Product(int id, String name, String bodypart, Float price, LocalDate date_creation) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.setDate_creation(date_creation);
		this.characteristics = new ArrayList<Characteristic>();
		this.engineers = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();

	}

	public Product(String name, String bodypart, Float price, LocalDate date_creation) {
		super();
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.setDate_creation(date_creation);
		this.characteristics = new ArrayList<Characteristic>();
		this.engineers = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();

	}

	public Product() {
		super();
		this.characteristics = new ArrayList<Characteristic>();
		this.engineers = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();
	}

	public Product(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public String getBodypart() {
		return bodypart;
	}

	public void setBodypart(String bodypart) {
		this.bodypart = bodypart;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public LocalDate getDate_creation() {
		return this.date_creation.toLocalDate();
	}

	public void setDate_creation(LocalDate date_creation) {
		this.date_creation = Date.valueOf(date_creation);
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public List<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public List<Engineer> getEngineers() {
		return engineers;
	}

	public void setEngineer(ArrayList<Engineer> engineers) {
		this.engineers = engineers;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(ArrayList<Material> materials) {
		this.materials = materials;
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toStringCustomer() {
		return "\nProduct ID: " + id + ", Name: " + name + ", Bodypart: " + bodypart + ", Price: " + price
				+ ", Date of creation: " + date_creation + ", Photo = " + Arrays.toString(photo)
				+ ", \nCharacteristics: " + characteristics.toString();
	}

	@Override
	public String toString() {
		return "\nProduct ID: " + id + ", Name: " + name + ", Bodypart: " + bodypart + ", Price: " + price
				+ ", Date of creation: " + date_creation + ", Photo = " + Arrays.toString(photo)
				+ ", \nCharacteristics: " + characteristics.toString() + ", \nMaterials: " + materials.toString();
	}

}
