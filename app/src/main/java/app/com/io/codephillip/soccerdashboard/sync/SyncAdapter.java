package app.com.io.codephillip.soccerdashboard.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.com.io.codephillip.soccerdashboard.MainActivity;
import app.com.io.codephillip.soccerdashboard.R;
import app.com.io.codephillip.soccerdashboard.data.SoccerContract;

/**
 * Created by codephillip on 11/9/15.
 */
public class SyncAdapter extends AbstractThreadedSyncAdapter {

    // Interval at which to sync with the weather, in seconds.
    // 60 seconds (1 minute) * 600 = 10 hours
    public static final int SYNC_INTERVAL = 60 * 600;
    // 60 seconds (1 minute) * 180 = 3 hours
    //    public static final int SYNC_INTERVAL = 60 * 180;
//    public static final int SYNC_INTERVAL = 15 * 1;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL / 3;
    private static final long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;
    private static final int SOCCER_NOTIFICATION_ID = 3004;
    private final String TAG = SyncAdapter.class.getSimpleName();
    private String[] fixturesUrl;
    private String[] TableUrl;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d("SYNCADAPTER", "ONPERFORMSYNC");

        notifyWeather();
//        try {
//            int k;
//            for (k=0; k<2 ; k++){
//                if (k == 0){
//                    getTableDataJson(connectToServer(Utility.SoccerUrls.BarclaysPLTableUrl));
//                } else if (k == 1) {
//                    getFixtureDataJson(connectToServer(Utility.SoccerUrls.BarclaysPLFixturesUrl));
//                }
//            }
//            notifyWeather();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("URL BUG", e.toString());
//        }

//        try {
//            int k, n;
//            //TODO replace with root api
//            getUrlsFromJson(connectToServer("http://api.football-data.org/alpha/soccerseasons/398/leagueTable"));
//            for (k=0; k<2 ; k++){
//                if (k == 0){
//                    for (n=0; n<6 ; n++){
//                        getTableDataJson(connectToServer(TableUrl[n]), n);
//                    }
//                } else if (k == 1) {
//                    for (n=0; n<6 ; n++) {
//                        getFixtureDataJson(connectToServer(fixturesUrl[n]), n);
//                    }
//                }
//            }
//            notifyWeather();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("URL BUG", e.toString());
//        }
    }

    private void getUrlsFromJson(String s) {

    }

    private Void getTableDataJson(String jsonData, int leagueNo) throws JSONException {
        final String TAG_STANDING = "standing";
        final String TAG_POSITION = "position";
        final String TAG_TEAM_NAME = "teamName";
        final String TAG_POINTS = "points";
        final String TAG_GOALS = "goals";
        final String TAG_GOALS_AGAINST = "goalsAgainst";
        final String TAG_GOALS_DIFFERENCE = "goalDifference";
        final String TAG_GAMES_PLAYED = "playedGames";

        String position;
        String teamName;
        String points;
        String goals;
        String values;
        String goalsAgainst;
        String goalDifference;
        String gamesPlayed;
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray(TAG_STANDING);

        int i;
        int jsonLength = jsonArray.length();

        for (i=0; i<jsonLength; i++){
            JSONObject innerObject = jsonArray.getJSONObject(i);

            position = innerObject.getString(TAG_POSITION);
            teamName = innerObject.getString(TAG_TEAM_NAME);
            points = innerObject.getString(TAG_POINTS);
            goals = innerObject.getString(TAG_GOALS);
            goalsAgainst = innerObject.getString(TAG_GOALS_AGAINST);
            goalDifference = innerObject.getString(TAG_GOALS_DIFFERENCE);
            gamesPlayed = innerObject.getString(TAG_GAMES_PLAYED);

            values = teamName+" "+position+" "+points+" "+goals;
            Log.d("JSONRESULT", values);

            storeInLeagueTable(position, teamName, points, goals, goalsAgainst, goalDifference, gamesPlayed, leagueNo);
        }
        return null;
    }

    private void getFixtureDataJson(String jsonData, int leagueNo) throws JSONException{
        final String TAG_DATE = "date";
        final String TAG_STATUS = "status";
        final String TAG_HOME_TEAM_NAME = "homeTeamName";
        final String TAG_AWAY_TEAM_NAME = "awayTeamName";
        final String TAG_GOALS_HOME_TEAM = "goalsHomeTeam";
        final String TAG_GOALS_AWAY_TEAM = "goalsAwayTeam";
        final String TAG_FIXTURES = "fixtures";
        final String TAG_RESULT = "result";

        String date;
        String status;
        String homeTeamName;
        String awayTeamName;
        String goalsHomeTeam;
        String goalsAwayTeam;

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray(TAG_FIXTURES);
        int n, jsonLength = jsonArray.length();

        for (n = 0; n < jsonLength; n++){
            JSONObject innerObject = jsonArray.getJSONObject(n);
            date = innerObject.getString(TAG_DATE);
            status = innerObject.getString(TAG_STATUS);
            homeTeamName = innerObject.getString(TAG_HOME_TEAM_NAME);
            awayTeamName = innerObject.getString(TAG_AWAY_TEAM_NAME);

            JSONObject resultObject = innerObject.getJSONObject(TAG_RESULT);
            goalsHomeTeam = resultObject.getString(TAG_GOALS_HOME_TEAM);
            goalsAwayTeam = resultObject.getString(TAG_GOALS_AWAY_TEAM);

            Log.d(TAG, date +"#"+ status +"#"+ homeTeamName +"#"+ awayTeamName +"#"+ goalsAwayTeam +"#"+ goalsAwayTeam);
            storeInFixtureTable(date, status, homeTeamName, awayTeamName, goalsHomeTeam, goalsAwayTeam, leagueNo);
        }
    }

    private String connectToServer(String urlConnection) throws Exception{
        Request request = new Request.Builder().url(urlConnection).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();
        Log.d("JSON STRING_DATA", jsonData);
        return jsonData;
    }

    private void storeInLeagueTable(String position, String teamName, String points, String goals, String goalsAgainst, String goalsDifference, String gamesPlayed, int leagueNo){
            Log.d("INSERT: ","starting");
            ContentValues values = new ContentValues();
            values.put(SoccerContract.LeagueTable.TAG_TEAM_NAME, teamName);
            values.put(SoccerContract.LeagueTable.TAG_POSITION,position);
            values.put(SoccerContract.LeagueTable.TAG_POINTS, points);
            values.put(SoccerContract.LeagueTable.TAG_GOALS, goals);
            values.put(SoccerContract.LeagueTable.TAG_GOALS_AGAINST,goalsAgainst);
            values.put(SoccerContract.LeagueTable.TAG_GOALS_DIFFERENCE, goalsDifference);
            values.put(SoccerContract.LeagueTable.TAG_GAMES_PLAYED, gamesPlayed);
            values.put(SoccerContract.FixturesTable.TAG_LEAGUE_NO, String.valueOf(leagueNo));
            Uri uri = getContext().getContentResolver().insert(SoccerContract.LeagueTable.CONTENT_URI, values);
            Log.d("INSERT: ", "inserting"+uri.toString());

    }

    private void storeInFixtureTable(String date, String status, String homeTeamName, String awayTeamName, String goalsHomeTeam, String goalsAwayTeam, int leagueNo){
            Log.d("INSERT: ", "starting");
            ContentValues values = new ContentValues();
            values.put(SoccerContract.FixturesTable.TAG_HOME_TEAM_NAME, homeTeamName);
            values.put(SoccerContract.FixturesTable.TAG_AWAY_TEAM_NAME, awayTeamName);
            values.put(SoccerContract.FixturesTable.TAG_DATE, date);
            values.put(SoccerContract.FixturesTable.TAG_GOALS_HOME_TEAM, goalsHomeTeam);
            values.put(SoccerContract.FixturesTable.TAG_GOALS_AWAY_TEAM, goalsAwayTeam);
            values.put(SoccerContract.FixturesTable.TAG_STATUS, status);
            values.put(SoccerContract.FixturesTable.TAG_LEAGUE_NO, String.valueOf(leagueNo));
            Uri uri = getContext().getContentResolver().insert(SoccerContract.FixturesTable.CONTENT_URI, values);
            Log.d("INSERT: ", "inserting"+uri.toString());
    }


    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     *
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if (null == accountManager.getPassword(newAccount)) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        SyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }

    public void notifyWeather() {
        Log.d(TAG, "NOTIFICATION STARTED");
        Context context = getContext();
        //checking the last update and notify if it' the first of the day
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String lastNotificationKey = context.getString(R.string.pref_last_notification);
        long lastSync = prefs.getLong(lastNotificationKey, 0);

        if (true) {
//        if (System.currentTimeMillis() - lastSync >= DAY_IN_MILLIS) {

            Cursor cursor = context.getContentResolver().query(SoccerContract.FixturesTable.CONTENT_URI, null, null, null, null);
            if (cursor.moveToFirst()) {
                while(cursor.moveToNext() && cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_STATUS)).equals("Finished")){
                    Log.d(TAG, "LOOPING");
                }
                String homeTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_HOME_TEAM_NAME));
                String awayTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_AWAY_TEAM_NAME));
                String goalsHomeTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_GOALS_HOME_TEAM));
                String goalsAwayTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_GOALS_AWAY_TEAM));
                String date = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_DATE));
                String status = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_STATUS));
                Log.d("CURSOR_bindview", homeTeam);
                Log.d("CURSOR_bindview", awayTeam);
                int iconId = R.mipmap.ic_launcher;
                String title = context.getString(R.string.app_name);

                String contentText = homeTeam + " VS "+ awayTeam + " at "+ date + status;

                // NotificationCompatBuilder is a very convenient way to build backward-compatible
                // notifications.  Just throw in some data.
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(iconId)
                                .setContentTitle(title)
                                .setContentText(contentText);

                // Make something interesting happen when the user clicks on the notification.
                // In this case, opening the app is sufficient.
                Intent resultIntent = new Intent(context, MainActivity.class);

                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                // WEATHER_NOTIFICATION_ID allows you to update the notification later on.
                mNotificationManager.notify(SOCCER_NOTIFICATION_ID, mBuilder.build());


                //refreshing last sync
                SharedPreferences.Editor editor = prefs.edit();
                editor.putLong(lastNotificationKey, System.currentTimeMillis());
                editor.commit();
            }
        }
    }
}

