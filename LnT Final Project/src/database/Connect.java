package database;

import java.sql.*;

public final class Connect {
	private final String username = "root"; 
	private final String password = ""; 
	private final String database = "bobacool"; 
	private final String host = "localhost:3306"; 
	private final String connection = String.format("jdbc:mysql://%s/%s", host, database);
	private Connection c;
	private Statement st;
	private static Connect connect;
	
    private Connect() {
    	try {  
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(connection, username, password);  
            st = c.createStatement(); 
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("Failed to connect!");
        	System.exit(0);
        }
    }
    
    public static synchronized Connect getConnection() {
		return connect = (connect == null) ? new Connect() : connect;
    }

    public ResultSet executeQuery(String query) {
        ResultSet rs = null;
        
    	try {
            rs = st.executeQuery(query);
        } catch(Exception e) {
        	e.printStackTrace();
        }
    	
        return rs;
    }

    public void executeUpdate(String query) {
    	try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	
    	try {
			ps = c.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return ps;
    }
}
