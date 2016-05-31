package com.example.sqlitenotetext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends BaseActivity
{
	private Button add;
	private Button check;
	private MyOpenHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		add = (Button)findViewById(R.id.add);
		check = (Button)findViewById(R.id.check);
		dbHelper = new MyOpenHelper(this, "notedata.db", null, 1);
		MyListener listener = new MyListener();
		add.setOnClickListener(listener);
		check.setOnClickListener(listener);
				
	}
	class MyListener implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			switch(v.getId())
			{
			case R.id.add:
				add(dbHelper);
				break;
			case R.id.check:
				check(dbHelper);
				break;
			default:
				break;
			}
		}
		
	}
	private void add(MyOpenHelper dbHelper)
	{
		Intent intent = new Intent(MainActivity.this, AddActivity.class);
		startActivity(intent);
	}
	private void check(MyOpenHelper dbHelper)
	{
		Intent intent = new Intent(MainActivity.this, CheckActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed()
	{
		ActivityManager.deleteAllActivity();
	}
}
