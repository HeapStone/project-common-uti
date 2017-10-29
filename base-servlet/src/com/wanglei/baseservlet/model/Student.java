package com.wanglei.baseservlet.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Student implements Serializable {
	
	private static final long serialVersionUID = -3168774581956018935L;
	/**
	 * 学生id
	 */
	private String studentUuid;
	/**
	 * 学生姓名
	 */
	private String studentName;
	/**
	 * 学生班级
	 */
	private String studentClass;
	/**
	 * 学生年龄
	 */
	private Integer studentAge;
	/**
	 * 学生性别
	 */
	private Integer studentSex;
	/**
	 * 学号
	 */
	private String  studentNum;
	/**
	 * 身份证号
	 */
	private String studentIdcardNum;
	/**
	 * 邮箱
	 */
	private String studentEmail;
	/**
	 * 成绩
	 */
	private double studentScore;
	/**
	 * 出生日期
	 */
	private Date studentBirth;
	/**
	 * 入学注册时间
	 */
	private Timestamp studentEnterDate;
	public String getStudentUuid() {
		return studentUuid;
	}
	public void setStudentUuid(String studentUuid) {
		this.studentUuid = studentUuid;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	public Integer getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}
	public Integer getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(Integer studentSex) {
		this.studentSex = studentSex;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getStudentIdcardNum() {
		return studentIdcardNum;
	}
	public void setStudentIdcardNum(String studentIdcardNum) {
		this.studentIdcardNum = studentIdcardNum;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public double getStudentScore() {
		return studentScore;
	}
	public void setStudentScore(double studentScore) {
		this.studentScore = studentScore;
	}
	public Date getStudentBirth() {
		return studentBirth;
	}
	public void setStudentBirth(Date studentBirth) {
		this.studentBirth = studentBirth;
	}
	public Timestamp getStudentEnterDate() {
		return studentEnterDate;
	}
	public void setStudentEnterDate(Timestamp studentEnterDate) {
		this.studentEnterDate = studentEnterDate;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [studentUuid=" + studentUuid + ", studentName="
				+ studentName + ", studentClass=" + studentClass
				+ ", studentAge=" + studentAge + ", studentSex=" + studentSex
				+ ", studentNum=" + studentNum + ", studentIdcardNum="
				+ studentIdcardNum + ", studentEmail=" + studentEmail
				+ ", studentScore=" + studentScore + ", studentBirth="
				+ studentBirth + ", studentEnterDate=" + studentEnterDate + "]";
	}
	
	
}
