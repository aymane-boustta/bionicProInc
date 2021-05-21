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

import bionicProInc.db.pojos.*;


public class Xml2JavaProduct {
	private static final String PERSISTENCE_PROVIDER = "user-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("/bionicProInc/ExternalProduct.xml");
		Product p = (Product) unmarshaller.unmarshal(file);

		// Print the report
		System.out.println("Product:");
		System.out.println("Name: " + p.getName());
		System.out.println("BodyPart: " + p.getBodypart());
		System.out.println("Price: " + p.getPrice());
		System.out.println("creation date: " + p.getDate_creation());
		List<Characteristic> ch = new ArrayList<>() ;
		List<Material> m = new ArrayList<>() ;
		List<Engineer> e = new ArrayList<>() ; 

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
		for (Engineer en : e) {
			em.persist(en);
		}
		
		for (Material ma : m) {
			em.persist(ma);
		}
		for (Characteristic c : ch) {
			em.persist(c);
		} 
		em.persist(p);
		
		// End transaction
		tx1.commit();
	}
}
