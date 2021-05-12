package bionicProInc.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bionicProInc.db.pojos.*;
import bionicProInc.db.ifaces.DBManager;

public class JDBCManager implements DBManager {
	private Connection c;

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
			String sql1 = "CREATE TABLE products " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT," + " name TEXT NOT NULL, "
					+ " bodypart  TEXT NOT NULL UNIQUE," + " price REAL NOT NULL," + " date_creation DATE NOT NULL,"
					+ " photo BLOB )";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE materials " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name     TEXT     NOT NULL UNIQUE, " + " price REAL NOT NULL," + " amount   INTEGER	 NOT NULL)";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE customers " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name_surname     TEXT     NOT NULL UNIQUE, " + " age INTEGER NOT NULL,"
					+ " gender TEXT NOT NULL," + " phone INTEGER NOT NULL," + " email TEXT NOT NULL,"
					+ " street TEXT NOT NULL," + " city TEXT NOT NULL," + " postal_code INTEGER NOT NULL)";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE engineers " + "(id INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name_surname     TEXT     NOT NULL UNIQUE, " + " email     TEXT     NOT NULL UNIQUE, "
					+ " contract_starting_date DATE NOT NULL," + " contract_ending_date DATE NOT NULL,"
					+ " current_service TEXT NOT NULL," + " salary REAL NOT NULL," + " bonus REAL NOT NULL,"
					+ " project_achieved INTEGER NOT NULL," + " experience_in_years INTEGER NOT NULL,"
					+ " date_of_birth DATE NOT NULL)";

			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE characteristics " + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " length REAL NOT NULL," + " width REAL NOT NULL," + " height REAL NOT NULL,"
					+ " weight REAL NOT NULL," + " joint_numb INTEGER NOT NULL,"
					+ " flexibility_scale INTEGER NOT NULL)";

			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE orders " + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " customer_id INTEGER references customers(id),"
					+ " product_id INTEGER  REFERENCES products(id))";
			stmt6.executeUpdate(sql6);
			stmt6.close();

			// Now we create the table that references the N-N relationships

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE engineers_products "
					+ "(eng_prod_id INTEGER PRIMARY KEY AUTOINCREMENT ,engineer_id INTEGER REFERENCES engineers(id),"
					+ " product_id INTEGER REFERENCES products(id))";
			stmt7.executeUpdate(sql7);
			stmt7.close();

			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE products_materials "
					+ "(prod_mat_id INTEGER PRIMARY KEY AUTOINCREMENT, product_id  INTEGER REFERENCES products(id),"
					+ " material_id INTEGER REFERENCES materials(id))";
			stmt8.executeUpdate(sql8);
			stmt8.close();

			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE products_characteristics" + "(product_id INTEGER REFERENCES products(id),"
					+ " characteristic_id INTEGER REFERENCES characteristics(id),"
					+ "PRIMARY KEY (product_id, characteristic_id))";

			stmt9.execute(sql9);
			stmt9.close();

		} catch (SQLException e) {
			if (!e.getMessage().contains("already exists")) {
				e.printStackTrace();
			}
		}
	}

