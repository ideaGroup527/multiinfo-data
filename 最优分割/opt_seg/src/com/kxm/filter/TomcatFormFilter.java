package com.kxm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import  javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class TomcatFormFilter implements Filter {
    /**
      * Request.java
      * 对 HttpServletRequestWrapper 进行扩充, 不影响原来的功能并能提供所有的 HttpServletRequest
      * 接口中的功能. 它可以统一的对 Tomcat 默认设置下的中文问题进行解决而只需要用新的 Request 对象替换页面中的
      * request 对象即可.
      */

    class Request extends HttpServletRequestWrapper
    {

        public Request(HttpServletRequest request) {
            super(request);
        }

        /**
         * 转换由表单读取的数据的内码.
         * 从 ISO 字符转到 GBK.
         */
        public String toChi(String input) {
            try {
              byte[] bytes = input.getBytes("UTF-8");
              return new String(bytes, "UTF-8");
            }
            catch (Exception ex) {
            }
            return null;
        }

        /**
         * Return the HttpServletRequest holded by this object.
         */
        private HttpServletRequest getHttpServletRequest()
        {
            return (HttpServletRequest)super.getRequest();
        }

        /**
         * 读取参数 -- 修正了中文问题.
         */
        public String getParameter(String name)
        {
            return toChi(getHttpServletRequest().getParameter(name));
        }

        /**
         * 读取参数列表 - 修正了中文问题.
         */
        public String[] getParameterValues(String name)
        {
              String values[] = getHttpServletRequest().getParameterValues(name);
              if (values != null) {
                for (int i = 0; i < values.length; i++) {
                  values[i] = toChi(values[i]);
                }
              }
              return values;
        }
    }
    public void destroy() {
        
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpreq = (HttpServletRequest)request;
        if(httpreq.getMethod().equals("POST")) {
            request.setCharacterEncoding("GBK");
        } else {
            request = new Request(httpreq);
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
