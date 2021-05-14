package bionicProInc.ui;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
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
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) throws Exception {
		dbman.connect();
		userman.connect();
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("0. Exit Database");
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
			System.out.println("1. Update an existing product");
			System.out.println("2. Add a new characteristic to the database");
			System.out.println("3. Add a new material to the database");
			System.out.println("4. Add a new product to the shop");
			System.out.println("5. Remove an existing product from the shop");
			System.out.println("6. See how many projects you have successfully achieved (Good job, keep it up!)");
			System.out.println("7. View your current bonus");
			System.out.println("0. Log out");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				viewProductE();
				break;

			case 2:
				addCharacteristic();
				break;

			case 3:
				addMaterial();
				break;

			case 4:
				addProduct(id);
				break;

			case 5:
				removeProduct();
				break;

			case 6:
				seeProject(id);
				break;

			case 7:
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
			System.out.println("3. Show my purchase history");
			System.out.println("4. Clear my purchase history");
			System.out.println("0. Log out");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				viewProductC();
				break;

			case 2:
				makePurchase(id);
				break;

			case 3:
				viewPurchaseHistory(id);
				break;

			case 4:
				clearPurchaseHistory(id);
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
		System.out.println(dbman.viewBodyparts());
		String bodypart = reader.readLine();
		List<Product> products = dbman.searchProductByBody(bodypart);
		if (products.isEmpty()) {
			System.out.println("There are no products with the bodypart: " + bodypart);
			return;
		}
		for (int i = 0; i < products.size(); i++) {
			System.out.println(products.get(i));
		}
		System.out.println("\nChoose the product that you want to update: ");
		int product_id = Integer.parseInt(reader.readLine());
		if (dbman.viewProduct(product_id).getName() == null) {
			System.out.println("There is no product with the ID: " + product_id);
			return;
		}
		System.out.println(dbman.viewProduct(product_id));
		productUpdateMenu(dbman.viewProduct(product_id));
		System.out.println("\nThe product has been successfully updated, and now it looks like this: ");
		System.out.println(dbman.viewProduct(product_id));
	}

	private static void productUpdateMenu(Product prod) throws Exception {
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. Update the name");
			System.out.println("2. Update the bodypart it substitutes");
			System.out.println("3. Update the price");
			System.out.println("4. Update the characteristics");
			System.out.println("5. Update the materials");
			System.out.println("0. Exit");
			int choice = Integer.parseInt(reader.readLine());
			switch (choice) {
			case 1:
				dbman.updateProductName(prod);
				break;

			case 2:
				dbman.updateProductBodypart(prod);
				break;

			case 3:
				dbman.updateProductPrice(prod);
				break;

			case 4:
				dbman.updateProducCharacteristics(prod);
				break;

			case 5:
				dbman.updateProductMaterials(prod);
				break;

			case 0:
				return;

			default:
				break;

			}
		} while (true);
	}

	// Engineer OPTION 2
	private static void addCharacteristic() throws Exception {
		try {
			System.out.println("Introduce the characteristic's length: ");
			Float length = Float.parseFloat(reader.readLine());
			System.out.println("Introduce the characteristic's width: ");
			Float width = Float.parseFloat(reader.readLine());
			System.out.println("Introduce the characteristic's height: ");
			Float height = Float.parseFloat(reader.readLine());
			System.out.println("Introduce the characteristic's weight: ");
			Float weight = Float.parseFloat(reader.readLine());
			System.out.println("Introduce the characteristic's joint_numb: ");
			int joint_numb = Integer.parseInt(reader.readLine());
			System.out.println("Introduce the characteristic's flexibility_scale: ");
			int flexibility_scale = Integer.parseInt(reader.readLine());
			Characteristic ch = new Characteristic(length, width, height, weight, joint_numb, flexibility_scale);
			System.out.println("The characteristic has been successfully added.");
			dbman.addCharacteristic(ch);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Engineer OPTION 3
	private static void addMaterial() throws Exception {
		try {
			System.out.println("Introduce the material's name: ");
			String name = reader.readLine();
			System.out.println("Introduce the material's price: ");
			Float price = Float.parseFloat(reader.readLine());
			System.out.println("Introduce the material's amount: ");
			int amount = Integer.parseInt(reader.readLine());
			Material mat = new Material(name, price, amount);
			System.out.println("The material has been successfully addded.");
			dbman.addMaterial(mat);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Engineer OPTION 4
	// NEED TO SPEAK ABOUT HOW TO ADD PHOTO ATTRIBUTE FROM A STRING ...
	private static void addProduct(int id) throws Exception {
		try {
			System.out.println("Introduce the prothesis' name: ");
			String name = reader.readLine();
			System.out.println("Introduce the bodypart that the prothesis will substitute: ");
			String bodypart = reader.readLine();
			System.out.println("Introduce the prothesis' price: ");
			Float price = Float.parseFloat(reader.readLine());
			System.out.print("Introduce the starting date of the project (yyyy-MM-dd): ");
			LocalDate date_creation = LocalDate.parse(reader.readLine(), formatter);
			byte[] photo = new byte[10];
			Product prod = new Product(name, bodypart, price, Date.valueOf(date_creation), photo);
			dbman.addProduct(prod);
			System.out.println("\nThe product has been successfully added.");
			dbman.addEng_Prod(dbman.getEngineer(id), dbman.getProduct(dbman.getProductID(prod.getName())));
			dbman.updateEngineerProjectAchieved(dbman.getEngineer(id));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Engineer OPTION 5
	private static void removeProduct() throws Exception {
		try {
			System.out.println(dbman.viewAllProducts());
			System.out.println("Introduce the ID of the product that you want to remove: ");
			int product_id = Integer.parseInt(reader.readLine());
			if (dbman.viewProduct(product_id).getName() == null) {
				System.out.println("\nThere is no product with the ID: " + product_id);
				return;
			}
			System.out.println("\nType YES to confirm the deletion, type anything else to cancel.");
			String option = reader.readLine();
			if (option.equalsIgnoreCase("yes")) {
				dbman.removeProduct(product_id);
				System.out.println("Deletion confirmed.");
			} else {
				System.out.println("Deletion cancelled.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Engineer OPTION 6
	private static void seeProject(int id) throws Exception {
		System.out.println(
				"You have completed " + dbman.viewProjectAchieved(id).toString() + " successful projects so far.");

	}

	// Engineer OPTION 7
	private static void viewBonus(int id) throws Exception {

		System.out.println("Your bonus is: " + dbman.viewBonus(id) + "€");

	}

	// Customer OPTION 1
	private static void viewProductC() throws Exception {
		List<Product> products = dbman.viewAllProducts();
		for (int i = 0; i < products.size(); i++) {
			System.out.println(products.get(i).toString());
		}
	}

	// Customer OPTION 2
	private static void makePurchase(int customer_id) throws Exception {
		try {
			System.out.println("Introduce the ID of the product that you want to buy: ");
			int product_id = Integer.parseInt(reader.readLine());
			if (dbman.viewProduct(product_id).getName() == null) {
				System.out.println("\nThere is no product with the ID: " + product_id);
				return;
			}
			System.out.println(dbman.viewProduct(product_id).toString());
			System.out.println("\nType YES to confirm your purchase. Type anything else to to cancel the purchase.");
			String option = reader.readLine();
			if (option.equalsIgnoreCase("yes")) {
				dbman.addCust_Prod(dbman.getCustomer(customer_id), dbman.getProduct(product_id));
				System.out.println("Purchase confirmed.");
			} else {
				System.out.println("Purchase cancelled.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Customer OPTION 3
	private static void viewPurchaseHistory(int id) throws Exception {
		List<Integer> previousPurchases = dbman.viewPurchaseHistory(id);
		if (previousPurchases.isEmpty()) {
			System.out.println(
					"Your purchase history is empty, you should buy some products to make your life better. ;)");
			return;
		}
		System.out.println("Your purchase history shows the following " + previousPurchases.size() + " products:");
		for (int i = 0; i < previousPurchases.size(); i++) {
			System.out.println(dbman.viewProduct(previousPurchases.get(i)));
		}
	}

	// Customer OPTION 4
	private static void clearPurchaseHistory(int id) throws Exception {
		
		System.out.println("\nType YES to confirm. Type anything else to to cancel.");
		String option = reader.readLine();
		if (option.equalsIgnoreCase("yes")) {
			dbman.clearPurchaseHistory(id);
			System.out.println("Your have purchase history has been cleared, it now shows no items.");
		} else {
			System.out.println("Action cancelled.");
		}


	}

}
