package com.oldoldb.doudoukupao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.doudoukupao.R;

public class WelcomeActivity extends Activity {

	private static final int DELAY_TIME = 2000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome_view);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WelcomeActivity.this, WelcomeViewPager.class);
				startActivity(intent);
				finish();
			}
		}, DELAY_TIME);
	}
	
}
