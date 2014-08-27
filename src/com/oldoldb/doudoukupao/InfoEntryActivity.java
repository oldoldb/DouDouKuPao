package com.oldoldb.doudoukupao;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import com.example.doudoukupao.R;

public class InfoEntryActivity extends Activity {
	
	private static final String WARNING_STRING = "同一天的暴走记录会累加哦!";
	private static final String ERROR_STRING = "日期超过今天了哦!";
	private String mPersonId;
	private int mCounter[] = new int[5];
	ExerciseInfo mExerciseInfo = new ExerciseInfo();
	private DouDouKuPaoDB mDouDouKuPaoDB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.info_entry);
		mPersonId = getIntent().getStringExtra("personId");
		mExerciseInfo.setPersonId(mPersonId);
		mDouDouKuPaoDB = DouDouKuPaoDB.getInstance(this);
		DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker_date);
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		datePicker.init(year, monthOfYear, dayOfMonth, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				mExerciseInfo.setYear(year);
				mExerciseInfo.setMonthOfYear(monthOfYear);
				mExerciseInfo.setDayOfMonth(dayOfMonth);
			}
		});
		
		NumberPicker numberPicker4 = (NumberPicker)findViewById(R.id.numberPicker_counter4);
		NumberPicker numberPicker3 = (NumberPicker)findViewById(R.id.numberPicker_counter3);
		NumberPicker numberPicker2 = (NumberPicker)findViewById(R.id.numberPicker_counter2);
		NumberPicker numberPicker1 = (NumberPicker)findViewById(R.id.numberPicker_counter1);
		NumberPicker numberPicker0 = (NumberPicker)findViewById(R.id.numberPicker_counter0);
		numberPicker0.setMinValue(0);
		numberPicker0.setMaxValue(9);
		numberPicker0.setOnValueChangedListener(mOnValueChangeListener);
		numberPicker1.setMinValue(0);
		numberPicker1.setMaxValue(9);
		numberPicker1.setOnValueChangedListener(mOnValueChangeListener);
		numberPicker2.setMinValue(0);
		numberPicker2.setMaxValue(9);
		numberPicker2.setOnValueChangedListener(mOnValueChangeListener);
		numberPicker3.setMinValue(0);
		numberPicker3.setMaxValue(9);
		numberPicker3.setOnValueChangedListener(mOnValueChangeListener);
		numberPicker4.setMinValue(0);
		numberPicker4.setMaxValue(9);
		numberPicker4.setOnValueChangedListener(mOnValueChangeListener);
		ImageButton okButton = (ImageButton)findViewById(R.id.button_ok);
		okButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int sum = 0;
				for(int i=0;i<5;i++)
				{
					sum = sum * 10 + mCounter[i];
				}
				mExerciseInfo.setCount(sum);
				Calendar calendar1 =Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
				calendar2.set(mExerciseInfo.getYear(), mExerciseInfo.getMonthOfYear(), mExerciseInfo.getDayOfMonth());
				if(mDouDouKuPaoDB.isExistSameDayData(mExerciseInfo))
				{
					showWarningDialog();
				}
				else if(calendar2.after(calendar1))
				{
					showErrorDialog();
				}
				else 
				{
					mDouDouKuPaoDB.updateExerciseInfo(mExerciseInfo);
					Intent intent = new Intent(InfoEntryActivity.this, HistoryActivity.class);
					intent.putExtra("personId", mPersonId);
					startActivity(intent);
				}
			}
		});
		Button backButton = (Button)findViewById(R.id.button_back);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(InfoEntryActivity.this, PersonalActivity.class);
				intent.putExtra("personId", mPersonId);
				startActivity(intent);
				finish();
			}
		});
	}
	private void showWarningDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(WARNING_STRING);
		builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				mDouDouKuPaoDB.updateExerciseInfo(mExerciseInfo);
				Intent intent = new Intent(InfoEntryActivity.this, HistoryActivity.class);
				intent.putExtra("personId", mPersonId);
				startActivity(intent);
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builder.create().show();
	}
	private void showErrorDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(ERROR_STRING);
		builder.setCancelable(true);
		builder.setNegativeButton("我知道了", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		builder.create().show();
	}
	OnValueChangeListener mOnValueChangeListener = new OnValueChangeListener() {
		
		@Override
		public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
			// TODO Auto-generated method stub
			int index = Integer.parseInt(picker.getTag().toString());
			mCounter[index] = newVal;
		}
	};

}