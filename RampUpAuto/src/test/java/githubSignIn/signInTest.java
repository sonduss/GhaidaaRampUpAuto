package githubSignIn;

public class signInTest {
    signInPage githubPage = new signInPage();
    Webdriver driver;


    @DataProvider (name = "githubCredintials")
    public Object[][] credintialsData() {
        return new Object [][] {
                {"asaltest19@gmail.com", "password123456789s*", true},
                { "asaltest1646449@gmail.com", "password123456789s*", false},
                {"asaltest19@gmail.com", "password123456789sgg", false},
                {"", "", false}
        }
    }

    @Test(dataProvider = "githubCredintials")
    public void githubSignInTest(String userName, String password, boolean expectedResult){


        userNameOrEmail.sendKeys(userName);
        password.sendKeys(password);
        signInButton.click();

        if (expectedResult){
            Assert.assertTrue(isSuccessfulSignIn(), true);
            else
                Assert.assertTrue(isInvalidDataMessage() , false)
        }

    }


}
