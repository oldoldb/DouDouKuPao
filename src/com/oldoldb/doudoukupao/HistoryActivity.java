package com.oldoldb.doudoukupao;

import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;

import com.example.doudoukupao.R;
import com.oldoldb.db.DouDouKuPaoDB;
import com.oldoldb.model.ExerciseInfo;
import com.oldoldb.util.DouDouKuPaoUtil;

public class HistoryActivity extends Activity {

	private static final String TAG = "HistoryActivity";
	private static final String URL_CHART = "file:///android_asset/barChart.html";
	private static final String[] MCOLOR_STRINGS = new String[]{"#83a6d5",
		"#f37db2","#edecee","#8fc640",
		"#648bbf","#e3b314","#a38989"};
	
	private DouDouKuPaoDB mDouDouKuPaoDB;
	private String mPersonId;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.history_view);
		init();		
	}
	@SuppressLint("SetJavaScriptEnabled") 
	private void init()
	{
		mDouDouKuPaoDB = DouDouKuPaoDB.getInstance(this);
		mPersonId = getIntent().getStringExtra("personId");
		Button backButton = (Button)findViewById(R.id.button_back);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DouDouKuPaoUtil.startActivity(HistoryActivity.this, PersonalActivity.class, mPersonId);
				finish();
			}
		});
		
		final WebView historyWebView = (WebView)findViewById(R.id.webView_history);
		historyWebView.addJavascriptInterface(this, TAG);
		historyWebView.getSettings().setJavaScriptEnabled(true);
		historyWebView.getSettings().setUseWideViewPort(true);
		historyWebView.getSettings().setSupportZoom(true);
		historyWebView.getSettings().setBuiltInZoomControls(true);
		historyWebView.getSettings().setLoadWithOverviewMode(true);
		historyWebView.requestFocus();
		historyWebView.loadUrl(URL_CHART);
		
	}
	@SuppressWarnings("unchecked")
	@JavascriptInterface
	public String getShowData()
	{
		try {
			List<ExerciseInfo> exerciseInfos = mDouDouKuPaoDB.loadExerciseInfos(mPersonId);
			Collections.sort(exerciseInfos);
			int size = exerciseInfos.size();
			int start = 0;
			int end = size > 7? 7 : size;
			JSONArray jsonArray = new JSONArray();
			for(int i=end-1;i>=0;i--)
			{
				JSONObject jsonObject = new JSONObject();
				ExerciseInfo exerciseInfo = exerciseInfos.get(i);
				if(DouDouKuPaoUtil.isBeforeToday(exerciseInfo, 7))
				{
					continue;
				}
				String str = exerciseInfo.getMonthOfYear() + 1 + "-" + exerciseInfo.getDayOfMonth();
				jsonObject.put("name", str);
				jsonObject.put("value", exerciseInfo.getCount());
				jsonObject.put("color", MCOLOR_STRINGS[i-start]);
				jsonArray.put(jsonObject);	
			}
			return jsonArray.toString();
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	
}
