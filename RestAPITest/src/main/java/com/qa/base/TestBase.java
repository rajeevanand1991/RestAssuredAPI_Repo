package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public static int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_500 = 500;
	
	public Properties prop;
	
		public TestBase() {
			try {
				prop = new Properties();
				//File file = new File("E:\\eclipseWorkspace\\RestAPITest\\src\\main\\java\\com\\qa\\config\\config.properties");
				 String wd = System.getProperty("user.dir");
				 System.out.println("user.dir ------>>>>>> " +wd);
				FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
