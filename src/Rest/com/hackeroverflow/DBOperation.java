package Rest.com.hackeroverflow;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import Persistance.com.hackeroverflow.GetConnection;

public class DBOperation {

	
	public static JSONObject GET(String tableName) throws JSONException, ClassNotFoundException, SQLException, IOException {
		JSONObject jsonResult= new JSONObject();
		 PreparedStatement statement = GetConnection.getInstance().prepareStatement("SELECT USER_NAME,USER_EMAIL FROM "+tableName);
		 ResultSet rs = statement.executeQuery();
		 ArrayList<JSONObject> userArray=new ArrayList();
		 while(rs.next()) {
			 JSONObject userObj=new JSONObject();
			 userObj.put("USER_NAME", rs.getString(1));
			 userObj.put("USER_EMAIL", rs.getString(2));
			 userArray.add(userObj);
		 }
		 
		 jsonResult.put("TOTAL_COUNT", userArray.size());
		 jsonResult.put("DATA", userArray);
		 
		return jsonResult;
	}

	public static JSONObject POST(String tableName) {
		return null;
		
	}

	public static JSONObject PUT(String tableName)  {
		return null;
		
	}
	
	public static void DELETE(String tableName)  {
	}
	
}
