package org.example.springboothz.viewResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.net.http.HttpRequest;

@Component
public class ViewHeaderInterceptor implements HandlerInterceptor {

    @Autowired
    ViewHolder viewHolder;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res,Object handler){

        viewHolder.setView(req.getHeader(ViewHolder.Views.INTERNAL.header));
        return true;
    }

}
