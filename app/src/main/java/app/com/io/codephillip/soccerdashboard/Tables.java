package app.com.io.codephillip.soccerdashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import app.com.io.codephillip.soccerdashboard.database.Database;
import app.com.io.codephillip.soccerdashboard.database.LeagueTable;

public class Tables extends Fragment {
    private String[] tableArray;
    private ListView tableList;
    private FetchTableTask fetchTableTask;
//    ArrayAdapter <String>  adapter;
    private final String imageBaseUrl = "http://img.uefa.com/imgml/TP/teams/logos/50x50/";
    private final String imageUrls[] = {
            "52919.png","2605445.png","2601593.png","75027.png","2603039.png","2606733.png"
    };
//    private final Database database = new Database(getActivity());
    private Database database = null;
    private final ArrayList<String> al = new ArrayList<String>();
    private final String TAG = Tables.class.getSimpleName();
    private TableListAdapter adapter;
    private int n = 0;

    public void onPause() {
        super.onPause();
        database.close();
    }

    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tables_layout, container, false);

        tableList = (ListView) view.findViewById(R.id.lv_tables);
//        fetchTableTask = new FetchTableTask();
//        fetchTableTask.execute("http://api.football-data.org/alpha/soccerseasons/398/leagueTable");

        tableArray = new String[]{
                "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
        };
//        database = new Database(getActivity());
//        database.addLeagueTableData(new LeagueTable("4", "6", "4", "4", "6", "4", "7"));

        //database returns a list of objects which will be stored in leagueTablelist
        final List<LeagueTable> leagueTableList = database.getLeagueTableData();
        //fetch the objects from the list and store them in cn(LeagueTable object variable)
        for(LeagueTable cn: leagueTableList){
            //then call the getter methods to get the data
            String logString = cn.getGoals() + "##" + cn.getPoints()+ "##" + cn.getTeamName();
            Log.d(TAG, logString);
            Toast.makeText(getActivity(), logString, Toast.LENGTH_SHORT).show();
            tableArray[n] = logString;
            n++;
        }
        adapter = new TableListAdapter(getActivity(), tableArray, null);
        tableList.setAdapter(adapter);


//        ArrayAdapter <String>  adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, tableArray
//                );
//        tableList.setAdapter(adapter);


//        TableListAdapter adapter = new TableListAdapter(getActivity(), tableArray, null);

//        tableList.setAdapter(adapter);

		return view;
	}

    class FetchTableTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... urls) {
            try {
                Request request = new Request.Builder().url(urls[0]).build();
                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();

                String jsonData = response.body().string();
                Log.d("JSON STRING_DATA", jsonData);
                return getTableDataJson(jsonData);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("URL BUG", e.toString());
                return null;
            }
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //super.onPostExecute(aVoid);


            tableList.setAdapter(adapter);
        }
    }

    private Void getTableDataJson(String jsonData) throws JSONException{
        final String TAG_STANDING = "standing";
        final String TAG_POSITION = "position";
        final String TAG_TEAM_NAME = "teamName";
        final String TAG_POINTS = "points";
        final String TAG_GOALS = "goals";

        String position;
        String teamName;
        String points;
        String goals;
        String results;

        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray(TAG_STANDING);

        int i;
        int jsonLength = jsonArray.length();
        tableArray = new String[jsonLength];

        for (i=0; i<jsonLength; i++){
            JSONObject innerObject = jsonArray.getJSONObject(i);

            position = innerObject.getString(TAG_POSITION);
            teamName = innerObject.getString(TAG_TEAM_NAME);
            points = innerObject.getString(TAG_POINTS);
            goals = innerObject.getString(TAG_GOALS);

            results = teamName+" "+position+" "+points+" "+goals;
            Log.d("JSONRESULT", results);

            tableArray[i] = results;
        }
//        adapter = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, tableArray
//        );
        adapter = new TableListAdapter(getActivity(), tableArray, imageUrls);

//        tableList.setAdapter(adapter);
        return null;
    }
}
