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

    public interface SoccerVaribles{
        //team  data urls
        String BarclaysPLTableUrl = "http://api.football-data.org/alpha/soccerseasons/398/leagueTable";
        String BarclaysPLFixturesUrl = "http://api.football-data.org/alpha/soccerseasons/398/fixtures";


        //team logos
        String imageBaseUrl = "http://www.premierleague.com/content/dam/premierleague/shared-images/clubs/";
        String endUrl = "/logo.png/_jcr_content/renditions/cq5dam.thumbnail.55.55.png";

        final String imageUrls[] = {
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
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[8] + SoccerVaribles.endUrl;
                        break;
                    case "Arsenal FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[0] + SoccerVaribles.endUrl;
                        break;
                    case "Manchester United FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[9] + SoccerVaribles.endUrl;
                        break;
                    case "Crystal Palace FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[4] + SoccerVaribles.endUrl;
                        break;
                    case "Leicester City FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[6] + SoccerVaribles.endUrl;

                        break;
                    case "West Ham United FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[19] + SoccerVaribles.endUrl;
                        break;
                    case "Everton FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[5] + SoccerVaribles.endUrl;
                        break;
                    case "Tottenham Hotspur FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[16] + SoccerVaribles.endUrl;
                        break;
                    case "Southampton FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[12] + SoccerVaribles.endUrl;
                        break;
                    case "Liverpool FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[7] + SoccerVaribles.endUrl;
                        break;
                    case "Swansea City FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[15] + SoccerVaribles.endUrl;
                        break;
                    case "Watford FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[17] + SoccerVaribles.endUrl;
                        break;
                    case "Norwich City FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[11] + SoccerVaribles.endUrl;
                        break;
                    case "Stoke City FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[13] + SoccerVaribles.endUrl;
                        break;
                    case "AFC Bournemouth":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[2] + SoccerVaribles.endUrl;
                        break;
                    case "Chelsea FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[3] + SoccerVaribles.endUrl;
                        break;
                    case "West Bromwich Albion FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[18] + SoccerVaribles.endUrl;
                        break;
                    case "Aston Villa FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[1] + SoccerVaribles.endUrl;

                        break;
                    case "Sunderland AFC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[14] + SoccerVaribles.endUrl;
                        break;
                    case "Newcastle United FC":
                        url = SoccerVaribles.imageBaseUrl + SoccerVaribles.imageUrls[10] + SoccerVaribles.endUrl;
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
