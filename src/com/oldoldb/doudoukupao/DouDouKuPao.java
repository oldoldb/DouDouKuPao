package com.oldoldb.doudoukupao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

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
