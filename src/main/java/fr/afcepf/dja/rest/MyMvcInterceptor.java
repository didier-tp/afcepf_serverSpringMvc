package fr.afcepf.dja.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyMvcInterceptor implements HandlerInterceptor  {
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
	        System.out.println("In Interceptor");
	        //return super.preHandle(request, response, handler);
	        return true;
	    }
	    @Override
	    public void postHandle( HttpServletRequest request, HttpServletResponse response,
	            Object handler, ModelAndView modelAndView) throws Exception {
	        System.out.println("---method executed---");
	    }
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
	            Object handler, Exception ex) throws Exception {
	        System.out.println("---Request Completed---");
	    }
	}