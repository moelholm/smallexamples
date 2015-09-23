package com.moelholm.wildfly.learning.urlrewrite;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * An extremely simple URL rewriting servlet filter.
 * 
 * <p>
 * 
 * <b>WARNING:</b> Never use for anything serious (other people have invented far better solutions!).
 *
 * <p>
 * 1) Imagine source HTTP request: <br>
 * 
 * http://localhost:8080/myapp/<b>from</b>/index.html
 * 
 * <p>
 * 2) This servlet filter will transform it into: <br>
 * 
 * http://localhost:8080/myapp/<b>to</b>/index.html
 * 
 * ( the from and to "servlet paths" are defined by the FROM and TO constants of this class respectively ).
 * 
 */
@WebFilter(urlPatterns = "/*")
public class RewriteServletFilter implements Filter {

    public static final String TO = "to";

    public static final String FROM = "from";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestURI = httpServletRequest.getRequestURI();

        boolean shouldRewrite = requestURI.contains(FROM);// ... :)

        if (shouldRewrite) {

            String urlThatShouldBeForwardedTo = rewriteUrl(request.getServletContext().getContextPath(), requestURI, FROM, TO);

            request.getServletContext().getRequestDispatcher(urlThatShouldBeForwardedTo).forward(request, response);

        } else {

            chain.doFilter(request, response);
        }

    }

    private String rewriteUrl(String contextPath, String requestURI, String from, String to) {
        return requestURI.replace(from, to).replace(contextPath, "");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
