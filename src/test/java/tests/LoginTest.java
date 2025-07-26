package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utiles.ExcelUtils;
import utiles.ExtentReportManager;
import utiles.Log;

public class LoginTest extends BaseTest {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {
		String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];
		for (int i = 1; i < rowCount; i++) {
			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password

		}

		ExcelUtils.closeExcel();
		return data;
	}

	@DataProvider(name = "LoginData2")
	public Object[][] getData() {
		return new Object[][] { { "user1", "pass1" }, { "user2", "pass2" }, { "user3", "pass3" } };
	}

//	@Test(dataProvider = "LoginData2")
	@Test
	@Parameters({"username","password"})
	public void testValidLogin(String username, String password) {
		Log.info("Starting Login Test");
		test = ExtentReportManager.createTest("LoginTest - " + username);

		test.info("Navigation to URL ");
		LoginPage loginPage = new LoginPage(driver);

		Log.info("Adding user and apssword");
		test.info("Adding user and apssword");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);

		test.info("Clicking on login Button");
		loginPage.clickLogin();
		System.out.println("Title of the page is" + driver.getTitle());
		Log.info("Vervfiy title");
		test.info("Verfiyng page title");
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		test.pass("Login Successfule");

	}

//	@Test
//	public void testInValidLogin() {
//		Log.info("Starting Login Test");
//		test = ExtentReportManager.createTest("LoginTest with invalid crendtials");
//
//		test.info("Navigation to URL ");
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Adding user and apssword");
//		test.info("Adding user and apssword");
//		loginPage.enterUsername("45564");
//		loginPage.enterPassword("454565");
//		test.info("Clicking on login Button");
//		loginPage.clickLogin();
//		System.out.println("Title of the page is" + driver.getTitle());
//		Log.info("Vervfiy title");
//		test.info("Verfiyng page title");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...");
//		test.pass("Login Successfule");
//
//	}

}
