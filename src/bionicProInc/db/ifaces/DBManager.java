package bionicProInc.db.ifaces;

import java.util.List;

import bionicProInc.db.pojos.*;

public interface DBManager {

	public void connect();

	public void disconnect();

	public void addProduct(Product prod);

	public void removeProduct(int id);
	
	public List<Product> viewAllProducts();

	public void addCharacteristic(Characteristic ch);

	public void addMaterial(Material mat);

	public void addCustomer(Customer cust);

	public int getCustomerID(String email);

	public void addProd_Ch(Product prod, Characteristic ch);

	public void addProd_Mat(Product prod, Material mat);

	public void addCust_Prod(Customer cust, Product prod);

	public void addEng_Prod(Engineer eng, Product prod);

	public void addEngineer(Engineer eng);

	int getEngineerID(String email);

	public List<Integer> viewProjectAchieved(int id);

	public Float viewBonus(int id);

	public List<String> searchProductByBody(String bodypart);

	public List<String> viewBodyparts();

	public List<Characteristic> viewCharacteristicsFromProduct(int id);

	public List<Material> viewMaterialsFromProduct(int id);

	public List<Integer> viewPreviousPurchases(int id);

}
