package app.com.io.codephillip.soccerdashboard.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by codephillip on 10/8/15.
 */
public class Database extends SQLiteOpenHelper {

    private static final String TAG = "SQL";

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
        String CREATE_LEAGUE_TABLE = "CREATE TABLE" + LEAGUETABLE + "(" + TAG_LEAGUE_TABLE_KEY_ID
                + " INTEGER PRIMARY KEY," + TAG_STANDING + " TINYTEXT," + TAG_POSITION + " TINYTEXT,"+
                TAG_TEAM_NAME + " TINYTEXT,"+ TAG_POINTS + " TINYTEXT,"+ TAG_GOALS + " TINYTEXT,"+
                TAG_GOALS_AGAINST + " TINYTEXT,"+ TAG_GOALS_DIFFERENCE + " TINYTEXT"+ ")";
        sqLiteDatabase.execSQL(CREATE_LEAGUE_TABLE);
        Log.d(TAG, "tables created successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addLeagueTableData(){

    }
}
