package app.com.io.codephillip.soccerdashboard.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by codephillip on 10/8/15.
 */
public class SoccerDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "###SQL###";

    private static final String DATABASE_NAME = "soccerdb";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_LEAGUE_TABLE = "CREATE TABLE " + SoccerContract.LeagueTable.LEAGUETABLE + "(" + SoccerContract.LeagueTable._ID
            + " INTEGER PRIMARY KEY," + SoccerContract.LeagueTable.TAG_POSITION + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_TEAM_NAME + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_POINTS + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_GOALS + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_LEAGUE_NO + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_GAMES_PLAYED + " TINYTENT,"+
            SoccerContract.LeagueTable.TAG_GOALS_AGAINST + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_GOALS_DIFFERENCE + " TINYTEXT"+ ")";
    private static final String CREATE_FIXUTERES_TABLE = "CREATE TABLE " + SoccerContract.FixturesTable.FIXTURES_TABLE + "(" + SoccerContract.FixturesTable._ID
            +" INTEGER PRIMARY KEY," + SoccerContract.FixturesTable.TAG_DATE +" TINYTEXT,"+
            SoccerContract.FixturesTable.TAG_STATUS +" TINYTEXT,"+
            SoccerContract.FixturesTable.TAG_LEAGUE_NO + " TINYTEXT,"+
            SoccerContract.FixturesTable.TAG_HOME_TEAM_NAME +" TINYTEXT,"+
            SoccerContract.FixturesTable.TAG_AWAY_TEAM_NAME +" TINYTEXT,"+
            SoccerContract.FixturesTable.TAG_GOALS_HOME_TEAM
            +" TINYTEXT,"+ SoccerContract.FixturesTable.TAG_GOALS_AWAY_TEAM + ")";


    public SoccerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_LEAGUE_TABLE);
        sqLiteDatabase.execSQL(CREATE_FIXUTERES_TABLE);
        Log.d(TAG, "tables created successfully");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
