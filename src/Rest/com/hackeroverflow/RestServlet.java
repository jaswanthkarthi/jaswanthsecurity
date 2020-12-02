package Rest.com.hackeroverflow;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;


@WebServlet("/api/v1/*")
public class RestServlet extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SAXException | ParserConfigurationException | JSONException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SAXException | ParserConfigurationException | JSONException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SAXException | ParserConfigurationException | JSONException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | SAXException | ParserConfigurationException | JSONException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SAXException, ParserConfigurationException, JSONException, ClassNotFoundException, SQLException {

        String uri = request.getRequestURI();  
        Resource obj=new Resource(); 
        obj.getTable(uri.split("/")[3]);
		doOperation(request,response,obj.getTableName());
    }
	
	private void doOperation(HttpServletRequest request, HttpServletResponse response,String tableName) throws JSONException, IOException, ClassNotFoundException, SQLException {
		
		JSONObject jsonResponse = null;
		
		switch (request.getMethod()) {
		case "GET":
			jsonResponse = DBOperation.GET(tableName);
			break;

		case "POST":
//			jsonResponse = RestUtil.getCreateSuccessResponse(DBOperation.POST(restURI));
			jsonResponse = null;
			break;

		case "PUT":
//			jsonResponse = RestUtil.getUpdateSuccessResponse(DBOperation.PUT(restURI));
			jsonResponse = null;
			break;
			
		case "DELETE":
//			jsonResponse = RestUtil.getDeleteSuccessResponse(DBOperation.DELETE(restURI));
			jsonResponse=null;
			break;
			
		}
		
		response.setContentType("application/json");
		response.getWriter().println(jsonResponse);
		
	}

}
