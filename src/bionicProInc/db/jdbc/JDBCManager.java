package bionicProInc.db.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bionicProInc.db.pojos.*;
import bionicProInc.db.utils.inputOutput;
import bionicProInc.db.ifaces.DBManager;

public class JDBCManager implements DBManager {
	private Connection c;
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static inputOutput io = new inputOutput();

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/bionicsproInc.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			// this.createTables();
		} catch (SQLException sqlE) {
			System.out.println("There was a database exception.");
			sqlE.printStackTrace();
		} catch (Exception e) {
			System.out.println("There was a general exception.");
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			c.close();
			System.out.println("Database is closed .");
		} catch (SQLException e) {
			System.out.println("There was a problem while closing the database connection.");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void createTables() {

		try {

			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE products " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT UNIQUE,"
					+ " name TEXT NOT NULL, " + " bodypart  TEXT NOT NULL UNIQUE," + " price REAL NOT NULL,"
					+ " date_creation DATE NOT NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE characteristics " + "(id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
					+ " length REAL NOT NULL," + " width REAL NOT NULL," + " height REAL NOT NULL,"
					+ " weight REAL NOT NULL," + " joint_numb INTEGER NOT NULL,"
					+ " flexibility_scale INTEGER NOT NULL)";

			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE materials " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT UNIQUE,"
					+ " name     TEXT     NOT NULL UNIQUE, " + " price REAL NOT NULL," + " amount   INTEGER	 NOT NULL)";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE engineers " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT UNIQUE,"
					+ " name_surname     TEXT     NOT NULL UNIQUE, " + " email     TEXT     NOT NULL UNIQUE, "
					+ " contract_starting_date DATE NOT NULL," + " contract_ending_date DATE NOT NULL,"
					+ " current_service TEXT NOT NULL," + " salary REAL NOT NULL," + " bonus REAL NOT NULL,"
					+ " project_achieved INTEGER NOT NULL," + " experience_in_years INTEGER NOT NULL,"
					+ " date_of_birth DATE NOT NULL)";

			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE customers " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT UNIQUE,"
					+ " name_surname     TEXT     NOT NULL UNIQUE, " + " age INTEGER NOT NULL,"
					+ " gender TEXT NOT NULL," + " phone INTEGER NOT NULL," + " email TEXT NOT NULL,"
					+ " street TEXT NOT NULL," + " city TEXT NOT NULL," + " postal_code INTEGER NOT NULL)";
			stmt5.executeUpdate(sql5);
			stmt5.close();

			// Now we create the table that references the Many-to-Many relationships

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE products_characteristics " + "(product_id INTEGER REFERENCES products(id),"
					+ " characteristic_id INTEGER REFERENCES characteristics(id),"
					+ "PRIMARY KEY (product_id, characteristic_id))";
			stmt6.execute(sql6);
			stmt6.close();

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE products_materials " + "(product_id  INTEGER REFERENCES products(id),"
					+ " material_id INTEGER REFERENCES materials(id)," + "PRIMARY KEY (product_id,material_id))";
			stmt7.executeUpdate(sql7);
			stmt7.close();

			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE engineers_products " + "(engineer_id INTEGER REFERENCES engineers(id),"
					+ " product_id INTEGER REFERENCES products(id)," + "PRIMARY KEY (engineer_id,product_id))";
			stmt8.executeUpdate(sql8);
			stmt8.close();

			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE customers_products " + "(customer_id INTEGER REFERENCES customers(id),"
					+ " product_id INTEGER REFERENCES products(id)," + "PRIMARY KEY (customer_id,product_id))";

			stmt9.execute(sql9);
			stmt9.close();

		} catch (SQLException e) {
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addCustomer(Customer cust) {
		try {
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO customers(name_surname, age, gender, phone, email, street, city, postal_code) "
					+ " VALUES ('" + cust.getName_surname() + "', '" + cust.getAge() + "','" + cust.getGender() + "','"
					+ cust.getPhone() + "', " + "'" + cust.getEmail() + "','" + cust.getStreet() + "','"
					+ cust.getCity() + "','" + cust.getPostal_code() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Customer getCustomer(int id) {
		Customer cust = new Customer();
		try {
			String sql = "SELECT id,name_surname FROM customers WHERE id= ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name_surname = rs.getString("name_surname");
				cust = new Customer(id, name_surname);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cust;
	}

	@Override
	public int getCustomerID(String email) {
		int id = 0;
		try {
			String sql = "SELECT id FROM customers WHERE email LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + email + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public void addProduct(Product prod) {
		try {
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO products (name,bodypart, price, date_creation) " + " VALUES('" + prod.getName()
					+ "','" + prod.getBodypart() + "','" + prod.getPrice() + "','" + prod.getDate().getTime() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product getProduct(int id) {
		Product prod = new Product();
		try {
			String sql = "SELECT id,name FROM products WHERE id= ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				prod = new Product(id, name);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}

	@Override
	public void removeProduct(int id) {
		try {
			String sql = "DELETE FROM products WHERE id = ? ";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> viewAllProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			String sql = "SELECT id,name,bodypart,price,date_creation FROM products";
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String bodypart = rs.getString("bodypart");
				float price = rs.getFloat("price");
				String string = rs.getString("date_creation");
				LocalDate date_creation = LocalDate.parse(string);
				ArrayList<Characteristic> characteristics = viewCharacteristicsFromProduct(id);
				ArrayList<Material> materials = viewMaterialsFromProduct(id);
				Product prod = new Product(id, name, bodypart, price, date_creation, characteristics, materials);
				products.add(prod);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product viewProduct(int id) {
		Product prod = new Product();
		try {
			String sql = "SELECT name,bodypart,price,date_creation FROM products WHERE id = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String bodypart = rs.getString("bodypart");
				float price = rs.getFloat("price");
				Date date_creation = rs.getDate("date_creation");
				ArrayList<Characteristic> characteristics = viewCharacteristicsFromProduct(id);
				ArrayList<Material> materials = viewMaterialsFromProduct(id);
				prod = new Product(id, name, bodypart, price, date_creation, characteristics, materials);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prod;
	}

	@Override
	public int getProductID(String name) {
		int id = 0;
		try {
			String sql = "SELECT id FROM products WHERE name LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean addCust_Prod(Customer cust, Product prod) {
		try {
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO customers_products (customer_id, product_id) " + " VALUES ('" + cust.getId()
					+ "','" + prod.getId() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void removeCust_Prod(Product prod) {
		try {
			String sql = "DELETE FROM customers_products WHERE product_id=?";
			PreparedStatement prep;

			prep = c.prepareStatement(sql);

			prep.setInt(1, prod.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Integer> viewPurchaseHistory(int id) {
		List<Integer> previousPurchases = new ArrayList<>();
		try {
			String sql = "SELECT product_id FROM customers_products WHERE customer_id LIKE ? ORDER BY product_id";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int product_id = rs.getInt("product_id");
				previousPurchases.add(product_id);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return previousPurchases;
	}

	@Override
	public void clearPurchaseHistory(int id) {
		try {
			String sql = "DELETE FROM customers_products WHERE customer_id=?";
			PreparedStatement prep;
			prep = c.prepareStatement(sql);
			prep.setInt(1, id);
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCharacteristic(Characteristic ch) {
		try {

			Statement stmt = c.createStatement();
			String sql = "INSERT INTO characteristics(length, width, height, weight, joint_numb, flexibility_scale) "
					+ " VALUES ('" + ch.getLength() + "', '" + ch.getWidth() + "','" + ch.getHeight() + "','"
					+ ch.getWeight() + "','" + ch.getJoint_numb() + "','" + ch.getFlexibilty_scale() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Characteristic> viewAllCharacteristics() {
		List<Characteristic> characteristics = new ArrayList<Characteristic>();
		try {
			String sql = "SELECT id,length,width,height,weight,joint_numb,flexibility_scale FROM characteristics";
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				float length = rs.getFloat("length");
				float width = rs.getFloat("width");
				float weight = rs.getFloat("height");
				float height = rs.getFloat("weight");
				int joint_numb = rs.getInt("joint_numb");
				int flexibilty_scale = rs.getInt("flexibility_scale");
				Characteristic ch = new Characteristic(id, length, width, height, weight, joint_numb, flexibilty_scale);
				characteristics.add(ch);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return characteristics;
	}

	@Override
	public Characteristic getCharacteristic(int id_) {
		Characteristic ch = new Characteristic();
		try {
			String sql = "SELECT id,length FROM characteristics WHERE id= ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id_);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				float length = rs.getFloat("length");
				ch = new Characteristic(id, length);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ch;
	}

	@Override
	public void addMaterial(Material mat) {
		try {
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO materials (name, price, amount) " + " VALUES ('" + mat.getName() + "','"
					+ mat.getPrice() + "','" + mat.getAmount() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Material> viewAllMaterials() {
		List<Material> materials = new ArrayList<Material>();
		try {
			String sql = "SELECT id,name,price,amount FROM materials";
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
				Material mat = new Material(id, name, price, amount);
				materials.add(mat);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materials;
	}

	@Override
	public Material getMaterial(int id_) {
		Material mat = new Material();
		try {
			String sql = "SELECT id, name FROM materials WHERE id= ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id_);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				mat = new Material(id, name);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mat;
	}

	@Override
	public boolean addProd_Ch(Product prod, Characteristic ch) {
		try {
			Statement stmt = c.createStatement();
			String sql = " INSERT INTO products_characteristics (product_id, characteristic_id) VALUES ('"
					+ prod.getId() + "','" + ch.getId() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean containsProd_Ch(Product prod, Characteristic ch) {
		try {
			String sql = "SELECT product_id, characteristic_id FROM products_characteristics WHERE product_id=? AND characteristic_id=?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, prod.getId());
			stmt.setInt(2, ch.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				rs.close();
				stmt.close();
				return true;
			}
			return false;
		} catch (SQLException e) {

			return false;
		}
	}

	@Override
	public void removeProd_Ch(Product prod, Characteristic ch) {
		try {
			String sql = "DELETE FROM products_characteristics WHERE product_id=? AND characteristic_id=?";
			PreparedStatement prep;
			prep = c.prepareStatement(sql);
			prep.setInt(1, prod.getId());
			prep.setInt(2, ch.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeAllProd_Ch(Product prod) {
		try {
			String sql = "DELETE FROM products_characteristics WHERE product_id=?";
			PreparedStatement prep;
			prep = c.prepareStatement(sql);
			prep.setInt(1, prod.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Characteristic> viewCharacteristicsFromProduct(int product_id) {
		ArrayList<Characteristic> characteristics = new ArrayList<Characteristic>();
		try {
			String sql = "SELECT c.id,c.length,c.width,c.height,c.weight,c.joint_numb,c.flexibility_scale, pc.characteristic_id "
					+ "FROM products_characteristics as pc JOIN characteristics as c "
					+ "ON pc.characteristic_id = c.id  WHERE pc.product_id = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, product_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				float length = rs.getFloat("length");
				float width = rs.getFloat("width");
				float weight = rs.getFloat("weight");
				float height = rs.getFloat("height");
				int joint_numb = rs.getInt("joint_numb");
				int flexibilty_scale = rs.getInt("flexibility_scale");
				Characteristic ch = new Characteristic(id, length, width, height, weight, joint_numb, flexibilty_scale);
				characteristics.add(ch);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return characteristics;
	}

	@Override
	public boolean addProd_Mat(Product prod, Material mat) {
		try {
			Statement stmt = c.createStatement();
			String sql = " INSERT INTO products_materials (product_id, material_id) VALUES ('" + prod.getId() + "','"
					+ mat.getId() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean containsProd_Mat(Product prod, Material mat) {
		try {
			String sql = "SELECT product_id, material_id FROM products_materials WHERE product_id=? AND material_id=?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, prod.getId());
			stmt.setInt(2, mat.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				rs.close();
				stmt.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public void removeProd_Mat(Product prod, Material mat) {
		try {
			String sql = "DELETE FROM products_materials WHERE product_id=? AND material_id=?";
			PreparedStatement prep;
			prep = c.prepareStatement(sql);

			prep.setInt(1, prod.getId());
			prep.setInt(2, mat.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeAllProd_Mat(Product prod) {
		try {
			String sql = "DELETE FROM products_materials WHERE product_id=?";
			PreparedStatement prep;
			prep = c.prepareStatement(sql);

			prep.setInt(1, prod.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Material> viewMaterialsFromProduct(int product_id) {
		ArrayList<Material> materials = new ArrayList<Material>();
		try {
			String sql = "SELECT m.id,m.name,m.price,m.amount, pm.material_id FROM products_materials as pm JOIN materials as m "
					+ "ON pm.material_id = m.id WHERE pm.product_id = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, product_id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
				Material mat = new Material(id, name, price, amount);
				materials.add(mat);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materials;
	}

	@Override
	public boolean addEng_Prod(Engineer eng, Product prod) {
		try {
			Statement stmt = c.createStatement();
			String sql = " INSERT INTO engineers_products (engineer_id, product_id) VALUES ('" + eng.getId() + "','"
					+ prod.getId() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void removeAllEng_Prod(Product prod) {
		try {
			String sql = "DELETE FROM engineers_products WHERE product_id=?";
			PreparedStatement prep;

			prep = c.prepareStatement(sql);

			prep.setInt(1, prod.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addEngineer(Engineer eng) {
		try {

			Statement stmt = c.createStatement();
			String sql = " INSERT INTO engineers (name_surname, contract_starting_date, contract_ending_date, "
					+ "current_service, salary, bonus, project_achieved, experience_in_years, date_of_birth) "
					+ " VALUES ('" + eng.getName_surname() + "','" + eng.getContract_strating_date() + "','"
					+ eng.getContract_ending_date() + "','" + eng.getCurrent_service() + "','" + eng.getSalary() + "','"
					+ eng.getBonus() + "','" + eng.getProject_achieved() + "','" + eng.getExperience_in_years() + "','"
					+ eng.getDate_of_birth() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateEngineerProjectAchieved(Engineer eng) {
		try {
			String sql = "UPDATE engineers SET project_achieved=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, (eng.getProject_achieved() + 1));
			prep.setInt(2, eng.getId());
			prep.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Engineer getEngineer(int id_) {
		Engineer eng = new Engineer();
		try {
			String sql = "SELECT id,name_surname,project_achieved FROM engineers WHERE id= ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id_);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name_surname = rs.getString("name_surname");
				int project_achieved = rs.getInt("project_achieved");
				eng = new Engineer(id, name_surname, project_achieved);

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eng;

	}

	@Override
	public int getEngineerID(String email) {
		int id = 0;
		try {
			String sql = "SELECT id FROM engineers WHERE email LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + email + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Integer> viewProjectAchieved(int id) {
		List<Integer> p_achieved = new ArrayList<>();
		try {
			String sql = "SELECT  project_achieved FROM engineers WHERE id LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int pr_achieved = rs.getInt("project_achieved");
				p_achieved.add(pr_achieved);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p_achieved;
	}

	@Override
	public List<String> viewBodyparts() {
		List<String> bodyParts = new ArrayList<String>();
		try {
			String sql = "SELECT DISTINCT bodypart FROM products ";
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String part = rs.getString("bodypart");
				bodyParts.add(part);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bodyParts;
	}

	@Override
	public List<Product> searchProductByBody(String bodypart_) {
		List<Product> products = new ArrayList<>();
		try {
			String sql = "SELECT id,name,bodypart,price,date_creation FROM products WHERE bodypart LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + bodypart_ + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String bodypart = rs.getString("bodypart");
				float price = rs.getFloat("price");
				Date date_creation = rs.getDate("date_creation");
				ArrayList<Characteristic> characteristics = viewCharacteristicsFromProduct(id);
				ArrayList<Material> materials = viewMaterialsFromProduct(id);
				Product prod = new Product(id, name, bodypart, price, date_creation, characteristics, materials);
				products.add(prod);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Characteristic> searchCharacteristicByJointNumb(int joint_numb_) {
		List<Characteristic> characteristics = new ArrayList<>();
		try {
			String sql = "SELECT id,length,width,height,weight,joint_numb,flexibility_scale FROM characteristics WHERE joint_numb LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, joint_numb_);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				float length = rs.getFloat("length");
				float width = rs.getFloat("width");
				float height = rs.getFloat("height");
				float weight = rs.getFloat("weight");
				int joint_numb = rs.getInt("joint_numb");
				int flexibility_scale = rs.getInt("flexibility_scale");
				Characteristic ch = new Characteristic(id, length, width, height, weight, joint_numb,
						flexibility_scale);
				characteristics.add(ch);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return characteristics;
	}

	@Override
	public List<Material> searchMaterialByName(String name_) {
		List<Material> materials = new ArrayList<>();
		try {
			String sql = "SELECT id,name,price,amount FROM materials WHERE name LIKE ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + name_ + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
				Material mat = new Material(id, name, price, amount);
				materials.add(mat);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materials;
	}

	@Override
	public List<Engineer> searchEngineerByName(String name_surname_) {
		List<Engineer> engineers = new ArrayList<>();
		try {
			String sql = "SELECT id,name_surname FROM engineers WHERE name_surname LIKE ? ORDER BY id";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + name_surname_ + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name_surname = rs.getString("name_surname");
				Engineer eng = new Engineer(id, name_surname);
				engineers.add(eng);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return engineers;
	}

	@Override
	public void updateProductName(Product prod) {
		try {
			System.out.println("Introduce the new name for the product: ");
			String name = reader.readLine();
			if (name.isBlank()) {
				System.out.println("The prosthesis' name cannot be blank.");
				return;
			}
			String sql = "UPDATE products SET name=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, name);
			prep.setInt(2, prod.getId());
			prep.executeUpdate();
			System.out.println("Name updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductBodypart(Product prod) {
		try {
			System.out.println("Introduce the new bodypart for the product: ");
			String bodypart = reader.readLine();
			if (bodypart.isBlank()) {
				System.out.println("The prosthesis' bodypart cannot be blank.");
				return;
			}
			String sql = "UPDATE products SET bodypart=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, bodypart);
			prep.setInt(2, prod.getId());
			prep.executeUpdate();
			System.out.println("Bodypart updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductPrice(Product prod) {
		try {
			System.out.println("Introduce the new price for the product: ");
			Float price = io.getFloatFromKeyboard();
			if (price <= 0) {
				System.out.println("The prosthesis' price has to be greater than 0.");
				return;
			}
			String sql = "UPDATE products SET price=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setFloat(1, price);
			prep.setInt(2, prod.getId());
			prep.executeUpdate();
			System.out.println("Price updated successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductCharacteristics(Product prod) throws Exception, SQLException {

		while (true) {
			try {

				System.out.println("\nType ADD if you want to add characteristics to the product, or type REMOVE if "
						+ "you want to remove characteristics from it. \nType anything else to finish updating: ");
				String option = reader.readLine();

				if (option.equalsIgnoreCase("add")) {
					System.out.println("\nEnter the number of joints of the characteristic that you want to add: ");
					int joint_numb = io.getIntFromKeyboard();
					List<Characteristic> characteristics = searchCharacteristicByJointNumb(joint_numb);
					if (characteristics.isEmpty()) {
						System.out.println("There are no characteristics with " + joint_numb + " joints.");
					} else {
						for (int i = 0; i < characteristics.size(); i++) {
							System.out.println(characteristics.get(i).toString());
						}
						System.out.println("\nSelect the ID of the characteristic that you want to add: ");
						int id = io.getIntFromKeyboard();
						Characteristic ch = getCharacteristic(id);
						if (ch.getId() == 0) {
							System.out.println("There is no characteristic with the ID: " + id);
						} else {
							if (addProd_Ch(prod, ch)) {
								System.out.println("\nThe characteristic has been successfully added.");
							} else {
								System.out.println(
										"\nThe product already contains this characteristic, try adding another one.");
							}

						}
					}

				} else if (option.equalsIgnoreCase("remove")) {
					if (viewCharacteristicsFromProduct(prod.getId()).isEmpty()) {
						System.out.println("The product currently has no characteristics.");
						break;
					}
					System.out.println("\nSelect the ID of the characteristic that you want to remove: "
							+ viewCharacteristicsFromProduct(prod.getId()));
					int id = io.getIntFromKeyboard();
					Characteristic ch = getCharacteristic(id);
					if (ch.getId() == 0) {
						System.out.println("There is no characteristic with the ID: " + id);
					} else {
						if (containsProd_Ch(prod, ch)) {
							removeProd_Ch(prod, ch);
							System.out.println("\nThe characteristic has been successfully removed.");
						} else {
							System.out.println(
									"\nThe product does not contain this characteristic, try removing another one.");
						}

					}
				} else {
					System.out.println("\nThe product has been successfully updated.");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void addCharacteristicsToNewProduct(Product prod) throws Exception, SQLException {
		try {
			while (true) {

				System.out.println(
						"\nType ADD if you want to add characteristics to the product, type anything else to finish: ");
				String option = reader.readLine();

				if (option.equalsIgnoreCase("add")) {
					System.out.println("\nEnter the number of joints of the characteristic that you want to add: ");
					int joint_numb = io.getIntFromKeyboard();
					List<Characteristic> characteristics = searchCharacteristicByJointNumb(joint_numb);
					if (characteristics.isEmpty()) {
						System.out.println("There are no characteristics with " + joint_numb + " joints.");
					} else {
						for (int i = 0; i < characteristics.size(); i++) {
							System.out.println(characteristics.get(i).toString());
						}
						System.out.println("\nSelect the ID of the characteristic that you want to add: ");
						int id = io.getIntFromKeyboard();
						Characteristic ch = getCharacteristic(id);
						if (ch.getId() == 0) {
							System.out.println("There is no characteristic with the ID: " + id);
						} else {
							if (addProd_Ch(prod, ch)) {
								System.out.println("\nThe characteristic has been successfully added.");
							} else {
								System.out.println(
										"\nThe product already contains this characteristic, try adding another one.");
							}

						}
					}

				} else {
					System.out.println("\nThe characteristics have been successfully added to the new product.");
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProductMaterials(Product prod) throws Exception, SQLException {
		try {
			while (true) {
				System.out.println("\nType ADD if you want to add materials to the product, or type REMOVE if "
						+ "you want to remove materials from it. \nType anything else to finish updating: ");
				String option = reader.readLine();

				if (option.equalsIgnoreCase("add")) {
					System.out.println("\nEnter the name of the material that you want to add: ");
					String name = reader.readLine();
					List<Material> materials = searchMaterialByName(name);
					if (materials.isEmpty()) {
						System.out.println("There are no materials with the name: " + name);
					} else {
						for (int i = 0; i < materials.size(); i++) {
							System.out.println(materials.get(i).toString());
						}
						System.out.println("\nSelect the ID of material that you want to add: ");
						int id = io.getIntFromKeyboard();
						Material mat = getMaterial(id);
						if (mat.getId() == 0) {
							System.out.println("There is no material with the ID: " + id);

						} else {
							if (addProd_Mat(prod, mat)) {
								System.out.println("\nThe material has been successfully added.");
							} else {
								System.out.println(
										"\nThe product already contains this material, try adding another one.");
							}

						}
					}

				} else if (option.equalsIgnoreCase("remove")) {
					if (viewMaterialsFromProduct(prod.getId()).isEmpty()) {
						System.out.println("The product currently has no materials.");
						break;
					}
					System.out.println("\nSelect the ID of the material that you want to remove: "
							+ viewMaterialsFromProduct(prod.getId()));
					int id = io.getIntFromKeyboard();
					Material mat = getMaterial(id);
					if (mat.getId() == 0) {
						System.out.println("There is no material with the ID: " + id);
					} else {
						if (containsProd_Mat(prod, mat)) {
							removeProd_Mat(prod, mat);
							System.out.println("\nThe material has been successfully removed.");
						} else {
							System.out
									.println("\nThe product does not contain this material, try removing another one.");
						}

					}
				} else {
					System.out.println("\nThe product has been successfully updated.");
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addMaterialsToNewProduct(Product prod) throws Exception, SQLException {
		try {
			while (true) {
				System.out.println(
						"\nType ADD if you want to add materials to the product, type anything else to finish: ");
				String option = reader.readLine();

				if (option.equalsIgnoreCase("add")) {
					System.out.println("\nEnter the name of the material that you want to add: ");
					String name = reader.readLine();
					List<Material> materials = searchMaterialByName(name);
					if (materials.isEmpty()) {
						System.out.println("There are no materials with the name: " + name);
					} else {
						for (int i = 0; i < materials.size(); i++) {
							System.out.println(materials.get(i).toString());
						}
						System.out.println("\nSelect the ID of material that you want to add: ");
						int id = io.getIntFromKeyboard();
						Material mat = getMaterial(id);
						if (mat.getId() == 0) {
							System.out.println("There is no material with the ID: " + id);

						} else {
							if (addProd_Mat(prod, mat)) {
								System.out.println("\nThe material has been successfully added.");
							} else {
								System.out.println(
										"\nThe product already contains this material, try adding another one.");
							}

						}
					}

				} else {
					System.out.println("\nThe materials have been successfully added to the new product.");
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}