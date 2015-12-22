package app.com.io.codephillip.soccerdashboard.cursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.com.io.codephillip.soccerdashboard.R;
import app.com.io.codephillip.soccerdashboard.Utility;
import app.com.io.codephillip.soccerdashboard.data.SoccerContract;

/**
 * Created by codephillip on 12/21/15.
 */
public class TableCursorAdapter extends CursorAdapter {

    private static final int VIEW_TYPE_COUNT = 2;
    private static final int VIEW_TYPE_TITLE = 0;
    private static final int VIEW_TYPE_BODY = 1;

    public static class ViewHolder {
        public final TextView teamName;
        public final TextView points;
        public final ImageView teamImage;
        public final TextView position;
        public final TextView goals;
        public final TextView goalsAgainst;
        public final TextView goalsDifference;

        public ViewHolder(View view) {

            teamName = (TextView) view.findViewById(R.id.teamname);
            points = (TextView) view.findViewById(R.id.points);
            teamImage = (ImageView) view.findViewById(R.id.teamImage);
            goals = (TextView) view.findViewById(R.id.goals);
            goalsAgainst = (TextView) view.findViewById(R.id.goalsAgainst);
            goalsDifference = (TextView) view.findViewById(R.id.goaldifference);
            position = (TextView) view.findViewById(R.id.teamposition);
        }
    }

    public TableCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        Log.d("CURSOR", "NEWVIEW");
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
//                throw new UnsupportedOperationException("Failed to load Layout"l);
                layoutId = R.layout.league_table_row;
        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
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
        Log.d("LOADER","BINDVIEW");
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        String teamName = cursor.getString(cursor.getColumnIndex(SoccerContract.LeagueTable.TAG_TEAM_NAME));
        String points = cursor.getString(cursor.getColumnIndex(SoccerContract.LeagueTable.TAG_POINTS));
        String goals = cursor.getString(cursor.getColumnIndex(SoccerContract.LeagueTable.TAG_GOALS));
        String goalsAgainst = cursor.getString(cursor.getColumnIndex(SoccerContract.LeagueTable.TAG_GOALS_AGAINST));
        String goalsDifference = cursor.getString(cursor.getColumnIndex(SoccerContract.LeagueTable.TAG_GOALS_DIFFERENCE));
        String position = cursor.getString(cursor.getColumnIndex(SoccerContract.LeagueTable.TAG_POSITION));

        Log.d("CURSOR_BINDVIEW", teamName + points + goalsDifference);

        try {
            viewHolder.teamName.setText(teamName);
            viewHolder.points.setText(points);
            viewHolder.goals.setText(goals);
            viewHolder.position.setText(position);
            viewHolder.goalsAgainst.setText(goalsAgainst);
            viewHolder.goalsDifference.setText(goalsDifference);
            if (viewHolder.teamImage != null && teamName != null) {
                Utility.picassoLoader(context, 0, viewHolder.teamImage, teamName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
