package com.wagnlei.baseserverlet.student.servelet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wagnlei.baseserverlet.student.dao.StudentDao;
import com.wagnlei.baseserverlet.student.dao.impl.StudenDaoImpl;
import com.wagnlei.baseserverlet.student.model.Student;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.View;
import com.wanglei.baseservlet.servlet.BaseServlet;

public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = -4369536711720857169L;
	StudentDao stuDao = new StudenDaoImpl();
	public String add(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		return "json:"+ "{\"name\":\"fly\",\"type\":\"虫子\"}";
	}
	public void delete(HttpServletRequest request, HttpServletResponse response){}
	public void update(HttpServletRequest request, HttpServletResponse response){}
	public String getstring(HttpServletRequest request, HttpServletResponse response){
		String re = "" ;
		return re;
	}
	public View list(HttpServletRequest request, HttpServletResponse response){
		List<Student>  Stus = new ArrayList<>();
		View view = new View("r:index.jsp");
		return view;
	}
	public Pager<Student> findPager(HttpServletRequest request, HttpServletResponse response){
		Pager<Student> pager = new Pager<Student> ();
		pager.setOffset(1);
		pager.setSize(20);
		pager=stuDao.findPager(pager);
		return pager;
	}

}
