package globalElite.model;

/**
 * Created by L on 10.11.2016.
 */
public class BalanceOfTeam {

    private int wins;
    private int looses;
    private int draws;
    private double points;

    public BalanceOfTeam() {
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLooses() {
        return looses;
    }

    public void setLooses(int looses) {
        this.looses = looses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public double getPoints() {

        points = (double) wins + (draws * 0.5);
        return points;
    }


}
