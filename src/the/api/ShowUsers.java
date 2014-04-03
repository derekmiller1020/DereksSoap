package the.api;

import java.util.HashMap; 
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;

public class ShowUsers {
	
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

}
