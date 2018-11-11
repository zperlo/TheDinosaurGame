import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * TestGame is a class to test the methods of the Game class, not including methods that are hard coded to create card
 * decks or the board
 * @author Tyler Anderson
 * @version 1.0
 * @since 2018-10-24
 */
public class TestGame {

    /**
     * This test method tests Game's turn method assuming the methods that turn calls execute correctly
     * @see Game's method turn(Player p, int roll, Space[] board, Player[] players, ChallengeDeck cDeck,
     * AttackDeck aDeck, NaturalDisasterDeck ndDeck)
     */

    final int foodForTesting = 50;



    @Test public void testTurn(){
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Forest",1,1,
                1, 1,1,1,1,1), foodForTesting);
        Player p2 = new Player(new Dinosaur("TestDino2", false, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);
        Player[] players = {p1, p2};
        Space[] board = Game.createBoard();

        //Create specific ChallengeDeck
        ChallengeCard cCard0 = new ChallengeCard("CONGRATULATIONS!",
                "Your Dinosaur has evolved above average (+) SPEED" + "and SIZE.",
                "Keep this card to use in any attack situation for the rest of the game.", 2, 3);
        ChallengeDeck cDeck = new ChallengeDeck();
        for(int i = 0; i < 20; i++)
            cDeck.setDeck(i, cCard0);

        String input = "1";
        InputStream in0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in0);

        //Create specific NaturalDisasterDeck to test if NaturalDisaster was called from turn correctly
        NaturalDisasterCard ndCard0 = new NaturalDisasterCard("", "", "", false,
                "intelligence", new int[] {1}, 2);
        NaturalDisasterDeck ndDeck = new NaturalDisasterDeck();
        for(int i = 0; i < 20; i++)
            ndDeck.setDeck(i, ndCard0);

        //Create specific AttackDeck to test if Attack was called from turn correctly
        AttackCard aCard0 = new AttackCard("para1","para2", "defenses",
                "move", 3, 0);
        AttackDeck aDeck = new AttackDeck();
        for(int i = 0; i < 20; i++)
            aDeck.setDeck(i, aCard0);

        /*
        Since turn handles food spaces in itself, must test those cases here,
        But because Attack, Natural Disaster, Challenge, and Danger Zones are handled in other methods,
        Only required to test the turn calls those methods correctly, the specific cases are handled
        In the test methods for those methods
         */

        int p1Food = p1.getFoodTokens();
        //Take p1's turn moving one space foward to space 1, the first herbivore space, p1 is a herbivore
        Game.turn(p1, 1, board, players, cDeck, aDeck, ndDeck);
        assertEquals(p1Food + 1, p1.getFoodTokens());
        p1Food = p1.getFoodTokens();

        //Take another turn for p1 moving one space forward to space 2, to the first carnivore space, p1 is a herbivore
        Game.turn(p1, 1, board, players, cDeck, aDeck, ndDeck);
        assertEquals(p1Food, p1.getFoodTokens());

        //Take another turn for p1 moving ten spaces forward to space 12, the first challenge space
        //The ChallengeCard should evolve p1's Dino's speed and size
        //Any player can only roll a number from 1 to 6, but I am inputting 10 as the roll instead
        //Of calling the move function of player, then passing in a smaller roll

        Game.turn(p1, 10, board, players, cDeck, aDeck, ndDeck);
        //p1.move(10);
        assertEquals(true, p1.isEvolveCardSpdSiz());

        //Take another turn for p1 moving one space forward to space 13, the first natural disaster space
        //The NaturalDisasterCard should not make p1 lost any food tokens, as p1's Dino's intelligence is 1
        Game.turn(p1, 1, board, players, cDeck, aDeck, ndDeck);
        assertEquals(p1Food, p1.getFoodTokens());

        //Take another turn for p1 moving nine spaces forward to space 22, the first danger zone space
        //This DangerZone space should make p1 move back 9 spaces to space 13
        Game.turn(p1, 9, board, players, cDeck, aDeck, ndDeck);
        assertEquals(13, p1.getLocation());

        //Tests fpr p2
        //herbivore space does not give a food token
        //carnivore space does give a food token
        //attack situation resuts in a loss for p2
        int p2Food = p2.getFoodTokens();
        Game.turn(p2, 1, board, players, cDeck, aDeck, ndDeck);
        assertEquals(p2Food, p2.getFoodTokens());


        Game.turn(p2, 1, board, players, cDeck, aDeck, ndDeck);
        assertEquals(p2Food + 1, p2.getFoodTokens());


        int p1Place = p1.getLocation();
        int p2Place = p2.getLocation() + 11;
        Game.turn(p2, 11, board, players, cDeck, aDeck, ndDeck);
        assertEquals(p1Place, p1.getLocation());
        assertEquals(p2Place - 3, p2.getLocation());
    }

    @Test public void testInitializePlayers(){
        Dinosaur[] dinos = Game.createDinoCards();
        String input = "5\n0\n1\n16\n-1\n5";
        /**
         * 5 and 0 are illegal values for # of players but 1 is a legal value
         * 16 and -1 are illegal values for which dino, but 5 is a legal value
         */
        InputStream in0 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in0);
        Player[] players = Game.initializePlayers(dinos);
        Player testPlayer0 = new Player(dinos[5], 3);
        assertEquals(testPlayer0.getDino(), players[0].getDino());
        assertEquals(testPlayer0.getFoodTokens(), players[0].getFoodTokens());

        input = "2\n0\n0\n1";
        /**2 is the valid number of players
         * 0 is the dinosaur the first player *picks*
         * the next player *tries to pick* the same dinosaur, invalid
         * so that player *picks* the dinosaur at 1
         */
        InputStream in1 = new ByteArrayInputStream(input.getBytes());
        System.setIn(in1);
        players = Game.initializePlayers(dinos);
        Player testPlayer1 = new Player(dinos[0], 3);
        Player testPlayer2 = new Player(dinos[1], 3);
        assertEquals(testPlayer1.getDino(), players[0].getDino());
        assertEquals(testPlayer1.getFoodTokens(), players[0].getFoodTokens());
        assertEquals(testPlayer2.getDino(), players[1].getDino());
        assertEquals(testPlayer2.getFoodTokens(), players[1].getFoodTokens());
    }

    @Test public void testChallengeByID(){
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Forest",1,1,
                1, 1,1,1,1,1), foodForTesting);
        Player p2 = new Player(new Dinosaur("TestDino2", false, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);
        Player[] players = new Player[2];
        Space[] board = Game.createBoard();

        AttackCard aCard0 = new AttackCard("para1","para2", "ror","food", 1,
                0);
        AttackDeck aDeck = new AttackDeck();
        for(int i = 0; i < 20; i++)
            aDeck.setDeck(i, aCard0);

        NaturalDisasterDeck ndDeck = new NaturalDisasterDeck();
        NaturalDisasterCard ndCard0 = new NaturalDisasterCard("", "", "", false, "intelligence",
                new int[] {1}, 2);
        for(int i = 0; i < 20; i++)
            ndDeck.setDeck(i, ndCard0);

        ChallengeDeck cDeck = Game.createChallengeDeck();

        int prevL1;
        int prevF1;
        int prevF2;

        for(int q = 0; q <42; q++) { // first time both players, second time p2 is extinct
            if(q > 1) {
                p2.setFoodTokens(0);
                p2.setSecondChance(false);
                p2.setExtinct(true);
            }
            if(q == 1 || q == 3) {
                players[0] = p2;
                players[1] = p1;
            }
            else {
                players[0] = p1;
                players[1] = p2;
            }
            for (int k = 0; k < 20; k++) {    //Run through 20 times to test each case
                ChallengeCard cCard = cDeck.draw();
                int id = cCard.getId();
                switch (id) {
                    case 0:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 + 5, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());
                        break;
                    case 1:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);
                        boolean found = false;
                        for (int i = 0; i < players.length; i++) {
                            if (players[i] == p1 && i != (players.length - 1) && !players[i + 1].isExtinct()) {
                                int prevT = players[i + 1].getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevT + 1, players[i + 1].getFoodTokens());
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            if (!players[0].isExtinct() && players[0] != p1) {
                                int prev0 = players[0].getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prev0 + 1, players[0].getFoodTokens());
                            }
                        break;
                    case 2:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertTrue(p1.isEvolveCardSpdSiz());
                        break;
                    case 3:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertTrue(p1.isEvolveCardSenInt());
                        break;
                    case 4:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());
                        break;
                    case 5:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());

                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 - 4, p1.getLocation());
                        break;
                    case 6:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(3);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(1, p1.getLostTurns());
                        break;
                    case 7:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(3);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(1, p1.getLostTurns());
                        break;
                    case 8:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);
                        boolean found1 = false;
                        for (int i = 0; i < players.length; i++) {
                            if (players[i] == p1 && i != (players.length - 1) && !players[i + 1].isExtinct()) {
                                prevF2 = players[i + 1].getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF2 - aCard0.getPenaltyAmount(), players[i + 1].getFoodTokens());
                                found1 = true;
                                break;
                            }
                        }
                        if (!found1)
                            if (!players[0].isExtinct() && players[0] != p1) {
                                prevF2 = players[0].getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF2 - aCard0.getPenaltyAmount(), players[0].getFoodTokens());
                            }

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 3, p1.getFoodTokens());
                        break;
                    case 9:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 10:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 - 3, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());
                        break;
                    case 11:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        //reduce the possible spaces to land on to ones without a challenge card
                        p1.move(-1 * p1.getLocation());
                        p1.move(23);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);

                        if (board[p1.getLocation()].getType().equals("herbivore"))
                            assertEquals(prevF1 + 1, p1.getFoodTokens());
                        else if (board[p1.getLocation()].getType().equals("carnivore"))
                            assertEquals(prevF1, p1.getFoodTokens());
                        else if (board[p1.getLocation()].getType().equals("natural disaster"))
                            assertEquals(prevF1, p1.getFoodTokens());

                        break;
                    case 12:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        boolean found2 = false;
                        for (int i = 0; i < players.length; i++) {
                            if (players[i] == p1 && i != (players.length - 1) && !players[i + 1].isExtinct()) {
                                int prevT = players[i + 1].getFoodTokens();
                                prevF1 = p1.getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF1 + 1, p1.getFoodTokens());
                                assertEquals(prevT - 1, players[i + 1].getFoodTokens());
                                found2 = true;
                                break;
                            }
                        }
                        if (!found2)
                            if (!players[0].isExtinct() && players[0] != p1) {
                                int prevT = players[0].getFoodTokens();
                                prevF1 = p1.getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF1 + 1, p1.getFoodTokens());
                                assertEquals(prevT - 1, players[0].getFoodTokens());
                            }

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 2, p1.getFoodTokens());
                        break;
                    case 13:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        //return to previous habitat
                        int count = 0;
                        if (prevL1 > 13) {
                            String currentHab = board[prevL1].getHabitat();
                            for (int i = prevL1; board[i].getHabitat().equalsIgnoreCase(currentHab); i--) {
                                count--;
                            }
                        }
                        assertEquals(prevL1 + count, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 14:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        //move to next habitat
                        int count2 = 0;
                        if (prevL1 < 92) {
                            String currentHab = board[prevL1].getHabitat();
                            for (int i = prevL1; board[i].getHabitat().equalsIgnoreCase(currentHab); i++) {
                                count2++;
                            }
                        }
                        assertEquals(prevL1 + count2, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());
                        break;
                    case 15:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 + 3, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());
                        break;
                    case 16:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        //move to your next food square
                        int count3 = 0;
                        boolean isHerb = p1.getDino().isHerbivore();
                        String diet;
                        if (isHerb) {
                            diet = "herbivore";
                        } else {
                            diet = "carnivore";
                        }
                        if (!(diet.equalsIgnoreCase("carnivore") && prevL1 > 100)) {
                            for (int i = prevL1; !board[i].getType().equalsIgnoreCase(diet); i++) {
                                count3++;
                            }
                        }
                        assertEquals(prevL1 + count3, p1.getLocation());

                        //reduce the possible spaces to land on to ones without a challenge card
                        p1.move(-1 * p1.getLocation());
                        p1.move(23);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);

                        if (board[p1.getLocation()].getType().equals("herbivore"))
                            assertEquals(prevF1 + 1, p1.getFoodTokens());
                        else if (board[p1.getLocation()].getType().equals("carnivore"))
                            assertEquals(prevF1, p1.getFoodTokens());
                        else if (board[p1.getLocation()].getType().equals("natural disaster"))
                            assertEquals(prevF1, p1.getFoodTokens());

                        break;
                    case 17:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        boolean found3 = false;
                        for (int i = 0; i < players.length; i++) {
                            if (players[i] == p1 && i != (players.length - 1) && !players[i + 1].isExtinct()) {
                                int prevT2 = players[i + 1].getFoodTokens();
                                prevF1 = p1.getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF1 - 1, p1.getFoodTokens());
                                assertEquals(prevT2 + 1, players[i + 1].getFoodTokens());
                                found3 = true;
                                break;
                            }
                        }
                        if (!found3)
                            if (!players[0].isExtinct() && players[0] != p1) {
                                int prevT2 = players[0].getFoodTokens();
                                prevF1 = p1.getFoodTokens();
                                Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF1 - 1, p1.getFoodTokens());
                                assertEquals(prevT2 + 1, players[0].getFoodTokens());
                            }

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 18:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        //go back to previous natural disaster square and play it
                        int count4 = 0;
                        if (prevL1 > 13) {
                            for (int i = prevL1; !board[i].getType().equals("natural disaster"); i--) {
                                count4--;
                            }
                        }
                        assertEquals(prevF1, p1.getFoodTokens());
                        assertEquals(prevL1 + count4, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 19:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevF1 = p1.getFoodTokens();
                        Game.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());

                        Game.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(1, p1.getLostTurns());
                        break;
                }

            }
        }


    }

    @Test public void testAttack(){
        Space[] board = Game.createBoard();
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Swamp",1,1,
                1, 1,1,1,1,1), 5);
        //one player to pass all tests, one to fail all attacks one to tie
        Player p2 = new Player(new Dinosaur("TestDino2", true, "Forest",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);
        Player p3 = new Player(new Dinosaur("TestDino3", true, "Forest",-1,-1,
                -1, -1,-1,-1,-1,-1), 5);

        //all penalties will result in movement backwards, so put players at the max -1 for caution
        p1.move(104);
        p2.move(104);
        p3.move(104);

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

        AttackCard aCard0 = new AttackCard("para1","para2", "speed",
                "move", 3, 0);
        AttackCard aCard1 = new AttackCard("para1","para2", "size",
                "move", 3, 0);
        AttackCard aCard2 = new AttackCard("para1","para2","intelligence",
                "move", 3, 0);
        AttackCard aCard3 = new AttackCard("para1","para2","defenses",
                "move", 3, 0);
        AttackCard aCard4 = new AttackCard("para1","para2","weapons",
                "move", 3, 0);
        AttackCard aCard5 = new AttackCard("para1","para2", "senses",
                "move", 3, 0);
        AttackCard aCard6 = new AttackCard("para1","para2", "ror",
                "move", 3, 0);
        AttackCard aCard7 = new AttackCard("para1","para2", "ata",
                "move", 3, 0);
        AttackCard aCard8 = new AttackCard("para1","para2", "habitat",
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

            Game.attack(p1, p2, adeckarr[i], board, false);
            //p2 should lose to p1
            assertEquals(prevL2 - 3, p2.getLocation());
            assertEquals(prevL1, p1.getLocation());
            //move p2 back to before the attack
            p2.move(3);

            Game.attack(p2, p3, adeckarr[i], board, false);
            //p3 should tie to p2 but p3 is in its habitat, so p2 should lose
            assertEquals(prevL2, p2.getLocation());
            assertEquals(prevL3, p3.getLocation());
            //move p2 back to before the attack
            p2.move(3);
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
                1, 1,1,1,1,1), foodForTesting);
        //one player to pass all tests, one to fail all tests
        Player p2 = new Player(new Dinosaur("TestDino2", true, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);
        Player p3 = new Player(new Dinosaur("TestDino2", true, "Swamp",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);
        p3.setEvolveCardSenInt(true);
        p3.setEvolveCardSpdSiz(true);
        final int lostTurns = 2;
        Space[] board = Game.createBoard();
        NaturalDisasterDeck ndDeck = Game.createNaturalDisasterDeck();

        NaturalDisasterCard card0 = ndDeck.draw();
        NaturalDisasterCard card1 = ndDeck.draw();
        NaturalDisasterCard card2 = ndDeck.draw();
        NaturalDisasterCard card3 = ndDeck.draw();
        NaturalDisasterCard card4 = ndDeck.draw();
        NaturalDisasterCard card5 = ndDeck.draw();
        NaturalDisasterCard card6 = ndDeck.draw();
        NaturalDisasterCard card7 = ndDeck.draw();
        NaturalDisasterCard card8 = ndDeck.draw();
        NaturalDisasterCard card9 = ndDeck.draw();
        NaturalDisasterCard card10 = ndDeck.draw();
        NaturalDisasterCard card11 = ndDeck.draw();
        NaturalDisasterCard card12 = ndDeck.draw();
        NaturalDisasterCard card13 = ndDeck.draw();
        NaturalDisasterCard card14 = ndDeck.draw();
        NaturalDisasterCard card15 = ndDeck.draw();
        NaturalDisasterCard card16 = ndDeck.draw();
        NaturalDisasterCard card17 = ndDeck.draw();
        NaturalDisasterCard card18 = ndDeck.draw();
        NaturalDisasterCard card19 = ndDeck.draw();
        NaturalDisasterCard[] carr = new NaturalDisasterCard[20];
        carr[0] = card0;
        carr[1] = card1;
        carr[2] = card2;
        carr[3] = card3;
        carr[4] = card4;
        carr[5] = card5;
        carr[6] = card6;
        carr[7] = card7;
        carr[8] = card8;
        carr[9] = card9;
        carr[10] = card10;
        carr[11] = card11;
        carr[12] = card12;
        carr[13] = card13;
        carr[14] = card14;
        carr[15] = card15;
        carr[16] = card16;
        carr[17] = card17;
        carr[18] = card18;
        carr[19] = card19;

        NaturalDisasterDeck deck0 = ndTestHelper(card0);
        NaturalDisasterDeck deck1 = ndTestHelper(card1);
        NaturalDisasterDeck deck2 = ndTestHelper(card2);
        NaturalDisasterDeck deck3 = ndTestHelper(card3);
        NaturalDisasterDeck deck4 = ndTestHelper(card4);
        NaturalDisasterDeck deck5 = ndTestHelper(card5);
        NaturalDisasterDeck deck6 = ndTestHelper(card6);
        NaturalDisasterDeck deck7 = ndTestHelper(card7);
        NaturalDisasterDeck deck8 = ndTestHelper(card8);
        NaturalDisasterDeck deck9 = ndTestHelper(card9);
        NaturalDisasterDeck deck10 = ndTestHelper(card10);
        NaturalDisasterDeck deck11 = ndTestHelper(card11);
        NaturalDisasterDeck deck12 = ndTestHelper(card12);
        NaturalDisasterDeck deck13 = ndTestHelper(card13);
        NaturalDisasterDeck deck14 = ndTestHelper(card14);
        NaturalDisasterDeck deck15 = ndTestHelper(card15);
        NaturalDisasterDeck deck16 = ndTestHelper(card16);
        NaturalDisasterDeck deck17 = ndTestHelper(card17);
        NaturalDisasterDeck deck18 = ndTestHelper(card18);
        NaturalDisasterDeck deck19 = ndTestHelper(card19);
        NaturalDisasterDeck[] darr = new NaturalDisasterDeck[20];
        darr[0] = deck0;
        darr[1] = deck1;
        darr[2] = deck2;
        darr[3] = deck3;
        darr[4] = deck4;
        darr[5] = deck5;
        darr[6] = deck6;
        darr[7] = deck7;
        darr[8] = deck8;
        darr[9] = deck9;
        darr[10] = deck10;
        darr[11] = deck11;
        darr[12] = deck12;
        darr[13] = deck13;
        darr[14] = deck14;
        darr[15] = deck15;
        darr[16] = deck16;
        darr[17] = deck17;
        darr[18] = deck18;
        darr[19] = deck19;

        Player[] players = {p1, p2, p3};

        for(int i = 0; i <20; i++) {
            NaturalDisasterCard ncard = carr[i];
            NaturalDisasterDeck ndeck = darr[i];

            for (int j = 0; j < 3; j++) {
                Player p = players[j];
                int pFood = p.getFoodTokens();
                int pTurns = p.getLostTurns();
                Game.naturalDisaster(p, ndeck, board);
                boolean safe = false;
                boolean none = false;
                if (ncard.getHabitatSafe()) {
                    if (board[p.getLocation()].getHabitat().equalsIgnoreCase(p.getDino().getHabitat())) {
                        safe = true;
                    }
                }
                if (!safe) {
                    String statChecked = ncard.getStat();
                    int pStat;
                    switch (statChecked) {
                        case "speed":
                            if (p.isEvolveCardSpdSiz()) {
                                pStat = 1;
                            } else {
                                pStat = p.getDino().getSpeed();
                            }
                            break;
                        case "size":
                            if (p.isEvolveCardSpdSiz()) {
                                pStat = 1;
                            } else {
                                pStat = p.getDino().getSize();
                            }
                            break;
                        case "intelligence":
                            if (p.isEvolveCardSenInt()) {
                                pStat = 1;
                            } else {
                                pStat = p.getDino().getIntelligence();
                            }
                            break;
                        case "defenses":
                            pStat = p.getDino().getDefenses();
                            break;
                        case "weapons":
                            pStat = p.getDino().getWeapons();
                            break;
                        case "senses":
                            if (p.isEvolveCardSenInt()) {
                                pStat = 1;
                            } else {
                                pStat = p.getDino().getSenses();
                            }
                            break;
                        case "ror":
                            pStat = p.getDino().getRor();
                            break;
                        case "ata":
                            pStat = p.getDino().getAta();
                            break;
                        case "none":
                            pStat = -2;
                            none = true;
                            break;
                        default:
                            pStat = -2;
                            break;
                    }
                    for (int x : ncard.getSafeStatValues()) {
                        System.out.println(pStat + "    compared to card's " + x);
                        if (x == pStat) {
                            safe = true;
                        }
                    }
                }
                if (safe) {
                    assertEquals(pFood, p.getFoodTokens());
                    assertEquals(pTurns, p.getLostTurns());
                }
                else{
                    if (none) {
                        assertEquals(pFood, p.getFoodTokens());
                        assertEquals(lostTurns, p.getLostTurns());
                    } else {
                        assertEquals(pFood - ncard.getFoodLost(), p.getFoodTokens());
                        assertEquals(pTurns, p.getLostTurns());
                    }
                }
            }
        }

    }

    public NaturalDisasterDeck ndTestHelper(NaturalDisasterCard card){
        NaturalDisasterDeck deck = new NaturalDisasterDeck();
        for(int i = 0; i < 20; i++)
            deck.setDeck(i, card);
        return deck;
    }

    @Test public void testDangerZone(){
        Player p = new Player(new Dinosaur("Styracosaurus", true, "Forest",0,-1,
                1, -1,-1,1,0,0), 5);
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