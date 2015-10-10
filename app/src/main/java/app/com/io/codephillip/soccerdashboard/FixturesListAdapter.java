package app.com.io.codephillip.soccerdashboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by codephillip on 10/10/15.
 */
public class FixturesListAdapter extends ArrayAdapter<String> {
    private String[] date;
    private String[] homeTeamName;
//            {
//            "Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace","Man-U","Chelsea","Arsenal","Spurs","Leicester City", "Liverpool FC", "Crystal Palace"
//    };
    private String[] awayTeamName;
    private String[] score;
    private String[] imgId;
    private final Activity context;
    final String imageBaseUrl = "http://img.uefa.com/imgml/TP/teams/logos/50x50/";
    String TAG = FixturesListAdapter.class.getSimpleName();


    public FixturesListAdapter(Activity context, String[] date, String homeTeamName[], String awayTeamName[], String score[], String imgId[]) {
        super(context, R.layout.fixtures_table_row, homeTeamName);
        this.context = context;
        this.date = date;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.imgId = imgId;
        this.score = score;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.fixtures_table_row, null, true);

        TextView homeTeamNameView = (TextView) rowView.findViewById(R.id.homeTeam);
        TextView awayTeamNameView = (TextView) rowView.findViewById(R.id.awayTeam);
        TextView dateView = (TextView) rowView.findViewById(R.id.date);
        TextView scoreView = (TextView) rowView.findViewById(R.id.score);
        ImageView homeImageView = (ImageView) rowView.findViewById(R.id.homeTeamImage);
        ImageView awayImageView = (ImageView) rowView.findViewById(R.id.awayImageTeam);

        try{
            homeTeamNameView.setText(homeTeamName[position]);
            awayTeamNameView.setText(awayTeamName[position]);
//            dateView.setText(date[position]);
            scoreView.setText(score[position]);
//            //homeImageView.setImageResource(homeImage[position]);
//            picassoLoader(imageBaseUrl+imgId[position], homeImageView);
//            picassoLoader(imageBaseUrl+imgId[position], awayImageView);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rowView;
    }

    public void picassoLoader(String url, ImageView imageView){
        Picasso.with(context)
                .load(url)
                        //.resize(30,30)
                .placeholder(R.drawable.abc_btn_rating_star_on_mtrl_alpha)
                .error(R.drawable.abc_btn_rating_star_on_mtrl_alpha)
                .into(imageView);
    }
}
