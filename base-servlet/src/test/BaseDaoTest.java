package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wagnlei.baseserverlet.student.dao.StudentDao;
import com.wagnlei.baseserverlet.student.dao.impl.StudenDaoImpl;
import com.wagnlei.baseserverlet.student.model.Student;
import com.wanglei.baseservlet.model.Pager;
import com.wanglei.baseservlet.model.SystemContext;
import com.wanglei.baseservlet.utils.DateUtil;

public class BaseDaoTest {

	StudentDao baseDao;
	Student stu;
	@Before
	public void initTest(){
		baseDao = new StudenDaoImpl();
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
	
	public void testLoad (){
		stu.setStudentUuid("1490e4ce-bcf0-11e7-a023-d481d78cb8f4");
		System.out.println(baseDao.load(stu,"STUDENT_UUID"));
	}
	
	public void testFindByPager(){
		SystemContext.setOrder("STUDENT_AGE");
		Pager<Student> pager = new Pager<Student>();
		Map<String,String> prams = new HashMap<>();
		pager.setOffset(1);
		pager.setSize(3);
		prams.put("studentAge", "22");
		prams.put("studentAge", "22");
		prams.put("studentAge", "22");
		pager.setParams(prams);
		System.out.println(baseDao.findPager(pager));
		System.out.println(baseDao.findPager(pager).getDatas().size());
		//System.out.println(baseDao.findPager(pager));
	}
	
	public void testbatchAdd(){
		List<Student> stus = new ArrayList<>();
		for(int i=0;i<10;i++){
			Student stu1 = new Student();
			stu1.setStudentAge(22+i);
			stu1.setStudentBirth(DateUtil.getCurrentSqlDate());
			stu1.setStudentClass("四年级"+i+"班");
			stu1.setStudentEmail(i+"x@xxc.com");
			stu1.setStudentEnterDate(DateUtil.getCurrentTimestamp());
			stu1.setStudentIdcardNum("25698566565");
			stu1.setStudentName("test"+i);
			stu1.setStudentNum("Xa259525222");
			stu1.setStudentScore(22.01+i);
			stu1.setStudentSex(1);
			stu1.setStudentUuid("149"+i+"e4ce-bcf"+i+"-11e7-a023-d481d78cb84"+i);
			stus.add(stu1);
		}
		baseDao.batchAdd(stus);;
	}
	public void testBacthUpdate(){
		List<Student> stus = new ArrayList<>();
		for(int i=0;i<10;i++){
			Student stu1 = new Student();
			stu1.setStudentAge(22+i);
			stu1.setStudentBirth(DateUtil.getCurrentSqlDate());
			stu1.setStudentClass("wu年级"+i+"班");
			stu1.setStudentEmail(i+"x@xxc.com");
			stu1.setStudentEnterDate(DateUtil.getCurrentTimestamp());
			stu1.setStudentIdcardNum("25698566565");
			stu1.setStudentName("test"+i);
			stu1.setStudentNum("Xa259525222");
			stu1.setStudentScore(22.01+i);
			stu1.setStudentSex(1);
			stu1.setStudentUuid("149"+i+"e4ce-bcf"+i+"-11e7-a023-d481d78cb84"+i);
			stus.add(stu1);
		}
		baseDao.batchUpdate(stus,"STUDENT_UUID");
	}
	
	public void testBacthDelete(){
		List<Student> stus = new ArrayList<>();
		for(int i=0;i<10;i++){
			Student stu1 = new Student();
			stu1.setStudentAge(22+i);
			stu1.setStudentBirth(DateUtil.getCurrentSqlDate());
			stu1.setStudentClass("wu年级"+i+"班");
			stu1.setStudentEmail(i+"x@xxc.com");
			stu1.setStudentEnterDate(DateUtil.getCurrentTimestamp());
			stu1.setStudentIdcardNum("25698566565");
			stu1.setStudentName("test"+i);
			stu1.setStudentNum("Xa259525222");
			stu1.setStudentScore(22.01+i);
			stu1.setStudentSex(1);
			stu1.setStudentUuid("149"+i+"e4ce-bcf"+i+"-11e7-a023-d481d78cb84"+i);
			stus.add(stu1);
		}
		baseDao.batchDelete(stus,"STUDENT_UUID");
	}
	@Test
	public void findpagerBysql(){
		Pager<Object> pager = new Pager<Object>();
		Map<String,String> prams = new HashMap<>();
		pager.setOffset(1);
		pager.setSize(10);
		prams.put("studentName", "%test%");
		prams.put("studentAge", "24");
		pager.setParams(prams);
		String sql = "SELECT  * FROM TB_STUDENT WHERE 1 = 1  AND STUDENT_NAME  LIKE ? AND STUDENT_AGE = ? ";
		
		System.out.println(baseDao.findPagerByPreSqlWithParams(sql, pager, Student.class));
		System.out.println(baseDao.findPagerByPreSqlWithParams(sql, pager, Student.class).getTotal());
		//System.out.println(baseDao.findPager(pager));
	}
}
