package bionicProInc.db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author macbookair
 *
 */
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2448117025953730410L;
	private int id;
	private String name;
	private String bodypart;
	private Float price;
	private Date date_creation;
	private byte[] photo;
	private ArrayList<Characteristic> characteristics;
	private ArrayList<Engineer> engineer;
	private ArrayList<Material> materials;

	public Product(int id, String name, String bodypart, Float price, Date date_creation, byte[] photo,
			ArrayList<Characteristic> characteristics, ArrayList<Engineer> engineer, ArrayList<Material> materials) {
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
			ArrayList<Characteristic> characteristics,ArrayList<Material> materials) {
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
		return "Product [id=" + id + ", name=" + name + ", bodypart=" + bodypart + ", price=" + price
				+ ", date_creation=" + date_creation + ", photo=" + Arrays.toString(photo) + ", characteristics="
				+ characteristics + ", materials=" + materials + "]";
	}

}
