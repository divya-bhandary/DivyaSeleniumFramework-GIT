package com.divya.tests;

import org.junit.Test;

import com.divya.frameworkengine.TestExecutor;

public class CreateCustomer {

	@Test
	public void testCreateCustomer() {
		System.out.println("Starting CreateCustomer Scenario");
		TestExecutor exe = new TestExecutor();
		exe.executeTest("CreateCustomer");
		System.out.println("Ending CreateCustomer Scenario");
	}

}
