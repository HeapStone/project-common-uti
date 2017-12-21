package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.wagnlei.baseserverlet.student.model.Student;
import com.wanglei.baseservlet.dao.BaseDao;
import com.wanglei.baseservlet.dao.impl.BaseDaoImpl;
import com.wanglei.baseservlet.utils.DateUtil;

public class BaseDaoTest {

	BaseDao<Student> baseDao;
	Student stu;
	@Before
	public void initTest(){
		baseDao = new BaseDaoImpl<Student>();
		 stu = new Student();
	}
	
	public void testAdd() {
		stu.setStudentAge(22);
		stu.setStudentBirth(DateUtil.getCurrentSqlDate());
		stu.setStudentClass("五班");
		stu.setStudentEmail("xxx@xxc.com");
		stu.setStudentEnterDate(DateUtil.getCurrentTimestamp());
		stu.setStudentIdcardNum("25698566565");
		stu.setStudentName("关羽");
		stu.setStudentNum("Xa259525222");
		stu.setStudentScore(22.01);
		stu.setStudentSex(1);
		stu.setStudentUuid("1490e4ce-bcf0-11e7-a023-d481d78cb8f4");
		baseDao.add(stu);
	}
	@Test
	public void testDelete(){
		stu.setStudentUuid("149084ce-bcf0-11e7-a023-d481d78cb8f4");
		baseDao.delete(stu,"STUDENT_UUID");
	}
	
	public void testupdate(){
		stu.setStudentAge(22);
		stu.setStudentBirth(DateUtil.getCurrentSqlDate());
		stu.setStudentClass("四年级五班");
		stu.setStudentEmail("关羽x@xxc.com");
//		stu.setStudentEnterDate(DateUtil.getCurrentTimestamp());
//		stu.setStudentIdcardNum("25698566565");
//		stu.setStudentName("关羽");
//		stu.setStudentNum("Xa259525222");
//		stu.setStudentScore(22.01);
//		stu.setStudentSex(1);
		stu.setStudentUuid("1490e4ce-bcf0-11e7-a023-d481d78cb8f4");
		baseDao.update(stu,"STUDENT_UUID");
	}

}
