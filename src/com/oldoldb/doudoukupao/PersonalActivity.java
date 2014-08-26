package com.oldoldb.doudoukupao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.doudoukupao.R;


public class PersonalActivity extends Activity {

	private String mPersonId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal);
		mPersonId = getIntent().getStringExtra("personId");
		System.out.println("mPersonId in PersonalActivity : " + mPersonId);
		ImageButton addInfoButton = (ImageButton) findViewById(R.id.button_addInfo);
		ImageButton viewHistoryButton = (ImageButton) findViewById(R.id.button_viewHistory);
		Button backButton = (Button)findViewById(R.id.button_back);
		addInfoButton.setOnClickListener(mOnClickListener);
		viewHistoryButton.setOnClickListener(mOnClickListener);
		backButton.setOnClickListener(mOnClickListener);
	}
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_addInfo:
				Intent intent = new Intent(PersonalActivity.this, InfoEntryActivity.class);
				intent.putExtra("personId", mPersonId);
				startActivity(intent);
				break;
			case R.id.button_viewHistory:
				Intent intent2 = new Intent(PersonalActivity.this, HistoryActivity.class);
				intent2.putExtra("personId", mPersonId);
				startActivity(intent2);
				break;
			case R.id.button_back:
				Intent intent3 = new Intent(PersonalActivity.this, DouDouKuPao.class);
				startActivity(intent3);
				finish();
				break;
			default:
				break;
			}
		}
	};

}
