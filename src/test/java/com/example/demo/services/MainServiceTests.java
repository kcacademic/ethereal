package com.example.demo.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainServiceTests {
	
	@Test
	public void serviceTests() {
		
		MainService service = new MainService();
		
		assertEquals(service.getUser("Kumar"), "Kumar");
		
	}

}
