package com.oldoldb.doudoukupao;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doudoukupao.R;


public class DouDouKuPao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		ImageButton fatherButton = (ImageButton) findViewById(R.id.button_father);
		ImageButton motherButton = (ImageButton) findViewById(R.id.button_mother);
		fatherButton.setOnClickListener(mOnClickListener);
		motherButton.setOnClickListener(mOnClickListener);
		DouDouKuPaoDB douDouKuPaoDB = DouDouKuPaoDB.getInstance(this);
		TextView fatherCounterTextView = (TextView)findViewById(R.id.text_best_counter_father);
		TextView motherCounterTextView = (TextView)findViewById(R.id.text_best_counter_mother);
		TextView fatherDateTextView = (TextView)findViewById(R.id.text_best_date_father);
		TextView motherDataTextView = (TextView)findViewById(R.id.text_best_date_mother);
		Pair<String, Integer> topDataOfFatherPair = douDouKuPaoDB.getRowWithMaxValue("father");
		Pair<String, Integer> topDataOfMotherPair = douDouKuPaoDB.getRowWithMaxValue("mother");
		if(topDataOfFatherPair != null)
		{
			fatherCounterTextView.setText(topDataOfFatherPair.second.toString() + "步");
			fatherDateTextView.setText(topDataOfFatherPair.first);
		}
		else
		{
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			fatherDateTextView.setText(year + "-" + month + "-" + day);
		}
		if(topDataOfMotherPair != null)
		{
			motherCounterTextView.setText(topDataOfMotherPair.second.toString() + "步");
			motherDataTextView.setText(topDataOfMotherPair.first);
		}
		else 
		{
			Calendar calendar = Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			motherDataTextView.setText(year + "-" + month + "-" + day);
		}
	}
	
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(DouDouKuPao.this, PersonalActivity.class);
			intent.putExtra("personId", v.getId()==R.id.button_father?"father":"mother");
			startActivity(intent);
		}
	};

}
