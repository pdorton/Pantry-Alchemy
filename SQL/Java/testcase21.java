import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testcase21 {

	public static void main(String[] args) throws SQLException {

		int i;
		ArrayList<MySQLConnection> connections = new ArrayList();

    	for(i=0; i < 500; i++){

            connections.add(new MySQLConnection());
            
            ResultSet rs = connections.get(i).request("SELECT recipe_name FROM recipe");

			rs.next();

			System.out.println(i + " " + rs.getString("recipe_name"));
        }

        System.out.println("Successfully opened " + i + " connections simultaneously.");

		for(i=i; i >= 0; i--){
			connections.get(i).CloseConnection();
        }
	}
}