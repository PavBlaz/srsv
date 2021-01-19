package hr.pb.fer.srsv.lri1.warningMechanism;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class xmlProvider extends Thread {

	private URL url;

	public xmlProvider(String url) {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try (BufferedInputStream in = new BufferedInputStream(url.openStream());
				FileOutputStream fileOutputStream = new FileOutputStream("temp.xml")) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// handle exception
		}

		File inputFile = new File("temp.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        
		
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        
			String readingTime;
			String input = doc.getElementsByTagName("observation_time_rfc822").toString();
			SimpleDateFormat parser = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
			Date date = parser.parse(input);

			readingTime = new SimpleDateFormat("yyyyyMMdHHmmss").format(date).toString();
			File newName = new File(readingTime + ".xml");
			inputFile.renameTo(newName);

		} catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
