package TestNG;

import org.testng.Assert;
import org.testng.annotations.*;

public class UserAuthenticationTest extends UserAuthenticate{
 
	@DataProvider (name="User Valid Data Provider")
	public Object[][] provideValidUserData() {
        return new Object[][] {
            //  Valid credentials
            {"admin", "password123"}};

      
    
	}
	@Test(dataProvider="User Valid Data Provider")
	public void testSucessAuthenticate(String username, String password) {
		
		UserAuthenticate auth = new UserAuthenticate();
		boolean actualResult = auth.authenticate(username, password);
		
		Assert.assertTrue(actualResult, "Authentication should have SUCCEEDED for valid credentials.");
	
        }
	
	



@DataProvider (name="User InValid Data Provider")
public Object[][] provideInvalidUserData() {
    return new Object[][] {
        //  Invalid credentials
        {"wronguser", "password123"},
        {"admin", "wrongpass"},
        {"randomUser", "randomPass"},

        //  Edge cases: Empty strings
        {"", "password123"},
        {"admin", ""},
        {"", ""},

        //  Edge cases: Null values
        {null, "password123"},
        {"admin", null},
        {null, null}
    };
}

@Test(dataProvider="User InValid Data Provider")
public void testFailedAuthenticate(String username, String password) {
	
	UserAuthenticate auth = new UserAuthenticate();
	try {
        boolean actualResult = auth.authenticate(username, password);
        
        // We know these should always be false, so we use assertFalse
        Assert.assertFalse(actualResult, 
            "Authentication should have FAILED for username: '" + username + "' and password: '" + password + "'");
            
    } catch (NullPointerException e) {
       
        Assert.assertTrue(true, "Authentication failed as expected via NullPointerException");
    }

    }



}



