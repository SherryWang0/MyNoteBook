package com.example.sqlitenotetext;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CheckActivity extends BaseActivity
{
	private ListView list;
	private List<String> l;
	private ArrayAdapter adapter;
	private MyOpenHelper dbHelper;
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_layout);
		list = (ListView)findViewById(R.id.list);
		l = new ArrayList<String>();

		dbHelper = new MyOpenHelper(this, "notedata.db", null, 1);
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		cursor = db.query("note", new String[]{"_id", "title"}, null, null, null, null, null);
		while(cursor.moveToNext())
		{
			String t = cursor.getString(cursor.getColumnIndex("title"));
			l.add(t);
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, l);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				String title = l.get(position);
				Cursor c = db.query("note", new String[]{"content"}, "title = ?", new String[]{title}, null, null, null);
				String content = "";
				while(c.moveToNext())
					content = c.getString(c.getColumnIndex("content"));
				Intent intent = new Intent(CheckActivity.this, ShowActivity.class);
				intent.putExtra("title", title);
				intent.putExtra("content", content);
				startActivity(intent);
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id)
			{
				final int po = position;
				Builder builder = new Builder(CheckActivity.this);
				builder.setSingleChoiceItems(new String[]{"查看", "修改", "删除"}, 0, new OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						String title = l.get(po);
						Cursor c = db.query("note", new String[]{"_id", "content"}, "title = ?", new String[]{title}, null, null, null);
						String content = "";
						int id = -1;
						while(c.moveToNext())
						{
							id = c.getInt(c.getColumnIndex("_id"));
							content = c.getString(c.getColumnIndex("content"));
						}
						switch(which)
						{
						case 0:
							Intent intent = new Intent(CheckActivity.this, ShowActivity.class);
							intent.putExtra("title", title);
							intent.putExtra("content", content);
							startActivity(intent);
							break;
						case 1:
							Intent intent1 = new Intent(CheckActivity.this, AddActivity.class);
							System.out.println(id);
							intent1.putExtra("type", id);
							intent1.putExtra("title", title);
							intent1.putExtra("content", content);
							startActivity(intent1);
							break;
						case 2:
							db.delete("note", "title = ?", new String[]{title});
							Toast.makeText(CheckActivity.this, "删除成功！", Toast.LENGTH_SHORT).show();
							break;
						default:
							break;
						}
					}
				});
				builder.show();
				return false;
			}
		});
	}
}
