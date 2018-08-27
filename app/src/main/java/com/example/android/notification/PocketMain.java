package com.example.android.notification;


import java.io.ByteArrayOutputStream;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import com.example.android.notification.models.ExpensePagerAdapter;

public class PocketMain extends AppCompatActivity {
	private ViewPager mViewPager;
	private ExpensePagerAdapter mExpensePagerAdapter;
	private TextView mExpenseShow;
	private TextView mBalanceShow;
	Intent intent;
	Button add_incm_btn, add_expnc_btn, add_cat_btn, view_expnc_btn, Add_Borrow, Send_msg, mini;
	SQLiteDatabase db;
	boolean flag;

	static int i = 1;
	private android.support.v7.widget.Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pocket_main);

		toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);

		setSupportActionBar(toolbar);

		mExpenseShow = (TextView) findViewById(R.id.show_expense1);
		mBalanceShow = (TextView) findViewById(R.id.show_balance1);
		mViewPager = (ViewPager) findViewById(R.id.Viewpager);
		mExpensePagerAdapter = new ExpensePagerAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mExpensePagerAdapter);
		mExpenseShow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(0);

			}
		});
		mBalanceShow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewPager.setCurrentItem(1);
			}
		});
		mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				changeTabs(position);

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		SharedPreferences preferences1 = getSharedPreferences("wallet", 0);
		flag = preferences1.getBoolean("flag", true);
		if(flag == true)
		{
			preferences1.edit().putBoolean("flag", false).commit();
			DBHelper helper = new DBHelper(this);
			preDefine(helper);
		}
		//add income
		add_incm_btn = (Button)findViewById(R.id.H_income);
		add_incm_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this, AddIncomeActivity.class);
				startActivity(intent);
				finish();
			}
		});


		add_expnc_btn = (Button)findViewById(R.id.H_expence);
		add_expnc_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this, AddExpenseActivity.class);
				startActivity(intent);
				finish();
			}
		});

		//add expense
		add_cat_btn = (Button)findViewById(R.id.H_category);
		add_cat_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this, AddCategoryActivity.class);
				startActivity(intent);
				finish();
			}
		});

		//add expense
		view_expnc_btn = (Button)findViewById(R.id.H_view_expence);
		view_expnc_btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this, ViewExpenseActivity.class);
				startActivity(intent);
				finish();
			}
		});

		//add borrow
		Add_Borrow = (Button)findViewById(R.id.H_view_expence1);
		Add_Borrow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this, Add_Borrow.class);
				startActivity(intent);
				finish();
			}
		});

		//Send msg
		Send_msg = (Button)findViewById(R.id.H_view_expence12);
		Send_msg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this, Send_Msg.class);
				startActivity(intent);
				finish();
			}
		});

		mini=(Button)findViewById(R.id.H_add_minimum);
		mini.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				intent = new Intent(PocketMain.this,minamount.class);
				startActivity(intent);
				finish();
			}
		});

		SharedPreferences preferences = getSharedPreferences("wallet", 0);
		String name = preferences.getString("name", "not found");
		if(name.equals("not found"))
		{
			intent = new Intent(this, CreateActivity.class);
			startActivity(intent);
			finish();
		}


	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menu_pocket_main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//			// Respond to a click on the "Save" menu option
//			case R.id.reset:
//				// Save pet to database
//				SharedPreferences preferences=getSharedPreferences("Income",0);
//				preferences.Editor editor=preferences.edit();
//				editor.clear();
//				editor.commit();
//				SharedPreferences preference=getSharedPreferences("old_income",0);
//				preference.edit().clear().commit();
//				SharedPreferences preferenc=getSharedPreferences("expense",0);
//				preferenc.edit().clear().commit();
//
//
//
//
//
//				return true;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}
	private  void changeTabs(int position){
		if(position==0){
			mExpenseShow.setTextColor(getColor(R.color.textTabBright));
			mExpenseShow.setTextSize(22);
			mBalanceShow.setTextColor(getColor(R.color.textTabLight));
			mBalanceShow.setTextSize(16);

		}
		if(position==1){
			mExpenseShow.setTextColor(getColor(R.color.textTabLight));
			mExpenseShow.setTextSize(16);
			mBalanceShow.setTextColor(getColor(R.color.textTabBright));
			mBalanceShow.setTextSize(22);

		}
	}









		

		

	




	

	//pre Enter values Function
	public void preDefine(DBHelper help)
	{
		Category cat;
		int i;
		byte[] img;
		DBHelper helper = help;
		//Food & Drink
		i = R.drawable.pic49;
		Bitmap b = BitmapFactory.decodeResource(getResources(), i);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Food & Drink", img);
        helper.insertData(cat);
        
        // Transport
        i = R.drawable.pic53;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();        
        cat = new Category("Transport", img);
        helper.insertData(cat);
        
        // Education
        i = R.drawable.pic55;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Education", img);
        helper.insertData(cat);
        
        // Entertainment
        i = R.drawable.pic48;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Entertainment", img);
        helper.insertData(cat);
        
        // Medical
        i = R.drawable.pic50;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Medical", img);
        helper.insertData(cat);
        
        // Shopping
        i = R.drawable.pic52;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Shopping", img);
        helper.insertData(cat);
        
        // Medical
        i = R.drawable.pic54;
        
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Travel", img);
        helper.insertData(cat);
        
        // Medical
        i = R.drawable.pic51;
		b = BitmapFactory.decodeResource(getResources(), i);
        bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        cat = new Category("Other", img);
        helper.insertData(cat);
	}	
	
	@Override
	public void onBackPressed() 
	{
		finish();	
		super.onBackPressed();
	}
	
}
























