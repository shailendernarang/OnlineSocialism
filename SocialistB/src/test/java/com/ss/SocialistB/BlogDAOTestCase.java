package com.ss.SocialistB;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class BlogDAOTestCase {
	
	@BeforeClass
	public static void intialize()
	{		
		AnnotationConfigApplicationContext annotationConfigApplicationContext =
				new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.ss.SocialistB");
		annotationConfigApplicationContext.refresh();
	}

	@Test
	public void test() {
	}

}
