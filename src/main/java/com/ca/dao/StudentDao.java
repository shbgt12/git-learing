package com.ca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.ca.bo.StudentBo;

public class StudentDao {
	final String SQL_GET_ALL_STUDENTS = "select student_no, student_nm, age, gender, qualification, mobile_no, email_address from student order by student_nm";

	private JdbcTemplate jdbcTemplate;

	public StudentDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<StudentBo> getAllStudents() {
		return jdbcTemplate.execute(new GetAllStudentsPreparedStatementCreator(),
				new GetAllStudentsPreparedStatementCallback());
	}

	public List<StudentBo> getStudentsByName(final String studentName) {
		return jdbcTemplate.execute(new GetStudentsByNamePreparedStatementCreator("%" + studentName + "%"),
				new GetStudentsByStudentNamePreparedStatementCallback());
	}

	private final class GetAllStudentsPreparedStatementCreator implements PreparedStatementCreator {
		@Override
		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement pstmt = null;

			pstmt = con.prepareStatement(SQL_GET_ALL_STUDENTS);

			return pstmt;
		}
	}

	private final class GetAllStudentsPreparedStatementCallback implements PreparedStatementCallback<List<StudentBo>> {
		@Override
		public List<StudentBo> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
			ResultSet rs = null;
			List<StudentBo> students = null;
			StudentBo bo = null;

			rs = ps.executeQuery();
			students = new ArrayList<>();
			while (rs.next()) {
				bo = new StudentBo();
				bo.setStudentNo(rs.getInt(1));
				bo.setStudentName(rs.getString(2));
				bo.setAge(rs.getInt(3));
				bo.setGender(rs.getString(4));
				bo.setQualification(rs.getString(5));
				bo.setMobileNo(rs.getString(6));
				bo.setEmailAddress(rs.getString(7));
				students.add(bo);
			}

			return students;
		}

	}

	private final class GetStudentsByNamePreparedStatementCreator implements PreparedStatementCreator {
		private String studentName;

		private GetStudentsByNamePreparedStatementCreator(String studentName) {
			this.studentName = studentName;
		}

		public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
			PreparedStatement pstmt = null;
			pstmt = con.prepareStatement(
					"select student_no, student_nm, age, gender, qualification, mobile_no, email_address from student where student_nm like ?");
			pstmt.setString(1, studentName);
			return pstmt;
		}
	}

	private final class GetStudentsByStudentNamePreparedStatementCallback
			implements PreparedStatementCallback<List<StudentBo>> {
		public List<StudentBo> doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {
			ResultSet rs = null;
			List<StudentBo> students = null;
			StudentBo bo = null;

			rs = pstmt.executeQuery();
			students = new ArrayList<>();
			while (rs.next()) {
				bo = new StudentBo();
				bo.setStudentNo(rs.getInt(1));
				bo.setStudentName(rs.getString(2));
				bo.setAge(rs.getInt(3));
				bo.setGender(rs.getString(4));
				bo.setQualification(rs.getString(5));
				bo.setMobileNo(rs.getString(6));
				bo.setEmailAddress(rs.getString(7));
				students.add(bo);
			}

			return students;
		}
	}

}
