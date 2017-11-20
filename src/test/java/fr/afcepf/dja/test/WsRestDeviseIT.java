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
	/*
	@Test
	public  void testRestSettingAuth()
	{
	    final String uri = "http://localhost:8080/serverSpringMvc/ws/rest/clients/settingAuth";
	    ClientAuth newClientAuth = new ClientAuth();
	    newClientAuth.setNumClient(1L);
	    newClientAuth.setPassword("nouveau-pwd1");
	    newClientAuth.setOk(null);
	   
	    //ClientAuth savedClientAuth = restTemplate.postForObject(uri, newClientAuth, ClientAuth.class);
	    //logger.info("savedClientAuth via rest: " + savedClientAuth.toString());
	    //Assert.assertTrue(savedClientAuth.getOk());
	   
	    String savedClientAuthAsJsonString = restTemplate.postForObject(uri, newClientAuth, String.class);
	    logger.info("savedClientAuth via rest (as Json String): " + savedClientAuthAsJsonString);

	}
*/
	
	
	
	
}
