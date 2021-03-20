package ohtuesimerkki;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {
    Statistics stats;
    Reader readerStub = new Reader(){
        public List<Player> getPlayers(){
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };


    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void unknownPlayerNull() {
        assertNull(stats.search("Muumipeikko"));
    }

    @Test
    public void findsPlayer() {
        assertNotNull(stats.search("Semenko"));
        assertNotNull(stats.search("Lemieux"));
        assertNotNull(stats.search("Kurri"));
    }

    @Test
    public void findsPlayerSubstring() {
        assertNotNull(stats.search("Seme"));
        assertNotNull(stats.search("mieux"));
        assertNotNull(stats.search(""));
        assertNotNull(stats.search("zer"));
        assertNotNull(stats.search("tz"));
    }

    @Test
    public void teamNotNull(){
        assertNotNull(stats.team("EDM"));
        assertNotNull(stats.team("PIT"));
    }

    @Test
    public void teamIsListOfPlayers(){
        List<Player> team = stats.team("EDM");
        assertTrue(team instanceof List<?>);
    }

    @Test
    public void teamLengthMatches(){
        assertEquals(3,stats.team("EDM").size());
        assertEquals(1,stats.team("PIT").size());
    }


    @Test
    public void unknownTeamProducesEmptyList(){
        List<Player> team = stats.team("XYZ");
        assertTrue(team instanceof List<?>);
        assertEquals(0,team.size());
    }

    @Test
    public void topScorersNegative(){
        List<Player> top = stats.topScorers(-1);
        assertTrue(top instanceof List<?>);
        assertEquals(0,top.size());
    }

    @Test
    public void topScorersZero(){
        List<Player> top = stats.topScorers(0);
        assertTrue(top instanceof List<?>);
        assertEquals(0,top.size());
    }

    @Test
    public void topScorersOne(){
        List<Player> top = stats.topScorers(1);
        assertTrue(top instanceof List<?>);
        assertEquals(1,top.size());
    }

    @Test(expected = Exception.class)
    public void topScorersTooMany(){
        stats.topScorers(7);
    }

    @Test
    public void topScorersAreOrdered(){
        List<Player> top3 = stats.topScorers(3);
        assertTrue(top3 instanceof List<?>);
        assertEquals(3,top3.size());
        
        assertTrue(top3.get(0).getPoints() >= top3.get(1).getPoints());
        assertTrue(top3.get(1).getPoints() >= top3.get(2).getPoints());
        
    }

}
