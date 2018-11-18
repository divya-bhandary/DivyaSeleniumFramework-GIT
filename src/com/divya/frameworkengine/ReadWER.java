package com.divya.frameworkengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadWER {

	/**
	 * @param args
	 */
	public String getElementFromWER(String logicalName) {
		Properties prop = new Properties();
		String retVal=null;
		FileInputStream fis;
		try {
			fis = new FileInputStream("C:\\Users\\divya\\Documents\\DivyaSeleniumFramework\\DivyaSeleniumFramework\\wer\\wer.properties");
			prop.load(fis);
			retVal=prop.getProperty(logicalName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retVal;
	}
}
