package bionicProInc.ui;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import bionicProInc.db.ifaces.*;
import bionicProInc.db.jdbc.JDBCManager;
import bionicProInc.db.jpa.JPAUserManager;
import bionicProInc.db.pojos.*;
import bionicProInc.db.pojos.users.*;

public class Menu {

	private static DBManager dbman = new JDBCManager();
	private static UserManager userman = new JPAUserManager();
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static JDBCManager JDBCmethod = new JDBCManager();
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) throws Exception {
		dbman.connect();
		userman.connect();
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("0. Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 0:
				dbman.disconnect();
				userman.disconnect();
				System.exit(0);
				break;
			default:
				break;
			}

		} while (true);

	}

	private static void register() throws Exception {
		System.out.println("Please, write your email address:");
		String email = reader.readLine();
		System.out.println("Please, write your password:");
		String password = reader.readLine();
		System.out.println(userman.getRoles());
		System.out.println("Type the chosen role ID:");
		int id = Integer.parseInt(reader.readLine());
		Role role = userman.getRole(id);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] hash = md.digest();
		User user = new User(email, hash, role);
		userman.newUser(user);
	}

	private static void login() throws Exception {
		System.out.println("Please, write your email address:");
		String email = reader.readLine();
		System.out.println("Please, write your password:");
		String password = reader.readLine();
		User user = userman.checkPassword(email, password);
		if (user == null) {
			System.out.println("Wrong email or password");
			return;
		} else if (user.getRole().getName().equalsIgnoreCase("engineer")) {
			int id = dbman.getEngineerID(email);
			engineerMenu(id);
		} else if (user.getRole().getName().equalsIgnoreCase("customer")) {
			int id = dbman.getCustomerID(email);
			customerMenu(id);
		}
	}

	private static void engineerMenu(int id) throws Exception {
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. View and existing product");
			System.out.println("2. Add a new product to the shop");
			System.out.println("3. Remove an existing product from the shop");
			System.out.println("4. See how many projects you have successfully achieved (Good Job!)");
			System.out.println("5. View bonus");
			System.out.println("0. Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				viewProductE();
				break;

			case 2:
				addProduct();
				break;

			case 3:
				removeProduct();
				break;

			case 4:
				seeProject(id);
				break;

			case 5:
				viewBonus(id);
				break;

			case 0:
				return;

			default:
				break;

			}
		} while (true);
	}

	private static void customerMenu(int id) throws Exception {
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. View all available products");
			System.out.println("2. Purchase a product");
			System.out.println("3. See all my previous purchases");
			System.out.println("0. Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				viewProductC();
				break;

			case 2:
				makePurchase();
				break;

			case 3:
				viewPreviousPurchases(id);
				break;

			case 0:
				return;

			default:
				break;

			}
		} while (true);
	}

	// Engineer OPTION 1
	private static void viewProductE() throws Exception {
		System.out.println("Choose a bodypart:");
		dbman.viewBodyparts();
		String name = reader.readLine();
		dbman.searchProductByBody(name);
		System.out.println("Choose a product: ");
		int id = Integer.parseInt(reader.readLine());
		dbman.viewCharacteristicsFromProduct(id);
		dbman.viewMaterialsFromProduct(id);
	}

	// Engineer OPTION 2
	// NEED TO SPEAK ABOUT HOW TO ADD PHOTO ATTRIBUTE FROM A STRING ...
	private static void addProduct() throws Exception {
		try {
			System.out.println("Introduce prothesis name: ");
			String name = reader.readLine();
			System.out.println("Introduce prothesis bodypart: ");
			String bodypart = reader.readLine();
			System.out.println("Introduce prothesis price: ");
			Float price = Float.parseFloat(reader.readLine());
			System.out.print("Start Date (yyyy-MM-dd): ");
			LocalDate date_creation = LocalDate.parse(reader.readLine(), formatter);
			byte[] photo = new byte[10];
			Product prod = new Product(name, bodypart, price,Date.valueOf(date_creation), photo);
			
			//MISSING SET CHARACTERISTICS AND MATERIALS
			//PRICE COULD BE SET AUTOMATICALLY USING A FORMULA WITH MATERIAL'S AMOUNT
			/*
			int y, m, d;
			System.out.println("Introduce prothesis creation's year: ");
			y = Integer.parseInt(reader.readLine());
			System.out.println("Introduce prothesis creation's month: ");
			m = Integer.parseInt(reader.readLine());
			System.out.println("Introduce prothesis creation's day: ");
			d = Integer.parseInt(reader.readLine());
			LocalDate ld = LocalDate.of(y, m, d);
			ZoneId systemTimeZone = ZoneId.systemDefault();
			ZonedDateTime zonedDateTime = ld.atStartOfDay(systemTimeZone);
			Date date = (Date) Date.from(zonedDateTime.toInstant());
			localprod.setDate_creation(date);
			*/
			
			/*
			localprod.setCharacteristics(JDBCmethod.viewCharacteristicsFromProduct(id));
			localprod.setMaterials(JDBCmethod.viewMaterialsFromProduct(id));
			
			*/
			dbman.addProduct(prod);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Engineer OPTION 3
	private static void removeProduct() throws Exception {
		try {
			System.out.println("Introduce the product ID: ");
			int id = Integer.parseInt(reader.readLine());
			dbman.removeProduct(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Engineer OPTION 4
	private static void seeProject(int id) throws Exception {
		System.out.println("You have completed " + dbman.viewProjectAchieved(id).toString() + " successful projects so far.");

	}

	// Engineer OPTION 5
	private static void viewBonus(int id) throws Exception {

		System.out.println("Your bonus is: " + dbman.viewBonus(id) + "€");

	}

	// Customer OPTION 1
	// TODO
	private static void viewProductC() throws Exception {
		List<Product> products = dbman.viewAllProducts();
		for(int i = 0;i<products.size();i++) {
			System.out.println(products.get(i));
		}
	}
	
	// Customer OPTION 2
	// TODO
	private static void makePurchase() throws Exception {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Customer OPTION 3
	// TODO
	private static void viewPreviousPurchases(int id) throws Exception {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
