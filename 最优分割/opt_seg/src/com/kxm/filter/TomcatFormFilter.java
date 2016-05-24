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
      * �� HttpServletRequestWrapper ��������, ��Ӱ��ԭ���Ĺ��ܲ����ṩ���е� HttpServletRequest
      * �ӿ��еĹ���. ������ͳһ�Ķ� Tomcat Ĭ�������µ�����������н����ֻ��Ҫ���µ� Request �����滻ҳ���е�
      * request ���󼴿�.
      */

    class Request extends HttpServletRequestWrapper
    {

        public Request(HttpServletRequest request) {
            super(request);
        }

        /**
         * ת���ɱ���ȡ�����ݵ�����.
         * �� ISO �ַ�ת�� GBK.
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
         * ��ȡ���� -- ��������������.
         */
        public String getParameter(String name)
        {
            return toChi(getHttpServletRequest().getParameter(name));
        }

        /**
         * ��ȡ�����б� - ��������������.
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
