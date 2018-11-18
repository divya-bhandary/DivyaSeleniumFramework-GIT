package com.divya.tests;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.runner.JUnitCore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.divya.frameworkengine.ExcelLibrary;
public class Startup {
	public static WebDriver ff;
	public static void main(String[] args){
		ExcelLibrary lib = new ExcelLibrary();	
		String browser = lib.getExcelData("URL", 1, 0);
		if(browser.equals("firefox")){
			System.setProperty("wendriver.gecko.driver", "C:\\Users\\divya\\Documents\\Trainings\\Selenium\\drivers\\geckodriver.exe");
			ff = new FirefoxDriver();			
		}
		else if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\divya\\Documents\\Trainings\\Selenium\\drivers\\chromedriver.exe");
			ff = new ChromeDriver();	
		}
		else if(browser.equals("ie")){
			System.setProperty("webdriver.ie.driver", "C:\\Users\\divya\\Documents\\Trainings\\Selenium\\drivers\\IEDriverServer.exe");
			ff = new InternetExplorerDriver();	
		}
		String appUrl = lib.getExcelData("URL", 1, 1);
		ff.get(appUrl);
		ff.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
		int numOfTestScripts = lib.getRowCount("Scenarios");
		JUnitCore core = new JUnitCore();
		for(int testScript=1;testScript<=numOfTestScripts;testScript++){
			String execStatus=lib.getExcelData("Scenarios", testScript, 1);
			if(execStatus.equalsIgnoreCase("yes")){
				String scenario=lib.getExcelData("Scenarios", testScript, 0);
				System.out.println(scenario);
				try {
					Class scriptToRun = Class.forName("com.divya.tests."+scenario);
					core.run(scriptToRun);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		}
		ff.quit();
		
	}
	public void zipFolder(){		
		  try
		 {
		 File inFolder=new File("tests");
		   File outFolder=new File("../tests.zip");
		 ZipOutputStream out = new ZipOutputStream(new 
		BufferedOutputStream(new FileOutputStream(outFolder)));
		 BufferedInputStream in = null;
		 byte[] data  = new byte[1000];
		 String files[] = inFolder.list();
		 for (int i=0; i<files.length; i++)
		  {
		  in = new BufferedInputStream(new FileInputStream
		(inFolder.getPath() + "/" + files[i]), 1000);  
		out.putNextEntry(new ZipEntry(files[i])); 
		  int count;
		  while((count = in.read(data,0,1000)) != -1)
		  {
		 out.write(data, 0, count);
		  }
		  out.closeEntry();
		  }
		  out.flush();
		  out.close();
		  }
		  catch(Exception e)
		 {
		  e.printStackTrace();
		  } 
		 }
}
