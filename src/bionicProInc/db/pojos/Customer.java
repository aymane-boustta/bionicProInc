package bionicProInc.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8505884502597501683L;
	private int id;
	private String name_surname;
	private int age;
	private String gender;
	private int phone;
	private String email;
	private String street;
	private String city;
	private int postal_code;
	private List<Product> products;

	public Customer(int id, String name_surname, int age, String gender, int phone, String email, String street,
			String city, int postal_code, List<Product> products) {
		super();
		this.id = id;
		this.name_surname = name_surname;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.street = street;
		this.city = city;
		this.postal_code = postal_code;
		this.products = products;
	}

	public Customer(int id, String name_surname, int age, String gender, int phone, String email, String street,
			String city, int postal_code) {
		super();
		this.id = id;
		this.name_surname = name_surname;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.street = street;
		this.city = city;
		this.postal_code = postal_code;
		this.products = new ArrayList<Product>();
	}

	public Customer(int id, String name_surname) {
		super();
		this.id = id;
		this.name_surname = name_surname;
	}

	public Customer(String name_surname, int age, String gender, int phone, String email, String street, String city,
			int postal_code) {
		super();
		this.name_surname = name_surname;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
		this.street = street;
		this.city = city;
		this.postal_code = postal_code;
		this.products = new ArrayList<Product>();
	}

	public Customer() {
		super();
		this.products = new ArrayList<Product>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName_surname() {
		return name_surname;
	}

	public void setName_surname(String name_surname) {
		this.name_surname = name_surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setOrders(List<Product> products) {
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
		Customer other = (Customer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer ID: " + id + ", Full name: " + name_surname + ", Age: " + age + ", Gender: " + gender
				+ ", Mobile phone: " + phone + ", Email address: " + email + ", Street: " + street + ", Ciry: " + city
				+ "" + ", Postal code :" + postal_code;
	}

}