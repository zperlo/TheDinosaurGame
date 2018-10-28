import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;

public class TestGame_GUI { //when TestGame_GUI file is run, it runs all test methods, no main method

    @Test public void testTurn(){

    }

    /*
    @Test
    public void shouldTakeUserInput() {
        InputOutput inputOutput= new InputOutput();

        String input = "add 5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals("add 5", inputOutput.getInput());
    }
     */

    @Test public void testInitializePlayers(){


        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Dinosaur[] dinos = Game.createDinoCards();
        Player[] players;


    }

    @Test public void testChallengeByID(){
        Space[] board = Game.createBoard();
        AttackDeck aDeck = Game.createAttackDeck();
        NaturalDisasterDeck nDeck = Game.createNaturalDisasterDeck();
        /*
        Need to create a ChallengeDeck for each challenge scenario
         */


    }

    @Test public void testAttack(){
        Space[] board = Game_GUI.createBoard();
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Forest",1,1,
                1, 1,1,1,1,1), 5);
        //one player to pass all tests, one to fail all attacks one to tie and decide on habitat, one to tie and do nothing
        Player p2 = new Player(new Dinosaur("TestDino2", true, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);
        Player p3 = new Player(new Dinosaur("TestDino3", true, "Swamp",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);
        Player p4 = new Player(new Dinosaur("TestDino4", true, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);

        //all penalties will result in movement backwards, so put players at the max -1 for caution
        p1.move(104);
        p2.move(104);
        p3.move(104);
        p4.move(104);

        AttackDeck deck0 = new AttackDeck();
        AttackDeck deck1 = new AttackDeck();
        AttackDeck deck2 = new AttackDeck();
        AttackDeck deck3 = new AttackDeck();
        AttackDeck deck4 = new AttackDeck();
        AttackDeck deck5 = new AttackDeck();
        AttackDeck deck6 = new AttackDeck();
        AttackDeck deck7 = new AttackDeck();
        AttackDeck deck8 = new AttackDeck();

        //{"speed", "size", "intelligence", "defenses",
        //    "weapons", "senses", "ror", "ata", "habitat"}

        AttackCard aCard0 = new AttackCard("","", "speed",
                "move", 3, 0);
        AttackCard aCard1 = new AttackCard("","", "size",
                "move", 3, 0);
        AttackCard aCard2 = new AttackCard("","", "intelligence",
                "move", 3, 0);
        AttackCard aCard3 = new AttackCard("","", "defenses",
                "move", 3, 0);
        AttackCard aCard4 = new AttackCard("","", "weapons",
                "move", 3, 0);
        AttackCard aCard5 = new AttackCard("","", "senses",
                "move", 3, 0);
        AttackCard aCard6 = new AttackCard("","", "ror",
                "move", 3, 0);
        AttackCard aCard7 = new AttackCard("","", "ata",
                "move", 3, 0);
        AttackCard aCard8 = new AttackCard("","", "habitat",
                "move", 3, 0);

        for(int i = 0; i < 20; i++){
            deck0.setDeck(i,aCard0);
            deck1.setDeck(i,aCard1);
            deck2.setDeck(i,aCard2);
            deck3.setDeck(i,aCard3);
            deck4.setDeck(i,aCard4);
            deck5.setDeck(i,aCard5);
            deck6.setDeck(i,aCard6);
            deck7.setDeck(i,aCard7);
            deck8.setDeck(i,aCard8);
        }

        AttackDeck[] adeckarr = {deck0, deck1, deck2, deck3, deck4, deck5, deck6, deck7, deck8};
        for(int i = 0; i < 9; i++){
            int prevL1 = p1.getLocation();
            int prevL2 = p2.getLocation();
            int prevL3 = p3.getLocation();
            int prevL4 = p4.getLocation();

            Game.attack(p1, p2, adeckarr[i], board, false);
            //p2 should lose to p1
            assertEquals(prevL2 - 3, p2.getLocation());
            assertEquals(prevL1, p1.getLocation());
            //move p2 back to before the attack
            p2.move(3);

            Game.attack(p2, p3, adeckarr[i], board, false);
            //p3 should tie to p2 but p3 is in its habitat, so p2 should lose
            assertEquals(prevL2 - 3, p2.getLocation());
            assertEquals(prevL3, p3.getLocation());
            //move p2 back to before the attack
            p2.move(3);

            Game.attack(p2, p4, adeckarr[i], board, false);
            //p2 should tie to p4, no one loses
            assertEquals(prevL2, p2.getLocation());
            assertEquals(prevL4, p4.getLocation());

        }

    }

    @Test public void testDeterminePenalty(){
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Forest",1,1,
                1, 1,1,1,1,1), 5);
        //one player to pass all tests, one to fail all attacks
        Player p2 = new Player(new Dinosaur("TestDino2", true, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);
        //move them to space 50 so adequate testing space
        p1.move(50);
        p2.move(50);
        //make attack cards with different (win/lose) (movement/tokens)
        AttackCard card1 = new AttackCard("","","weapons",
                "move",3, 0);
        AttackCard card2 = new AttackCard("","","weapons",
                "move",3, 1);
        AttackCard card3 = new AttackCard("","", "ata",
                "food", 1, 0);
        AttackCard card4 = new AttackCard("","", "ata",
                "food", 1, 1);
        int prevL2 = p2.getLocation();
        Game.determinePenalty(p1, p2, card1);
        assertEquals(prevL2 - card1.getPenaltyAmount(), p2.getLocation());

        int prevL1 = p1.getLocation();
        Game.determinePenalty(p1, p2, card2);
        assertEquals(prevL1 + card2.getPenaltyAmount(), p1.getLocation());

        int prevF1 = p1.getFoodTokens();
        int prevF2 = p2.getFoodTokens();
        Game.determinePenalty(p1, p2, card3);
        assertEquals(prevF2 - card3.getPenaltyAmount(), p2.getFoodTokens());
        assertEquals(prevF1 + card3.getPenaltyAmount(), p1.getFoodTokens());

        prevF1 = p1.getFoodTokens();
        prevF2 = p2.getFoodTokens();
        Game.determinePenalty(p1, p2, card4);
        assertEquals(prevF2 - card4.getPenaltyAmount(), p2.getFoodTokens());
        assertEquals(prevF1 + card4.getPenaltyAmount(), p1.getFoodTokens());
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
        NaturalDisasterCard card2 = new NaturalDisasterCard("OOPS!", "QUICKSAND! You fell into " +
                "quicksand.", "You are stuck for 2 turns.", true, "none");
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

        System.out.println(board[p2.getLocation()].getHabitat());
        System.out.println(p2.getDino().getHabitat());
        System.out.println(board[p2.getLocation()].getHabitat().equalsIgnoreCase(p2.getDino().getHabitat()));
        Game_GUI.naturalDisaster(p1, ndDeck2, board);
        assertEquals(turns1, p1.getLostTurns());
        Game_GUI.naturalDisaster(p2, ndDeck2, board);
        assertEquals(2, p2.getLostTurns());
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