import java.sql.*
import java.util.ArrayList;

public class Queries {

    protected static String getRecipeName() {

    }

    protected static String getIngredients() {
        
    	ArrayList<String> ingredientList = new ArrayList<String>();
	MySQLConnection connect = new MySQLConnection();
	PreparedStatement psmt = null;

	psmt = connect.startConnection().prepareStatement("SELECT ing_name FROM ingredients ORDER BY ing_name");

	ResultSet results = psmt.executeQuery();

	while (results.next())
		ingredientList.add(results.getString("ing_name"));
	
	    
	connect.CloseConnection();
	return ingredientList;
    }

    protected static Image getPhoto() {

    }
}
