package com.optimagrowth.organization.utils;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Извлекает идентификатор корреляции из HTTP заголовка и сохраняет его в объекте UserContext
 */
@Component
@Slf4j
public class UserContextFilter implements Filter {

    /**
     * Извлекает идентификатор корреляции из заголова и записывает его в экземпляр класса UserContext
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        UserContextHolder.getContext().setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
        UserContextHolder.getContext().setUserId( httpServletRequest.getHeader(UserContext.USER_ID) );
        UserContextHolder.getContext().setAuthToken( httpServletRequest.getHeader(UserContext.AUTH_TOKEN) );
        UserContextHolder.getContext().setOrgId( httpServletRequest.getHeader(UserContext.ORG_ID) );

        log.debug("Organization Service Incoming Correlation id: {}" ,UserContextHolder.getContext().getCorrelationId());
        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
