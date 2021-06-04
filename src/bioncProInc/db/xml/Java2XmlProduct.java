package bioncProInc.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import bionicProInc.db.pojos.Product;
import bionicProInc.db.utils.inputOutput;

public class Java2XmlProduct {
	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static inputOutput io = new inputOutput();

	private static void printProducts() {
		Query q1 = em.createNativeQuery("SELECT id, name, bodypart, price, date_creation FROM products", Product.class);
		@SuppressWarnings("unchecked")
		List<Product> prods = (List<Product>) q1.getResultList();
		for (Product prod : prods) {
			System.out.println(prod.toStringSimple());
		}
	}

	public void exportToXml() throws Exception {
		try {
		// Get the entity manager
		// Note that we are using the class' entity manager
		em = Persistence.createEntityManagerFactory("user-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
		// Get the marshaller
		Marshaller marshaller = jaxbContext.createMarshaller();

		// Pretty formatting
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// Choose the report to turn into an XML
		printProducts();
		System.out.print("\nChoose a product to turn into an XML file:");
		int prod_id = io.getIntFromKeyboard();
		Query q2 = em.createNativeQuery("SELECT id, name, bodypart, price, date_creation FROM products WHERE id = ? ",
				Product.class);
		q2.setParameter(1, prod_id);
		Product prod = (Product) q2.getSingleResult();

		// Use the Marshaller to marshal the Java object to a file
		System.out.print("\nType the desired name for the file (FileName.xml): ");
		String fileName = reader.readLine();
		File file = new File(fileName);
		
		marshaller.marshal(prod, file);
		marshaller.marshal(prod, System.out);
	} catch (NoResultException nre) {
		System.out.println("\n No product with that ID.");
	}
	}
}
