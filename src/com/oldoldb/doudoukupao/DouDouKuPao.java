package com.oldoldb.doudoukupao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doudoukupao.R;


public class DouDouKuPao extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button fatherButton = (Button) findViewById(R.id.button_father);
		Button motherButton = (Button) findViewById(R.id.button_mother);
		fatherButton.setOnClickListener(mOnClickListener);
		motherButton.setOnClickListener(mOnClickListener);
	}
	
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(DouDouKuPao.this, PersonalActivity.class);
			intent.putExtra("person", v.getId());
			startActivity(intent);
		}
	};

}
