package bionicProInc.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bionicProInc.db.xml.utils.SQLDateAdapter;

@Entity
@Table(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Product")
@XmlType(propOrder = { "name", "bodypart", "employees","price","date_creation","characteristics","engineers","materials" })
public class Product implements Serializable {
	
	private static final long serialVersionUID = -2448117025953730410L;
	@Id
	@GeneratedValue(generator="products")
	@TableGenerator(name="products", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="products")	

	@XmlAttribute
	private int id;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String bodypart;
	@XmlAttribute
	private Float price;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date date_creation;
	private byte[] photo;
	@OneToMany(mappedBy="product")
	@XmlElement(name = "Characteristic")
    @XmlElementWrapper(name = "characteristics")
	private ArrayList<Characteristic> characteristics;
	@OneToMany(mappedBy="product")
	@XmlElement(name = "Engineer")
    @XmlElementWrapper(name = "engineers")
	private ArrayList<Engineer> engineer;
	@OneToMany(mappedBy="product")
	@XmlElement(name = "Material")
    @XmlElementWrapper(name = "materials")
	private ArrayList<Material> materials;

	public Product(int id, String name, String bodypart, Float price, Date date_creation, byte[] photo,
			ArrayList<Characteristic> characteristics, ArrayList<Engineer> engineer, ArrayList<Material> materials) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.date_creation = date_creation;
		this.photo = photo;
		this.characteristics = characteristics;
		this.engineer = engineer;
		this.materials = materials;
	}

	public Product(int id, String name, String bodypart, Float price, Date date_creation, byte[] photo,
			ArrayList<Characteristic> characteristics, ArrayList<Material> materials) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.date_creation = date_creation;
		this.photo = photo;
		this.characteristics = characteristics;
		this.engineer = new ArrayList<Engineer>();
		this.materials = materials;
	}

	public Product(int id, String name, String bodypart, Float price, Date date_creation, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.date_creation = date_creation;
		this.photo = photo;
		this.characteristics = new ArrayList<Characteristic>();
		this.engineer = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();
	}

	public Product(String name, String bodypart, Float price, Date date_creation, byte[] photo) {
		super();
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.date_creation = date_creation;
		this.photo = photo;
		this.characteristics = new ArrayList<Characteristic>();
		this.engineer = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();

	}

	public Product(int id, String name, String bodypart, Float price, Date date_creation) {
		super();
		this.id = id;
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.date_creation = date_creation;
		this.characteristics = new ArrayList<Characteristic>();
		this.engineer = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();

	}

	public Product(String name, String bodypart, Float price, Date date_creation) {
		super();
		this.name = name;
		this.bodypart = bodypart;
		this.price = price;
		this.date_creation = date_creation;
		this.characteristics = new ArrayList<Characteristic>();
		this.engineer = new ArrayList<Engineer>();
		this.materials = new ArrayList<Material>();

	}

	public Product() {
		super();
		this.characteristics = new ArrayList<Characteristic>();
		this.engineer = new ArrayList<Engineer>();
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

	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public ArrayList<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(ArrayList<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public ArrayList<Engineer> getEngineer() {
		return engineer;
	}

	public void setEngineer(ArrayList<Engineer> engineer) {
		this.engineer = engineer;
	}

	public ArrayList<Material> getMaterials() {
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

	@Override
	public String toString() {
		return "\nProduct ID: " + id + ", Name: " + name + ", Bodypart: " + bodypart + ", Price: " + price
				+ ", Date of creation: " + date_creation + ", Photo = " + Arrays.toString(photo)
				+ ", \nCharacteristics: " + characteristics.toString() + ", \nMaterials: " + materials.toString();
	}

}
