package niord.apps;

import niord.apps.data.Data;
import niord.apps.data.Race;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class MotoGP_Widget extends AppWidgetProvider {

	Race nextRace;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		// Get all ids
		ComponentName thisWidget = new ComponentName(context, MotoGP_Widget.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {

			// Get link to the widget view
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

			// Set the text
			nextRace = Data.getNextRace();

			remoteViews.setTextViewText(R.id.cirquit, nextRace.getCirquit());
			remoteViews.setTextViewText(R.id.grandprix, nextRace.getGrandprix());
			remoteViews.setTextViewText(R.id.date, nextRace.getDate());

			// Register an onClickListener
			Intent intent = new Intent(context, MotoGP_Widget.class);

			// Create new action UPDATE with parameters
			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			// Create an event
			PendingIntent pendingIntentUpdate = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

			// Register an event
			remoteViews.setOnClickPendingIntent(R.id.cirquit, pendingIntentUpdate);
			remoteViews.setOnClickPendingIntent(R.id.grandprix, pendingIntentUpdate);
			remoteViews.setOnClickPendingIntent(R.id.date, pendingIntentUpdate);

			// Show toast
			if (nextRace.getDays() > 0) {
				Toast.makeText(context, "Next race in " + nextRace.getDays() + " days!", Toast.LENGTH_SHORT).show();
			}
			else
				if (nextRace.getHours() > 0) {
					Toast.makeText(context, "Next race in " + nextRace.getHours() + " hours!", Toast.LENGTH_SHORT).show();
				}
				else
					if (nextRace.getMinutes() > 0) {
						Toast.makeText(context, "Next race in " + nextRace.getMinutes() + " minutes!", Toast.LENGTH_SHORT).show();
					}
					else {
						Toast.makeText(context, "Next race in " + nextRace.getSeconds() + " seconds!", Toast.LENGTH_SHORT).show();
					}

			// Update Widget
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
}