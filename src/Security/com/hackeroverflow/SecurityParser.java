package Security.com.hackeroverflow;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import Persistance.com.hackeroverflow.*;

public class SecurityParser {

	private static final String PATH="/Users/jaswa-zuch749/securityxml/src/security";

    public static ArrayList<String> getDatafiles() throws ParserConfigurationException, IOException, SAXException {

        ArrayList<String> ddfiles= new ArrayList<>();

        Document ddfilesdoc=persistanceDb.xmlparsetodocobject(PATH+"/security-config.xml");
        NodeList fileLists = ddfilesdoc.getElementsByTagName("file");

        for (int temp = 0; temp < fileLists.getLength(); temp++) {
            Node fileNode = fileLists.item(temp);

            if (fileNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) fileNode;
                String filepath=PATH+eElement.getAttribute("path");
                System.out.println(eElement.getAttribute("path"));
                ddfiles.add(filepath);

            }

        }
        return ddfiles;
    }

    public static JSONObject setUrlRule(String xmlpath, JSONObject urlObject, JSONObject regexObject) throws IOException, SAXException, ParserConfigurationException, JSONException {

        JSONObject object=new JSONObject();
        Document securityFilesDoc = persistanceDb.xmlparsetodocobject(xmlpath);
        NodeList urlNodeList = securityFilesDoc.getElementsByTagName("urls");

        for (int itr = 0; itr < urlNodeList.getLength(); itr++)
        {
            Node node = urlNodeList.item(itr);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                NodeList urlList=eElement.getElementsByTagName("url");
                for (int itr2 = 0; itr2 < urlList.getLength(); itr2++)
                {
                    Node node2 = urlList.item(itr2);
                    Element urlElement = (Element) node2;



                    JSONObject urlValueObject=new JSONObject();

                    urlValueObject.put("METHOD",urlElement.getAttribute("method"));

                    if(urlElement.getAttribute("method").equals("post")){

                        ArrayList<JSONObject> paramArray=new ArrayList();
                        JSONObject paramNameObject=new JSONObject();
                        NodeList paramList=urlElement.getElementsByTagName("param");
                        for (int p = 0; p < paramList.getLength(); p++) {
                            Node param = paramList.item(p);
                            JSONObject emptyObj=new JSONObject();
                            Element paramElement = (Element) param;

                            JSONObject paramValueObject=new JSONObject();

                            paramValueObject.put("REGEX_NAME",paramElement.getAttribute("regex"));
                            paramValueObject.put("OCCURANCE",paramElement.getAttribute("occurrence"));
                            paramValueObject.put("NAME",paramElement.getAttribute("name"));

                            paramArray.add(paramValueObject);
                            paramNameObject.put(paramElement.getAttribute("name"),emptyObj);
                        }
                        
                        urlValueObject.put("TOTAL_PARAM_NAMES",paramNameObject);
                        urlValueObject.put("PARAM",paramArray);
                    }


                    if (!urlObject.has(urlElement.getAttribute("path"))) {
                        JSONObject urlValuesArray=new JSONObject();
                        urlValuesArray.put(urlElement.getAttribute("method"),urlValueObject);
                        urlObject.put(urlElement.getAttribute("path"),urlValuesArray);
                    } else {
                        urlObject.getJSONObject(urlElement.getAttribute("path")).put(urlElement.getAttribute("method"),urlValueObject);
                    }

                }

            }
        }

        NodeList regexNodeList = securityFilesDoc.getElementsByTagName("regexes");
        for (int itr = 0; itr < regexNodeList.getLength(); itr++)
        {
            Node node = regexNodeList.item(itr);

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                NodeList urlList=eElement.getElementsByTagName("regex");
                for (int itr2 = 0; itr2 < urlList.getLength(); itr2++)
                {
                    Node node2 = urlList.item(itr2);
                    Element regexElement = (Element) node2;

                    JSONObject regexInnerObject=new JSONObject();

                    regexInnerObject.put("VALUE",regexElement.getAttribute("value"));

                    regexObject.put(regexElement.getAttribute("name"),regexInnerObject);

                }

            }
        }


        object.put("URL_RULE",urlObject);
        object.put("REGEX",regexObject);

        return object;
    }

	
}
