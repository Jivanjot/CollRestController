package com.collaboration.webconfig;

import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.collaboration.hibernateconfig.HibernateConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{


	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) 
	{
		System.out.println("customizeRegistration");
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() 
	{	
		return new Class[] {WebResolver.class,HibernateConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() 
	{
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters()
	{
		CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
		encodingFilter.setEncoding(StandardCharsets.UTF_8.name());
		return new Filter[] {encodingFilter};
	}

	
}
