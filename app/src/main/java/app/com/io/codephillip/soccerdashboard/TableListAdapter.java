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
 * Created by codephillip on 9/26/15.
 */
public class TableListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] imgId;
    private final String TAG = TableListAdapter.class.getSimpleName();

    public TableListAdapter(Activity context, String[] itemname, String[] imgId) {
        super(context, R.layout.league_table_row, itemname);
        this.context = context;
        this.itemname = itemname;
        this.imgId = imgId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.league_table_row, null, true);

        TextView teamName = (TextView) rowView.findViewById(R.id.teamname);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView points = (TextView) rowView.findViewById(R.id.points);
        TextView teamPosition = (TextView) rowView.findViewById(R.id.teamposition);
        TextView playedGames = (TextView) rowView.findViewById(R.id.playedgames);
        TextView wins = (TextView) rowView.findViewById(R.id.wins);
        TextView draws = (TextView) rowView.findViewById(R.id.draws);
        TextView losses = (TextView) rowView.findViewById(R.id.losses);
        TextView goalDifference = (TextView) rowView.findViewById(R.id.goaldifference);
        final String imageBaseUrl = "http://www.premierleague.com/content/dam/premierleague/shared-images/clubs/";
        final String endUrl = "/logo.png/_jcr_content/renditions/cq5dam.thumbnail.55.55.png";

        try{
            teamName.setText(itemname[position]);

            switch (itemname[position]){
                case "Manchester City FC":
                    picassoLoader(imageBaseUrl+imgId[8]+endUrl, imageView);
                    Log.d(TAG+"###", "Picasso loading image");
                    break;
                case "Arsenal FC":
                    picassoLoader(imageBaseUrl+imgId[0]+endUrl, imageView);
                    break;
                case "Manchester United FC":
                    picassoLoader(imageBaseUrl+imgId[9]+endUrl, imageView);
                    break;
                case "Crystal Palace FC":
                    picassoLoader(imageBaseUrl+imgId[4]+endUrl, imageView);
                    break;
                case "Leicester City FC":
                    picassoLoader(imageBaseUrl+imgId[6]+endUrl, imageView);
                    break;
                case "West Ham United FC":
                    picassoLoader(imageBaseUrl+imgId[19]+endUrl, imageView);
                    break;
                case "Everton FC":
                    picassoLoader(imageBaseUrl+imgId[5]+endUrl, imageView);
                    break;
                case "Tottenham Hotspur FC":
                    picassoLoader(imageBaseUrl+imgId[16]+endUrl, imageView);
                    break;
                case "Southampton FC":
                    picassoLoader(imageBaseUrl+imgId[12]+endUrl, imageView);
                    break;
                case "Liverpool FC":
                    picassoLoader(imageBaseUrl+imgId[7]+endUrl, imageView);
                    break;
                case "Swansea City FC":
                    picassoLoader(imageBaseUrl+imgId[15]+endUrl, imageView);
                    break;
                case "Watford FC":
                    picassoLoader(imageBaseUrl+imgId[17]+endUrl, imageView);
                    break;
                case "Norwich City FC":
                    picassoLoader(imageBaseUrl+imgId[11]+endUrl, imageView);
                    break;
                case "Stoke City FC":
                    picassoLoader(imageBaseUrl+imgId[13]+endUrl, imageView);
                    break;
                case "AFC Bournemouth":
                    picassoLoader(imageBaseUrl+imgId[2]+endUrl, imageView);
                    break;
                case "Chelsea FC":
                    picassoLoader(imageBaseUrl+imgId[3]+endUrl, imageView);
                    break;
                case "West Bromwich Albion FC":
                    picassoLoader(imageBaseUrl+imgId[18]+endUrl, imageView);
                    break;
                case "Aston Villa FC":
                    picassoLoader(imageBaseUrl+imgId[1]+endUrl, imageView);
                    break;
                case "Sunderland AFC":
                    picassoLoader(imageBaseUrl+imgId[14]+endUrl, imageView);
                    break;
                case "Newcastle United FC":
                    picassoLoader(imageBaseUrl+imgId[10]+endUrl, imageView);
                    break;
                default:
                    Log.d(TAG+"####", "Flag not found");
            }
//            for(String k: itemname){
//                Log.d("###ARRAY RESULTS###", k);
//            }
            //imageView.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
//            if (itemname[position].equals("Manchester City FC")) {
//                Log.d("string$$$###", "comparison true");
//                Picasso.with(context)
//                        .load(barclaysImageBaseUrl+imgId[0])
//                                //.resize(30,30)
//                        .placeholder(R.drawable.com_facebook_button_icon)
//                        .error(R.drawable.com_facebook_tooltip_black_xout)
//                        .into(imageView);
//                Log.d("picasso$$$###", "picasso endeed");
//            }

            position++;
            teamPosition.setText(String.valueOf(position));
            points.setText(String.valueOf(position));
            position = 3;
            playedGames.setText(String.valueOf(position));
            wins.setText(String.valueOf(position));
            draws.setText(String.valueOf(position));
            position = 15;
            losses.setText(String.valueOf(position));
            goalDifference.setText(String.valueOf(position));
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            Log.d("ARRAY BUG", "ARRAY OUT OF BOUNDS EXCEPTION");
        }catch (NullPointerException e){
            e.printStackTrace();
            Log.d("NULLPOINTER BUG", "INCOMPLETE VALUES");
        }
//
// finally {
//            teamPosition.setText(String.valueOf(position));
//            points.setText(String.valueOf(position));
//            position = 3;
//            playedGames.setText(String.valueOf(position));
//            wins.setText(String.valueOf(position));
//            draws.setText(String.valueOf(position));
//            losses.setText(String.valueOf(position));
//            goalDifference.setText(String.valueOf(position));
//        }

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
