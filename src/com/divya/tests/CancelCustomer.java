package com.divya.tests;

import org.junit.Test;

import com.divya.frameworkengine.TestExecutor;

public class CancelCustomer {

	@Test
	public void testCancelCustomer() {
		System.out.println("Starting CancelCustomer Scenario");
		TestExecutor exe = new TestExecutor();
		exe.executeTest("CancelCustomer");
		System.out.println("Ending CancelCustomer Scenario");
	}

}
