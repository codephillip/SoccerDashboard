package app.com.io.codephillip.soccerdashboard;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by codephillip on 12/17/15.
 */
public class Utility {
    static String TAG = "PICASSO_LOADER";
    public static String BarclaysPLTableUrl = "http://api.football-data.org/alpha/soccerseasons/398/leagueTable";

    public interface SoccerUrls {
        //team  data urls
        String BarclaysPLTableUrl = "http://api.football-data.org/alpha/soccerseasons/398/leagueTable";
        String BarclaysPLFixturesUrl = "http://api.football-data.org/alpha/soccerseasons/398/fixtures";


        //team logos
        String barclaysImageBaseUrl = "http://www.premierleague.com/content/dam/premierleague/shared-images/clubs/";
        String barclasyEndUrl = "/logo.png/_jcr_content/renditions/cq5dam.thumbnail.55.55.png";
       
    }

    public interface ImageUrls{
        final String barclaysImageUrls[] = {
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
    }

//    private void snackBar(String message){
//        Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT);
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
//        snackbar.show();
//    }


    public static void picassoLoader(Context context,int position, ImageView imageView, String teamName){
        String url;
        Log.d(TAG + "###", "Picasso loading image");
        switch (position){
            case 0:{
                switch (teamName) {
                    case "Manchester City FC":
                        String id = BarclaysPLTableUrl;
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[8] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Arsenal FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[0] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Manchester United FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[9] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Crystal Palace FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[4] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Leicester City FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[6] + SoccerUrls.barclasyEndUrl;

                        break;
                    case "West Ham United FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[19] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Everton FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[5] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Tottenham Hotspur FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[16] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Southampton FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[12] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Liverpool FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[7] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Swansea City FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[15] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Watford FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[17] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Norwich City FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[11] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Stoke City FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[13] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "AFC Bournemouth":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[2] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Chelsea FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[3] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "West Bromwich Albion FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[18] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Aston Villa FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[1] + SoccerUrls.barclasyEndUrl;

                        break;
                    case "Sunderland AFC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[14] + SoccerUrls.barclasyEndUrl;
                        break;
                    case "Newcastle United FC":
                        url = SoccerUrls.barclaysImageBaseUrl + ImageUrls.barclaysImageUrls[10] + SoccerUrls.barclasyEndUrl;
                        break;
                    default:
                        Log.d(TAG + "####", "Flag not found");
                        throw new UnsupportedOperationException("Unknown Url: Failed to load Picasso image");
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Url: Failed to load Picasso image");
        }
        Picasso.with(context)
                .load(url)
                        //.resize(30,30)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }
}
