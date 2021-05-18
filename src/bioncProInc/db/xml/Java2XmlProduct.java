package bioncProInc.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import bionicProInc.db.pojos.Product;

public class Java2XmlProduct {
	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	private static void printProducts() {
		Query q1 = em.createNativeQuery("SELECT id, name, bodypart, price, date_creation FROM products", Product.class);
		@SuppressWarnings("unchecked")
		List<Product> prods = (List<Product>) q1.getResultList();
		for (Product prod : prods) {
			System.out.println(prod);
		}
	}

	public static void main(String[] args) throws Exception {
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
		int prod_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT id, name, bodypart, price, date_creation FROM products WHERE id = ? ",
				Product.class);
		q2.setParameter(1, prod_id);
		Product prod = (Product) q2.getSingleResult();

		// Use the Marshaller to marshal the Java object to a file
		File file = new File("./bionicProInc-SampleProduct.xml");
		marshaller.marshal(prod, file);
		// Printout
		marshaller.marshal(prod, System.out);

	}
}