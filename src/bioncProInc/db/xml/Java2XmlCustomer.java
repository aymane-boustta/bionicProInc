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

import bionicProInc.db.pojos.Customer;
import bionicProInc.db.utils.inputOutput;

public class Java2XmlCustomer {
	// Put entity manager and buffered reader here so it can be used
	// in several methods
	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static inputOutput io = new inputOutput();

	private static void printCustomers() {
		Query q1 = em.createNativeQuery("SELECT * FROM customers", Customer.class);
		@SuppressWarnings("unchecked")
		List<Customer> customers = (List<Customer>) q1.getResultList();
		for (Customer cust : customers) {
			System.out.println(cust);
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
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();

			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// Choose the report to turn into an XML
			printCustomers();
			System.out.print("\nChoose a customer to turn into an XML file:");
			int cust_id = io.getIntFromKeyboard();
			Query q2 = em.createNativeQuery("SELECT * FROM customers WHERE id = ? ", Customer.class);
			q2.setParameter(1, cust_id);
			Customer cust = (Customer) q2.getSingleResult();

			// Use the Marshaller to marshal the Java object to a file
			System.out.print("\nType the desired name for the file (FileName.xml): ");
			String fileName = reader.readLine();
			File file = new File(fileName);
			marshaller.marshal(cust, file);
			// Printout
			marshaller.marshal(cust, System.out);
		} catch (NoResultException nre) {
			System.out.println("\n No customer with that ID.");
		}
	}
}
