package com.newscorp.project.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Filter before accessing the rest resource. It has been annotated with highest
 * precedence to ensure that it will get highest priority.
 * 
 * @author mazharshaikh
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {

	public void init(FilterConfig filterConfig) {
	}

	/**
	 * This filter method will be executed before executing any REST API. You
	 * can enhance this method to perform more robust checks before allowing any
	 * user/application to access the exposed API's.
	 * 
	 * @param of
	 *            type {@link javax.servlet.ServletRequest}
	 * @param of
	 *            type {@link javax.servlet.FilterChain}
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		// This will help in avoiding CRSF attacks and checking cross site
		// origin
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
		response.setHeader("Access-Control-Allow-Methods", "POST, PATCH, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Max-Age", "3600");

		if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
			try {
				chain.doFilter(req, res);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Pre-flight");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "authorization, content-type,"
					+ "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
			response.setStatus(HttpServletResponse.SC_OK);
		}
	}

	public void destroy() {
	}

}
