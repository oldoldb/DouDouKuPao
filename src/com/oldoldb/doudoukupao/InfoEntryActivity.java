package com.oldoldb.doudoukupao;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import com.example.doudoukupao.R;
import com.oldoldb.db.DouDouKuPaoDB;
import com.oldoldb.model.ExerciseInfo;
import com.oldoldb.util.DouDouKuPaoUtil;

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
		init();
	}
	private void init()
	{
		mDouDouKuPaoDB = DouDouKuPaoDB.getInstance(this);
		mPersonId = getIntent().getStringExtra("personId");
		mExerciseInfo.setPersonId(mPersonId);
		ImageButton okButton = (ImageButton)findViewById(R.id.button_ok);
		Button backButton = (Button)findViewById(R.id.button_back);
		okButton.setOnClickListener(mOnClickListener);
		backButton.setOnClickListener(mOnClickListener);
		
		DatePicker datePicker = (DatePicker)findViewById(R.id.datePicker_date);
		int year = DouDouKuPaoUtil.getNowYear();
		int monthOfYear = DouDouKuPaoUtil.getNowMonthOfYear();
		int dayOfMonth = DouDouKuPaoUtil.getNowDayOfMonth();
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
		NumberPicker numberPicker[] = new NumberPicker[5];
		numberPicker[4] = (NumberPicker)findViewById(R.id.numberPicker_counter4);
		numberPicker[3] = (NumberPicker)findViewById(R.id.numberPicker_counter3);
		numberPicker[2] = (NumberPicker)findViewById(R.id.numberPicker_counter2);
		numberPicker[1] = (NumberPicker)findViewById(R.id.numberPicker_counter1);
		numberPicker[0] = (NumberPicker)findViewById(R.id.numberPicker_counter0);
		for(int i=0;i<5;i++)
		{
			numberPicker[i].setMinValue(0);
			numberPicker[i].setMaxValue(9);
			numberPicker[i].setOnValueChangedListener(new OnValueChangeListener() {
				
				@Override
				public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
					// TODO Auto-generated method stub
					int index = Integer.parseInt(picker.getTag().toString());
					mCounter[index] = newVal;
				}
			});
		}	
	}
	
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_ok:
				int sum = 0;
				for(int i=0;i<5;i++)
				{
					sum = sum * 10 + mCounter[i];
				}
				mExerciseInfo.setCount(sum);
				if(mDouDouKuPaoDB.isExistSameDayData(mExerciseInfo))
				{
					showWarningDialog();
				}
				else if(DouDouKuPaoUtil.isAfterToday(mExerciseInfo))
				{
					showErrorDialog();
				}
				else 
				{
					finishInputData();
				}
				break;
			case R.id.button_back:
				DouDouKuPaoUtil.startActivity(InfoEntryActivity.this, PersonalActivity.class, mPersonId);
				finish();
				break;
			default:
				break;
			}
		}
	};
	private void finishInputData()
	{
		mDouDouKuPaoDB.updateExerciseInfo(mExerciseInfo);
		DouDouKuPaoUtil.startActivity(InfoEntryActivity.this, HistoryActivity.class, mPersonId);
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
				finishInputData();
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

}