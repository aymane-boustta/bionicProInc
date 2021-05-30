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

import bionicProInc.db.pojos.*;

public class Xml2JavaProduct {
	private static final String PERSISTENCE_PROVIDER = "user-provider";
	private static EntityManagerFactory factory;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public void importFromXml() throws JAXBException, IOException {

		// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		// Use the Unmarshaller to unmarshal the XML document from a file
		try {
			System.out.print("\nType the name of the file (FileName.xml): ");
			String fileName = reader.readLine();
			File file = new File(fileName);
			Product p = (Product) unmarshaller.unmarshal(file);

			// Print the product
			System.out.println("Product:");
			System.out.println("Name: " + p.getName());
			System.out.println("BodyPart: " + p.getBodypart());
			System.out.println("Price: " + p.getPrice());
			System.out.println("Creation date: " + p.getDate());
			List<Characteristic> ch = new ArrayList<>();
			List<Material> m = new ArrayList<>();
			List<Engineer> e = new ArrayList<>();

			// Store the product in the database
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
		} catch (IllegalArgumentException iae) {
			System.out.print("\nNo such file with that name.\n");
		}
	}
}
