package Security.com.hackeroverflow;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SecurityProperties {

	public static final Map<String, SecurityProperties> FILTER_INSTANCES = new HashMap<String, SecurityProperties>();

    public Map<String, JSONObject> urlRuleMap = new HashMap<String, JSONObject>();
    
    public Map<String, JSONObject> regexMap = new HashMap<String, JSONObject>();
	
}
