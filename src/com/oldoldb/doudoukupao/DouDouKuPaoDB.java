package com.oldoldb.doudoukupao;

import java.util.ArrayList;
import java.util.List;






import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

public class DouDouKuPaoDB {

	public static final String DB_NAME = "doudou_kupao_db";

	public static final int VERSION = 1;
	private static DouDouKuPaoDB douDouKuPaoDB;
	private SQLiteDatabase db;
	

	private DouDouKuPaoDB(Context context){
		DouDouKuPaoDBHelper dbHelper = new DouDouKuPaoDBHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	

	public synchronized static DouDouKuPaoDB getInstance(Context context){
		if(douDouKuPaoDB == null){
			douDouKuPaoDB = new DouDouKuPaoDB(context);
		}
		return douDouKuPaoDB;
	}

	public void saveExerciseInfo(ExerciseInfo exerciseInfo){
		if(exerciseInfo != null){
			ContentValues values = new ContentValues();
			values.put("date_year", exerciseInfo.getYear());
			values.put("date_month", exerciseInfo.getMonthOfYear());
			values.put("date_day", exerciseInfo.getDayOfMonth());
			values.put("personId", exerciseInfo.getPersonId());
			values.put("counter", exerciseInfo.getCount());
			db.insert("CounterInfo", null, values);
		}
	}
	public List<ExerciseInfo> loadExerciseInfos(String personId){
		List<ExerciseInfo> list = new ArrayList<ExerciseInfo>();
		System.out.println("personid : " + personId);
		Cursor cursor = db.query("CounterInfo", null, "personId = ?", new String[]{personId}, null, null, null);
		System.out.println(cursor == null);
		if(cursor.moveToFirst()){
			do{
				ExerciseInfo exerciseInfo = new ExerciseInfo();
				exerciseInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
				exerciseInfo.setYear(cursor.getInt(cursor.getColumnIndex("date_year")));
				exerciseInfo.setMonthOfYear(cursor.getInt(cursor.getColumnIndex("date_month")));
				exerciseInfo.setDayOfMonth(cursor.getInt(cursor.getColumnIndex("date_day")));
				exerciseInfo.setPersonId(personId);
				exerciseInfo.setCount(cursor.getInt(cursor.getColumnIndex("counter")));
				list.add(exerciseInfo);
			} while(cursor.moveToNext());
		}
		return list;
	}
	
	public Pair<String, Integer> getRowWithMaxValue(String personId)
	{
		Cursor cursor = db.query("CounterInfo", null, "personId = ?", new String[]{personId}, null, null, "counter desc");
		if(cursor.moveToFirst())
		{
			int year = cursor.getInt(cursor.getColumnIndex("date_year"));
			int monthOfYear = cursor.getInt(cursor.getColumnIndex("date_month"));
			int dayOfMonth = cursor.getInt(cursor.getColumnIndex("date_day"));
			int counter = cursor.getInt(cursor.getColumnIndex("counter"));
			return new Pair<String, Integer>(year + "-" + monthOfYear + "-" + dayOfMonth, counter);
		}
		return null;
	}
}
