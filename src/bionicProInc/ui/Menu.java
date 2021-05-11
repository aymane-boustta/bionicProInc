package bionicProInc.ui;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
	private static Order temporaryOrder = new Order();
	private static Product localprod = new Product();
	private static JDBCManager JDBCmethod = new JDBCManager();
	private static List<Product> p;

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
			engineerMenu();
		} else if (user.getRole().getName().equalsIgnoreCase("customer")) {
			customerMenu();
		}
	}

	private static void engineerMenu() throws Exception {
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. View product");
			System.out.println("2. Add new product");
			System.out.println("3. Remove product");
			System.out.println("4. See existing projects");
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

				seeProject();
				break;

			case 5:
				viewBonus();
				break;

			case 0:
				return;

			default:
				break;

			}
		} while (true);
	}

	private static void customerMenu() throws Exception {
		do {
			System.out.println("\n Choose an option:");
			System.out.println("1. View product");
			System.out.println("2. Make purchase");
			System.out.println("3. Change product");
			System.out.println("4. See other purchases");
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
				changeProduct();
				break;

			case 4:
				seeOtherPurchases();
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

			int y, m, d;
			System.out.println("Introduce prothesis ID: ");
			int id = Integer.parseInt(reader.readLine());
			localprod.setId(id);
			System.out.println("Introduce prothesis name: ");
			String name = reader.readLine();
			localprod.setName(name);
			System.out.println("Introduce prothesis bodypart: ");
			String bodypart = reader.readLine();
			localprod.setBodypart(bodypart);
			System.out.println("Introduce prothesis price: ");
			Float price = Float.parseFloat(reader.readLine());
			localprod.setPrice(price);
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
			localprod.setCharacteristics(JDBCmethod.viewCharacteristicsFromProduct(id));
			localprod.setMaterials(JDBCmethod.viewMaterialsFromProduct(id));
			dbman.addProduct(localprod);

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
	private static void seeProject() throws Exception {
		try {
			System.out.println("Introduce your ID: ");
			
			int id = Integer.parseInt(reader.readLine());
			dbman.viewProjectAchieved(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// Engineer OPTION 5
	private static void viewBonus() throws Exception {
		System.out.println("Introduce your ID:");
		userman.getUsers().toString();
		int id = Integer.parseInt(reader.readLine());
		dbman.viewBonus(id);
	}

	// CUSTOMER OPTION 1
	private static void viewProductC() throws Exception {
		try {
			System.out.println("Choose a bodypart:");
			dbman.viewBodyparts();
			String name = reader.readLine();
			dbman.searchProductByBody(name);
			System.out.println("Choose a product: ");
			int id = Integer.parseInt(reader.readLine());
			dbman.viewCharacteristicsFromProduct(id);
			dbman.viewMaterialsFromProduct(id);
			System.out.println("Do you want to add it to your cart? 1->YES 0->NO");
			int op = Integer.parseInt(reader.readLine());
			if (op == 1) {
				for (int i = 1; i < p.size(); i++) {
					if (p.get(i).getId() == id) {
						Product pr = p.get(i);
						dbman.addToOrder(pr, temporaryOrder);
					}
				}
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Customer OPTION 2
	private static void makePurchase() throws Exception {
		try {
			System.out.println("These are the products: \n");
			dbman.viewCart(temporaryOrder);
			System.out.println("Are you sure? " + " 1->YES 0->NO");
			int option = Integer.parseInt(reader.readLine());
			if (option == 1) {
				dbman.addOrder(temporaryOrder);
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Customer OPTION 3
	private static void changeProduct() throws Exception {
		try {
			System.out.println("Choose the product you want to remove from cart:");
			dbman.viewCart(temporaryOrder);
			String pName = reader.readLine();
			System.out.println("Are you sure you want to delete that product? " + " 1->YES 0->NO");
			int option = Integer.parseInt(reader.readLine());
			if (option == 1) {
				dbman.deleteProdFromCart(pName, temporaryOrder);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CUSTOMER OPTION 4
	private static void seeOtherPurchases() throws Exception {
		try {
			System.out.println("Confirm your ID: ");
			int id = Integer.parseInt(reader.readLine());
			dbman.viewOtherOrders(id);
			System.out.println("Do you want to select a product? 1->YES 0->NO");
			int option = Integer.parseInt(reader.readLine());
			if (option == 1) {
				System.out.println("Select id of product: ");
				int p_id = Integer.parseInt(reader.readLine());
				dbman.viewCharacteristicsFromProduct(p_id);
				dbman.viewMaterialsFromProduct(p_id);
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
