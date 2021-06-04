package bioncProInc.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import bionicProInc.db.pojos.Customer;
import bionicProInc.db.pojos.Product;

public class Xml2JavaCustomer {
	private static final String PERSISTENCE_PROVIDER = "user-provider";
	private static EntityManagerFactory factory;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void importFromXml() throws JAXBException, IOException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		try {
			System.out.print("\nType the name of the file (FileName.xml): ");
			String fileName = reader.readLine();
			DTDCheckerCustomer d = new DTDCheckerCustomer();
			if (!d.checkCustomer(fileName)) {
				System.out.println("please check the file");
			} else {
				File file = new File(fileName);
				Customer c = (Customer) unmarshaller.unmarshal(file);

				// Print the customer
				System.out.println("Customer:");
				System.out.println("Name and surname: " + c.getName_surname());
				System.out.println("Age: " + c.getAge());
				System.out.println("Phone: " + c.getPhone());
				System.out.println("Email: " + c.getEmail());
				List<Product> p = new ArrayList<>();

				// Store the customer in the database
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
				for (Product product : p) {
					em.persist(product);
				}
				em.persist(c);

				// End transaction
				tx1.commit();
			}
		} catch (IllegalArgumentException iae) {
			System.out.print("\nNo such file with that name.\n");
		}
	}
}
