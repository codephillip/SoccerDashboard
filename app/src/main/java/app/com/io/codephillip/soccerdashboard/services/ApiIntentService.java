package app.com.io.codephillip.soccerdashboard.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.com.io.codephillip.soccerdashboard.database.Database;
import app.com.io.codephillip.soccerdashboard.database.FixturesTable;
import app.com.io.codephillip.soccerdashboard.database.LeagueTable;


public class ApiIntentService extends IntentService {
    private final String BarclaysPLTableUrl = "http://api.football-data.org/alpha/soccerseasons/398/leagueTable";
    private final String BarclaysPLFixturesUrl = "http://api.football-data.org/alpha/soccerseasons/398/fixtures";
    private Database database = null;
    private final String TAG = ApiIntentService.class.getSimpleName();

    public ApiIntentService() {
        super("ApiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            int k;
            for (k=0; k<2 ; k++){
                if (k == 0){
                    getTableDataJson(connectToServer(BarclaysPLTableUrl));
                } else if (k == 1) {
                    getFixtureDataJson(connectToServer(BarclaysPLFixturesUrl));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("URL BUG", e.toString());
        }
    }

    private Void getTableDataJson(String jsonData) throws JSONException {
        final String TAG_STANDING = "standing";
        final String TAG_POSITION = "position";
        final String TAG_TEAM_NAME = "teamName";
        final String TAG_POINTS = "points";
        final String TAG_GOALS = "goals";
        final String TAG_GOALS_AGAINST = "goalsAgainst";
        final String TAG_GOALS_DIFFERENCE = "goalDifference";

        String position;
        String teamName;
        String points;
        String goals;
        String values;
        String goalsAgainst;
        String goalDifference;
        String standings;

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

            values = teamName+" "+position+" "+points+" "+goals;
            Log.d("JSONRESULT", values);

            storeInLeagueTable(position, teamName, points, goals, goalsAgainst, goalDifference);
        }
        return null;
    }

    private void getFixtureDataJson(String jsonData) throws JSONException{
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
        String values;

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
            storeInFixtureTable(date, status, homeTeamName, awayTeamName, goalsHomeTeam, goalsAwayTeam);
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

    private void storeInLeagueTable(String position, String teamName, String points, String goals, String goalsAgainst, String goalsDifference){
        database = new Database(this);
        database.addLeagueTableData(new LeagueTable( position, teamName, points, goals, goalsAgainst, goalsDifference));
    }

    private void storeInFixtureTable(String date, String status, String homeTeamName, String awayTeamName, String goalsHomeTeam, String goalsAwayTeam){
        database = new Database(this);
        database.addFixtures(new FixturesTable(date, status, homeTeamName, awayTeamName, goalsHomeTeam, goalsAwayTeam));
    }
}
