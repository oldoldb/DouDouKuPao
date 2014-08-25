package com.oldoldb.doudoukupao;

import java.util.Calendar;

import com.example.doudoukupao.R;

public class ExerciseInfo {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int year;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	private int monthOfYear;
	public int getMonthOfYear() {
		return monthOfYear;
	}
	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear + 1;
	}
	private int dayOfMonth;
	public int getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	private String personId;
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public ExerciseInfo()
	{
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		monthOfYear = calendar.get(Calendar.MONTH) + 1;
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		personId = "father";
		count = 0;
	}
	
	public ExerciseInfo(int year, int monthOfYear, int dayOfMonth, int count, String personId)
	{
		this.year = year;
		this.monthOfYear = monthOfYear + 1;
		this.dayOfMonth = dayOfMonth;
		this.personId = personId;
		this.count = count;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return year + "y" + monthOfYear + "m" + dayOfMonth + "d" + personId + count;
	}
	
	
}
