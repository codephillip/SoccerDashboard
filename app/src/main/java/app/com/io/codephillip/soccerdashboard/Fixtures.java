package app.com.io.codephillip.soccerdashboard;

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
import app.com.io.codephillip.soccerdashboard.database.FixturesTable;

public class Fixtures extends Fragment {
    private ListView fixtureList;
    private String[] fixtureArray;
    private String[] awayTeamName;
    private String[] score;
    private String[] imgId;
    private String[] date;
    private String[] homeTeamName;
    private Database database = null;
    private String TAG = Fixtures.class.getSimpleName();
    private final ArrayList<String> homeTeamNameList = new ArrayList<String>();
    private final ArrayList<String> awayTeamNameList = new ArrayList<String>();
    private final ArrayList<String> scoreList = new ArrayList<String>();
    private final ArrayList<String> dateList = new ArrayList<String>();

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fixtures_layout, container, false);

        fixtureList = (ListView) view.findViewById(R.id.lv_fixtures);

        fetchFromDatabase();
//        FixturesListAdapter adapter = new FixturesListAdapter(getActivity(), null, homeTeamNameList, awayTeamNameList, scoreList, null);
        FixturesListAdapter adapter = new FixturesListAdapter(getActivity(), null, homeTeamName, awayTeamName, score, null);
//        FixturesListAdapter adapter = new FixturesListAdapter(getActivity(), null, homeTeamName, null, null, null);
        fixtureList.setAdapter(adapter);
		return view;
	}

    private void fetchFromDatabase() {
        database = new Database(getActivity());
//        database.addLeagueTableData(new LeagueTable("4", "6", "4", "4", "6", "4", "7"));

//        //database returns a list of objects which will be stored in leagueTablelist
        final List<FixturesTable> fixturesTableList = database.getFixuturesTableData();
//        homeTeamName = awayTeamName = score new String[fixturesTableList.size()];

        try{
            //fetch the objects from the list and store them in cn(LeagueTable object variable)
            for(FixturesTable cn: fixturesTableList){
                //then call the getter methods to get the data
                String logString = cn.getTagHomeTeamName() + "##" + cn.getTagAwayTeamName()+ "##" + cn.getTagGoalsAwayTeam()+ cn.getTagGoalsHomeTeam();
                Log.d(TAG + " #########", logString);
                homeTeamNameList.add(cn.getTagHomeTeamName());
                awayTeamNameList.add(cn.getTagAwayTeamName());
                scoreList.add(cn.getTagGoalsHomeTeam() + " - " + cn.getTagGoalsAwayTeam());
                dateList.add(cn.getTagDate());

//                homeTeamName[n] = cn.getTagHomeTeamName();
//                awayTeamName[n] = cn.getTagAwayTeamName();
            }
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        //converting ArrayList to Array
        homeTeamName = new String[homeTeamNameList.size()];
        awayTeamName = new String[homeTeamNameList.size()];
        score = new String[homeTeamNameList.size()];
        date = new String[homeTeamNameList.size()];
        homeTeamName = homeTeamNameList.toArray(homeTeamName);
        awayTeamName = awayTeamNameList.toArray(awayTeamName);
        score = scoreList.toArray(score);
        date = dateList.toArray(date);
    }

    @Override
    public void onPause() {
        super.onPause();
        database.close();
    }

    @Override
    public void onResume() {
        super.onResume();
        //fetchFromDatabase();
    }
}
