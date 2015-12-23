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
public class FixturesCursorAdapter extends CursorAdapter {
    private static final String TAG = "CURSORLOADER";
    private final int VIEW_TYPE_TITLE = 0;
    private final int VIEW_TYPE_BODY = 1;
    private final int VIEW_TYPE_COUNT = 2;
    private String itemname;
    private int n = 0;
    private ImageView imageView;

    public static class ViewHolder {
        public final TextView homeTeam;
        public final TextView awayTeam;
        public final TextView score;
        public final TextView date;
        public final ImageView homeImage;
        public final ImageView awayImage;

        public ViewHolder(View view) {

            homeTeam = (TextView) view.findViewById(R.id.homeTeam);
            awayTeam = (TextView) view.findViewById(R.id.awayTeam);
            score = (TextView) view.findViewById(R.id.score);
            date = (TextView) view.findViewById(R.id.date);
            homeImage = (ImageView) view.findViewById(R.id.homeTeamImage);
            awayImage = (ImageView) view.findViewById(R.id.awayImageTeam);
        }
    }


    public FixturesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0)? VIEW_TYPE_TITLE : VIEW_TYPE_BODY;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        int viewType = getItemViewType(cursor.getPosition());

        int layoutId = -1;

        if (viewType == VIEW_TYPE_TITLE){
            layoutId = R.layout.fixtures_row_title;
        }
        else if (viewType == VIEW_TYPE_BODY){
            layoutId = R.layout.fixtures_table_row;
        }
//     TODO place date strips in the fixtures tab
//        int layoutId = -1;
//
//        String status = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_STATUS));
//
//        if (status.equals("Timed")){
//            layoutId = R.layout.table_row_title;
//        }
//        else if (status.equals("Finished")){
//            layoutId = R.layout.fixtures_table_row;
//        }

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        String homeTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_HOME_TEAM_NAME));
        String awayTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_AWAY_TEAM_NAME));
        String goalsHomeTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_GOALS_HOME_TEAM));
        String goalsAwayTeam = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_GOALS_AWAY_TEAM));
//        String date = cursor.getString(cursor.getColumnIndex(SoccerContract.FixturesTable.TAG_DATE));
        Log.d("CURSOR_bindview", homeTeam);
        Log.d("CURSOR_bindview", awayTeam);

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        try {
            viewHolder.homeTeam.setText(homeTeam);
            viewHolder.awayTeam.setText(awayTeam);
            viewHolder.score.setText(goalsHomeTeam + " - " + goalsAwayTeam);
//            viewHolder.date.setText(Utility.timeTruncate(date));

            Log.d("PicassoLoader", "initiating" + n);
            for (n = 0; n < 2; n++) {
                Log.d("PicassoLoader", "started" + n);
                if (n == 0) {
                    itemname = homeTeam;
                    imageView = viewHolder.homeImage;
                } else {
                    itemname = awayTeam;
                    imageView = viewHolder.awayImage;
                }

                if (imageView != null && itemname != null) {
                    Utility.picassoLoader(context, 0, imageView, itemname);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
