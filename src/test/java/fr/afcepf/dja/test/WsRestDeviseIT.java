package fr.afcepf.dja.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import fr.afcepf.dja.data.Devise;
	
//NB: ...IT = convention des noms des "Integration Test" avec Maven

//NB: ici volontairement pas de @RunWith(SpringJUnit4ClassRunner.class)
//ni de @ContextConfiguration
//car ce test EXTERNE d'intégration est supposé être indépendant de la
//structure interne de l'application à tester (et à préalableament démarrer dans Tomcat)
public class WsRestDeviseIT {
	
	private static Logger logger = LoggerFactory.getLogger(WsRestDeviseIT.class);
	
	private static RestTemplate restTemplate;
	
	@BeforeClass
	public static void initRestTemplate(){
		restTemplate = new RestTemplate();
	}
	
	
	@Test
	public  void testRestGetClientByNum()
	{
		final String codeDevise = "EUR";
	    final String uri = "http://localhost:8080/serverSpringMvc/ws/rest/devise/"+codeDevise;
	    String resultAsJsonString = restTemplate.getForObject(uri, String.class);
	    logger.info("json devise EUR via rest: " + resultAsJsonString);
	    Devise dev = restTemplate.getForObject(uri, Devise.class);
	    logger.info("java devise EURvia rest: "  +dev.toString());
	    Assert.assertTrue(dev.getCodeDevise().equals("EUR"));
	}
	
	@Test
	public  void testRestPostAndDelete()
	{
	    final String uri = "http://localhost:8080/serverSpringMvc/ws/rest/devise";
	    //ajout:
	    Devise newDevise = new Devise();
	    newDevise.setCodeDevise("MS9");
	    newDevise.setTauxChange(1.123456);
	   
	    Devise savedDev = restTemplate.postForObject(uri, newDevise, Devise.class);
	    logger.info("savedDev via rest: " + savedDev.toString());
	    Assert.assertTrue(savedDev.getCodeDevise().equals("MS9"));
	   
	   //verification:
	   String resultAsJsonString = restTemplate.getForObject(uri, String.class);
	   logger.info("json devise list via rest (after add MS9): " + resultAsJsonString);
	   Assert.assertTrue(resultAsJsonString.indexOf("MS9")>0);
	    
	   //Suppression:
	   restTemplate.delete(uri+"/MS9");
	   
	   //verification:
	   String resultAsJsonString2 = restTemplate.getForObject(uri, String.class);
	   logger.info("json devise list via rest (after delete MS9): " + resultAsJsonString2);
	   Assert.assertTrue(resultAsJsonString2.indexOf("MS9")==-1);

	}

	
	
	
	
}
