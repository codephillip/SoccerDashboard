package app.com.io.codephillip.soccerdashboard.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codephillip on 10/8/15.
 */
public class Database extends SQLiteOpenHelper {

    private static final String TAG = "###SQL###";

    private static final String DATABASE_NAME = "soccerdb";
    private static final int DATABASE_VERSION = 1;

    //tables
    private static final  String LEAGUETABLE = "LeagueTable";

    //columns
    //leagueTable columns
    private static final  String TAG_LEAGUE_TABLE_KEY_ID = "id";
    private static final  String TAG_STANDING = "standing";
    private static final  String TAG_POSITION = "position";
    private static final  String TAG_TEAM_NAME = "teamName";
    private static final  String TAG_POINTS = "points";
    private static final  String TAG_GOALS = "goals";
    private static final  String TAG_GOALS_AGAINST = "goalsAgainst";
    private static final  String TAG_GOALS_DIFFERENCE = "goalsDifference";



    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LEAGUE_TABLE = "CREATE TABLE " + LEAGUETABLE + "(" + TAG_LEAGUE_TABLE_KEY_ID
                + " INTEGER PRIMARY KEY," + TAG_STANDING + " TINYTEXT," + TAG_POSITION + " TINYTEXT,"+
                TAG_TEAM_NAME + " TINYTEXT,"+ TAG_POINTS + " TINYTEXT,"+ TAG_GOALS + " TINYTEXT,"+
                TAG_GOALS_AGAINST + " TINYTEXT,"+ TAG_GOALS_DIFFERENCE + " TINYTEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_LEAGUE_TABLE);
        Log.d(TAG, "tables created successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addLeagueTableData(LeagueTable leagueTable){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //put() takes the "name" to be used in storage and the "data" object
        values.put(TAG_STANDING, leagueTable.getStandings());
        values.put(TAG_POSITION, leagueTable.getStandings());
        values.put(TAG_TEAM_NAME, leagueTable.getTeamName());
        values.put(TAG_POINTS, leagueTable.getPoints());
        values.put(TAG_GOALS, leagueTable.getGoals());
        values.put(TAG_GOALS_AGAINST, leagueTable.getGoalsAgainst());
        values.put(TAG_GOALS_DIFFERENCE, leagueTable.getGoalsDifference());

        db.insert(LEAGUETABLE, null, values);
        Log.d(TAG, "Inserting values into League table");
        db.close();
    }

    public List<LeagueTable> getTableData(){
        List<LeagueTable> leagueTableList = new ArrayList<LeagueTable>();
        String query = "SELECT * FROM "+ LEAGUETABLE;
        //opening connection to the database for reading only
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //cursor that point to the individual rows and fetches the data from the table
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        //looping through table fetching data then moves to the next cursor(row)
        while (cursor.moveToNext()){
            LeagueTable leagueTable = new LeagueTable();
            leagueTable.setId(Integer.parseInt(cursor.getString(0)));
            leagueTable.setStandings(cursor.getString(1));
            leagueTable.setPosition(cursor.getString(2));
            leagueTable.setTeamName(cursor.getString(3));
            leagueTable.setPoints(cursor.getString(4));
            leagueTable.setGoals(cursor.getString(5));
            leagueTable.setGoalsAgainst(cursor.getString(6));
            leagueTable.setGoalsDifference(cursor.getString(7));
            //adding the object to the arrayList
            leagueTableList.add(leagueTable);
        }
        return leagueTableList;
    }
}
