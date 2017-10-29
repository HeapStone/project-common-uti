package test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.wanglei.baseservlet.model.Student;
import com.wanglei.baseservlet.utils.DbHelper;

public class DbHelperTest {
	DbHelper dh = null;
	@Before
	public void init(){
		dh =DbHelper.getDbHelperInstance();
	}
	
	public void testGetConnection() {
	  System.out.println(dh.getConnection());
	}
	
	public void testExcuteSql(){
		String sql = "INSERT INTO "
				+ "tb_student "
				+ "(STUDENT_UUID, STUDENT_NAME, "
				+ "STUDENT_AGE, STUDENT_SEX, STUDENT_CLASS, "
				+ "STUDENT_NUM, STUDENT_IDCARD_NUM, STUDENT_EMAIL, STUDENT_SCORE, "
				+ "STUDENT_BIRTH, STUDENT_ENTER_DATE) "
				+ "VALUES (uuid(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object> params = new ArrayList<>();
		params.add("李逍遥");
		params.add("21");
		params.add("2");
		params.add("一班");
		params.add("1025665200");
		params.add("10256652005266662255");
		params.add("0122@qq.com");
		params.add("22.15");
		params.add("2017-10-28");
		params.add("2017-10-28 00:00:00");
		dh.excuteSqlByPrepareStatement(sql, params);
	}
	
	public void testExcuteQuery(){
		String sql = "SELECT STUDENT_UUID, STUDENT_NAME, STUDENT_AGE, STUDENT_SEX, "
				+ "STUDENT_CLASS, STUDENT_NUM, STUDENT_IDCARD_NUM, STUDENT_EMAIL, "
				+ "STUDENT_SCORE, STUDENT_BIRTH, STUDENT_ENTER_DATE FROM tb_student";
		List<Map<String,Object>> result = dh.excuteQuerySql(sql, null);
		for(Map<String,Object> resm :result){
			Set<String> keys = resm.keySet();
			Iterator<String> it= keys.iterator();
			while(it.hasNext()){
				System.out.println(resm.get(it.next()));
			}
		}	
		
	}
	
	public void testprintProperty(){
		Student stu = new Student();
		Class<?> clazz = stu.getClass();
		Field [] fs = clazz.getDeclaredFields();
		System.out.println(clazz.toString());
		for(Field f :fs ){
			if(!f.getName().equals("serialVersionUID"))
			System.out.println("\t<result property=\""+f.getName()+"\""+" column=\"" +"\" type=\""+f.getType().toString().replace("class", "").trim()+"\" />");
		}
	}
	@Test
	public void testprintTaleCoulm() throws SQLException{
		DatabaseMetaData dbmd = dh.getConnection().getMetaData();
		ResultSet rs = dbmd.getTables(dh.getConnection().getCatalog(), "%","%",new String[]{"TABLE"}); 
		while(rs.next()) {
			String columnName =null; 
			String columnType =null; 
			String currentTableName= rs.getString("TABLE_NAME");
			System.out.println(currentTableName+":>");
			ResultSet colRet = dbmd.getColumns(null,"%",currentTableName ,"%"); 
			while(colRet.next()){ 
			   columnName = colRet.getString("COLUMN_NAME"); 
				columnType = colRet.getString("TYPE_NAME"); 
				int datasize = colRet.getInt("COLUMN_SIZE"); 
				int digits = colRet.getInt("DECIMAL_DIGITS"); 
				int nullable = colRet.getInt("NULLABLE"); 
				String REMARKS  = colRet.getString("REMARKS"); 
				System.out.println(columnName+" "+columnType+" "+datasize+" "+digits+" "+ nullable); 
			}
	}
		
	} 
}
