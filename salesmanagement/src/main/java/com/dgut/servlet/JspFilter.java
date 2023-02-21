package com.dgut.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

public class JspFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 执行需要的servlet
        // ...
        //先执行RefreshGoodsServlet
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletContext servletContext = request.getServletContext();

        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/refresh");
        requestDispatcher.include(request, response);


//       RequestDispatcher requestDispatcher2 = servletContext.getRequestDispatcher("/api/contracts/1");
//        requestDispatcher2.include(request, response);

        // 执行下一个过滤器或目标资源
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}