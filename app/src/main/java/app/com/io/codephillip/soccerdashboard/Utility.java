package app.com.io.codephillip.soccerdashboard;

/**
 * Created by codephillip on 12/17/15.
 */
public class Utility {

    public interface SoccerVaribles{
        //team  data urls
        String BarclaysPLTableUrl = "http://api.football-data.org/alpha/soccerseasons/398/leagueTable";
        String BarclaysPLFixturesUrl = "http://api.football-data.org/alpha/soccerseasons/398/fixtures";


        //team logos
        String imageBaseUrl = "http://www.premierleague.com/content/dam/premierleague/shared-images/clubs/";
        String endUrl = "/logo.png/_jcr_content/renditions/cq5dam.thumbnail.55.55.png";
    }

//    private void snackBar(String message){
//        Snackbar snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_SHORT);
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
//        snackbar.show();
//    }

}
