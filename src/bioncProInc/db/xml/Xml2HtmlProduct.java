package bioncProInc.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Xml2HtmlProduct {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Simple transformation method. You can use it in your project.
	 * 
	 * @param sourcePath - Absolute path to source xml file.
	 * @param xsltPath   - Absolute path to xslt file.
	 * @param resultDir  - Directory where you want to put resulting files.
	 */
	public static void simpleTransform(String sourcePath, String xsltPath, String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)), new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exportToHtml() throws IOException {
		try {
			System.out.print("\nType the name of the XML file (FileName.xml): ");
			String xmlName = reader.readLine();
			System.out.print("\nType the desired name of the Html file (FileName.html): ");
			String htmlName = reader.readLine();
			simpleTransform(xmlName, "./Product-Style.xslt", htmlName);
		} catch (IllegalArgumentException iae) {
			System.out.print("\nNo such file with that name.\n");
		}

	}

}
