package com.oldoldb.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.doudoukupao.R;
import com.oldoldb.util.DouDouKuPaoUtil;

public class WelcomeActivity extends Activity {

	private static final int DELAY_TIME = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.welcome_view);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DouDouKuPaoUtil.startActivity(WelcomeActivity.this, WelcomeViewPager.class, "");
				finish();
			}
		}, DELAY_TIME);
	}
	
}
