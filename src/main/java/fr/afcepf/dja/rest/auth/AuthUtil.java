package fr.afcepf.dja.rest.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;

public class AuthUtil {	
	//à peaufiner sur un vrai projet : supprimer les très anciens jetons
	private static List<String> listeJetons = new ArrayList<String>();
	public static  String generateToken(){	String token=null;
		token = java.util.UUID.randomUUID().toString();
		listeJetons.add(token);		return token;
	}
	public static boolean verifyToken(String token){
		return listeJetons.contains(token);//jeton considéré comme valide si dans la liste des jetons générés et mémorisés
	}
	public static String extractBearerTokenFromHttpHeaders(HttpHeaders headers){
		List<String> listOfAuthorization= headers.get(HttpHeaders.AUTHORIZATION);
		if(listOfAuthorization==null || listOfAuthorization.size()==0){
			return null;
		}
		String mainAuthorisation = listOfAuthorization.get(0);
		System.out.println(mainAuthorisation);
		if(mainAuthorisation.length()<8) {
			return null;
		}
		if(mainAuthorisation.startsWith("Bearer")){
			return mainAuthorisation.substring(7);
		}
		return  null;
	}
}
