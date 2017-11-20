package fr.afcepf.dja.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.afcepf.dja.ws.IServiceDevise;

@RunWith(SpringJUnit4ClassRunner.class) //de spring-test
@ContextConfiguration(locations="/jpaSpringConf.xml")
public class VerifierServiceDevise {
	
	@Autowired
	private IServiceDevise serviceDevise; //à tester
	
	@Test
	public void testConvertir(){
		double res = serviceDevise.convertir(50.0, "EUR", "USD");
		System.out.println("res conversion=" + res);
		Assert.assertTrue(res > 0);
	}

}
