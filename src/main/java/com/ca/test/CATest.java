package com.ca.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ca.dao.CourseDao;

public class CATest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/ca/common/application-context.xml");
//		StudentDao dao = context.getBean("studentDao", StudentDao.class);
//		//List<StudentBo> students = dao.getAllStudents();
//		List<StudentBo> students = dao.getStudentsByName("J");
//		students.forEach(System.out::println);
		CourseDao courseDao = context.getBean("courseDao", CourseDao.class);
		courseDao.getAllCourses().forEach(System.out::println);
	}
}
