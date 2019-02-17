package com.itbin.utils;

import org.springframework.web.servlet.HandlerExceptionResolver;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SystemExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        SystemException  se = null;
        if( e  instanceof  SystemException ){
            se = (SystemException)e;
        }else {
            se = new SystemException("系统正在维护");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message",se.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
