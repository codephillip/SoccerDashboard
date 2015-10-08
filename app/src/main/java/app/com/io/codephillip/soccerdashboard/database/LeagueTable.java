package app.com.io.codephillip.soccerdashboard.database;

/**
 * Created by codephillip on 10/8/15.
 */
public class LeagueTable {
    private int id;
    private String standings;
    private String position;
    private String teamName;
    private String points;
    private String goals;
    private String goalsAgainst;
    private String goalsDifference;

    public LeagueTable(int id, String standings, String position, String teamName, String points, String goals, String goalsAgainst, String goalsDifference) {
        this.id = id;
        this.standings = standings;
        this.position = position;
        this.teamName = teamName;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalsDifference = goalsDifference;
    }

    public LeagueTable(String standings, String position, String teamName, String points, String goals, String goalsAgainst, String goalsDifference) {
        this.standings = standings;
        this.position = position;
        this.teamName = teamName;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalsDifference = goalsDifference;
    }

    public LeagueTable() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStandings() {
        return standings;
    }

    public void setStandings(String standings) {
        this.standings = standings;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(String goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public String getGoalsDifference() {
        return goalsDifference;
    }

    public void setGoalsDifference(String goalsDifference) {
        this.goalsDifference = goalsDifference;
    }
}
