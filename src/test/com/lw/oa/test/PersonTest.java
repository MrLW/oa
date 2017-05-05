package com.lw.oa.test;

import com.lw.oa.service.PersonService;
import org.junit.Test;


public class PersonTest extends SpringUtils{
	@Test
	public void testPersonService(){
		PersonService personService = (PersonService) context.getBean("personService");
		System.out.println(personService);
	}
}
