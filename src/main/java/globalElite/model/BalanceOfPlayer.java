package globalElite.model;

/**
 * Created by L on 10.11.2016.
 */
public class BalanceOfPlayer {

    private int kills;
    private int deaths;
    private double ratio;

    public BalanceOfPlayer() {
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
