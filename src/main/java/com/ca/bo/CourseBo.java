package com.ca.bo;

public class CourseBo {
	private int courseNo;
	private String courseName;
	private int duration;
	private double fees;

	public int getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	@Override
	public String toString() {
		return "CourseBo [courseNo=" + courseNo + ", courseName=" + courseName + ", duration=" + duration + ", fees="
				+ fees + "]";
	}

}
