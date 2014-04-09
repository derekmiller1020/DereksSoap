package the.api;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

public class TheAction {

	//register the user
	public String register(String username, String password) throws Exception{
		MySqlStuff theStuff = new MySqlStuff();
		
		try {
			String unique_id = UUID.randomUUID().toString();
			String registerStuff = theStuff.registerUser(username, password, unique_id);
			return registerStuff;
		} 
		catch (Exception e) {
			throw e;
		} finally {
			//to do
		}
	}
	
	//Check Login
	public String checkLogin(String username, String password) throws Exception{
		MySqlStuff theStuff = new MySqlStuff();
		
		try {
			int theValue = theStuff.checkLogin(username, password);
			if (theValue > 0){
				return "You entered the right credentials";
			}
			else{
				return "You entered the wrong credentials";
			}
		} 
		catch (Exception e) {
			throw e;
		} finally {
			//to do
		}
	}
	
	public String doIt() throws Exception{
		MySqlStuff theStuff = new MySqlStuff();
		
		try {
			String registerStuff = theStuff.displayUsers();
			return registerStuff;
		} 
		catch (Exception e) {
			throw e;
		} finally {
			//figure out what I want to finally do
		}
		
	}
	
	public String insertInfo(String unique_id, String movie, String music, String book, String biography) throws Exception{
		MySqlStuff theStuff = new MySqlStuff();
		
		Map<String,String> theData = new HashMap<String,String>();
		theData.put("unique_id", unique_id);
		theData.put("movie", movie);
		theData.put("music", music);
		theData.put("book", book);
		theData.put("biography", biography);
		
		String theReturn = theStuff.insertInfo(theData); 
		return theReturn;
		
	}
	
}
