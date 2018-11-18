package com.divya.frameworkengine;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.divya.tests.Startup;

public class TestExecutor {

	/**
	 * @param args
	 */
	public void executeTest(String scenario) {
		WebElement element=null;
		String scenarioName=scenario;
		ExcelLibrary lib = new ExcelLibrary();
		ReadWER wer=new ReadWER(); //This line
		//System.out.println(wer.getElementFromWER("LOGIN_BUTTON"));		
		int numOfTestSteps = lib.getRowCount(scenarioName);		
		for(int testStep=1;testStep<=numOfTestSteps;testStep++){
			String elementIdMethod = lib.getExcelData(scenarioName, testStep, 1);
			String logicalName = lib.getExcelData(scenarioName, testStep, 2);
			String elementIdValue = wer.getElementFromWER(logicalName);
			String action = lib.getExcelData(scenarioName, testStep, 3);
			String data = lib.getExcelData(scenarioName, testStep, 4);
			//System.out.println(elementIdMethod+"--"+elementIdValue+"--"+action+"--"+data);
			//element identification
			if(elementIdMethod.equals("name")){
				try {
					element = Startup.ff.findElement(By.name(elementIdValue));
					//element=Startup.ff.findElementByName(elementIdValue);
				} catch (Exception e) {
					element=null;
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Element "+logicalName+" not found");
					
				}				
			}
			else if(elementIdMethod.equals("id")){
				try {
					element = Startup.ff.findElement(By.id(elementIdValue));
					//element=Startup.ff.findElementById(elementIdValue);
				} catch (Exception e) {
					element=null;
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Element "+logicalName+" not found");					
				}				
			}
			else if(elementIdMethod.equals("xpath")){
				try {
					element = Startup.ff.findElement(By.xpath(elementIdValue));
					//element=Startup.ff.findElementByXPath(elementIdValue);
				} catch (Exception e) {
					element=null;
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Element "+logicalName+" not found");					
				}				
			}
			else if(elementIdMethod.equals("css")){
				try {
					element = Startup.ff.findElement(By.cssSelector(elementIdValue));
					//element=Startup.ff.findElementByCssSelector(elementIdValue);
				} catch (Exception e) {
					element=null;
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Element "+logicalName+" not found");					
				}				
			}
			else if(elementIdMethod.equals("linkText")){
				try {
					element = Startup.ff.findElement(By.linkText(elementIdValue));
					//element=Startup.ff.findElementByLinkText(elementIdValue);
				} catch (Exception e) {
					element=null;
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Link "+logicalName+" not found");					
				}				
			}
			else if(elementIdMethod.equals("className")){
				try {
					element = Startup.ff.findElement(By.className(elementIdValue));
					//element=Startup.ff.findElementByClassName(elementIdValue);
				} catch (Exception e) {
					element=null;
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Element "+logicalName+" not found");					
				}				
			}
			//actions
			if(action.equals("sendKeys")){
				if(element !=null){
					element.sendKeys(data);
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Typed "+data+" into "+logicalName+" element");
				}
			}
			else if(action.equals("click")){
				if(element !=null){
					element.click();
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Clicked on "+logicalName);					
				}				
			}
			else if(action.equals("select")){
				if(element !=null){
					Select dd = new Select(element);
					dd.selectByVisibleText(data);
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Selected "+data+" from "+logicalName+" dropdown");					
				}
			}
			else if(action.equals("alert")){
				Alert alrt = Startup.ff.switchTo().alert();
				if(data.equals("ok")){			
					alrt.accept();	
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Clicked on OK");					
				}
				else if(data.equals("cancel")){
					alrt.dismiss();
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Clicked on Cancel");					
				}
			}
			else if(action.equals("switchToWindow")){
				boolean flag=false;
				Set<String> windowHandles= Startup.ff.getWindowHandles();
				Iterator<String> it = windowHandles.iterator();
				while(it.hasNext()){
					Startup.ff.switchTo().window(it.next());
					if(Startup.ff.getTitle().equals(data)){
						lib.setExcelData(scenarioName, testStep, 5, "PASS");
						lib.setExcelData(scenarioName, testStep, 6,"Switched to window with title "+data);						
						flag=true;						
						break;
					}
				}
				if(!flag){
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Now window found with the title given");					
				}
			}
			else if(action.equals("SwitchToFrame")){
				if(element !=null){
					Startup.ff.switchTo().frame(element);
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Switched to frame");
				}
				else{
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"No such frame");
				}
			}			
			else if(action.equals("verifyTitle")){
				String actual = Startup.ff.getTitle();
				if(actual.equals(data)){
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Title matches");					
				}
				else{
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Title fails to match");					
				}
			}
			else if(action.equals("verifyText")){
				if(element !=null){
					String actual = element.getText();
					if(actual.equals(data)){
						lib.setExcelData(scenarioName, testStep, 5, "PASS");
						lib.setExcelData(scenarioName, testStep, 6,actual+" Text matches "+data);								
					}
					else{
						lib.setExcelData(scenarioName, testStep, 5, "FAIL");
						lib.setExcelData(scenarioName, testStep, 6,actual+" Text does not match "+data);						
					}
				}				
			}
			else if(action.equals("verifyElement")){
				if(element !=null){
					lib.setExcelData(scenarioName, testStep, 5, "PASS");
					lib.setExcelData(scenarioName, testStep, 6,"Element is found");
					System.out.println("Element is found");
				}
				else{
					lib.setExcelData(scenarioName, testStep, 5, "FAIL");
					lib.setExcelData(scenarioName, testStep, 6,"Element not found");
					System.out.println("Element not found");
				}
			}
		}		
	}	
}
