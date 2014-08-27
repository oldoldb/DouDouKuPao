package com.oldoldb.doudoukupao;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.doudoukupao.R;
import com.oldoldb.util.DouDouKuPaoUtil;

public class WelcomeViewPager extends Activity {
	private ViewPager mViewPager;
	private ImageView mImageView;
	private List<View> mPageViews;
	private ImageView[] mDotImageViews;
	private ViewGroup mImageGroup;
	private ViewGroup mDotGroup;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		init();
	}
	private void init()
	{
		LayoutInflater inflater = getLayoutInflater();
		mPageViews = new ArrayList<View>();
		mPageViews.add(inflater.inflate(R.layout.viewpage1, null));
		mPageViews.add(inflater.inflate(R.layout.viewpage2, null));
		mPageViews.add(inflater.inflate(R.layout.viewpage3, null));
		mPageViews.add(inflater.inflate(R.layout.viewpage4, null));
		mDotImageViews = new ImageView[mPageViews.size()];
		mImageGroup = (ViewGroup)inflater.inflate(R.layout.viewpager, null);
		mViewPager = (ViewPager)mImageGroup.findViewById(R.id.guidePagers);
		mDotGroup = (ViewGroup)mImageGroup.findViewById(R.id.viewPoints);
		
		for(int i=0;i<mPageViews.size();i++)
		{
			mImageView = new ImageView(this);
			mImageView.setLayoutParams(new LayoutParams(50, 50));
			mImageView.setPadding(20, 0, 20, 0);
			mDotImageViews[i] = mImageView;
			if(i==0)
			{
				mDotImageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_focused));
			}
			else 
			{
				mDotImageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_unfocused));
			}
			mDotGroup.addView(mDotImageViews[i]);
		}
		setContentView(mImageGroup);
		mViewPager.setAdapter(new NavigationPageAdapter());
		mViewPager.setOnPageChangeListener(new NavigationPageChangeListener());
		Button startButton = (Button)mPageViews.get(3).findViewById(R.id.start_btn);
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DouDouKuPaoUtil.startActivity(WelcomeViewPager.this, DouDouKuPao.class, "");
				finish();
			}
		});
	}
	class NavigationPageAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager)container).removeView(mPageViews.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			((ViewPager)container).addView(mPageViews.get(position));
			return mPageViews.get(position);
		}
		
	}
	class NavigationPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<mDotImageViews.length;i++)
			{
				mDotImageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_focused));
				if(arg0!=i)
				{
					mDotImageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_unfocused));
				}
			}
		}
		
	}
	
}
