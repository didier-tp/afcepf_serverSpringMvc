package fr.afcepf.dja.swagger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MySwaggerUiConfig extends WebMvcConfigurerAdapter {
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//System.out.println("############ MySwaggerUiConfig  ###################");
		//configuration nécessaire pour que swagger-ui.html puisse accéder
		//à ses ressources (.html, .css , .js ) dans une partie "META-INF/resources" 
		//du fichier sprinfox-swagger-ui.jar téléchargé via maven
		//NB: l'url déclenchante doit invoquer swagger-ui à coté de v2/api-docs
		// systématiquement recherché en relatif
		registry.addResourceHandler("**/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("**/**").addResourceLocations("classpath:/META-INF/resources/");
	}
}
