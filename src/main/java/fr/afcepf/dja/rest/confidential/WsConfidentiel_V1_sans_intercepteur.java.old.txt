package fr.afcepf.dja.rest.confidential;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.afcepf.dja.rest.auth.AuthUtil;

@RestController
@RequestMapping(value="/rest/confidential" ,headers="Accept=application/json" )
public class WsConfidentiel {
	
	//url = http://localhost:8080/serverSpringMvc/ws/rest/confidential/news
	@RequestMapping(value="/news" ,method=RequestMethod.GET )
	public ResponseEntity<News> getNews(@RequestHeader HttpHeaders httpHeaders){
		String token = AuthUtil.extractBearerTokenFromHttpHeaders(httpHeaders);
		if(AuthUtil.verifyToken(token)){
			News news = new News();
			news.setTitre("news of the day");
			news.setTexte("you know what ? i am happy !");
			return new ResponseEntity<News>(news,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<News>(HttpStatus.UNAUTHORIZED);
			                                //ou HttpStatus.FORBIDDEN;
		}
	}

}
