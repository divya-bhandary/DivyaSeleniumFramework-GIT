package com.divya.tests;

import org.junit.Test;

import com.divya.frameworkengine.TestExecutor;

public class DeleteCustomer {

	@Test
	public void testDeleteCustomer() {
		System.out.println("Starting DeleteCustomer Scenario");
		TestExecutor exe = new TestExecutor();
		exe.executeTest("DeleteCustomer");
		System.out.println("Ending DeleteCustomer Scenario");
	}

}
