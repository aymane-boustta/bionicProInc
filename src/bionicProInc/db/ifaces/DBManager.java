package bionicProInc.db.ifaces;

import java.util.ArrayList;
import java.util.List;

import bionicProInc.db.pojos.*;

public interface DBManager {

	public void connect();

	public void disconnect();

	public void addCustomer(Customer cust);

	public Customer getCustomer(int id);

	public int getCustomerID(String email);

	public void addProduct(Product prod);

	public Product getProduct(int id);

	public void removeProduct(int id);

	public List<Product> viewAllProducts();

	public Product viewProduct(int id);

	public int getProductID(String name);

	public void addCust_Prod(Customer cust, Product prod);
	
	public void removeCust_Prod(Product prod);

	public List<Integer> viewPurchaseHistory(int id);

	public void clearPurchaseHistory(int id);

	public void addCharacteristic(Characteristic ch);

	public List<Characteristic> viewAllCharacteristics();

	public Characteristic getCharacteristic(int id);

	public void addMaterial(Material mat);

	public List<Material> viewAllMaterials();

	public Material getMaterial(int id);

	public void addProd_Ch(Product prod, Characteristic ch);

	public void removeProd_Ch(Product prod, Characteristic ch);
	
	public void removeAllProd_Ch(Product prod);

	public ArrayList<Characteristic> viewCharacteristicsFromProduct(int id);

	public void addProd_Mat(Product prod, Material mat);

	public void removeProd_Mat(Product prod, Material mat);
	
	public void removeAllProd_Mat(Product prod);

	public ArrayList<Material> viewMaterialsFromProduct(int id);

	public void addEng_Prod(Engineer eng, Product prod);
	
	public void removeEng_Prod(Product prod);

	public void addEngineer(Engineer eng);

	public void updateEngineerProjectAchieved(Engineer eng);

	public Engineer getEngineer(int id);

	public int getEngineerID(String email);

	public List<Integer> viewProjectAchieved(int id);

	public Float viewBonus(int id);

	public List<String> viewBodyparts();

	public List<Product> searchProductByBody(String bodypart);

	public void updateProductName(Product prod);

	public void updateProductBodypart(Product prod);

	public void updateProductPrice(Product prod);

	public void updateProductCharacteristics(Product prod);
	
	public void addCharacteristicsToNewProduct(Product prod);

	public void updateProductMaterials(Product prod);
	
	public void addMaterialsToNewProduct(Product prod);

}
