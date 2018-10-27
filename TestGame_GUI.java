import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestGame_GUI { //when TestGame_GUI file is run, it runs all test methods, no main method

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
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Forest",1,1,
                1, 1,1,1,1,1), 5);
        //one player to pass all tests, one to fail all tests
        Player p2 = new Player(new Dinosaur("TestDino2", true, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);
        NaturalDisasterDeck ndDeck1 = new NaturalDisasterDeck();
        int[] arr1 = {1};
        ndDeck1.setDeck(0, new NaturalDisasterCard("", "", "", false, "intelligence",
               arr1, 2));
        NaturalDisasterDeck ndDeck2 = new NaturalDisasterDeck();
        ndDeck2.setDeck(0, new NaturalDisasterCard("", "", "", true, "none"));
        //make my own decks for different types for penalties
        //deck1 for testing safe values and food token loss
        //deck2 for habitat safe testing and turn loss
        //space 0 is a forest habitat so p1 should be habitat safe, but p2 won't be.
        //covers both safe conditions and both penalties with two players
        Space[] board = Game_GUI.createBoard();

        int food1 = p1.getFoodTokens();
        int food2 = p2.getFoodTokens();
        int turns1 = p1.getLostTurns();
        int turns2 = p2.getLostTurns();

        Game_GUI.naturalDisaster(p1, ndDeck1, board);
        assertEquals(food1, p1.getFoodTokens());
        Game_GUI.naturalDisaster(p2, ndDeck1, board);
        assertEquals(food2 - 2, p2.getFoodTokens());

        Game_GUI.naturalDisaster(p1, ndDeck2, board);
        assertEquals(turns1, p1.getLostTurns());
        Game_GUI.naturalDisaster(p2, ndDeck2, board);
        assertEquals(turns2 + 2, p2.getLostTurns());
    }

    @Test public void testDangerZone(){
        Player p = new Player(new Dinosaur("Styracosaurus", true, "Forest",0,-1,
                1, -1,-1,1,0,0), 5);
        p.move(22);
        int prevLocation = p.getLocation();
        Game_GUI.dangerZone(p);
        int postLocation = p.getLocation();
        assertEquals(prevLocation - 9, postLocation);

        p.move(29);
        prevLocation = p.getLocation();
        Game_GUI.dangerZone(p);
        postLocation = p.getLocation();
        assertEquals(prevLocation - 8, postLocation);

        p.move(26);
        int prevFood = p.getFoodTokens();
        Game_GUI.dangerZone(p);
        int postFood = p.getFoodTokens();
        assertEquals(prevFood - 3, postFood);

        p.move(9);
        prevFood = p.getFoodTokens();
        Game_GUI.dangerZone(p);
        postFood = p.getFoodTokens();
        assertEquals(prevFood - 4, postFood);

        p.move(15);
        prevFood = p.getFoodTokens();
        Game_GUI.dangerZone(p);
        postFood = p.getFoodTokens();
        assertEquals(prevFood - 3, postFood);

        p.move(19);
        prevLocation = p.getLocation();
        Game_GUI.dangerZone(p);
        postLocation = p.getLocation();
        assertEquals(prevLocation - 8, postLocation);
    }
}