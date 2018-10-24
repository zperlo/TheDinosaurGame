import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestGame {

    private Player p = new Player(new Dinosaur("Styracosaurus", true, "Forest",0,-1,
            1, -1,-1,1,0,0), 5);

    public static void main(){

    }

    @Test public void testTurn(){

    }

    @Test public void testInitializePlayers(){

    }

    @Test public void testChallengeByID(){

    }

    @Test public void testAttack(){

    }

    @Test public void testDeterminePenalty(){

    }

    @Test public void testNaturalDisaster(){

    }

    @Test public void testDangerZone(){
        p.move(22);
        int prevLocation = p.getLocation();
        Game.dangerZone(p);
        int postLocation = p.getLocation();
        assertEquals(prevLocation - 9, postLocation);

        p.move(29);
        prevLocation = p.getLocation();
        Game.dangerZone(p);
        postLocation = p.getLocation();
        assertEquals(prevLocation - 8, postLocation);

        p.move(26);
        int prevFood = p.getFoodTokens();
        Game.dangerZone(p);
        int postFood = p.getFoodTokens();
        assertEquals(prevFood - 3, postFood);

        p.move(9);
        prevFood = p.getFoodTokens();
        Game.dangerZone(p);
        postFood = p.getFoodTokens();
        assertEquals(prevFood - 4, postFood);

        p.move(15);
        prevFood = p.getFoodTokens();
        Game.dangerZone(p);
        postFood = p.getFoodTokens();
        assertEquals(prevFood - 3, postFood);

        p.move(19);
        prevLocation = p.getLocation();
        Game.dangerZone(p);
        postLocation = p.getLocation();
        assertEquals(prevLocation - 8, postLocation);
    }
}