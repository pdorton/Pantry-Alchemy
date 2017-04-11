import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Random;

public class testcase21 {

	public static void main(String[] args) throws SQLException {

		MySQLConnection connect = new MySQLConnection();
		Random rand = new Random();
		 HashSet<String> inserts = new HashSet<>();
		long startupdateTime = System.nanoTime();
		
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for(int i=0; i < 500; i++){
            StringBuilder sb = new StringBuilder();
            String test = new String();
            
            for(int j=0; j < rand.nextInt(60); j++)
                sb.append(alphabet[rand.nextInt(25)+1]);
            
            test = sb.toString();
            
            int result = connect.insert("insert into pantry_database.test_db (test_string)"
					+ "VALUES('" + test + "');");
         
         	try {
				connect.CloseConnection();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        long endUpdateTime = System.nanoTime();
        long duration = (endUpdateTime - startupdateTime);
        
        System.out.println("Done inserting, it took " + (duration/1000000000) + "seconds");
        
        ResultSet rs = connect.requestQuery("Select test_string from pantry_database.test_db");
       
        try {
			for(int j=0; j < rs.getRow(); j++)
			{
			    rs.next();
			    if(!inserts.contains(rs.getString("test_string")));
			    {
			        System.out.println("Result not in hashset: " + rs.getString("test_string"));
			    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Successfully inserted and accurately retrieved 500ishhh records");
    }
}
