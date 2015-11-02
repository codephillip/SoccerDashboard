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
            SoccerContract.LeagueTable.TAG_GOALS_AGAINST + " TINYTEXT,"+
            SoccerContract.LeagueTable.TAG_GOALS_DIFFERENCE + " TINYTEXT"+ ")";
    private static final String CREATE_FIXUTERES_TABLE = "CREATE TABLE " + SoccerContract.FixturesTable.FIXTURES_TABLE + "(" + SoccerContract.FixturesTable._ID
            +" INTEGER PRIMARY KEY," + SoccerContract.FixturesTable.TAG_DATE +" TINYTEXT,"+
            SoccerContract.FixturesTable.TAG_STATUS +" TINYTEXT,"+
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

//    public void addLeagueTableData(LeagueTable leagueTable){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        //put() takes the "name" to be used in storage and the "data" object
//        values.put(TAG_POSITION, leagueTable.getPosition());
//        values.put(TAG_TEAM_NAME, leagueTable.getTeamName());
//        values.put(TAG_POINTS, leagueTable.getPoints());
//        values.put(TAG_GOALS, leagueTable.getGoals());
//        values.put(TAG_GOALS_AGAINST, leagueTable.getGoalsAgainst());
//        values.put(TAG_GOALS_DIFFERENCE, leagueTable.getGoalsDifference());
//        db.insert(LEAGUETABLE, null, values);
//        Log.d(TAG, "Inserting values into League table");
//        db.close();
//    }
//
//    public List<LeagueTable> getLeagueTableData(){
//        List<LeagueTable> leagueTableList = new ArrayList<LeagueTable>();
//        String query = "SELECT * FROM "+ LEAGUETABLE;
//        //opening connection to the database for reading only
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        //cursor that point to the individual rows and fetches the data from the table
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//        //looping through table fetching data then moves to the next cursor(row)
//
//        if (cursor.moveToFirst()) {
//            do {
//                LeagueTable leagueTable = new LeagueTable();
//                leagueTable.setId(Integer.parseInt(cursor.getString(0)));
//                leagueTable.setPosition(cursor.getString(1));
//                leagueTable.setTeamName(cursor.getString(2));
//                leagueTable.setPoints(cursor.getString(3));
//                leagueTable.setGoals(cursor.getString(4));
//                leagueTable.setGoalsAgainst(cursor.getString(5));
//                leagueTable.setGoalsDifference(cursor.getString(6));
//                //adding the object to the arrayList
//                leagueTableList.add(leagueTable);
//            } while (cursor.moveToNext());
//        }
////        while (cursor.moveToNext()){
////            leagueTable.setId(Integer.parseInt(cursor.getString(0)));
////            leagueTable.setPosition(cursor.getString(1));
////            leagueTable.setTeamName(cursor.getString(2));
////            leagueTable.setPoints(cursor.getString(3));
////            leagueTable.setGoals(cursor.getString(4));
////            leagueTable.setGoalsAgainst(cursor.getString(5));
////            leagueTable.setGoalsDifference(cursor.getString(6));
////            //adding the object to the arrayList
////            leagueTableList.add(leagueTable);
////        }
//        return leagueTableList;
//    }
//
//    public void addFixtures(FixturesTable fixturesTable){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(TAG_DATE, fixturesTable.getTagDate());
//        values.put(TAG_STATUS, fixturesTable.getTagStatus());
//        values.put(TAG_HOME_TEAM_NAME, fixturesTable.getTagHomeTeamName());
//        values.put(TAG_AWAY_TEAM_NAME, fixturesTable.getTagAwayTeamName());
//        values.put(TAG_GOALS_HOME_TEAM, fixturesTable.getTagGoalsHomeTeam());
//        values.put(TAG_GOALS_AWAY_TEAM, fixturesTable.getTagGoalsAwayTeam());
//        db.insert(FIXTURES_TABLE, null, values);
//        Log.d(TAG, "Inserting values into Fixutures table");
//        db.close();
//    }
//
//    public List<FixturesTable> getFixuturesTableData(){
//        ArrayList<FixturesTable> fixturesTableArrayList = new ArrayList<FixturesTable>();
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String query = "SELECT * FROM " + FIXTURES_TABLE;
//        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
//        while (cursor.moveToNext()){
//            FixturesTable fixturesTable = new FixturesTable();
//            fixturesTable.setTAG_FIXTURES_TABLE_KEY_ID(Integer.parseInt(cursor.getString(0)));
//            fixturesTable.setTAG_DATE(cursor.getString(1));
//            fixturesTable.setTAG_STATUS(cursor.getString(2));
//            fixturesTable.setTAG_HOME_TEAM_NAME(cursor.getString(3));
//            fixturesTable.setTAG_AWAY_TEAM_NAME(cursor.getString(4));
//            fixturesTable.setTAG_GOALS_HOME_TEAM(cursor.getString(5));
//            fixturesTable.setTAG_GOALS_AWAY_TEAM(cursor.getString(6));
//            fixturesTableArrayList.add(fixturesTable);
//        }
//        return fixturesTableArrayList;
//    }
//
//    public void deleteLeagueTable(LeagueTable leagueTable) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(LEAGUETABLE, TAG_LEAGUE_TABLE_KEY_ID + " >= ?",
//                new String[] { String.valueOf(leagueTable.getId()) });
//        Log.d("SQL", "deleting content from LeagueTable");
//        db.close();
//    }
//
//    public void deleteFixturesTable(FixturesTable fixturesTable){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(FIXTURES_TABLE, TAG_FIXTURES_TABLE_KEY_ID + " >= ?",
//                new String[] { String.valueOf(fixturesTable.getTagFixturesTableKeyId()) });
//        Log.d("SQL", "deleting content from FixturesTable");
//        db.close();
//    }
}
