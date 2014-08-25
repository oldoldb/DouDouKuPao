package com.oldoldb.doudoukupao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doudoukupao.R;


public class PersonalActivity extends Activity {

	private int mPersonId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal);
		mPersonId = getIntent().getIntExtra("person", R.id.button_father);
		Button addInfoButton = (Button) findViewById(R.id.button_addInfo);
		Button viewHistoryButton = (Button) findViewById(R.id.button_viewHistory);
		addInfoButton.setOnClickListener(mOnClickListener);
		viewHistoryButton.setOnClickListener(mOnClickListener);
	}
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_addInfo:
				Intent intent = new Intent(PersonalActivity.this, InfoEntryActivity.class);
				intent.putExtra("presonId", mPersonId);
				startActivity(intent);
				break;
			case R.id.button_viewHistory:
				Intent intent2 = new Intent(PersonalActivity.this, HistoryActivity.class);
				intent2.putExtra("personId", mPersonId);
				startActivity(intent2);
				break;
			default:
				break;
			}
		}
	};

}
