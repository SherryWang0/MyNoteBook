package com.example.sqlitenotetext;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends BaseActivity
{
	private EditText editTitle;
	private EditText editContent;
	private Button button;
	private MyOpenHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_layout);
		editTitle = (EditText)findViewById(R.id.edit1);
		editContent = (EditText)findViewById(R.id.edit2);
		button = (Button)findViewById(R.id.button);
		Intent intent = getIntent();
		final int type = intent.getIntExtra("type", -9);
		String title = intent.getStringExtra("title");
		String content = intent.getStringExtra("content");
		System.out.println(title + content + "//" + type);
		if(type != -9)
		{
			System.out.println("????");
			editTitle.setText(title);
			editContent.setText(content);
		}
		dbHelper = new MyOpenHelper(this, "notedata.db", null, 1);
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				String title = editTitle.getText().toString();
				String content = editContent.getText().toString();
				ContentValues values = new ContentValues();
				values.put("title", title);
				values.put("content", content);
				if(type > 0)
					db.update("note", values, "_id = ?", new String[]{type+""});
				else
					db.insert("note", null, values);
				Toast.makeText(AddActivity.this, "±£´æ³É¹¦£¡", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(AddActivity.this, MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
