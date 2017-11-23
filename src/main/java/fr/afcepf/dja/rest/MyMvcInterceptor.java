package fr.afcepf.dja.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.net.HttpHeaders;

import fr.afcepf.dja.rest.annotation.AuthTokenRequired;
import fr.afcepf.dja.rest.auth.AuthUtil;

/**
 * Cette classe d'intercepteur pour spring-web-mvc (et @RestController)
 * doit être déclarée via <mvc:interceptors> <bean ....> 
 * ou via l' équivalent "javaConfig" suivant:
 * 
 * 
 * @Configuration
 * public class MyWebMvcInterceptorConfig extends WebMvcConfigurerAdapter{
 * 	
 * 	@Bean
 * 	public MyMvcAuthInterceptor myMvcAuthInterceptor(){
 * 		return new MyMvcInterceptor();
 * 	}
 * 
 * 	@Override
 * 	public void addInterceptors(InterceptorRegistry registry) {	
 * 		registry.addInterceptor(myMvcAuthInterceptor());		        
 * 	}
 * 	
 * }
 */


public class MyMvcInterceptor implements HandlerInterceptor  {
private static Logger logger = LoggerFactory.getLogger(MyMvcInterceptor.class);
	
	
	private String extractBearerTokenFromAuthHeader(String authorizationHeader){
		logger.debug("in MyMvcAuthInterceptor, AuthorizationHeader:" + authorizationHeader);
		if(authorizationHeader.length()<8) {
			return null;
		}
		//Format de l'authorisation standard http:
		//Authorization: Bearer 1234ab344..token_au_porteur...566
		//ou bien 
		//Authorization: Basic A45D3455
		if(authorizationHeader.startsWith("Bearer")){
			return authorizationHeader.substring(7);
		}
		return  null;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("MyMvcAuthInterceptor.preHandle() : ...");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		AuthTokenRequired tokenRequiredAnnot = handlerMethod.getMethodAnnotation(AuthTokenRequired.class);
		if(tokenRequiredAnnot==null){
			return true;//rien à vérifier
		}else{
			logger.debug("in MyMvcInterceptor, AuthTokenRequired ...");
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authorizationHeader==null){
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				return false;
			}else{
				String authToken = extractBearerTokenFromAuthHeader(authorizationHeader);
				logger.debug("in MyMvcInterceptor, authToken="+authToken);
				if(authToken ==null){
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return false;					
				}else{ 
					if(!AuthUtil.verifyToken(authToken)){
						response.setStatus(HttpStatus.FORBIDDEN.value());
						return false;
					}					
				}
									
			}
			return true; //if allowed
		}
		
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		//logger.debug("in MyMvcAuthInterceptor, MyMvcAuthInterceptor.postHandle() : ...");
	}
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		//logger.debug("in MyMvcAuthInterceptor, MyMvcAuthInterceptor.afterCompletion() : ...");
	}
	}