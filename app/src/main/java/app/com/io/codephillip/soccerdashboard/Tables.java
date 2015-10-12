package app.com.io.codephillip.soccerdashboard;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.com.io.codephillip.soccerdashboard.database.Database;
import app.com.io.codephillip.soccerdashboard.database.LeagueTable;

public class Tables extends Fragment {
    private String[] tableArray;
    private ListView tableList;
    private IntentFilter intentFilter;
    //private FetchTableTask fetchTableTask;
//    private final String imageUrls[] = {
//            "52919.png","2605445.png","2601593.png","75027.png","2603039.png","2606733.png"
//    };
    private final String imageUrls[] = {
            "a/arsenal",
            "a/aston-villa",
            "b/bournemouth",
            "c/chelsea",
            "c/crystal-palace",
            "e/everton",
            "l/leicester",
            "l/liverpool",
            "m/man-city",
            "m/man-utd",
            "n/newcastle",
            "n/norwich",
            "s/southampton",
            "s/stoke",
            "s/sunderland",
            "s/swansea",
            "s/spurs",
            "w/watford",
            "w/west-brom",
            "w/west-ham",
    };
//    private final Database database = new Database(getActivity());
    private Database database = null;
    private final String TAG = Tables.class.getSimpleName();
    private TableListAdapter adapter;
    private final ArrayList<String> tableArrayList = new ArrayList<String>();
    private int n = 0;

    public void onPause() {
        super.onPause();
        database.close();
    }

    @Override
    public void onResume() {
        super.onResume();
//        intentFilter = new IntentFilter();
//        intentFilter.addAction("FINISHED SERVICE");
//        getActivity().registerReceiver(intentReceiver, intentFilter);
    }

    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tables_layout, container, false);
        Log.d("TABLES###$$$", "TABLE FRAGMENT CREATED");

        tableList = (ListView) view.findViewById(R.id.lv_tables);
//        fetchTableTask = new FetchTableTask();
//        fetchTableTask.execute("http://api.football-data.org/alpha/soccerseasons/398/leagueTable");

//        tableArray = new String[]{
//                "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
//        };
        database = new Database(getActivity());
//        database.addLeagueTableData(new LeagueTable("4", "6", "4", "4", "6", "4", "7"));

//
        do{
//            if (n >= 1){
//                Thread timer = new Thread(){
//                    public void run(){
//                        try {
//                            Log.d("THREAD","SLEEPING");
//                            sleep(10000);
//                        }catch (InterruptedException e){
//                            e.printStackTrace();
//                            Log.d("THREAD", "INTERRUPTED");
//                        }
//                    }
//                };
//                timer.start();
//                Log.d("THREAD", "AWAKE");
//            }

            Log.d("SQL###$$$", "league table data from the database");
            n++;


            //database returns a list of objects which will be stored in leagueTablelist
            final List<LeagueTable> leagueTableList = database.getLeagueTableData();
            try{
                //fetch the objects from the list and store them in cn(LeagueTable object variable)
                for(LeagueTable cn: leagueTableList){
                    //then call the getter methods to get the data
                    String logString = cn.getGoals() + "##" + cn.getPoints()+ "##" + cn.getTeamName()+ cn.getId();
                    Log.d(TAG, logString);
                    tableArrayList.add(cn.getTeamName());
                    Log.d("ARRAYLISTCONTENT###$$$", "ADDING CONTENT TO ARRAY LIST" + cn.getTeamName());
                    if(tableArrayList.get(0) != null)
                        n = 4;
                }
            }catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }while (n < 10);

        try{
            Log.d("ARRAYLISTCONTENT###","POSITION ZERO, "+tableArrayList.get(0));

            for (String k: tableArrayList){
                Log.d("ARRAYLISTCONTENT", k);
            }

            tableArray = new String[tableArrayList.size()];
            tableArray = tableArrayList.toArray(tableArray);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            n = 3;
        }

        if (n == 4){
            Log.d("ADAPTER", "LOADING ADAPTER");
            adapter = new TableListAdapter(getActivity(), tableArray, imageUrls);
//        adapter = new TableListAdapter(getActivity(), tableArray, null);
            tableList.setAdapter(adapter);
        }else {
            Log.d("ADAPTER", "CANT LOAD THE ADAPTER AND LISTVIEW");
        }
		return view;
	}

//    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.d("BROADCAST###", "BROADCAST RECEIVED");
////            Toast.makeText(getActivity(), "Received broadcast from service",
////            Toast.LENGTH_LONG).show();
//        }
//    };

//    class FetchTableTask extends AsyncTask<String, Void, Void>{
//
//        @Override
//        protected Void doInBackground(String... urls) {
//            try {
//                Request request = new Request.Builder().url(urls[0]).build();
//                OkHttpClient client = new OkHttpClient();
//                Response response = client.newCall(request).execute();
//
//                String jsonData = response.body().string();
//                Log.d("JSON STRING_DATA", jsonData);
//                return getTableDataJson(jsonData);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.d("URL BUG", e.toString());
//                return null;
//            }
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            //super.onPostExecute(aVoid);
//
//
//            tableList.setAdapter(adapter);
//        }
//    }
//
//    private Void getTableDataJson(String jsonData) throws JSONException{
//        final String TAG_STANDING = "standing";
//        final String TAG_POSITION = "position";
//        final String TAG_TEAM_NAME = "teamName";
//        final String TAG_POINTS = "points";
//        final String TAG_GOALS = "goals";
//
//        String position;
//        String teamName;
//        String points;
//        String goals;
//        String results;
//
//        JSONObject jsonObject = new JSONObject(jsonData);
//        JSONArray jsonArray = jsonObject.getJSONArray(TAG_STANDING);
//
//        int i;
//        int jsonLength = jsonArray.length();
//        tableArray = new String[jsonLength];
//
//        for (i=0; i<jsonLength; i++){
//            JSONObject innerObject = jsonArray.getJSONObject(i);
//
//            position = innerObject.getString(TAG_POSITION);
//            teamName = innerObject.getString(TAG_TEAM_NAME);
//            points = innerObject.getString(TAG_POINTS);
//            goals = innerObject.getString(TAG_GOALS);
//
//            results = teamName+" "+position+" "+points+" "+goals;
//            Log.d("JSONRESULT", results);
//
//            tableArray[i] = results;
//        }
////        adapter = new ArrayAdapter<String>(getActivity(),
////                android.R.layout.simple_list_item_1, android.R.id.text1, tableArray
////        );
//        adapter = new TableListAdapter(getActivity(), tableArray, imageUrls);
//
////        tableList.setAdapter(adapter);
//        return null;
//    }
}
