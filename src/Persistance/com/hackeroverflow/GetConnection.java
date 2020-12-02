package Persistance.com.hackeroverflow;

import Persistance.com.hackeroverflow.propertyFileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection {

    private static java.sql.Connection Connection=null;

    public static Connection getInstance() throws ClassNotFoundException, SQLException, IOException {

        if(Connection==null){

        	
            Properties prop=propertyFileReader.readPropertiesFile("/Users/jaswa-zuch749/eclipse-workspace/hrof/WebContent/WEB-INF/hackeroverflow/database.properties");

            Class.forName(prop.getProperty("connectionclass"));

            Connection = DriverManager.getConnection(
                    prop.getProperty("connector")+prop.getProperty("hostname")+":"+prop.getProperty("port")+"/"+prop.getProperty("database"), prop.getProperty("user"), prop.getProperty("password"));

        }
        return Connection;
    }
}
