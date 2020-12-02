package Rest.com.hackeroverflow;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Resource {

	public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String tableName;

    public void getTable(String apiName) throws IOException, SAXException, ParserConfigurationException {


        Document ddfilesdoc=xmlparsetodocobject("/Users/jaswa-zuch749/eclipse-workspace/hrof/WebContent/WEB-INF/hackeroverflow/database/tables.xml");
        NodeList fileLists = ddfilesdoc.getElementsByTagName("table");

        int temp = 0;
        boolean tableNameNotFound=true;
        while (tableNameNotFound && temp<fileLists.getLength()) {
            Node fileNode = fileLists.item(temp);

            if (fileNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) fileNode;
                if(eElement.getElementsByTagName("api-ref-name").item(0).getTextContent().equals(apiName)){
                    tableNameNotFound=false;
                    setTableName(eElement.getElementsByTagName("name").item(0).getTextContent());
                }

            }

            temp+=1;
        }

    }

    public static Document xmlparsetodocobject(String path) throws ParserConfigurationException, IOException, SAXException {

        File xmlfile = new File(path);
        DocumentBuilderFactory ddfile = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = ddfile.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();

        return doc;
    }
	
}
