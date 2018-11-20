import org.junit.Test;
import static org.junit.Assert.*;

public class TestGame_GUI { //when TestGame_GUI file is run, it runs all test methods, no main method

    final int foodForTesting = 50;

    @Test public void testTurn(){

    }

    @Test public void testInitializePlayers(){

    }

    @Test public void testChallengeByID(){
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Forest",1,1,
                1, 1,1,1,1,1), foodForTesting);
        Player p2 = new Player(new Dinosaur("TestDino2", false, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);
        Player[] players = new Player[2];
        Space[] board = Game_GUI.createBoard();

        AttackCard aCard0 = new AttackCard("para1","para2", "ror","food", 1,
                0);
        Deck<AttackCard> aDeck = new Deck<>();
        for(int i = 0; i < 20; i++)
            aDeck.setDeck(i, aCard0);

        Deck<NaturalDisasterCard> ndDeck = new Deck<>();
        NaturalDisasterCard ndCard0 = new NaturalDisasterCard("", "", "", false, "intelligence",
                new int[] {1}, 2);
        for(int i = 0; i < 20; i++)
            ndDeck.setDeck(i, ndCard0);

        Deck<ChallengeCard> cDeck = Game_GUI.createChallengeDeck();

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
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 + 5, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
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
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevT + 1, players[i + 1].getFoodTokens());
                                found = true;
                                break;
                            }
                        }
                        if (!found)
                            if (!players[0].isExtinct() && players[0] != p1) {
                                int prev0 = players[0].getFoodTokens();
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prev0 + 1, players[0].getFoodTokens());
                            }
                        break;
                    case 2:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertTrue(p1.isEvolveCardSpdSiz());
                        break;
                    case 3:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertTrue(p1.isEvolveCardSenInt());
                        break;
                    case 4:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());
                        break;
                    case 5:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());

                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevL1 = p1.getLocation();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 - 4, p1.getLocation());
                        break;
                    case 6:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(3);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(1, p1.getLostTurns());
                        break;
                    case 7:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(3);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
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
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF2 - aCard0.getPenaltyAmount(), players[i + 1].getFoodTokens());
                                found1 = true;
                                break;
                            }
                        }
                        if (!found1)
                            if (!players[0].isExtinct() && players[0] != p1) {
                                prevF2 = players[0].getFoodTokens();
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF2 - aCard0.getPenaltyAmount(), players[0].getFoodTokens());
                            }

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 3, p1.getFoodTokens());
                        break;
                    case 9:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 10:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        p1.move(-1 * p1.getLocation());
                        p1.move(6);

                        prevL1 = p1.getLocation();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 - 3, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());
                        break;
                    case 11:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());

                        //reduce the possible spaces to land on to ones without a challenge card
                        p1.move(-1 * p1.getLocation());
                        p1.move(23);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);

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
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
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
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF1 + 1, p1.getFoodTokens());
                                assertEquals(prevT - 1, players[0].getFoodTokens());
                            }

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 2, p1.getFoodTokens());
                        break;
                    case 13:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
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
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 14:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
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
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());
                        break;
                    case 15:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevL1 + 3, p1.getLocation());

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 + 1, p1.getFoodTokens());
                        break;
                    case 16:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
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
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);

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
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
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
                                Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                                assertEquals(prevF1 - 1, p1.getFoodTokens());
                                assertEquals(prevT2 + 1, players[0].getFoodTokens());
                            }

                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 18:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevL1 = p1.getLocation();
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
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
                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 2, p1.getFoodTokens());
                        break;
                    case 19:
                        System.out.println("Testing case #" + id + " iteration " + k);
                        p1.setLostTurns(0);
                        prevF1 = p1.getFoodTokens();
                        Game_GUI.challengeByID(p1, id, 1, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(prevF1 - 1, p1.getFoodTokens());

                        Game_GUI.challengeByID(p1, id, 2, players, board, aDeck, cDeck, ndDeck);
                        assertEquals(1, p1.getLostTurns());
                        break;
                }

            }
        }


    }

    @Test public void testAttack(){
        Space[] board = Game_GUI.createBoard();
        Player p1 = new Player(new Dinosaur("TestDino1", true, "Swamp",1,1,
                1, 1,1,1,1,1), foodForTesting);
        //one player to pass all tests, one to fail all attacks one to tie
        Player p2 = new Player(new Dinosaur("TestDino2", true, "Forest",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);
        Player p3 = new Player(new Dinosaur("TestDino3", true, "Desert",-1,-1,
                -1, -1,-1,-1,-1,-1), foodForTesting);

        p3.setEvolveCardSpdSiz(true);
        p3.setEvolveCardSenInt(true);
        //all penalties will result in movement backwards, so put players at the max -1 for caution
        p1.move(52);
        p2.move(52);
        p3.move(52);

        Deck<AttackCard> ndDeck = Game_GUI.createAttackDeck();

        AttackCard card0 = ndDeck.draw();
        AttackCard card1 = ndDeck.draw();
        AttackCard card2 = ndDeck.draw();
        AttackCard card3 = ndDeck.draw();
        AttackCard card4 = ndDeck.draw();
        AttackCard card5 = ndDeck.draw();
        AttackCard card6 = ndDeck.draw();
        AttackCard card7 = ndDeck.draw();
        AttackCard card8 = ndDeck.draw();
        AttackCard card9 = ndDeck.draw();
        AttackCard card10 = ndDeck.draw();
        AttackCard card11 = ndDeck.draw();
        AttackCard card12 = ndDeck.draw();
        AttackCard card13 = ndDeck.draw();
        AttackCard card14 = ndDeck.draw();
        AttackCard card15 = ndDeck.draw();
        AttackCard card16 = ndDeck.draw();
        AttackCard card17 = ndDeck.draw();
        AttackCard card18 = ndDeck.draw();
        AttackCard card19 = ndDeck.draw();

        AttackCard[] carr = new AttackCard[20];

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

        Player[] players = {p1, p2, p3};

        for(int i = 0; i < 20; i++) {
            AttackCard aCard = carr[i];
            Deck<AttackCard> aDeck = aTestHelper(carr[i]);

            for (int j = 0; j < 3; j++) {
                Player p = players[j];
                Player q = players[(j+1)%3];
                String statChecked = aCard.getStat();
                boolean prev = false;
                boolean tie = false;
                Player winner = p;
                Player loser = q;
                switch (statChecked) { //{"speed", "size", "intelligence", "defenses",
                    //    "weapons", "senses", "ror", "ata", "habitat"}
                    case "speed":
                        int pspd, qspd;
                        if (p.isEvolveCardSpdSiz()) {
                            pspd = 1;
                        } else {
                            pspd = p.getDino().getSpeed();
                        }
                        if (q.isEvolveCardSpdSiz()) {
                            qspd = 1;
                        } else {
                            qspd = q.getDino().getSpeed();
                        }
                        if (pspd > qspd) {
                            winner = p;
                            loser = q;
                        } else if (pspd < qspd) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "size":
                        int psiz, qsiz;
                        if (p.isEvolveCardSpdSiz()) {
                            psiz = 1;
                        } else {
                            psiz = p.getDino().getSize();
                        }
                        if (q.isEvolveCardSpdSiz()) {
                            qsiz = 1;
                        } else {
                            qsiz = q.getDino().getSize();
                        }
                        if (psiz > qsiz) {
                            winner = p;
                            loser = q;
                        } else if (psiz < qsiz) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "intelligence":
                        int pint, qint;
                        if (p.isEvolveCardSenInt()) {
                            pint = 1;
                        } else {
                            pint = p.getDino().getIntelligence();
                        }
                        if (q.isEvolveCardSenInt()) {
                            qint = 1;
                        } else {
                            qint = q.getDino().getIntelligence();
                        }
                        if (pint > qint) {
                            winner = p;
                            loser = q;
                        } else if (pint < qint) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "defenses":
                        if (p.getDino().getDefenses() > q.getDino().getDefenses()) {
                            winner = p;
                            loser = q;
                        } else if (p.getDino().getDefenses() < q.getDino().getDefenses()) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "weapons":
                        if (p.getDino().getWeapons() > q.getDino().getWeapons()) {
                            winner = p;
                            loser = q;
                        } else if (p.getDino().getWeapons() < q.getDino().getWeapons()) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "senses":
                        int psen, qsen;
                        if (p.isEvolveCardSenInt()) {
                            psen = 1;
                        } else {
                            psen = p.getDino().getSenses();
                        }
                        if (q.isEvolveCardSenInt()) {
                            qsen = 1;
                        } else {
                            qsen = q.getDino().getSenses();
                        }
                        if (psen > qsen) {
                            winner = p;
                            loser = q;
                        } else if (psen < qsen) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "ror":
                        if (p.getDino().getRor() > q.getDino().getRor()) {
                            winner = p;
                            loser = q;
                        } else if (p.getDino().getRor() < q.getDino().getRor()) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "ata":
                        if (p.getDino().getAta() > q.getDino().getAta()) {
                            winner = p;
                            loser = q;
                        } else if (p.getDino().getAta() < q.getDino().getAta()) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                    case "habitat":
                        if (board[p.getLocation()].getHabitat().equalsIgnoreCase(p.getDino().getHabitat()) &&
                                !board[q.getLocation()].getHabitat().equalsIgnoreCase(q.getDino().getHabitat())) {
                            winner = p;
                            loser = q;
                        } else if (!board[p.getLocation()].getHabitat().equalsIgnoreCase(p.getDino().getHabitat()) &&
                                board[q.getLocation()].getHabitat().equalsIgnoreCase(q.getDino().getHabitat())) {
                            winner = q;
                            loser = p;
                        } else {
                            tie = true;
                        }
                        break;
                }

                if(!tie) {
                    int wFood = winner.getFoodTokens();
                    int wLoc = winner.getLocation();
                    int lFood = loser.getFoodTokens();
                    int lLoc = loser.getLocation();
                    Game_GUI.attack(p, q, aDeck, board, prev);
                    if (aCard.getPenalty().equals("food")) {
                        assertEquals(wFood + 1, winner.getFoodTokens());
                        assertEquals(lFood - 1, loser.getFoodTokens());
                        assertEquals(wLoc, winner.getLocation());
                        assertEquals(lLoc, loser.getLocation());
                    }
                    else {
                        if (aCard.getWinner() == 0) {
                            assertEquals(lLoc - aCard.getPenaltyAmount(), loser.getLocation());
                            assertEquals(wFood, winner.getFoodTokens());
                            assertEquals(wLoc, winner.getLocation());
                            assertEquals(lFood, loser.getFoodTokens());
                        }
                        else {
                            assertEquals(wLoc + aCard.getPenaltyAmount(), winner.getLocation());
                            assertEquals(wFood, winner.getFoodTokens());
                            assertEquals(lFood, loser.getFoodTokens());
                            assertEquals(lLoc, loser.getLocation());
                        }
                    }
                }

                if (tie && !prev) {
                    int wFood = winner.getFoodTokens();
                    int wLoc = winner.getLocation();
                    int lFood = loser.getFoodTokens();
                    int lLoc = loser.getLocation();
                    Game_GUI.attack(p, q, aDeck, board, true);
                    assertEquals(wFood, winner.getFoodTokens());
                    assertEquals(wLoc, winner.getLocation());
                    assertEquals(lFood, loser.getFoodTokens());
                    assertEquals(lLoc, loser.getLocation());
                }
            }
        }
    }

    public Deck<AttackCard> aTestHelper(AttackCard card){
        Deck<AttackCard> deck = new Deck<>();
        for(int i = 0; i < 20; i++)
            deck.setDeck(i, card);
        return deck;
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
        Game_GUI.determinePenalty(p1, p2, card1);
        assertEquals(prevL2 - card1.getPenaltyAmount(), p2.getLocation());

        int prevL1 = p1.getLocation();
        Game_GUI.determinePenalty(p1, p2, card2);
        assertEquals(prevL1 + card2.getPenaltyAmount(), p1.getLocation());

        int prevF1 = p1.getFoodTokens();
        int prevF2 = p2.getFoodTokens();
        Game_GUI.determinePenalty(p1, p2, card3);
        assertEquals(prevF2 - card3.getPenaltyAmount(), p2.getFoodTokens());
        assertEquals(prevF1 + card3.getPenaltyAmount(), p1.getFoodTokens());

        prevF1 = p1.getFoodTokens();
        prevF2 = p2.getFoodTokens();
        Game_GUI.determinePenalty(p1, p2, card4);
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
        Space[] board = Game_GUI.createBoard();
        Deck<NaturalDisasterCard> ndDeck = Game_GUI.createNaturalDisasterDeck();

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

        Player[] players = {p1, p2, p3};

        for(int i = 0; i <20; i++) {
            NaturalDisasterCard ncard = carr[i];
            Deck<NaturalDisasterCard> ndeck = ndTestHelper(carr[i]);

            for (int j = 0; j < 3; j++) {
                Player p = players[j];
                int pFood = p.getFoodTokens();
                int pTurns = p.getLostTurns();
                Game_GUI.naturalDisaster(p, ndeck, board);
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

    public Deck<NaturalDisasterCard> ndTestHelper(NaturalDisasterCard card){
        Deck<NaturalDisasterCard> deck = new Deck<>();
        for(int i = 0; i < 20; i++)
            deck.setDeck(i, card);
        return deck;
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