package app.com.io.codephillip.soccerdashboard.database;

/**
 * Created by codephillip on 10/9/15.
 */
public class FixturesTable {
    private  int TAG_FIXTURES_TABLE_KEY_ID;
    private  String TAG_DATE;
    private  String TAG_STATUS;
    private  String TAG_HOME_TEAM_NAME;
    private  String TAG_AWAY_TEAM_NAME;
    private  String TAG_GOALS_HOME_TEAM;
    private  String TAG_GOALS_AWAY_TEAM;

    public FixturesTable(int TAG_FIXTURES_TABLE_KEY_ID, String TAG_DATE, String TAG_STATUS, String TAG_HOME_TEAM_NAME, String TAG_AWAY_TEAM_NAME, String TAG_GOALS_HOME_TEAM, String TAG_GOALS_AWAY_TEAM) {
        this.TAG_FIXTURES_TABLE_KEY_ID = TAG_FIXTURES_TABLE_KEY_ID;
        this.TAG_DATE = TAG_DATE;
        this.TAG_STATUS = TAG_STATUS;
        this.TAG_HOME_TEAM_NAME = TAG_HOME_TEAM_NAME;
        this.TAG_AWAY_TEAM_NAME = TAG_AWAY_TEAM_NAME;
        this.TAG_GOALS_HOME_TEAM = TAG_GOALS_HOME_TEAM;
        this.TAG_GOALS_AWAY_TEAM = TAG_GOALS_AWAY_TEAM;
    }

    public FixturesTable(String TAG_DATE, String TAG_STATUS, String TAG_HOME_TEAM_NAME, String TAG_AWAY_TEAM_NAME, String TAG_GOALS_HOME_TEAM, String TAG_GOALS_AWAY_TEAM) {
        this.TAG_DATE = TAG_DATE;
        this.TAG_STATUS = TAG_STATUS;
        this.TAG_HOME_TEAM_NAME = TAG_HOME_TEAM_NAME;
        this.TAG_AWAY_TEAM_NAME = TAG_AWAY_TEAM_NAME;
        this.TAG_GOALS_HOME_TEAM = TAG_GOALS_HOME_TEAM;
        this.TAG_GOALS_AWAY_TEAM = TAG_GOALS_AWAY_TEAM;
    }

    public FixturesTable() {
    }

    public FixturesTable(int TAG_FIXTURES_TABLE_KEY_ID) {
        this.TAG_FIXTURES_TABLE_KEY_ID = TAG_FIXTURES_TABLE_KEY_ID;
    }

    public void setTAG_FIXTURES_TABLE_KEY_ID(int TAG_FIXTURES_TABLE_KEY_ID) {
        this.TAG_FIXTURES_TABLE_KEY_ID = TAG_FIXTURES_TABLE_KEY_ID;
    }

    public void setTAG_DATE(String TAG_DATE) {
        this.TAG_DATE = TAG_DATE;
    }

    public void setTAG_STATUS(String TAG_STATUS) {
        this.TAG_STATUS = TAG_STATUS;
    }

    public void setTAG_HOME_TEAM_NAME(String TAG_HOME_TEAM_NAME) {
        this.TAG_HOME_TEAM_NAME = TAG_HOME_TEAM_NAME;
    }

    public void setTAG_AWAY_TEAM_NAME(String TAG_AWAY_TEAM_NAME) {
        this.TAG_AWAY_TEAM_NAME = TAG_AWAY_TEAM_NAME;
    }

    public void setTAG_GOALS_HOME_TEAM(String TAG_GOALS_HOME_TEAM) {
        this.TAG_GOALS_HOME_TEAM = TAG_GOALS_HOME_TEAM;
    }

    public void setTAG_GOALS_AWAY_TEAM(String TAG_GOALS_AWAY_TEAM) {
        this.TAG_GOALS_AWAY_TEAM = TAG_GOALS_AWAY_TEAM;
    }

    public int getTagFixturesTableKeyId() {
        return TAG_FIXTURES_TABLE_KEY_ID;
    }

    public String getTagDate() {
        return TAG_DATE;
    }

    public String getTagStatus() {
        return TAG_STATUS;
    }

    public String getTagHomeTeamName() {
        return TAG_HOME_TEAM_NAME;
    }

    public String getTagAwayTeamName() {
        return TAG_AWAY_TEAM_NAME;
    }

    public String getTagGoalsHomeTeam() {
        return TAG_GOALS_HOME_TEAM;
    }

    public String getTagGoalsAwayTeam() {
        return TAG_GOALS_AWAY_TEAM;
    }
}
