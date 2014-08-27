package com.oldoldb.activity;


import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doudoukupao.R;
import com.oldoldb.db.DouDouKuPaoDB;
import com.oldoldb.util.DouDouKuPaoUtil;


public class DouDouKuPao extends Activity {

	private TextView mFatherCounterTextView;
	private TextView mMotherCounterTextView;
	private TextView mFatherDateTextView;
	private TextView mMotherDateTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.main);
		init();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		updateTopData();
	}

	private void init()
	{
		ImageButton fatherButton = (ImageButton) findViewById(R.id.button_father);
		ImageButton motherButton = (ImageButton) findViewById(R.id.button_mother);
		fatherButton.setOnClickListener(mOnClickListener);
		motherButton.setOnClickListener(mOnClickListener);
		mFatherCounterTextView = (TextView)findViewById(R.id.text_best_counter_father);
		mMotherCounterTextView = (TextView)findViewById(R.id.text_best_counter_mother);
		mFatherDateTextView = (TextView)findViewById(R.id.text_best_date_father);
		mMotherDateTextView = (TextView)findViewById(R.id.text_best_date_mother);
		updateTopData();
	}
	private void updateTopData()
	{
		DouDouKuPaoDB douDouKuPaoDB = DouDouKuPaoDB.getInstance(this);
		Pair<String, Integer> topDataOfFatherPair = douDouKuPaoDB.getRowWithMaxValue("father");
		Pair<String, Integer> topDataOfMotherPair = douDouKuPaoDB.getRowWithMaxValue("mother");
		if(topDataOfFatherPair != null)
		{
			mFatherCounterTextView.setText(topDataOfFatherPair.second.toString() + "步");
			mFatherDateTextView.setText(topDataOfFatherPair.first);
		}
		else
		{
			mFatherDateTextView.setText(DouDouKuPaoUtil.getNowDataForTextView());
		}
		if(topDataOfMotherPair != null)
		{
			mMotherCounterTextView.setText(topDataOfMotherPair.second.toString() + "步");
			mMotherDateTextView.setText(topDataOfMotherPair.first);
		}
		else 
		{
			mMotherDateTextView.setText(DouDouKuPaoUtil.getNowDataForTextView());
		}
	}
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DouDouKuPaoUtil.startActivity(DouDouKuPao.this, 
					PersonalActivity.class, 
					v.getId()==R.id.button_father?DouDouKuPaoUtil.PERSONID_FATHER:DouDouKuPaoUtil.PERSONID_MOTHER);
		}
	};

}