	public void addProduct(Product prod) {
		try {
			Statement st1 = c.createStatement();
			String sql = "INSERT INTO products (name,bodypart, price, date_creation, photo) " + " VALUES('"
					+ prod.getName() + "','" + prod.getBodypart() + "','" + prod.getPrice() + "','"
					+ prod.getDate_creation() + "','" + prod.getPhoto() + "');";
			st1.executeUpdate(sql);
			st1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public void addCharacteristic(Characteristic ch) {
		try {

			Statement st1 = c.createStatement();
			String sql = "INSERT INTO characteristics(length, width, height, weight, joint_numb, flexibility_scale) "
					+ " VALUES ('" + ch.getLength() + "', '" + ch.getWidth() + "','" + ch.getHeight() + "','"
					+ ch.getWeight() + "','" + ch.getJoint_numb() + "','" + ch.getFlexibilty_scale() + "');";
			st1.executeUpdate(sql);
			st1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Characteristic> getCharacteristics(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addMaterial(Material mat) {
		try {
			Statement st = c.createStatement();
			String sql = "INSERT INTO materials (name, price, amount) " + " VALUES ('" + mat.getName() + "','"
					+ mat.getPrice() + "','" + mat.getAmount() + "');";
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addCustomer(Customer cust) {
		try {
			Statement st = c.createStatement();
			String sql = "INSERT INTO customers(name_surname, age, gender, phone, email, street, city, postal_code) "
					+ " VALUES ('" + cust.getName_surname() + "', '" + cust.getAge() + "','" + cust.getGender() + "','"
					+ cust.getPhone() + "', " + "'" + cust.getEmail() + "','" + cust.getStreet() + "','"
					+ cust.getCity() + "','" + cust.getPostal_code() + "');";
			st.executeUpdate(sql);
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addProdIntoCh(Product prod, Characteristic ch) {
		try {
			Statement stmt = c.createStatement();
			String sql = " INSERT INTO products_characteristics (product_id, characteristic_id) VALUES ('"
					+ prod.getId() + "','" + ch.getId() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void addChIntoProd(Material mat) {
		// TODO Auto-generated method stub

	}

	public void addProdIntoMat(Product prod) {
		try {
			Statement st = c.createStatement();
			String sql = "INSERT INTO products_materials (product_id) " + " VALUES ('" + prod.getId() + "');";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addMatIntoProd(Material mat) {
		try {
			Statement st = c.createStatement();
			String sql = "INSERT INTO products_materials (material_id) " + " VALUES ('" + mat.getId() + "');";
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addProdIntoCust(Product p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCustIntoProd(Customer cust) {
		// TODO Auto-generated method stub

	}

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
	public List<Engineer> viewEngineersID() {
		ArrayList<Engineer> engineers = new ArrayList<Engineer>();
		try {
			String sql = " SELECT id,name_surname FROM engineers ORDER BY id ";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name_surname = rs.getString("name_surname");
				Engineer eng = new Engineer(id, name_surname);
				engineers.add(eng);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return engineers;
	}

	public List<Integer> viewProjectAchieved(int id) {
		List<Integer> p_achieved = new ArrayList<>();
		try {
			String sql = "SELECT  project_achieved FROM engineers WHERE id = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
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
	public Float viewBonus(int id) {
		Float bonus = null;
		try {
			String sql = "SELECT bonus FROM engineers WHERE id= ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			bonus = rs.getFloat("bonus");

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bonus;
	}

	public void addOrder(Order ord) {
		try {
			Statement stmt = c.createStatement();
			String sql = " INSERT INTO orders (customer_id, product_id)" + " VALUES ('" + ord.getCustomer_id() + "','"
					+ ord.getProduct_id() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// LIKE ADDTOCART- THE SAME FUNCTION
	// WHAT IS THIS FUNCTION
	public void addToOrder(Product prod, Order ord) {

		try {
			Statement stmt = c.createStatement();
			String sql = " INSERT INTO orders (id, customer_id, product_id) VALUES ('" + ord.getId() + "','"
					+ ord.getCustomer_id() + "','" + ord.getProduct_id() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> viewProductsFromOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> viewOtherOrders(int id) {
		List<Integer> Ids = new ArrayList<Integer>();
		try {
			String sql = " SELECT id FROM orders WHERE costumer_id= ? ";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int ids = rs.getInt(id);
				Ids.add(ids);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Ids;
	}

	public List<String> viewCart(Order ord) {
		List<String> p_names = new ArrayList<String>();
		try {
			String sql = " SELECT p.name FROM products AS p JOIN order AS or ON or.product_id=p.id WHERE or.order_id= ? ";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, ord.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String productName = rs.getString("name");
				p_names.add(productName);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p_names;
	}

	public void deleteProdFromCart(String name, Order ord) {
		try {
			String sql = "SELECT id FROM products WHERE name = ? ";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("product_id");
				ord.removeProduct(id);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CHECK
	@Override
	public List<String> searchProductByBody(String bodypart) {
		List<String> prodname = new ArrayList<>();
		int id;
		String productname;
		try {
			String sql = "SELECT id ,name FROM products WHERE bodypart LIKE ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, "%" + bodypart + "%");
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				productname = rs.getString("name");
				prodname.add(productname);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prodname;
	}

	@Override
	public List<String> viewBodyparts() {
		List<String> bodyPart = new ArrayList<String>();
		try {
			String sql = "SELECT DISTINCT bodypart FROM products ";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				String part = rs.getString("bodypart");
				bodyPart.add(part);
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bodyPart;
	}

	public ArrayList<Characteristic> viewCharacteristicsFromProduct(int id) {
		ArrayList<Characteristic> characteristics = new ArrayList<Characteristic>();
		try {
			String sql = "SELECT c.*, p.id FROM products_characteristics as pc JOIN characteristics as c "
					+ "ON pc.characteristic_id = c.id JOIN products as p ON pc.products_id = p.id WHERE p.id = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				float length = rs.getFloat("length");
				float width = rs.getFloat("width");
				float weight = rs.getFloat("weight");
				float height = rs.getFloat("height");
				int joint_numb = rs.getInt("joint_numb");
				int flexibilty_scale = rs.getInt("flexibilty_scale");
				Characteristic ch = new Characteristic(length, width, height, weight, joint_numb, flexibilty_scale);
				characteristics.add(ch);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return characteristics;
	}

	public ArrayList<Material> viewMaterialsFromProduct(int id) {
		ArrayList<Material> materials = new ArrayList<Material>();
		try {
			String sql = "SELECT m.*, p.id FROM products_materials as pm JOIN materials as m "
					+ "ON pm.material_id = m.id JOIN products as p ON pm.products_id = p.id WHERE p.id = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				float price = rs.getFloat("price");
				int amount = rs.getInt("amount");
				Material mat = new Material(name, price, amount);

				materials.add(mat);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return materials;
	}

}