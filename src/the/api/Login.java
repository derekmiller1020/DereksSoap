package the.api;

public class Login {

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

		}
	}
}
