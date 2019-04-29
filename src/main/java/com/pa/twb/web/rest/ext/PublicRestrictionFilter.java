package com.pa.twb.web.rest.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@SuppressWarnings("unused")
public class PublicRestrictionFilter extends GenericFilterBean {

    private static final String API_KEY_HEADER = "SmartTours-Api-Key";

    private static final String API_KEY = "fHPCFQzuCDuT3Kah";

    private final Logger log = LoggerFactory.getLogger(PublicRestrictionFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUri = httpRequest.getRequestURI();
        if (requestUri.startsWith("/api/ext-")) {
//          catching the preflight request from browsers
            if (httpRequest.getMethod().toLowerCase().startsWith("option")) {
                chain.doFilter(httpRequest, response);
                return;
            }
            Enumeration<String> headerNames = httpRequest.getHeaderNames();
            if (headerNames != null) {
                String apiKey = httpRequest.getHeader(API_KEY_HEADER);
                if (apiKey != null && apiKey.equals(API_KEY)) {
                    chain.doFilter(httpRequest, response);
                    return;
                }
            }
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.reset();
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Smart Tours Api Key not valid");
            return;
        }
        chain.doFilter(httpRequest, response);
    }
}
