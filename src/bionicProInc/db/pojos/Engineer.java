package bionicProInc.db.pojos;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "engineers")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Engineer")
@XmlType(propOrder = {"name_surname", "email", "contract_starting_date","contract_ending_date","current_service","salary","bonus","project_achieved","experience_in_years","date_of_birth" })

public class Engineer implements Serializable {
	
	private static final long serialVersionUID = 7354779387678883946L;
	
	@Id
	@GeneratedValue(generator="engineers")
	@TableGenerator(name="engineers", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="engineers")	
	@XmlTransient
	private int id;
	@XmlAttribute
	private String name_surname;
	@XmlAttribute
	private String email;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date contract_starting_date;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date contract_ending_date;
	@XmlAttribute
	private String current_service;
	@XmlAttribute
	private float salary;
	@XmlAttribute
	private float bonus;
	@XmlAttribute
	private int project_achieved;
	@XmlAttribute
	private int experience_in_years;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date date_of_birth;
	@ManyToMany
	@XmlTransient
	private List<Product> products;

	public Engineer(int id, String name_surname, String email, Date contract_strating_date, Date contract_ending_date,
			String current_service, float salary, float bonus, int project_achieved, int experience_in_years,
			Date date_of_birth, List<Product> products) {
		super();
		this.id = id;
		this.name_surname = name_surname;
		this.email = email;
		this.contract_starting_date = contract_strating_date;
		this.contract_ending_date = contract_ending_date;
		this.current_service = current_service;
		this.salary = salary;
		this.bonus = bonus;
		this.project_achieved = project_achieved;
		this.experience_in_years = experience_in_years;
		this.date_of_birth = date_of_birth;
		this.products = products;
	}

	public Engineer(int id, String name_surname, String email, Date contract_strating_date, Date contract_ending_date,
			String current_service, float salary, float bonus, int project_achieved, int experience_in_years,
			Date date_of_birth) {
		super();
		this.id = id;
		this.name_surname = name_surname;
		this.email = email;
		this.contract_starting_date = contract_strating_date;
		this.contract_ending_date = contract_ending_date;
		this.current_service = current_service;
		this.salary = salary;
		this.bonus = bonus;
		this.project_achieved = project_achieved;
		this.experience_in_years = experience_in_years;
		this.date_of_birth = date_of_birth;
		this.products = new ArrayList<Product>();
	}

	public Engineer(String name_surname, String email, Date contract_strating_date, Date contract_ending_date,
			String current_service, float salary, float bonus, int project_achieved, int experience_in_years,
			Date date_of_birth) {
		super();
		this.name_surname = name_surname;
		this.email = email;
		this.contract_starting_date = contract_strating_date;
		this.contract_ending_date = contract_ending_date;
		this.current_service = current_service;
		this.salary = salary;
		this.bonus = bonus;
		this.project_achieved = project_achieved;
		this.experience_in_years = experience_in_years;
		this.date_of_birth = date_of_birth;
		this.products = new ArrayList<Product>();
	}

	public Engineer(int id, String name_surname,int project_achieved) {
		super();
		this.id = id;
		this.name_surname = name_surname;
		this.project_achieved = project_achieved;
	}

	public Engineer() {
		super();
		this.products = new ArrayList<Product>();
	}

	public Engineer(int id, float bonus) {
		super();
		this.id = id;
		this.bonus = bonus;
	}

	public Engineer(int id, String name_surname) {
		super();
		this.id = id;
		this.name_surname = name_surname;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getContract_strating_date() {
		return contract_starting_date;
	}

	public void setContract_strating_date(Date contract_strating_date) {
		this.contract_starting_date = contract_strating_date;
	}

	public Date getContract_ending_date() {
		return contract_ending_date;
	}

	public void setContract_ending_date(Date contract_ending_date) {
		this.contract_ending_date = contract_ending_date;
	}

	public String getCurrent_service() {
		return current_service;
	}

	public void setCurrent_service(String current_service) {
		this.current_service = current_service;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float getBonus() {
		return bonus;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public int getProject_achieved() {
		return project_achieved;
	}

	public void setProject_achieved(int project_achieved) {
		this.project_achieved = project_achieved;
	}

	public int getExperience_in_years() {
		return experience_in_years;
	}

	public void setExperience_in_years(int experience_in_years) {
		this.experience_in_years = experience_in_years;
	}

	public Date getDate_of_birth() {
		return date_of_birth;
	}

	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
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
		Engineer other = (Engineer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String showID() {
		return "[id=" + this.id + ", name_surname=" + this.name_surname + "]";
	}

	@Override
	public String toString() {
		return "Engineer ID: " + id + ", Full name: " + name_surname + ", Email address: " + email
				+ ", Contract starting date: " + contract_starting_date + ", Contract ending date: "
				+ contract_ending_date + ", Current service: " + current_service + ", Salary: " + salary + ", Bonus: "
				+ bonus + ", Number of projects achieved: " + project_achieved + ", Years of experience: "
				+ experience_in_years + ", Date of birth:" + date_of_birth;
	}

}
