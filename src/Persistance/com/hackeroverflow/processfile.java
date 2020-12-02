package Persistance.com.hackeroverflow;
import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.*;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import Security.com.hackeroverflow.SecurityLoader;

public class processfile implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {
		System.out.println("\n\n\nPERSISTANCE        :   LOADING");
		System.out.println("CREATING TABLES    :   [[][][]");

            Statement stmt = GetConnection.getInstance().createStatement();
            ResultSet rs = stmt.executeQuery("Show tables");
            System.out.println("Tables in the current database: ");
            boolean tables=false;
            while(rs.next()) {
                System.out.print(rs.getString(1));
                System.out.println();
                tables=true;
            }

            if(tables){
                System.out.println("There are tables already.");
            }
		    else{
                persistanceDb.createTables();
            }

            
            
            System.out.println("\n\n------------------------\n"+SecurityLoader.getFilterInstance());
            
		System.out.println("     -----SUCCESS-----     \n\n\n");
		
		
        
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	
}
