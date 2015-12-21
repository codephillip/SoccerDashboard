package app.com.io.codephillip.soccerdashboard;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import app.com.io.codephillip.soccerdashboard.cursoradapter.TableCursorAdapter;
import app.com.io.codephillip.soccerdashboard.data.SoccerContract;
import app.com.io.codephillip.soccerdashboard.database.Database;

public class Tables extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private String[] tableArray;
    private String[] debugTableArray = new String[]{
            "Arsenal FC","Manchester United FC","Everton FC","Watford FC", "Norwich City FC", "Swansea City FC"
    };
//    private ListView tableList;
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
    private TableCursorAdapter cursorAdapter;
    SimpleCursorAdapter simpleCursorAdapter;
    private static final int LOADER_ID = 1;
    private Uri allContacts = Uri.parse("content://com.codephillip.intmain.colourcontentprovider/secondary");

    public void onPause() {
        super.onPause();
        database.close();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tables_layout, container, false);

        Log.d("LOADER", "ONCREATEVIEW_FRAGMENT");

//        getLoaderManager().initLoader(LOADER_ID, null, this);
//        tableList = (ListView) view.findViewById(R.id.lv_tables);
//        fetchTableTask = new FetchTableTask();
//        fetchTableTask.execute("http://api.football-data.org/alpha/soccerseasons/398/leagueTable");

//        tableArray = new String[]{
//                "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
//        };
//        database = new Database(getActivity());
////        database.addLeagueTableData(new LeagueTable("4", "6", "4", "4", "6", "4", "7"));
//
////        //database returns a list of objects which will be stored in leagueTablelist
//        final List<LeagueTable> leagueTableList = database.getLeagueTableData();
//        try{
//            //fetch the objects from the list and store them in cn(LeagueTable object variable)
//            for(LeagueTable cn: leagueTableList){
//                //then call the getter methods to get the data
//                String logString = cn.getGoals() + "##" + cn.getPoints()+ "##" + cn.getTeamName()+ cn.getId();
//                Log.d(TAG, logString);
//                tableArrayList.add(cn.getTeamName());
//            }
//        }catch (ArrayIndexOutOfBoundsException e){
//            e.printStackTrace();
//        }
//
//        for (String k: tableArrayList){
//            Log.d("ARRAYLISTCONTENT", k);
//        }
//
//        tableArray = new String[tableArrayList.size()];
//        tableArray = tableArrayList.toArray(tableArray);

//        adapter = new TableListAdapter(getActivity(), debugTableArray, imageUrls);
//        adapter = new TableListAdapter(getActivity(), tableArray, imageUrls);
//        CursorLoader cursorLoader = new CursorLoader(getContext(),allContacts,
//                null, null,null, null
//        );
//        Cursor cursor = cursorLoader.loadInBackground();
        cursorAdapter = new TableCursorAdapter(getContext(), null, 0);
        Log.d("LOADER","starting cursoradapter");

//        simpleCursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.tables_layout, null, new String[]{"teamName"}, new int[]{R.id.teamname}, 0);
//        tableList.setAdapter(cursorAdapter);
        ListView tableList = (ListView) view.findViewById(R.id.lv_tables);
        tableList.setAdapter(cursorAdapter);

		return view;
	}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("LOADER","ONCREATELOADER");
        return new CursorLoader(
                getContext(),
//                allContacts,
                SoccerContract.FixturesTable.CONTENT_URI,
                null, null,null, null
                );
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("LOADER", "ONLOADFINISHED");
        cursorAdapter.swapCursor(data);
//        simpleCursorAdapter.swapCursor(data);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
//        simpleCursorAdapter.swapCursor(null);
    }

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
