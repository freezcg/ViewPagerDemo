package com.example.viewpager;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity{
	private BaseViewPager viewPager;
	private DragImageView imageView;
	private List<MPicture> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		list = setList(); //填充数据
		viewPager = (BaseViewPager) findViewById(R.id.view_pager);
		viewPager.setAdapter(new GuidePageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
		
	}
	
	/**
	 * 设置数据
	 * @return
	 * 2014-6-5
	 */
	public List<MPicture> setList(){
		List<MPicture> list = new ArrayList<MPicture>();
		for(int i = 0; i<30; i++){
			MPicture mp = new MPicture();
			mp.setPicturePath("http://d.hiphotos.baidu.com/image/pic/item/9f2f070828381f302276f50cab014c086e06f02c.jpg");
			mp.setPictureName(1+".jpg");
			list.add(mp);
			
			mp = new MPicture();
			mp.setPicturePath("http://h.hiphotos.baidu.com/image/pic/item/faedab64034f78f05009e2567b310a55b3191c10.jpg");
			mp.setPictureName(2+".jpg");
			list.add(mp);
			
			mp = new MPicture();
			mp.setPicturePath("http://h.hiphotos.baidu.com/image/pic/item/7af40ad162d9f2d3800f5c4aabec8a136327cc7d.jpg");
			mp.setPictureName(3+".jpg");
			list.add(mp);
		}
		return list;
	}	
	
	/**
	 * 生成view
	 * @param picture
	 * @return
	 * 2014-6-5
	 */
	public View getView(MPicture picture){
		imageView = new DragImageView(this);
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		LayoutParams params = new LayoutParams(new LayoutParams(LayoutParams.FILL_PARENT ,LayoutParams.FILL_PARENT));
		imageView.setLayoutParams(params);
		imageView.setImage(picture.getPicturePath(), picture.getPictureName());
		LinearLayout llayout = new LinearLayout(this);
		llayout.addView(imageView);
		imageView.setOnClickListener(new ClickListener());
		return llayout;
	}
	
	/**
	 * 点击退出
	 * @author ling
	 * 2014-6-5
	 */
	class ClickListener implements OnClickListener{
		public void onClick(View v) {
			MainActivity.this.finish();
		}
		
	}
	
	class GuidePageAdapter extends BasePagerAdapter {
		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			super.setPrimaryItem(container, position, object);
		}

		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			View view = getView(list.get(position));
			((ViewPager)collection).addView(view);
			return view;
		}

		public GuidePageAdapter() {
			super(list.size());
		}

	}

	class GuidePageChangeListener implements OnPageChangeListener {

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int position) {
//			current_pic.setText(String.valueOf(position+1));
		}

	}


}



