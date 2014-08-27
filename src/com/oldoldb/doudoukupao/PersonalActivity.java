package com.oldoldb.doudoukupao;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.doudoukupao.R;
import com.oldoldb.util.DouDouKuPaoUtil;


public class PersonalActivity extends Activity {

	private String mPersonId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal);
		init();
	}
	private void init()
	{
		mPersonId = getIntent().getStringExtra("personId");
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
				DouDouKuPaoUtil.startActivity(PersonalActivity.this, InfoEntryActivity.class, mPersonId);
				break;
			case R.id.button_viewHistory:
				DouDouKuPaoUtil.startActivity(PersonalActivity.this, HistoryActivity.class, mPersonId);
				break;
			case R.id.button_back:
				DouDouKuPaoUtil.startActivity(PersonalActivity.this, DouDouKuPao.class, "");
				finish();
				break;
			default:
				break;
			}
		}
	};

}
