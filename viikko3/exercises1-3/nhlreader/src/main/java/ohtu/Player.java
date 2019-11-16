
package ohtu;

import java.util.Comparator;

public class Player {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getPoints() {
        return goals + assists;
    }

    @Override
    public String toString() {
        return String.format("%-20s %s  %2s + %2s = %2s", name, team, goals, assists, getPoints());
    }

    static class SortByPoints implements Comparator<Player> {
        public int compare(Player a, Player b) {
            int pointsCompare = b.getPoints() - a.getPoints();

            if (pointsCompare == 0) {
                return b.goals - a.goals;
            }
            return pointsCompare;
        }
    }

}
