package bioncProInc.db.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bionicProInc.db.pojos.Characteristic;
import bionicProInc.db.pojos.Customer;
import bionicProInc.db.pojos.Engineer;
import bionicProInc.db.pojos.Material;
import bionicProInc.db.pojos.Product;

public class Xml2JavaCustomer {
	private static final String PERSISTENCE_PROVIDER = "user-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
		
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		
		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./External-Customer.xml");
		Customer c = (Customer) unmarshaller.unmarshal(file);

		// Print the report
		System.out.println("Customer:");
		System.out.println("Name and surname: " + c.getName_surname());
		System.out.println("Age: " + c.getAge());
		System.out.println("Phone: " + c.getPhone());
		System.out.println("email: " + c.getEmail());
		List<Product> p = new ArrayList<>();

		// Store the report in the database
		// Create entity manager
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create a transaction
		EntityTransaction tx1 = em.getTransaction();

		// Start transaction
		tx1.begin();

		// Persist
		// We assume the authors are not already in the database
		// In a real world, we should check if they already exist
		// and update them instead of inserting as new
		
		for (Product product : p) {
			em.persist(product);
		} 
		em.persist(c); 
		
		// End transaction
		tx1.commit();
	}
}
