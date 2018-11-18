package com.divya.tests;
import org.junit.Test;

import com.divya.frameworkengine.TestExecutor;

public class LoginLogout {

	@Test
	public void testLoginLogout() {		
		System.out.println("Starting LoginLogout Scenario");
		TestExecutor exe = new TestExecutor();
		exe.executeTest("LoginLogout");
		System.out.println("Ending LoginLogout Scenario");
	}

}
