package com.oldoldb.doudoukupao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DouDouKuPaoDBHelper extends SQLiteOpenHelper {

	public static final String CREATE_COUNTER_INFO = "create table CounterInfo ("
			+ "id integer primary key autoincrement, "
			+ "date_year integer, "
			+ "date_month integer, "
			+ "date_day integer, "
			+ "personId integer, "
			+ "counter integer) ";
	
	public DouDouKuPaoDBHelper(Context context, String name, CursorFactory factory, int version){
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_COUNTER_INFO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
