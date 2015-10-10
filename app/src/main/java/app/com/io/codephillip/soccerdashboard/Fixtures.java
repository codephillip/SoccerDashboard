package app.com.io.codephillip.soccerdashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    int n = 0;
    private String TAG = Fixtures.class.getSimpleName();

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fixtures_layout, container, false);

        fixtureList = (ListView) view.findViewById(R.id.lv_fixtures);

//        fixtureArray = new String[]{
//                "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
//        };

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
//                android.R.id.text1, fixtureArray
//                );
        fetchFromDatabase();
        FixturesListAdapter adapter = new FixturesListAdapter(getActivity(), null, homeTeamName, awayTeamName, score, null);
        fixtureList.setAdapter(adapter);
		return view;
	}

    private void fetchFromDatabase() {
        database = new Database(getActivity());
//        database.addLeagueTableData(new LeagueTable("4", "6", "4", "4", "6", "4", "7"));

//        //database returns a list of objects which will be stored in leagueTablelist
        final List<FixturesTable> fixturesTableList = database.getFixuturesTableData();
//        homeTeamName = awayTeamName = score new String[fixturesTableList.size()];
        homeTeamName = new String[fixturesTableList.size()];
        awayTeamName = new String[fixturesTableList.size()];
        score = new String[fixturesTableList.size()];
        try{
            //fetch the objects from the list and store them in cn(LeagueTable object variable)
            for(FixturesTable cn: fixturesTableList){
                //then call the getter methods to get the data
                String logString = cn.getTagHomeTeamName() + "##" + cn.getTagHomeTeamName()+ "##" + cn.getTagGoalsAwayTeam()+ cn.getTagGoalsHomeTeam();
                Log.d(TAG + " #########", logString);
//                Toast.makeText(getActivity(), logString, Toast.LENGTH_SHORT).show();
                //tableArrayList.add(cn.getTeamName()+" "+cn.getId());

                homeTeamName[n] = cn.getTagHomeTeamName();
                awayTeamName[n] = cn.getTagAwayTeamName();
                score[n] = cn.getTagGoalsHomeTeam() + " - " + cn.getTagGoalsAwayTeam();
                //date[n] = cn.getTagDate();
                n++;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
