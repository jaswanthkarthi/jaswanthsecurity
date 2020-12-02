package Security.com.hackeroverflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class SecurityLoader {

	
	public static Map<String, SecurityProperties> getFilterInstance() throws IOException, SAXException, ParserConfigurationException, JSONException {

		
		System.out.println("Size : "+SecurityProperties.FILTER_INSTANCES.size());
		
		 if(SecurityProperties.FILTER_INSTANCES.size()==0){
        ArrayList<String> securityFiles = SecurityParser.getDatafiles();

        JSONObject Object = new JSONObject();
        JSONObject urlObject = new JSONObject();
        JSONObject regexObject = new JSONObject();

        Object.put("URL_RULE",urlObject);
        Object.put("REGEX",regexObject);
        
        for (int temp = 0; temp < securityFiles.size(); temp++) {

        	Object = SecurityParser.setUrlRule(securityFiles.get(temp), (JSONObject) Object.get("URL_RULE"), (JSONObject) Object.get("REGEX"));


        }

        SecurityProperties obj=new SecurityProperties();
        obj.urlRuleMap.put("URL_RULE", (JSONObject) Object.get("URL_RULE"));
        obj.regexMap.put("REGEX", (JSONObject) Object.get("REGEX"));
        SecurityProperties.FILTER_INSTANCES.put("FILTER",obj);
        
        
		 }
		 
		return SecurityProperties.FILTER_INSTANCES;

    }
	
	
}
