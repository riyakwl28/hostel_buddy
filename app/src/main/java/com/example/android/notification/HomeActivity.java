package com.example.android.notification;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Mthread m = new Mthread();


	}
	
	class Mthread extends Thread
	{
		Mthread()
		{
			start();			
		}
		public void run()
		{

			Intent intent = new Intent(getApplicationContext(), PocketMain.class);
			startActivity(intent);
			finish();
		}
	}

}
