package app.com.io.codephillip.soccerdashboard.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.io.codephillip.soccerdashboard.R;

/**
 * Created by codephillip on 12/21/15.
 */
public class TableCursorAdapter extends CursorAdapter {

    private static final int VIEW_TYPE_COUNT = 2;
    private static final int VIEW_TYPE_TITLE = 0;
    private static final int VIEW_TYPE_BODY = 1;

    public TableCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int viewType = cursor.getPosition();
        int layoutId = -1;
        switch (viewType){
            case VIEW_TYPE_TITLE:
                layoutId = R.layout.table_row_title;
                break;
            case VIEW_TYPE_BODY:
                layoutId = R.layout.league_table_row;
                break;
            default:
                throw new UnsupportedOperationException("Failed to load Layout");
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0)? VIEW_TYPE_TITLE : VIEW_TYPE_BODY;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView teamName = (TextView) view.findViewById(R.id.teamname);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView points = (TextView) view.findViewById(R.id.points);
        TextView teamPosition = (TextView) view.findViewById(R.id.teamposition);
        TextView playedGames = (TextView) view.findViewById(R.id.playedgames);
        TextView wins = (TextView) view.findViewById(R.id.wins);
        TextView draws = (TextView) view.findViewById(R.id.draws);
        TextView losses = (TextView) view.findViewById(R.id.losses);
        TextView goalDifference = (TextView) view.findViewById(R.id.goaldifference);
        

    }
}
