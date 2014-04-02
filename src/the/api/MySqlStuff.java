package the.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySqlStuff {
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;
	  
	  public int checkLogin(String username, String password) throws Exception{
		  try {
			  Class.forName("com.mysql.jdbc.Driver");

			  connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/api", "root", "password");
			  
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

		  }

	  }
	  public String registerUser(String username, String password, String unique_id) throws Exception{
		  try {
			  
			  int checkData = checkLogin(username, password);
			  
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/api", "root", "password");
			  
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

		  }
	  }

}