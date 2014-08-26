package com.oldoldb.doudoukupao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;

import com.example.doudoukupao.R;

public class HistoryActivity extends Activity {

	private DouDouKuPaoDB mDouDouKuPaoDB;
	private static final String[] MCOLOR_STRINGS = new String[]{"#83a6d5",
		"#f37db2","#edecee","#8fc640",
		"#648bbf","#e3b314","#a38989"};
	String mPersonId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_view);
		mDouDouKuPaoDB = DouDouKuPaoDB.getInstance(this);
		mPersonId = getIntent().getStringExtra("personId");
		final WebView historyWebView = (WebView)findViewById(R.id.webView_history);
		historyWebView.getSettings().setJavaScriptEnabled(true);
		historyWebView.getSettings().setUseWideViewPort(true);
		historyWebView.getSettings().setSupportZoom(true);
		historyWebView.getSettings().setBuiltInZoomControls(true);
		historyWebView.getSettings().setLoadWithOverviewMode(true);
		historyWebView.requestFocus();
		historyWebView.loadUrl("file:///android_asset/barChart.html");
		Button testButton = (Button)findViewById(R.id.button_test);
		testButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str = "javascript:setContentInfo('"+getShowData()+ "')";
				System.out.println(str);
				historyWebView.loadUrl(str);
			}
		});
		
	}
	
	private String getShowData()
	{
		try {
			List<ExerciseInfo> exerciseInfos = mDouDouKuPaoDB.loadExerciseInfos(mPersonId);
			int size = exerciseInfos.size();
			int start = size > 7 ? size - 7 : 0;
			int end = size;
			JSONArray jsonArray = new JSONArray();
			for(int i=start;i<end;i++)
			{
				JSONObject jsonObject = new JSONObject();
				ExerciseInfo exerciseInfo = exerciseInfos.get(i);
				String str = exerciseInfo.getYear() + "-" + exerciseInfo.getMonthOfYear() + "-" + exerciseInfo.getDayOfMonth();
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
