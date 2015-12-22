package app.com.io.codephillip.soccerdashboard;

import android.database.Cursor;
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

import app.com.io.codephillip.soccerdashboard.cursoradapter.FixturesCursorAdapter;
import app.com.io.codephillip.soccerdashboard.data.SoccerContract;

public class Fixtures extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int LOADER_ID = 1;
    private FixturesCursorAdapter cursorAdapter;

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fixtures_layout, container, false);
        ListView fixtureList = (ListView) view.findViewById(R.id.lv_fixtures);
        cursorAdapter = new FixturesCursorAdapter(getContext(), null, 0);
        fixtureList.setAdapter(cursorAdapter);
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
                SoccerContract.FixturesTable.CONTENT_URI,
                null, null,null, null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("LOADER", "ONLOADFINISHED");
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}
