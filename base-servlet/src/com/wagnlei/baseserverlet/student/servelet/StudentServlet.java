package com.wagnlei.baseserverlet.student.servelet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import com.wagnlei.baseserverlet.student.dao.StudentDao;
import com.wagnlei.baseserverlet.student.dao.impl.StudenDaoImpl;
import com.wagnlei.baseserverlet.student.model.Student;
import com.wanglei.baseservlet.model.ExcelModel;
import com.wanglei.baseservlet.model.FileView;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.UploadModel;
import com.wanglei.baseservlet.model.View;
import com.wanglei.baseservlet.servlet.BaseServlet;

public class StudentServlet extends BaseServlet {
	private static final long serialVersionUID = -4369536711720857169L;
	StudentDao stuDao = new StudenDaoImpl();
	public String add()throws IOException,ServletException{
		return "json:"+ "{\"name\":\"fly\",\"type\":\"虫子\"}";
	}
	public void delete(){}
	public void update(){}
	public String getstring(){
		String re = "" ;
		return re;
	}
	public View list(){
		View view = new View("json:index.jsp");
		view.bind("name", "lisi");
		view.bind("age", 20);
		return view;
	}
	public Pager<Student> findPager(){
		Pager<Student> pager = new Pager<Student> ();
		pager.setOffset(1);
		pager.setSize(20);
		pager=stuDao.findPager(pager);
		return pager;
	}
	public void uploadFile() throws Exception{
		UploadModel upm = new UploadModel();
		upm.setFileUploadRealPath("d:\\testup\\");
		upm.setFileSystemName("image1"+System.currentTimeMillis());
		upm = this.uploadFlie(upm, request(), response());
		String filename = (String) upm.getParameter("filename1");
		System.out.println(filename);
		System.out.println(upm);
	}
	public FileView downLoadFiel(){
		File dfile = new File("D:\\otherproject\\资源信息改造需求.docx");
		return new FileView(dfile,"资源信息改造需求.docx");
	}
	public void downLoadExcelFiel() throws Exception{
		ExcelModel exm = new ExcelModel();
		List<Student> stus =  stuDao.listBysql(" select * from TB_STUDENT");
		String [] columns = new String[]{"studentUuid","studentName","studentClass","studentAge","studentNum","studentIdcardNum","studentEmail","studentBirth","studentEnterDate"};
		exm.setColumns(columns);
		exm.setExportList(stus);
		exm.setDesFileName("学生信息列表.xls");
		exm.setTitles(new String [] {"学生标示","学生姓名","学生班级","学生年龄","学生学号","学生身份证号","学生邮箱","学生生日","学生入学时间"});
		exm.setDataLength(1000);
		this.responseExcelFile(exm, request(), response());
	}
	public void renderImage(){
		File file = new File("D:\\testup\\image11514789266999.jpg");
		try {
			this.responseImage(response(), file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void findStudent(){
		//Student stu = (Student) initBean(request(), Student.class);
		Pager<Student> pager =  (Pager<Student>) initBean(request(), Pager.class);
		System.out.println(pager);
	}
}
