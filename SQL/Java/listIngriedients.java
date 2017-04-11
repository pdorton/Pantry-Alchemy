import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class listIngriedients {

	public static void main(String[] args) {

		MySQLConnection connect = new MySQLConnection();
		ResultSet firstQuery = connect.requestQuery("select ing_name from pantry_database.ingredients");
		
		
		while (firstQuery.next())
			System.out.println(firstQuery.getString("ing_name"));
	
		connect.CloseConnection();
	}
}
		