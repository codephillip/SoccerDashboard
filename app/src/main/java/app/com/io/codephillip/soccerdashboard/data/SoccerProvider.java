package app.com.io.codephillip.soccerdashboard.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by codephillip on 11/2/15.
 */
public class SoccerProvider extends ContentProvider {
    private UriMatcher sUriMatcher = buildUriMatcher();
    private final int FIXTURES = 100;
    private final int FIXTURES_WITH_TEAM = 101;
    private final int LEAGUE_TABLE = 200;
    private final int LEAGUE_TABLE_WITH_TEAM = 201;

    private SoccerDbHelper soccerDbHelper;

    private UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = SoccerContract.CONTENT_AUTHORITY;
        uriMatcher.addURI(authority, SoccerContract.PATH_FIXTURES, FIXTURES);
        uriMatcher.addURI(authority, SoccerContract.PATH_FIXTURES+"/*", FIXTURES_WITH_TEAM);
        uriMatcher.addURI(authority, SoccerContract.PATH_LEAGUE_TABLE, LEAGUE_TABLE);
        uriMatcher.addURI(authority, SoccerContract.PATH_LEAGUE_TABLE+"/*", LEAGUE_TABLE_WITH_TEAM);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        soccerDbHelper = new SoccerDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor = null;
        SQLiteDatabase db = soccerDbHelper.getReadableDatabase();
        switch (sUriMatcher.match(uri)){
            case FIXTURES:
                retCursor = db.query(
                        SoccerContract.FixturesTable.CONTENT_TYPE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                break;

            case FIXTURES_WITH_TEAM:
                retCursor = db.query(
                        SoccerContract.FixturesTable.CONTENT_ITEM_TYPE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case LEAGUE_TABLE:
                retCursor = db.query(
                        SoccerContract.LeagueTable.CONTENT_TYPE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case LEAGUE_TABLE_WITH_TEAM:
                retCursor = db.query(
                        SoccerContract.LeagueTable.CONTENT_ITEM_TYPE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

                default:
                    throw new UnsupportedOperationException("Unknown Uri: "+uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        //check for uri match and if found return the corresponding content number
        switch (sUriMatcher.match(uri)){
            case FIXTURES:
                return SoccerContract.FixturesTable.CONTENT_TYPE;
            case FIXTURES_WITH_TEAM:
                return SoccerContract.FixturesTable.CONTENT_ITEM_TYPE;
            case LEAGUE_TABLE:
                return SoccerContract.LeagueTable.CONTENT_TYPE;
            case LEAGUE_TABLE_WITH_TEAM:
                return SoccerContract.LeagueTable.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("unknown uri: "+ uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri returnUri;
        SQLiteDatabase db = soccerDbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case FIXTURES:{
                long id = db.insert(SoccerContract.FixturesTable.FIXTURES_TABLE, null, contentValues);
                if (id > 0){
                    returnUri = SoccerContract.FixturesTable.buildUri(id);
                } else{
                    throw new android.database.SQLException("failed to insert into table: "+uri);
                }
                break;
            }

            case LEAGUE_TABLE:{
                long id = db.insert(SoccerContract.LeagueTable.LEAGUETABLE, null, contentValues);
                if (id > 0){
                    returnUri = SoccerContract.LeagueTable.buildUri(id);
                } else{
                    throw new android.database.SQLException("failed to insert into table: "+uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Wrond uri: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
