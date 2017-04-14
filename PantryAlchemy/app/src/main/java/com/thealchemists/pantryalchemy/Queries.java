import java.sql.*
import java.util.ArrayList;

public class Queries {


    //sends back list of all ingredients ordered by name
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

    //not sure if we need this...
    protected static Image getPhoto() {
    	//todo
    	return;
    }


    //creates a arraylist of ingredients based on the recipeId given sends it back
    protected static ArrayList<Ingredient> getRecipeingredients(int recipeId){

    	MySQLConnection connect = new MySQLConnection();
		PreparedStatement psmt = null;
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		Ingredient tempIngredient = null;
		

		psmt = connect.startConnection().prepareStatement("SELECT ing_name, unit, quantity "
														+ "FROM recipe_ingredient "
														+ "JOIN ingredients, measurements, recipe "
														+ "WHERE recipe_ingredient.rid = recipe.id "
														+ "AND recipe_ingredient.measure_id = measurements.id "
														+ "AND recipe_ingredient.ing_id = ingredients.id "
														+ "AND recipe.id = ?" + ";");

		psmt.setString(1,"" + recipeId);
		
		ResultSet results = psmt.executeQuery();
		
		//for all results make the ingredient for every row
		while(results.next()){
			tempIngredient = new Ingredient(results.getString("ing_name"), results.getString("unit"), Integer.parseInt(results.getString("quantity")));
			ingredients.add(tempIngredient);
		}
		//close connection send back arraylist
		connect.CloseConnection();
		return ingredients;
    }


    /*makes the recipe object given a Id, could make the first 5 in the table etc..
      probably need to get number of times favorited but not sure yet*/
    protected static Recipe makeRecipe(int recipeId){

		MySQLConnection connect = new MySQLConnection();
		PreparedStatement psmt = null;
		ArrayList<Ingredient> recipeIngredients = getRecipeingredients(thisRecipe);
		Recipe thisRecipe = null;

		psmt = connect.startConnection().prepareStatement("SELECT recipe_name, directions, photo "
														+ "FROM recipe "
														+ "WHERE recipe.id = ?" + ";");

		psmt.setString(1,"" + recipeId);
		ResultSet results = psmt.executeQuery();
		
		//make recipe from one row
		while(results.next()){

			//get photo as blob
			blob = results.getBlob("photo");
			 
			//get length of binary data
			int blobLength = (int) blob.length(); 
				
			//change blob to byte array
			byte[] photo = blob.getBytes(1, blobLength);
			
			//free up memory space
			blob.free();
			
			//create recipe
			thisRecipe = new Recipe(results.getString("recipe_name"), results.getString("directions"), photo, recipeIngredients);
		}
		
		return thisRecipe;

    }




}
