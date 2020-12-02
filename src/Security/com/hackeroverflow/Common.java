package Security.com.hackeroverflow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Servlet Filter implementation class Common
 */
@WebFilter("/*")
public class Common implements Filter {

    /**
     * Default constructor. 
     */
    public Common() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		JSONObject urlArray=new JSONObject();
		JSONObject regexObj=new JSONObject();
		String method=req.getMethod();
		System.out.println(req.getRequestURI());
		boolean post=true;
		String msg="";
		
		try {
			if(SecurityLoader.getFilterInstance().get("FILTER").urlRuleMap.get("URL_RULE").has(req.getRequestURI())) {
				urlArray= (JSONObject) SecurityLoader.getFilterInstance().get("FILTER").urlRuleMap.get("URL_RULE").get(req.getRequestURI());  
				regexObj= SecurityLoader.getFilterInstance().get("FILTER").regexMap.get("REGEX");
				System.out.println(urlArray);
//				int temp = 0;
//		        boolean tableNameNotFound=true;
//		        while (tableNameNotFound && temp<urlArray.length()) {
//
//		        	JSONObject urlValueObject=(JSONObject) urlArray.get(temp);
//	
//		            if(method.toLowerCase().equals(urlValueObject.get("METHOD").toString().toLowerCase())) {
//		            	
////		            	if(urlValueObject.has("PARAM")) {
////		            	System.out.print(urlValueObject.get("PARAM"));
////		            	JSONArray paramArray=(JSONArray) urlValueObject.get("PARAM");
////		            	
////		            	// param configuration;
////		            	for(int i=0;i<paramArray.length();i++) {
////		            		JSONObject paramObj=(JSONObject) paramArray.get(i);
////		            		
////		            	}
////		            	
////		            	}
//		                tableNameNotFound=false;     
//		                }
//
//		            temp+=1;
//		        }
		        
//		        if(!tableNameNotFound) {
//				
//				chain.doFilter(req, res);
//				
//		        }
//		        else {
//		        	res.setStatus(403);
//					res.getWriter().println("the given url - "+req.getRequestURI()+" is valid, but "+method+" method is invalid 403");
//		        }
				
			
				if(urlArray.has(req.getMethod().toLowerCase())) {
				
					if(req.getMethod().toLowerCase().equals("get")) {
					chain.doFilter(req, res);
					}
					else if(req.getMethod().toLowerCase().equals("post")) {
						
						
						JSONObject methodObj=(JSONObject) urlArray.get("post");
						
						if(methodObj.has("PARAM")) {
		            	
		            	JSONArray paramArray=(JSONArray) methodObj.get("PARAM");
		            	JSONObject totalParamObject=(JSONObject) methodObj.get("TOTAL_PARAM_NAMES");
//		            	
		            	// param configuration;
		            	
//		            	if(req.getParameter((String) paramObj.get("NAME"))!=null) {
//		            		System.out.println("param present.");
//		            	}
		            	System.out.println(paramArray);
		            	Enumeration en=req.getParameterNames();
		            	
		            	int size=0;
		            	while(en.hasMoreElements() && size<10)
		        		{
		        			Object objOri=en.nextElement();
		        			String param=(String)objOri;
		        			if(totalParamObject.has(param)) {
		        				
		        			}
		        			else {
		        				msg+="Extra params found - "+param;
		        				post=false;
//		        				break;
		        			}
		        			size+=1;
		        		}
		            	
		            	if(size<10) {
		            	
		            	if(post) {
		            		
		            		for(int i=0;i<paramArray.length();i++) {
		            			JSONObject paramObj=(JSONObject) paramArray.get(i);
		            			System.out.println(paramObj);
		            		
		            		
		            			if(paramObj.get("OCCURANCE").equals("1")) {
		            	
		            				if(req.getParameter((String) paramObj.get("NAME"))!=null) {
		            				
		            					System.out.print(regexObj.getJSONObject((String) paramObj.get("REGEX_NAME")).get("VALUE"));
		            					if(req.getParameter((String) paramObj.get("NAME")).matches((String) regexObj.getJSONObject((String) paramObj.get("REGEX_NAME")).get("VALUE"))) {
		            						System.out.println("regex checked.");
		            					}
		            					else {
		            						post=false;
		            						msg+="wrong value in \""+paramObj.get("NAME")+"\" param \n";
		            					}
		            				
		            				}
		            				else {
		            					post=false;
		            					msg+="required \""+paramObj.get("NAME")+"\" param is absent\n";
		            				}
		            			
		            			}
		            			else {
		            					if(req.getParameter((String) paramObj.get("NAME"))!=null) {
		            				
		            						System.out.print(regexObj.getJSONObject((String) paramObj.get("REGEX_NAME")).get("VALUE"));
		            						if(req.getParameter((String) paramObj.get("NAME")).matches((String) regexObj.getJSONObject((String) paramObj.get("REGEX_NAME")).get("VALUE"))) {
		            							System.out.println("regex checked.");
		            						}
		            						else {
		            							post=false;
		            							msg+="wrong value in \""+paramObj.get("NAME")+"\" param \n";
		            						}
		            				
		            					}
		            				}
		            		
	            				}
		            		}
		            		
		            	
		            	}
		            	else {
		            		post=false;
		            		msg+="more params are found.";
		            	}
		            	
						}
						
						if(post) {
							res.addHeader("jas", "jas");
							chain.doFilter(req, res);
						}
						else {
							res.setStatus(400);
							res.getWriter().println("Bad request \n"+msg);
						}
					}
					else if(req.getMethod().toLowerCase().equals("put")) {
						chain.doFilter(req, res);
					}
					else if(req.getMethod().toLowerCase().equals("delete")) {
						chain.doFilter(req, res);
					}
				}
				else {
		        	res.setStatus(403);
					res.getWriter().println("the given url - "+req.getRequestURI()+" is valid, but "+method+" method is invalid 403");
		        }
				
			}
			else
			{
				res.setStatus(404);
				res.getWriter().println("the given url - "+req.getRequestURI()+" is invalid 404");
			}
		} catch (IOException | SAXException | ParserConfigurationException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println(fConfig.getServletContext().getRealPath(""));
	}

}
