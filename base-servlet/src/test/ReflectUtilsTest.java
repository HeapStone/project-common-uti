package test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.wagnlei.baseserverlet.student.model.Student;
import com.wanglei.baseservlet.utils.ReflectUtils;

public class ReflectUtilsTest {
	Student stu = null;
	 @SuppressWarnings("rawtypes")
	Class clazz = null;
	@Before
	public void befro(){
		stu = new Student();
		clazz = stu.getClass();
	}
	
	public void testGetFields() {
		Field [] fields = ReflectUtils.getClassFileds(clazz);
		for(Field field :fields){
			System.out.println(field.getName());
		}
	}
	
	public void testgetGetterMethodByfieldName(){
		System.out.println(ReflectUtils.getGetterMethodByfieldName("studentEnterDate", clazz));;
	}
	
	public void testgetSetterMethodByfieldName(){
		System.out.println(ReflectUtils.getSetterMethodByfieldName("studentEnterDate", clazz));;
	}

	public void testsetBeaProperty(){
		Map<String,Object> stuMap = new HashMap<String,Object>();
		stuMap.put("studentUuid","0112555");
		stuMap.put("studentName","李四");
		stuMap.put("studentClass","高一四班");
		stuMap.put("studentAge","12");
		stuMap.put("studentSex","1");
		stuMap.put("studentNum","0112554555");
		stuMap.put("studentIdcardNum","6215011998256422");
		stuMap.put("studentEmail","xxx@qq.com");
		stuMap.put("studentScore","220.1");
		stuMap.put("studentBirth","1998-05-25");
		stuMap.put("studentEnterDate","2017-12-25 22:00:00");
		System.out.println(ReflectUtils.setBeanProperty(stuMap, clazz));
	}
	@Test
	public void testgetBeaProperty(){
		Student stu2 = new Student();
		Map<String,Object> stuMap = new HashMap<String,Object>();
		stuMap.put("studentUuid","0112555");
		stuMap.put("studentName","李四");
		stuMap.put("studentClass","高一四班");
		stuMap.put("studentAge","12");
		stuMap.put("studentSex","1");
		stuMap.put("studentNum","0112554555");
		stuMap.put("studentIdcardNum","6215011998256422");
		stuMap.put("studentEmail","xxx@qq.com");
		stuMap.put("studentScore","220.1");
		stuMap.put("studentBirth","1998-05-25");
		stuMap.put("studentEnterDate","2017-12-25 22:00:00");
		stu2 = (Student)ReflectUtils.setBeanProperty(stuMap, clazz);
		System.out.println(ReflectUtils.getBeanProperty(stu2));
	}

}
