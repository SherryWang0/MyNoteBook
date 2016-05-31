package com.example.sqlitenotetext;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityManager
{
	public static List<Activity> activities = new ArrayList<Activity>();
	public static void addActivity(Activity activity)
	{
		activities.add(activity);
	}
	public static void deleteActivity(Activity activity)
	{
		activities.remove(activity);
	}
	public static void deleteAllActivity()
	{
		for(Activity activity : activities)
		{
			if(!activity.isFinishing())
				activity.finish();
		}
	}
}
