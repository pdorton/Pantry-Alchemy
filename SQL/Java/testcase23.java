import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class testcase21 {

	public static void main(String[] args) throws SQLException {

		MySQLConnection connect = new MySQLConnection();
		PreparedStatement psmt = null;
		
		//location of the picture you want to upload
		File image = new File("C:\\Users\\Burus\\Documents\\image.jpeg");
		try {
			
			FileInputStream fis = new FileInputStream(image);
			psmt = connect.startConnection().prepareStatement("insert into recipe(recipe_name, directions, photo, times_favorited) "+ "values(?,?,?,?)");
	        psmt.setString(1,"Szechuan");
	        psmt.setString(2,"test");
	        psmt.setBinaryStream(3, (InputStream)fis, (int)(image.length()));
	        psmt.setString(4,"0");
	        
	        int s = psmt.executeUpdate();
	        if(s>0)
	        	System.out.println("success");
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connect.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}