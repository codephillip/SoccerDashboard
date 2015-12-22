package app.com.io.codephillip.soccerdashboard;

import android.annotation.TargetApi;
import android.database.Cursor;
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

import app.com.io.codephillip.soccerdashboard.cursoradapter.TableCursorAdapter;
import app.com.io.codephillip.soccerdashboard.data.SoccerContract;
import app.com.io.codephillip.soccerdashboard.database.Database;

public class Tables extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private String[] debugTableArray = new String[]{
            "Arsenal FC", "Manchester United FC", "Everton FC", "Watford FC", "Norwich City FC", "Swansea City FC"
    };
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
    private Database database = null;
    private final String TAG = Tables.class.getSimpleName();
    private TableCursorAdapter cursorAdapter;
    private static final int LOADER_ID = 1;

    public void onPause() {
        super.onPause();
        database.close();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tables_layout, container, false);

        cursorAdapter = new TableCursorAdapter(getContext(), null, 0);
        Log.d("LOADER", "starting cursoradapter");
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
        Log.d("LOADER", "ONCREATELOADER");
        return new CursorLoader(
                getContext(),
                SoccerContract.LeagueTable.CONTENT_URI,
                null, null, null, null
        );
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("LOADER", "ONLOADFINISHED");
        cursorAdapter.swapCursor(data);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
