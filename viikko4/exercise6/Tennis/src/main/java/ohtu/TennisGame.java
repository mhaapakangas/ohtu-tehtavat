package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    private static final String[] pointsToScore = new String[] { "Love", "Fifteen", "Thirty", "Forty" };
    private static final int winMargin = 2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (player1Name.equals(playerName)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        String score;

        if (player1Score == player2Score) {
            score = getDrawScore();
        } else if (player1Score >= pointsToScore.length || player2Score >= pointsToScore.length) {
            score = getAdvantageOrWinScore();
        } else {
            score = getNormalScore();
        }

        return score;
    }

    private String getDrawScore() {
        if (player1Score < pointsToScore.length) {
            return pointsToScore[player1Score] + "-All";
        } else {
            return "Deuce";
        }
    }

    private String getAdvantageOrWinScore() {
        String score;
        int scoreDifference = player1Score - player2Score;
        if (Math.abs(scoreDifference) >= winMargin) {
            score = "Win for ";
        } else {
            score = "Advantage ";
        }
        if (scoreDifference < 0) {
            score += player2Name;
        } else {
            score += player1Name;
        }

        return score;
    }

    private String getNormalScore() {
        return pointsToScore[player1Score] + "-" + pointsToScore[player2Score];
    }
}