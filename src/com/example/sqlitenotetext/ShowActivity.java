package com.example.sqlitenotetext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity
{
	private TextView text1;
	private TextView text2;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_layout);
		text1 = (TextView)findViewById(R.id.tit);
		text2 = (TextView)findViewById(R.id.con);
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		String content = intent.getStringExtra("content");
		text1.setText(title);
		text2.setText(content);
		
	}
}
