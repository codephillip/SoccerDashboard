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
import app.com.io.codephillip.soccerdashboard.database.LeagueTable;


public class ApiIntentService extends IntentService {
    private final String BarclaysPLUrl = "http://api.football-data.org/alpha/soccerseasons/398/leagueTable";
    private Database database = null;

    public ApiIntentService() {
        super("ApiIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            int k;
            for (k=0; k<1 ; k++){
                if (k == 0){
                    getTableDataJson(connectToServer(BarclaysPLUrl));
                } else if (k == 1){
                    //getBbnFromJson(connectToServer("http://192.168.56.1/lynda-php/apibbn.php"));
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
        final String TAG_GOALS_DIFFERENCE = "goalsDifference";

        String position;
        String teamName;
        String points;
        String goals;
        String results;
        String goalsAgainst;
        String goalsDifference;
        String standings;

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray(TAG_STANDING);

        int i;
        int jsonLength = jsonArray.length();
        //tableArray = new String[jsonLength];

        for (i=0; i<jsonLength; i++){
            JSONObject innerObject = jsonArray.getJSONObject(i);

            position = innerObject.getString(TAG_POSITION);
            teamName = innerObject.getString(TAG_TEAM_NAME);
            points = innerObject.getString(TAG_POINTS);
            goals = innerObject.getString(TAG_GOALS);
            goalsAgainst = innerObject.getString(TAG_GOALS_AGAINST);
            goalsDifference = innerObject.getString(TAG_GOALS_DIFFERENCE);
            standings = innerObject.getString(TAG_STANDING);

            results = teamName+" "+position+" "+points+" "+goals;
            Log.d("JSONRESULT", results);

            storeInLeagueTable(position, teamName, points, goals, goalsAgainst, goalsDifference, standings);

            //tableArray[i] = results;
        }
//        adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, tableArray
//        );
//        adapter = new TableListAdapter(getActivity(), tableArray, imageUrls);

//        tableList.setAdapter(adapter);
        return null;
    }

    private String connectToServer(String urlConnection) throws Exception{
        Request request = new Request.Builder().url(urlConnection).build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();
        Log.d("JSON STRING_DATA", jsonData);
        return jsonData;
    }

    private void storeInLeagueTable(String position, String teamName, String points, String goals, String standings, String goalsAgainst, String goalsDifference){
        database = new Database(this);
        database.addLeagueTableData(new LeagueTable(standings, position, teamName, points, goals, goalsAgainst, goalsDifference));
    }
}
