package fr.afcepf.dja.rest.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.dja.rest.auth.data.AuthResponse;
import fr.afcepf.dja.rest.auth.data.Credential;

@RestController
@RequestMapping(value="/rest/auth" ,headers="Accept=application/json" )
public class WsAuth {
	
	private String generateToken(){
		String token=null;
		token = java.util.UUID.randomUUID().toString();
		return token;
	}
	
	//url = http://localhost:8080/serverSpringMvc/ws/rest/auth/verifAuth
	//avec { "username" : "toto" , "password": "pwd_toto" }
	//à tester avec POSTMAN (POST , raw , et "Content-Type application/json dans header)
	@RequestMapping(value="/verifAuth" ,method=RequestMethod.POST )
	public ResponseEntity<AuthResponse> postAuth(@RequestBody Credential credential){
		
		//code à peaufiner pour rendre plus fiable (exception)
		AuthResponse authResponse = new AuthResponse();
		if(credential.getPassword().equals("pwd_" + credential.getUsername())){
			authResponse.setAuthOk(true);
			authResponse.setMessage("authentification reussie");
			authResponse.setAuthToken(generateToken());
		}
		else{
			authResponse.setAuthOk(false);
			authResponse.setMessage("echec authentification");
		}
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
	}

}
