package the.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.json.*;


public class MySqlStuff {
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  
	  public MySqlStuff() throws Exception{
		  
		  Class.forName("com.mysql.jdbc.Driver");

		  connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/api", "root", "password");
	  }
	  
	  public int checkLogin(String username, String password) throws Exception{
		  try {
			  
			  preparedStatement = connect.prepareStatement("select count(username) from api.users where username = ? and password = ?");
			  preparedStatement.setString(1, username);
			  preparedStatement.setString(2, password);
			  resultSet = preparedStatement.executeQuery();

			  while(resultSet.next()){
				  int theResult = resultSet.getInt(1);
				  return theResult;
			  } 
			  return 0;
			  
		  } catch (Exception e) {
			  throw e;
		  } finally {
			  //Once, I am done testing this prototype, I'll close connection here
		  }

	  }
	  public String registerUser(String username, String password, String unique_id) throws Exception{
		  try {
			  
			  int checkData = checkLogin(username, password);
		  
			  if (checkData < 1){
				  preparedStatement = connect.prepareStatement("INSERT INTO users (username, password, unique_id) VALUES (?,?,?)");
				  preparedStatement.setString(1, username);
				  preparedStatement.setString(2, password);
				  preparedStatement.setString(3, unique_id);
				  preparedStatement.executeUpdate();
				  
				  return "Username successfully entered";
			  }
			  else{
				  return "That username has already been created";
			  }
			  
		  } catch (Exception e) {
			  
			  throw e;
		  
		  } finally {
			//Once, I am done testing this prototype, I'll close connection here
		  }
	  }
	  
	  public String displayUsers() throws Exception{
		  
		  List<String> theString = new ArrayList();
		  try{
			  
			  statement = connect.createStatement();
			  resultSet = statement.executeQuery("select username from users");
			  
			  while (resultSet.next()) {

				  String user = resultSet.getString("username");

				  theString.add(user);
				
			  }
		  JSONArray jsonA = new JSONArray(theString);
				
		  return jsonA.toString();

		  }
		  catch(Exception e){
			  throw e;
		  }
		  finally {
			//Once, I am done testing this prototype, I'll close connection here 
		  }
	  }
	  
	  public String insertInfo(Map<String,String> theData) throws Exception {
		  
		  String sql = "insert into stuff(unique_id, music, movie, book, biography) VALUES( ?, ?, ?, ?, ?)";
		  preparedStatement = connect.prepareStatement(sql);
		  preparedStatement.setString(1, theData.get("unique_id"));
		  preparedStatement.setString(2, theData.get("music"));
		  preparedStatement.setString(3, theData.get("movie"));
		  preparedStatement.setString(4, theData.get("book"));
		  preparedStatement.setString(5, theData.get("biography"));
		  
		  preparedStatement.executeUpdate();
		  
		  return "Your data has been inserted";
	  }
}