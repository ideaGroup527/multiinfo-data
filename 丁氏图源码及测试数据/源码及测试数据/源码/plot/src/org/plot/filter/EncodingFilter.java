package org.plot.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter extends HttpServlet implements Filter {
	
	private String charSet  ;
	

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.charSet) ;
		chain.doFilter(request, response) ;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.charSet = filterConfig.getInitParameter("charSet") ;
	}

	public EncodingFilter() {
		super();
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

}
