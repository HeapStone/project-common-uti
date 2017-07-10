package com.baseservlet.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 2543080326681088942L;

	@Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	    // TODO Auto-generated method stub
	    super.service(arg0, arg1);
    }

	@Override
    public void init(ServletConfig config) throws ServletException {
	    // TODO Auto-generated method stub
	    super.init(config);
    }
    
}
