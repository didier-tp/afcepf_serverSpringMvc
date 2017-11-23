package fr.afcepf.dja.rest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation permettant de paramétrer le besoin 
 * en Token d'authentification sur une méthode 
 * d'un @RestController 
 * NB: cette annotation sera examinée (par reflection) par MyMvcAuthInterceptor
 */

@Target({ElementType.METHOD /*, ElementType.TYPE */})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthTokenRequired {
	public static final String DEFAULT_REQUIRED_ROLE="any"; 
	public String requiredRole() default DEFAULT_REQUIRED_ROLE;
}
