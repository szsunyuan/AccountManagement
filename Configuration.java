/**
 *
 * @author yuansun
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Configuration {
    private Document dom;
    private NodeList nl;
    private Client client;
    private Element eElement;
    
    public Configuration() {
        ParseXML();
    }
    //This is for parsing the xml file(act like a database)
    public void ParseXML() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse("client.xml");
            System.out.println(dom);
            Element docEle = dom.getDocumentElement();
            nl = docEle.getElementsByTagName("Client");
	}catch(ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e);
	}
    }
    //verifying matching email and pw, used for login process
    public boolean verifyPw(String email,String pw) {
        int index = searchByEmail(email);
        if(index>=0) {
            Node nNode = nl.item(index);
            eElement = (Element) nNode;
            if(pw.equals(eElement.getElementsByTagName("pw").item(0).getTextContent())) {
                loadClient(index);
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    //loading the config from xml file with a specified client and create an identical object
    public void loadClient(int index) {
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<String> locations = new ArrayList<>();
        Node nNode = nl.item(index);
        eElement = (Element) nNode;
        for(int i=0;i<eElement.getElementsByTagName("category").getLength();i++) {
            categories.add(eElement.getElementsByTagName("category").item(i).getTextContent());
        }
        for(int i=0;i<eElement.getElementsByTagName("location").getLength();i++) {
            locations.add(eElement.getElementsByTagName("location").item(i).getTextContent());
        }
        client = new Client(eElement.getElementsByTagName("name").item(0).getTextContent(),
                eElement.getElementsByTagName("pw").item(0).getTextContent(),
                eElement.getElementsByTagName("email").item(0).getTextContent(),
                Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()),
                Integer.parseInt(eElement.getElementsByTagName("accessLvl").item(0).getTextContent()),
                categories,locations);
    }
    //saving the changes to xml file(database), NOT finished.
    public void saveClient() {
        int index = searchById(client.getId());
        Node nNode = nl.item(index);
        eElement = (Element) nNode;
        for(int i=0;i<eElement.getElementsByTagName("category").getLength();i++) {
            eElement.getElementsByTagName("category").item(i).setTextContent((client.getCategory().get(i)));
        }
        for(int i=0;i<eElement.getElementsByTagName("location").getLength();i++) {
            eElement.getElementsByTagName("location").item(i).setTextContent((client.getLocation().get(i)));
        }
        eElement.getElementsByTagName("name").item(0).setTextContent(client.getName());
        eElement.getElementsByTagName("pw").item(0).setTextContent(client.getPw());
        eElement.getElementsByTagName("email").item(0).setTextContent(client.getEmail());
        eElement.getElementsByTagName("accessLvl").item(0).setTextContent(Integer.toString(client.getAccessLvl()));
        eElement.getElementsByTagName("id").item(0).setTextContent(Integer.toString(client.getId()));
        //last part is not working
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(dom);
            StreamResult result = new StreamResult(new File("client.xml"));
            transformer.transform(source, result);
        }catch ( TransformerException pce) {
		pce.printStackTrace();
	   }
    }
    //searching the client by email address
    public int searchByEmail(String email) {
        for(int i=0;i<nl.getLength();i++) {
            Node nNode = nl.item(i);
            eElement = (Element) nNode;
            if(email.equalsIgnoreCase(eElement.getElementsByTagName("email").item(0).getTextContent()))
                return i;
        }
        return -1;
    }
    //searching the client by user id
    public int searchById(int id) {
        for(int i=0;i<nl.getLength();i++) {
            Node nNode = nl.item(i);
            eElement = (Element) nNode;
            int temp = Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
            if(id == temp)
                return i;
        }
        return -1;
    }
    //set the client for configurations
    public void setClient(Client client) {
        this.client = client;
    }
    //get the current client in the config instance
    public Client getClient() {
        return client;
    } 
  }