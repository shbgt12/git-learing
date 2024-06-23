package com.ca.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;

import com.ca.bo.CourseBo;

public class CourseDao {
	private final String SQL_GET_ALL_COURSES = "select course_no, course_nm, duration, fee from course order by course_nm";
	private JdbcTemplate jdbcTemplate;

	public CourseDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<CourseBo> getAllCourses() {
		return jdbcTemplate.execute(new GetAllCoursesStatementCallback());
	}

	private final class GetAllCoursesStatementCallback implements StatementCallback<List<CourseBo>> {
		@Override
		public List<CourseBo> doInStatement(Statement stmt) throws SQLException, DataAccessException {
			CourseBo bo = null;
			ResultSet rs = null;
			List<CourseBo> courseBos = null;

			rs = stmt.executeQuery(SQL_GET_ALL_COURSES);
			courseBos = new ArrayList<>();

			while (rs.next()) {
				bo = new CourseBo();
				bo.setCourseNo(rs.getInt(1));
				bo.setCourseName(rs.getString(2));
				bo.setDuration(rs.getInt(3));
				bo.setFees(rs.getDouble(4));
				courseBos.add(bo);
			}
			return courseBos;
		}
	}

}
