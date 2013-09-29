package co.sfng.calendarwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


public class ConfigureActivity extends Activity {

	private static final String TAG = ConfigureActivity.class.toString();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize SharedPreferences with default values if it hasn't already.
		PreferenceManager.setDefaultValues(getApplicationContext(), R.xml.preferences, false);

		setContentView(R.layout.appwidget_settings);
		findViewById(R.id.ok_button).setOnClickListener(mOnClickListener);

		Log.i(TAG, "onCreate()");
	}

	private OnClickListener mOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Log.i(TAG, "Ok button is clicked.");

			// Obtain all app widget IDs.
			Context context = getApplicationContext();
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			ComponentName cn = new ComponentName(context, CalendarWidgetProvider.class);
			int[] appWidgetIds = appWidgetManager.getAppWidgetIds(cn);

			// Broadcast update action to all running app widgets.
			Intent intent = new Intent();
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
			context.sendBroadcast(intent);

			finish();
		}
	};

}
