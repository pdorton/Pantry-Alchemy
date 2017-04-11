import java.sql.*;

public class MySQLConnection {

    String url = "jdbc:mysql://162.243.207.144:3306/pantry_database?useSSL=false";
    String userName = "admin";
    String password = "Pantry1!";
    Connection conn = null;
    ResultSet resultSet = null;
    int updateResult = 0;
    
    
    public Connection startConnection() throws SQLException{
			return conn = DriverManager.getConnection(url, userName, password);
    }
    
    public ResultSet requestQuery(String query) throws SQLException {

            /* Initializing the connection */
            conn = DriverManager.getConnection(url, userName, password);

            Statement statement = conn.createStatement();

             resultSet = statement.executeQuery(query);
            
        return resultSet;
    }
    
    public int insert(String query){
    	try {
            /* Initializing the connection */
            conn = DriverManager.getConnection(url, userName, password);

            Statement statement = conn.createStatement();

            updateResult = statement.executeUpdate(query);
            
            
        }
        
        catch(SQLException e){
            System.out.println("SQL connection error: " + e.getMessage());
        }
        return updateResult;
    }
    
    
    public void CloseConnection() throws SQLException{
		conn.close();
	}
}