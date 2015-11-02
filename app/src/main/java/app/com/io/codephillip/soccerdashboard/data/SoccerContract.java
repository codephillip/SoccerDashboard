package app.com.io.codephillip.soccerdashboard.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by codephillip on 11/2/15.
 */
public class SoccerContract {
    public static final String CONTENT_AUTHORITY = "app.com.io.codephillip.soccerdashboard";
    public static final Uri BASE_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_FIXTURES = "fixturesTable";
    public static final String PATH_LEAGUE_TABLE = "leagueTable";

    public static final class FixturesTable implements BaseColumns {
        public static final  String FIXTURES_TABLE = "fixturesTable";

        //fixtures columns
        public static final String TAG_DATE = "date";
        public static final String TAG_STATUS = "status";
        public static final String TAG_HOME_TEAM_NAME = "homeTeamName";
        public static final String TAG_AWAY_TEAM_NAME = "awayTeamName";
        public static final String TAG_GOALS_HOME_TEAM = "goalsHomeTeam";
        public static final String TAG_GOALS_AWAY_TEAM = "goalsAwayTeam";

        public static final Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_FIXTURES).build();

        //mime types
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FIXTURES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FIXTURES;

        public static Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static final class LeagueTable implements BaseColumns{
            public static final  String LEAGUETABLE = "leagueTable";

            //leaguetable columns
            public static final String TAG_POSITION = "position";
            public static final String TAG_TEAM_NAME = "teamName";
            public static final String TAG_POINTS = "points";
            public static final String TAG_GOALS = "goals";
            public static final String TAG_GOALS_AGAINST = "goalsAgainst";
            public static final String TAG_GOALS_DIFFERENCE = "goalsDifference";

            public static Uri CONTENT_URI = BASE_URI.buildUpon().appendPath(PATH_LEAGUE_TABLE).build();

            //mime type
            public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUE_TABLE;
            public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUE_TABLE;

            public static Uri buildUri(long id){
                return ContentUris.withAppendedId(CONTENT_URI, id);
            }
        }
    }
}
