package the.api;

import java.util.UUID;

public class Register {
	
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

		}
	}
}
