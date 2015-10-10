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
        final String imageBaseUrl = "http://img.uefa.com/imgml/TP/teams/logos/50x50/";

        try{
            teamName.setText(itemname[position]);

//            switch (itemname[position]){
//                case "Manchester City FC":
//                    Log.d(TAG+"###", "Picasso loading image");
//                    //picassoLoader(imageBaseUrl+imgId[0], imageView);
//                    Picasso.with(context)
//                            .load(imageBaseUrl+imgId[0])
//                                    //.resize(30,30)
//                            .placeholder(R.drawable.com_facebook_button_icon)
//                            .error(R.drawable.com_facebook_tooltip_black_xout)
//                            .into(imageView);
//                    break;
//                case "Arsenal FC":
//                    picassoLoader(imageBaseUrl+imgId[1], imageView);
//                    break;
//                case "Manchester United FC":
//                    picassoLoader(imageBaseUrl+imgId[2], imageView);
//                    break;
//                case "Crystal Palace FC":
//                    picassoLoader(imageBaseUrl+imgId[3], imageView);
//                    break;
//                case "Leicester City FC":
//                    picassoLoader(imageBaseUrl+imgId[4], imageView);
//                    break;
//                case "West Ham United FC":
//                break;
//                case "Everton FC":
//                break;
//                case "Tottenham Hotspur FC":
//                break;
//                case "Southampton FC":
//                break;
//                case "Liverpool FC":
//                break;
//                case "Swansea City FC":
//                break;
//                case "Watford FC":
//                break;
//                case "Norwich City FC":
//                break;
//                case "Stoke City FC":
//                break;
//                case "AFC Bournemouth":
//                break;
//                case "Chelsea FC":
//                break;
//                case "West Bromwich Albion FC":
//                break;
//                case "Aston Villa FC":
//                break;
//                case "Sunderland AFC":
//                break;
//                case "Newcastle United FC":
//                break;
//                default:
//                    Log.d(TAG+"####", "Flag not found");
//            }
//            for(String k: itemname){
//                Log.d("###ARRAY RESULTS###", k);
//            }
            //imageView.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
            if (itemname[position].equals("Manchester City FC")) {
                Log.d("string$$$###", "comparison true");
                Picasso.with(context)
                        .load(imageBaseUrl+imgId[0])
                                //.resize(30,30)
                        .placeholder(R.drawable.com_facebook_button_icon)
                        .error(R.drawable.com_facebook_tooltip_black_xout)
                        .into(imageView);
                Log.d("picasso$$$###", "picasso endeed");
            }

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
//                .placeholder(R.drawable.abc_btn_rating_star_on_mtrl_alpha)
//                .error(R.drawable.abc_btn_rating_star_on_mtrl_alpha)
                .into(imageView);
    }
}
