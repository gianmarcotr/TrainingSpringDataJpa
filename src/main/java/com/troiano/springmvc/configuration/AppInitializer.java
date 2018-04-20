package com.troiano.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}


	/*
		This registration provides opportunity to set specific properties like
		maximum file size, request size, location and threshold after which file
		will be stored temporarily on disk during upload operation
	 */
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration){
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement(){
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	private static final String LOCATION = "C:\\Users\\SI2001\\IdeaProjects\\TutorialWebAppSpringJPA\\src\\main\\resources\\temp";
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 25; //25MB
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30; //30MB
	private static final int FILE_SIZE_THRESHOLD = 0;
}
