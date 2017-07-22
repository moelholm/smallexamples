package com.moelholm.async.demo;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import java.io.IOException;

@Component
public class MdcFilter extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      MDC.put("mdcData", "[userId:Duke]");
      chain.doFilter(request, response);
    } finally {
      MDC.clear();
    }
  }
}
