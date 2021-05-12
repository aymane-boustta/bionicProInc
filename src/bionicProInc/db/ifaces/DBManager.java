package bionicProInc.db.ifaces;

import java.util.List;

import bionicProInc.db.pojos.*;

public interface DBManager {

	public void connect();

	public void disconnect();

	public void addProduct(Product prod);
	
	public void removeProduct(int id);
	
	public void addCharacteristic(Characteristic ch);
	
	public List<Characteristic> getCharacteristics(int id);

	public void addMaterial(Material mat);
	
	public void addCustomer(Customer cust);
	
	public int getCustomerID(String email);

	public void addProdIntoCh(Product prod, Characteristic ch);

	public void addChIntoProd(Material mat);
	
	public void addProdIntoMat(Product prod);

	public void addMatIntoProd(Material mat);

	public void addProdIntoCust(Product prod);

	public void addCustIntoProd(Customer cust);

	public void addEngineer(Engineer eng);
	
	public void addOrder(Order ord);

	public List<String> searchProductByBody(String bodypart);

	public List<String> viewBodyparts();

	public void addToOrder(Product prod, Order ord);

	public List<String> viewCart(Order ord);
	
	public List<Product> viewProductsFromOrder(int id);

	public void deleteProdFromCart(String name, Order ord);
	
	public List<Integer> viewOtherOrders(int id);
	
	public List<Characteristic> viewCharacteristicsFromProduct(int id);
	
	public List<Material> viewMaterialsFromProduct(int id);
	
	public List<Engineer> viewEngineersID();

	public List<Integer> viewProjectAchieved(int id);
	
	public Float viewBonus(int id);
	




	

	

	

}
