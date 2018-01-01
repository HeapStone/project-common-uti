package com.wagnlei.baseserverlet.student.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wanglei.baseservlet.servlet.BaseServlet;

public class TeacherServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		return "json:"+ "{\"name\":\"tea\",\"type\":\"老师\"}";
	}
	
}
