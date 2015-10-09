package app.com.io.codephillip.soccerdashboard;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by codephillip on 9/26/15.
 */
public class TableListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] imgId;

    public TableListAdapter(Activity context, String[] itemname, String[] imgId) {
        super(context, R.layout.mylist, itemname);
        this.context = context;
        this.itemname = itemname;
        this.imgId = imgId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.teamname);
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
            txtTitle.setText(itemname[position]);
            for(String k: itemname){
                Log.d("###ARRAY RESULTS###", k);
            }
            imageView.setImageResource(R.drawable.abc_btn_rating_star_on_mtrl_alpha);
//            Picasso.with(context)
//                    .load(imageBaseUrl+imgId[position])
//                    //.resize(30,30)
//                    .placeholder(R.drawable.abc_btn_rating_star_on_mtrl_alpha)
//                    .error(R.drawable.abc_btn_rating_star_on_mtrl_alpha)
//                    .into(imageView);
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
}
