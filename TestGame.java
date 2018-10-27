import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestGame { //when TestGame file is run, it runs all test methods, no main method

    private Player p = new Player(new Dinosaur("Styracosaurus", true, "Forest",0,-1,
            1, -1,-1,1,0,0), 5);

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
        NaturalDisasterCard card1 = new NaturalDisasterCard("", "", "", false, "intelligence",
                arr1, 2);
        for(int i = 0; i < 20; i++)
            ndDeck1.setDeck(i, card1);

        NaturalDisasterDeck ndDeck2 = new NaturalDisasterDeck();
        NaturalDisasterCard card2 = new NaturalDisasterCard("", "", "", true, "none");
        for(int i = 0; i < 20; i++)
            ndDeck2.setDeck(i, card2);
        //make my own decks for different types for penalties
        //deck1 for testing safe values and food token loss
        //deck2 for habitat safe testing and turn loss
        //space 0 is a forest habitat so p1 should be habitat safe, but p2 won't be.
        //covers both safe conditions and both penalties with two players
        Space[] board = Game_GUI.createBoard();

        int food1 = p1.getFoodTokens();
        int food2 = p2.getFoodTokens();
        int turns1 = p1.getLostTurns();
        //int turns2 = p2.getLostTurns();

        Game_GUI.naturalDisaster(p1, ndDeck1, board);
        assertEquals(food1, p1.getFoodTokens());
        Game_GUI.naturalDisaster(p2, ndDeck1, board);
        assertEquals(food2 - 2, p2.getFoodTokens());

        System.out.println(board[p1.getLocation()].getHabitat());
        System.out.println(p1.getDino().getHabitat());
        System.out.println(board[p2.getLocation()].getHabitat());
        System.out.println(p2.getDino().getHabitat());
        Game_GUI.naturalDisaster(p1, ndDeck2, board);
        assertEquals(turns1, p1.getLostTurns());
        Game_GUI.naturalDisaster(p2, ndDeck2, board);
        assertEquals(2, p2.getLostTurns());
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