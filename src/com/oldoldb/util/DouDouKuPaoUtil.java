package com.oldoldb.util;

import java.util.Calendar;

import com.oldoldb.model.ExerciseInfo;

import android.content.Context;
import android.content.Intent;

public class DouDouKuPaoUtil {
	public static final String PERSONID_FATHER = "father";
	public static final String PERSONID_MOTHER = "mother";
	
	public static String getNowDataForTextView()
	{
		return Calendar.getInstance().get(Calendar.YEAR) + "-" + getNowDataForChart();
	}
	public static String getNowDataForChart()
	{
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return month + "-" + day;
	}
	public static int getNowYear()
	{
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static int getNowMonthOfYear()
	{
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	public static int getNowDayOfMonth()
	{
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	public static boolean isBeforeToday(ExerciseInfo exerciseInfo, int addDay)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(exerciseInfo.getYear(), exerciseInfo.getMonthOfYear(), exerciseInfo.getDayOfMonth());
		calendar.add(Calendar.DAY_OF_MONTH, addDay);
		if(calendar.before(Calendar.getInstance()))
		{
			return true;
		}
		return false;
	}
	public static boolean isAfterToday(ExerciseInfo exerciseInfo)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(exerciseInfo.getYear(), exerciseInfo.getMonthOfYear(), exerciseInfo.getDayOfMonth());
		if(calendar.after(Calendar.getInstance()))
		{
			return true;
		}
		return false;
	}
	public static void startActivity(Context packageContext, Class<?> cls, String personId)
	{
		Intent intent = new Intent(packageContext, cls);
		if(personId != "")
		{
			intent.putExtra("personId", personId);
		}
		packageContext.startActivity(intent);
	}
}
