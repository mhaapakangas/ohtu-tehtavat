package ohtuesimerkki;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    private Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }

    @Test
    public void returnsAllPlayersOfTeam() {
        List<Player> result = stats.team("EDM");

        ArrayList<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player("Semenko", "EDM", 4, 12));
        expectedPlayers.add(new Player("Kurri",   "EDM", 37, 53));
        expectedPlayers.add(new Player("Gretzky", "EDM", 35, 89));

        Assert.assertEquals(expectedPlayers, result);
    }

    @Test
    public void searchReturnsCorrectPlayer() {
        Player result = stats.search("Lemieux");

        Player expectedPlayer = new Player("Lemieux", "PIT", 45, 54);

        Assert.assertEquals(expectedPlayer, result);
    }

    @Test
    public void searchReturnsNullIfPlayerNotFound() {
        Player result = stats.search("Random");

        Assert.assertNull(result);
    }

    @Test
    public void returnsTopScorersCorrectly() {
        List<Player> result = stats.topScorers(2);

        ArrayList<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player("Gretzky", "EDM", 35, 89));
        expectedPlayers.add(new Player("Lemieux", "PIT", 45, 54));
        expectedPlayers.add(new Player("Yzerman", "DET", 42, 56));

        Assert.assertEquals(expectedPlayers, result);
    }
}
