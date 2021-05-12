package bionicProInc.db.pojos;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Engineer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7354779387678883946L;
	private int id;
	private String name_surname;
	private String email;
	private Date contract_strating_date;
	private Date contract_ending_date;
	private String current_service;
	private float salary;
	private float bonus;
	private int project_achieved;
	private int experience_in_years;
	private Date date_of_birth;
	private List<Product> products;

	public Engineer(String name_surname, String email, Date contract_strating_date, Date contract_ending_date,
			String current_service, float salary, float bonus, int project_achieved, int experience_in_years,
			Date date_of_birth, List<Product> products) {
		super();
		this.name_surname = name_surname;
		this.email = email;
		this.contract_strating_date = contract_strating_date;
		this.contract_ending_date = contract_ending_date;
		this.current_service = current_service;
		this.salary = salary;
		this.bonus = bonus;
		this.project_achieved = project_achieved;
		this.experience_in_years = experience_in_years;
		this.date_of_birth = date_of_birth;
		this.products = products;
	}

	public Engineer(String name_surname, String email, Date contract_strating_date, Date contract_ending_date,
			String current_service, float salary, float bonus, int project_achieved, int experience_in_years,
			Date date_of_birth) {
		super();
		this.name_surname = name_surname;
		this.email = email;
		this.contract_strating_date = contract_strating_date;
		this.contract_ending_date = contract_ending_date;
		this.current_service = current_service;
		this.salary = salary;
		this.bonus = bonus;
		this.project_achieved = project_achieved;
		this.experience_in_years = experience_in_years;
		this.date_of_birth = date_of_birth;
		this.products = new ArrayList<Product>();
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
		return contract_strating_date;
	}

	public void setContract_strating_date(Date contract_strating_date) {
		this.contract_strating_date = contract_strating_date;
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
		return "Engineer [id=" + id + ", name_surname=" + name_surname + ", email=" + email
				+ ", contract_strating_date=" + contract_strating_date + ", contract_ending_date="
				+ contract_ending_date + ", current_service=" + current_service + ", salary=" + salary + ", bonus="
				+ bonus + ", project_achieved=" + project_achieved + ", experience_in_years=" + experience_in_years
				+ ", date_of_birth=" + date_of_birth + ", products=" + products + "]";
	}

}
