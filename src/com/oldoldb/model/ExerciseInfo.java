package com.oldoldb.model;

import java.util.Calendar;


@SuppressWarnings("rawtypes")
public class ExerciseInfo implements Comparable{
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
		this.monthOfYear = monthOfYear;
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
		monthOfYear = calendar.get(Calendar.MONTH);
		dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		personId = "father";
		count = 0;
	}
	
	public ExerciseInfo(int year, int monthOfYear, int dayOfMonth, int count, String personId)
	{
		this.year = year;
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
		this.personId = personId;
		this.count = count;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return year + "y" + monthOfYear + "m" + dayOfMonth + "d" + personId + count;
	}
	@Override
	public int compareTo(Object another) {
		// TODO Auto-generated method stub
		ExerciseInfo exerciseInfo2 = (ExerciseInfo)another;
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(this.year, this.monthOfYear, this.dayOfMonth);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(exerciseInfo2.getYear(), exerciseInfo2.getMonthOfYear(), exerciseInfo2.getDayOfMonth());
		if(calendar1.before(calendar2))
		{
			return 1;
		}
		else if(calendar1.equals(calendar2))
		{
			return 0;
		}
		else 
		{
			return -1;
		}
	}
	
	
}
