package app.com.io.codephillip.soccerdashboard;

import android.app.Activity;
import android.util.Log;
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
//    final String imageBaseUrl = "http://img.uefa.com/imgml/TP/teams/logos/50x50/";
    String TAG = FixturesListAdapter.class.getSimpleName();
    final String imageBaseUrl = "http://www.premierleague.com/content/dam/premierleague/shared-images/clubs/";
    final String endUrl = "/logo.png/_jcr_content/renditions/cq5dam.thumbnail.55.55.png";
    private String itemname;
    private int n = 0;
    private ImageView imageView;


    public FixturesListAdapter(Activity context, String[] date, String homeTeamName[], String awayTeamName[], String score[], String imgId[]) {
        super(context, R.layout.fixtures_table_row, homeTeamName);
        this.context = context;
        this.date = date;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.imgId = imgId;
        this.score = score;
    }

//    public FixturesListAdapter(Activity context, ArrayList<String> date, ArrayList<String> homeTeamName, ArrayList<String> awayTeamName, ArrayList<String> score, String imgId[]) {
//        super(context, R.layout.fixtures_table_row, homeTeamName);
//        this.context = context;
//        this.date = new String[date.size()];
//        this.date = date.toArray(this.date);
//        this.homeTeamName = new String[date.size()];
//        this.homeTeamName = homeTeamName.toArray(this.homeTeamName);
//        this.awayTeamName = new String[date.size()];
//        this.awayTeamName = awayTeamName.toArray(this.awayTeamName);
//        this.imgId = imgId;
//        this.score = new String[date.size()];
//        this.score = score.toArray(this.score);
//    }


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

            Log.d("PicassoLoader", "initiating" + n);
            for(n=0; n<2; n++){
                Log.d("PicassoLoader", "started"+n );
                if(n == 0){
                    itemname = homeTeamName[position];
                    imageView = homeImageView;
                } else{
                    itemname = awayTeamName[position];
                    imageView = awayImageView;
                }

            switch (itemname) {
                case "Manchester City FC":
                    picassoLoader(imageBaseUrl + imgId[8] + endUrl, imageView);
                    Log.d(TAG + "###", "Picasso loading image");
                    break;
                case "Arsenal FC":
                    picassoLoader(imageBaseUrl + imgId[0] + endUrl, imageView);
                    break;
                case "Manchester United FC":
                    picassoLoader(imageBaseUrl + imgId[9] + endUrl, imageView);
                    break;
                case "Crystal Palace FC":
                    picassoLoader(imageBaseUrl + imgId[4] + endUrl, imageView);
                    break;
                case "Leicester City FC":
                    picassoLoader(imageBaseUrl + imgId[6] + endUrl, imageView);
                    break;
                case "West Ham United FC":
                    picassoLoader(imageBaseUrl + imgId[19] + endUrl, imageView);
                    break;
                case "Everton FC":
                    picassoLoader(imageBaseUrl + imgId[5] + endUrl, imageView);
                    break;
                case "Tottenham Hotspur FC":
                    picassoLoader(imageBaseUrl + imgId[16] + endUrl, imageView);
                    break;
                case "Southampton FC":
                    picassoLoader(imageBaseUrl + imgId[12] + endUrl, imageView);
                    break;
                case "Liverpool FC":
                    picassoLoader(imageBaseUrl + imgId[7] + endUrl, imageView);
                    break;
                case "Swansea City FC":
                    picassoLoader(imageBaseUrl + imgId[15] + endUrl, imageView);
                    break;
                case "Watford FC":
                    picassoLoader(imageBaseUrl + imgId[17] + endUrl, imageView);
                    break;
                case "Norwich City FC":
                    picassoLoader(imageBaseUrl + imgId[11] + endUrl, imageView);
                    break;
                case "Stoke City FC":
                    picassoLoader(imageBaseUrl + imgId[13] + endUrl, imageView);
                    break;
                case "AFC Bournemouth":
                    picassoLoader(imageBaseUrl + imgId[2] + endUrl, imageView);
                    break;
                case "Chelsea FC":
                    picassoLoader(imageBaseUrl + imgId[3] + endUrl, imageView);
                    break;
                case "West Bromwich Albion FC":
                    picassoLoader(imageBaseUrl + imgId[18] + endUrl, imageView);
                    break;
                case "Aston Villa FC":
                    picassoLoader(imageBaseUrl + imgId[1] + endUrl, imageView);
                    break;
                case "Sunderland AFC":
                    picassoLoader(imageBaseUrl + imgId[14] + endUrl, imageView);
                    break;
                case "Newcastle United FC":
                    picassoLoader(imageBaseUrl + imgId[10] + endUrl, imageView);
                    break;
                default:
                    Log.d(TAG + "####", "Flag not found");
            }}
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
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
