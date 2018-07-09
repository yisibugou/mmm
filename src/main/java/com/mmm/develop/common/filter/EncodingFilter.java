package com.mmm.develop.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 采用filter统一处理字符集
 *
 */
@WebFilter(filterName="encodingFilter")
public class EncodingFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        // 继续执行
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
}
