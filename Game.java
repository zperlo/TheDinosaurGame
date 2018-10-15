import java.util.Scanner;

// central class to run game events
public class Game {
    public static void main(String[] args) {

        Dinosaur[] dinoCards = createDinoCards();
        NaturalDisasterDeck ndDeck = createNaturalDisasterDeck();
        AttackDeck aDeck = createAttackDeck();
        ChallengeDeck cDeck = createChallengeDeck();
        Player[] players = initializePlayers(dinoCards);
        Space[] board = createBoard();

        boolean gameEnd = false;
        while(!gameEnd) {
            for (Player p: players) {
                // roll to see how far to move, as if on a 6-sided die
                int roll = (int)(Math.random() * 6 + 1);
                turn(p, roll, board, players, cDeck, aDeck, ndDeck);
                for (Player e: players){
                    if(board[e.getLocation()].getType().equals("finish")){
                        gameEnd = true;
                    }
                }
            }
        }

    }

    // one player takes their turn
    private static void turn(Player p, int roll, Space[] board, Player[] players, ChallengeDeck cDeck,
                            AttackDeck aDeck, NaturalDisasterDeck ndDeck) {

        // see if the player currently is on a lost turn
        int lostTurns = p.getLostTurns();
        if (lostTurns > 0) {
            lostTurns--; // use up turn, do nothing
            p.setLostTurns(lostTurns);
        }

        // the player gets a turn otherwise
        else {
            // have player move on board
            p.move(roll);

            // run the encounter on that space
            boolean playSpace = true;
            for (Player x: players){
                if(x.getLocation() == p.getLocation() && x != p){
                    playSpace = false;
                    attack(p, x, aDeck, board, false);
                }
            }

            if(playSpace) {
                switch (board[p.getLocation()].getType()) {
                    case "herbivore":
                        if (p.getDino().isHerbivore()) {
                            p.changeFood(1);
                        }
                        break;
                    case "carnivore":
                        if (!p.getDino().isHerbivore()) {
                            p.changeFood(1);
                        }
                        break;
                    case "challenge":
                        ChallengeCard cCard = cDeck.draw();
                        int id = cCard.getId();
                        int choice = 1; // NEEDS TO GET FROM PLAYER CHOICE -- GUI
                        challengeByID(p, id, choice, players, board, aDeck, cDeck, ndDeck);
                        break;
                    case "natural disaster":
                        naturalDisaster(p, ndDeck, board);
                        break;
                    case "danger zone":
                        //do danger zone stuff
                        break;
                }
            }
            for (Player x: players){
                if(x.getLocation() == p.getLocation() && x != p){
                    attack(p, x, aDeck, board, false);
                }
            }


        }

    }

    // hardcode the board into game
    private static Space[] createBoard() {

        Space[] board = new Space[106];

        // starting space
        board[0] = new Space("forest", "start");

        // first leg of forest
        board[1] = new Space("forest", "herbivore");
        board[2] = new Space("forest", "carnivore");
        board[3] = new Space("forest", "carnivore");
        board[4] = new Space("forest", "carnivore");
        board[5] = new Space("forest", "herbivore");
        board[6] = new Space("forest", "challenge");
        board[7] = new Space("forest", "carnivore");
        board[8] = new Space("forest", "herbivore");
        board[9] = new Space("forest", "herbivore");
        board[10] = new Space("forest", "carnivore");
        board[11] = new Space("forest", "herbivore");
        board[12] = new Space("forest", "challenge");
        board[13] = new Space("forest", "natural disaster");

        // first leg of desert
        board[14] = new Space("desert", "carnivore");
        board[15] = new Space("desert", "herbivore");
        board[16] = new Space("desert", "natural disaster");
        board[17] = new Space("desert", "carnivore");
        board[18] = new Space("desert", "challenge");
        board[19] = new Space("desert", "herbivore");
        board[20] = new Space("desert", "natural disaster");
        board[21] = new Space("desert", "carnivore");
        board[22] = new Space("desert", "danger zone");
        board[23] = new Space("desert", "challenge");
        board[24] = new Space("desert", "herbivore");
        board[25] = new Space("desert", "natural disaster");

        // tiny leg in swamp
        board[26] = new Space("swamp", "carnivore");
        board[27] = new Space("swamp", "herbivore");
        board[28] = new Space("swamp", "natural disaster");

        // back down into desert
        board[29] = new Space("desert", "herbivore");
        board[30] = new Space("desert", "carnivore");
        board[31] = new Space("desert", "carnivore");
        board[32] = new Space("desert", "challenge");
        board[33] = new Space("desert", "herbivore");
        board[34] = new Space("desert", "carnivore");
        board[35] = new Space("desert", "herbivore");
        board[36] = new Space("desert", "natural disaster");
        board[37] = new Space("desert", "herbivore");

        // back into the forest for bit
        board[38] = new Space("forest", "challenge");
        board[39] = new Space("forest", "herbivore");
        board[40] = new Space("forest", "carnivore");
        board[41] = new Space("forest", "natural disaster");
        board[42] = new Space("forest", "danger zone");
        board[43] = new Space("forest", "challenge");

        // swamp middle leg
        board[44] = new Space("swamp", "challenge");
        board[45] = new Space("swamp", "carnivore");
        board[46] = new Space("swamp", "natural disaster");
        board[47] = new Space("swamp", "herbivore");
        board[48] = new Space("swamp", "natural disaster");
        board[49] = new Space("swamp", "challenge");
        board[50] = new Space("swamp", "carnivore");
        board[51] = new Space("swamp", "herbivore");

        // forest big trek
        board[52] = new Space("forest", "natural disaster");
        board[53] = new Space("forest", "carnivore");
        board[54] = new Space("forest", "natural disaster");
        board[55] = new Space("forest", "herbivore");
        board[56] = new Space("forest", "carnivore");
        board[57] = new Space("forest", "natural disaster");
        board[58] = new Space("forest", "challenge");
        board[59] = new Space("forest", "herbivore");
        board[60] = new Space("forest", "danger zone");
        board[61] = new Space("forest", "natural disaster");

        // first time in the plains
        board[62] = new Space("plains", "carnivore");
        board[63] = new Space("plains", "challenge");
        board[64] = new Space("plains", "herbivore");
        board[65] = new Space("plains", "natural disaster");
        board[66] = new Space("plains", "carnivore");
        board[67] = new Space("plains", "challenge");
        board[68] = new Space("plains", "herbivore");
        board[69] = new Space("plains", "danger zone");
        board[70] = new Space("plains", "natural disaster");

        // last bit in forest
        board[71] = new Space("forest", "carnivore");
        board[72] = new Space("forest", "herbivore");
        board[73] = new Space("forest", "challenge");
        board[74] = new Space("forest", "carnivore");

        // back to the plains for a wee bit
        board[75] = new Space("plains", "natural disaster");
        board[76] = new Space("plains", "challenge");
        board[77] = new Space("plains", "herbivore");
        board[78] = new Space("plains", "carnivore");

        // swamp time (tiny)
        board[79] = new Space("swamp", "carnivore");
        board[80] = new Space("swamp", "natural disaster");
        board[81] = new Space("swamp", "challenge");
        board[82] = new Space("swamp", "herbivore");

        // last leg of the plains
        board[83] = new Space("plains", "carnivore");
        board[84] = new Space("plains", "danger zone");
        board[85] = new Space("plains", "challenge");
        board[86] = new Space("plains", "carnivore");
        board[87] = new Space("plains", "herbivore");
        board[88] = new Space("plains", "natural disaster");
        board[89] = new Space("plains", "carnivore");
        board[90] = new Space("plains", "challenge");
        board[91] = new Space("plains", "herbivore");

        // final part of game: top of swamp
        board[92] = new Space("swamp", "carnivore");
        board[93] = new Space("swamp", "natural disaster");
        board[94] = new Space("swamp", "challenge");
        board[95] = new Space("swamp", "herbivore");
        board[96] = new Space("swamp", "carnivore");
        board[97] = new Space("swamp", "natural disaster");
        board[98] = new Space("swamp", "challenge");
        board[99] = new Space("swamp", "carnivore");
        board[100] = new Space("swamp", "carnivore");
        board[101] = new Space("swamp", "natural disaster");
        board[102] = new Space("swamp", "challenge");
        board[103] = new Space("swamp", "danger zone");
        board[104] = new Space("swamp", "herbivore");

        // finish space
        board[105] = new Space("swamp", "finish");

        return board;
    }

    // get player dino choices from user input and initialize players
    private static Player[] initializePlayers(Dinosaur[] dinoCards) {

        // take in input for how many players
        Scanner input = new Scanner(System.in);
        System.out.println("How many players (1 - 4) are playing?");
        int playerCount = input.nextInt();

        // handle input errors
        while (!(playerCount <= 4 && playerCount >= 1)) {
            System.out.println("Invalid player count. Input 1 - 4");
            playerCount = input.nextInt();
        }

        // print all the dinosaurs the players can choose from
        System.out.println("The following are the Dinosaurs to choose from:");
        for (int i = 0; i < dinoCards.length; i++) {
            System.out.println("" + i + ". " + dinoCards[i].getName());
        }

        Player[] players = new Player[playerCount];
        int[] chosenDinos = new int[playerCount];
        for (int i = 0; i < playerCount; i++) {
            System.out.println("Player " + (i + 1) + " input the number for the dinosaur you want");
            int num = input.nextInt();
            chosenDinos[i] = num;

            // loop to prevent 2 players from choosing the same dinosaur.
            // Second player to do so will choose another dinosaur
            int j = 0;
            while (j < i) {
                if (chosenDinos[j] == num) {
                    System.out.println("Chosen dinosaur already chosen by a previous player. Choose another dinosaur");
                    num = input.nextInt();
                    chosenDinos[i] = num;
                    j = 0;
                }
                else j++;
            }

            players[i] = new Player(dinoCards[num], 3);
        }

        return players;
    }

    // create an array of all dinosaurs
    private static Dinosaur[] createDinoCards() {
        Dinosaur[] dinoCards = new Dinosaur[16];

        dinoCards[0] = new Dinosaur("Velociraptor", false, "Desert", 1,-1,
                1,0,1,0,0,-1);
        dinoCards[1] = new Dinosaur("Ankylosaurus",true,"Desert",-1,-1,
                0,1,1,-1,0,0);
        dinoCards[2] = new Dinosaur("Styracosaurus", true, "Forest",0,-1,
                1, -1,-1,1,0,0);
        dinoCards[3] = new Dinosaur("Spinosaurus", false,"Desert",-1,0,
                -1,0,0,-1,1,1);
        dinoCards[4] = new Dinosaur("Triceratops", true,"Plains",0,0,
                0,1,0,1,-1,-1);
        dinoCards[5] = new Dinosaur("Parasaurolophus", true,"Forest",0,0,
                -1,-1,-1,0,1,1);
        dinoCards[6] = new Dinosaur("Stegosaurus",true,"Plains",-1,1,
                -1,1,1,-1,0,0);
        dinoCards[7] = new Dinosaur("Protoceratops",true,"Desert",0,-1,
                0,-1,-1,1,1,0);
        dinoCards[8] = new Dinosaur("Iguanodon",true,"Swamp",0,0,
                0,-1,-1,-1,1,1);
        dinoCards[9] = new Dinosaur("Dilophosaurus",false,"Swamp",1,0,
                1,0,0,-1,-1,-1);
        dinoCards[10] = new Dinosaur("Apatosaurus",true,"Swamp",-1,1,
                -1,0,0,-1,1,1);
        dinoCards[11] = new Dinosaur("Compsognathus",false,"Swamp",1,-1,
                1,-1,-1,0,1,0);
        dinoCards[12] = new Dinosaur("Allosaurus",false,"Plains",0,1,
                0,0,1,1,-1,-1);
        dinoCards[13] = new Dinosaur("Tyrannosaurus", false,"Forest",1,1,
                0,1,1,0,-1,-1);
        dinoCards[14] = new Dinosaur("Brachiosaurus",true,"Forest",-1,1,
                -1,0,0,1,0,1);
        dinoCards[15] = new Dinosaur("Deinonychus", false,"Plains",1,-1,
                1,1,1,0,-1,0);

        return dinoCards;
    }

    // Creating the Natural Disaster Card Deck
    private static NaturalDisasterDeck createNaturalDisasterDeck() {
        NaturalDisasterCard card0 = new NaturalDisasterCard("SLIM PICKINGS!",
                "FAMINE: Food is getting hard to find. Are your senses sharp enough to find food?",
                "If your SENSES are average (0) or below average (-) lose 2 food tokens.",
                true, "senses", new int[] {1}, 2);
        NaturalDisasterCard card1 = new NaturalDisasterCard("YUCK!",
                "POISONOUS PLANTS: Are your senses keen enough to tell which plants are good to eat" +
                        " and which are poisonous?", "If your SENSES are average (0) or below average " +
                "(-) lose 2 food tokens.", true, "senses", new int[] {1}, 2);
        NaturalDisasterCard card2 = new NaturalDisasterCard("SPLASH!", "FLOOD: Water is rising " +
                "everywhere. Are you smart enough to reach higher ground?", "If your INTELLIGENCE" +
                " is average (0) or below average (-) lose 4 food tokens.",
                true, "intelligence", new int[] {1}, 4);
        NaturalDisasterCard card3 = new NaturalDisasterCard("OUCH! SAND FELL INTO MY EYES!",
                "SAND STORM: A sand storm will hurt anyone out in the open. Are you smart " +
                        "enough to find shelter?", "If your INTELLIGENCE is average (0) or below " +
                "average (-) lose 1 food token.", true,
                "intelligence", new int[] {1}, 1);
        NaturalDisasterCard card4 = new NaturalDisasterCard("STOP THIEF!", "TINY MAMMALS: Those " +
                "tiny mammals are after your eggs. Are you able to protect your eggs?", "If your " +
                "DEFENSES are average (0) or below average (-) lose 2 food tokens.", true,
                "defenses", new int[] {1}, 2);
        NaturalDisasterCard card5 = new NaturalDisasterCard("BRRR!", "CLIMATE CHANGE: It is getting " +
                "very cold. Are you able to adapt to this colder climate?", "If your ABILITY TO ADAPT" +
                " is average (0) or below average (-) lose 3 food tokens.",
                true, "ata", new int[] {1}, 3);
        NaturalDisasterCard card6 = new NaturalDisasterCard("WATCH OUT!", "VOLCANO erupting! Are you " +
                "fast enough to run away from the hot lava?", "If your SPEED is average (0) or below " +
                "average (-) lose 1 food token.", false, "speed", new int[] {1}, 1);
        NaturalDisasterCard card7 = new NaturalDisasterCard("SHAKE IT UP BABY!", "EARTHQUAKE: Are " +
                "your senses keen enough to warn you of this disaster?", "If your SENSES are average" +
                " (0) or below average (-) lose 1 food token.",
                false, "senses", new int[] {1}, 1);
        NaturalDisasterCard card8 = new NaturalDisasterCard("BONK!", "ROCK SLIDE: Are you fast " +
                "enough to run away from disaster?", "If your SPEED is average (0) or below average" +
                " (-) lose 1 food token.", false, "speed", new int[] {1}, 2);
        NaturalDisasterCard card9 = new NaturalDisasterCard("DO YOU SMELL SMOKE?", "FOREST FIRE: " +
                "A forest fire is blazing toward you. Can you smell the smoke in time to run away?",
                "If your SENSES are average (0) or below average (-) lose 3 food tokens.", false,
                "senses", new int[] {1}, 2);
        NaturalDisasterCard card10 = new NaturalDisasterCard("GET OUT OF MY WAY!", "OVER POPULATION:" +
                " The food supply is scarce because of overcrowding. Are you tough enough to fight" +
                " for your food?", "If your WEAPONS are average (0) or below average (-) lose 2 food " +
                "tokens.", true, "weapons", new int[] {1}, 2);
        NaturalDisasterCard card11 = new NaturalDisasterCard("SIZZLE!", "RADIATION: Radiation from " +
                "the sun is damaging the Earth's ozone layer. Can you protect yourself from sun " +
                "damage?", "If your DEFENSES are average (0) or below average (-) lose 1 food token.",
                false, "defenses", new int[] {1}, 1);
        NaturalDisasterCard card12 = new NaturalDisasterCard("CREAK! CRACK! SHIFT!",
                "SHIFTING CONTINENTS:" +
                        " The conditions in your habitat are changing. Are you able to adapt to these changes?",
                "If your ABILITY TO ADAPT is below average (-) lose 3 food tokens.", false,
                "ata", new int[] {0, 1}, 3);
        NaturalDisasterCard card13 = new NaturalDisasterCard("LOOK OUT!", "MUD SLIDE: The mud will " +
                "bury everything in its path. Are you fast enough to run to safety?", "If your SPEED" +
                " is average (0) or below average (-) lose 2 food tokens.",
                false, "speed", new int[] {1}, 2);
        NaturalDisasterCard card14 = new NaturalDisasterCard("WHAT A MESS!", "POLLUTION! Radiation " +
                "and dust from a super nova are polluting the Earth. Are you able to adapt to " +
                "changes in your environment?", "If your ABILITY TO ADAPT is average (0) or below" +
                " average (-) lose 3 food tokens.", false, "ata", new int[] {1}, 3);
        NaturalDisasterCard card15 = new NaturalDisasterCard("THE SKY IS FALLING!", "METEORITE SHOWER:" +
                " Meteorites are crashing to the Earth. Many dinosours die. Are you reproducing fast" +
                " enough to survive this disaster?", "If your RATE OF REPRODUCTION is average (0) " +
                "or below average (-) lose 3 food tokens.",
                false, "ror", new int[] {1}, 3);
        NaturalDisasterCard card16 = new NaturalDisasterCard("WOW, IT'S HOT!", "CHANGE IN CLIMATE: " +
                "It is hot enough to fry your eggs. Can you reproduce fast enough to escape " +
                "extinction?", "If your RATE OF REPRODUCTION is average (0) or below average (-) " +
                "lose 3 food tokens.", false, "ror", new int[] {1}, 3);
        NaturalDisasterCard card17 = new NaturalDisasterCard("CRACK!", "LIGHTENING: Are you small" +
                " enough to avoid being struck by lightening?", "If your SIZE is above average " +
                "(+) or average (0) lose 1 food token.",
                false, "size", new int[] {-1}, 1);
        NaturalDisasterCard card18 = new NaturalDisasterCard("ITCH! ITCH!", "DISEASE! Pesky insects" +
                " are spreading disease. Dinosaurs who defend themselves by traveling in herds " +
                "are in danger of spreading disease faster.", "If your DEFENSES are above average " +
                "(+) or average (0) lose 1 food token.",
                false, "defenses", new int[] {-1}, 1);
        NaturalDisasterCard card19 = new NaturalDisasterCard("OOPS!", "QUICKSAND! You fell into " +
                "quicksand.", "You are stuck for 2 turns.", true, "none");
        NaturalDisasterDeck ndDeck = new NaturalDisasterDeck();
        ndDeck.setDeck(0, card0);
        ndDeck.setDeck(1, card1);
        ndDeck.setDeck(2, card2);
        ndDeck.setDeck(3, card3);
        ndDeck.setDeck(4, card4);
        ndDeck.setDeck(5, card5);
        ndDeck.setDeck(6, card6);
        ndDeck.setDeck(7, card7);
        ndDeck.setDeck(8, card8);
        ndDeck.setDeck(9, card9);
        ndDeck.setDeck(10, card10);
        ndDeck.setDeck(11, card11);
        ndDeck.setDeck(12, card12);
        ndDeck.setDeck(13, card13);
        ndDeck.setDeck(14, card14);
        ndDeck.setDeck(15, card15);
        ndDeck.setDeck(16, card16);
        ndDeck.setDeck(17, card17);
        ndDeck.setDeck(18, card18);
        ndDeck.setDeck(19, card19);

        return ndDeck;
    }

    // create the attack card deck
    private static AttackDeck createAttackDeck() {
        AttackCard aCard0 = new AttackCard("The dinosaur with the LEAST WEAPONS loses.",
                "The loser moves back 3 spaces.", "weapons", "move", 3, 0);
        AttackCard aCard1 = new AttackCard("The dinosaur with the BEST ABILITY TO ADAPT survives.",
                "The survivor receives 1 food token from the loser.", "ata",
                "food", 1, 0);
        AttackCard aCard2 = new AttackCard("The dinosaur with the LOWEST RATE OF REPRODUCTION loses.",
                "The loser moves back 4 spaces.", "ror", "move", 4, 0);
        AttackCard aCard3 = new AttackCard("The SLOWEST dinosaur loses.",
                "The loser moves back 3 spaces.", "speed", "move", 3, 0);
        AttackCard aCard4 = new AttackCard("Any dinosaur OUT OF ITS HABITAT loses.",
                "The loser moves back 4 spaces.", "habitat", "move", 4, 0);
        AttackCard aCard5 = new AttackCard("The SMALLEST dinosaur loses.",
                "The loser moves back 2 spaces.", "size", "move", 2, 0);
        AttackCard aCard6 = new AttackCard("The dinosaur with the LOWEST ABILITY TO ADAPT loses.",
                "The loser moves back 3 spaces.", "ata", "move", 3, 0);
        AttackCard aCard7 = new AttackCard("The dinosaur with the LEAST SENSES loses.",
                "The loser moves back 2 spaces.", "senses", "move", 2, 0);
        AttackCard aCard8 = new AttackCard("The LARGEST dinosaur survives.",
                "The survivor moves ahead 4 spaces.", "size", "move", 4, 1);
        AttackCard aCard9 = new AttackCard("The MOST INTELLIGENT dinosaur survives.",
                "The survivor moves ahead 2 spaces.", "intelligence",
                "move", 2, 1);
        AttackCard aCard10 = new AttackCard("The dinosaur with the BEST DEFENSES survives.",
                "The survivor moves ahead 3 spaces.", "weapons",
                "move", 3, 1);
        AttackCard aCard11 = new AttackCard("The FASTEST dinosaur survives.",
                "TThe survivor moves ahead 3 spaces.", "weapons",
                "move", 3, 1);
        AttackCard aCard12 = new AttackCard("The dinosaur with the HIGHEST RATE OF REPRODUCTION survives.",
                "The survivor receives 1 food token from the loser.", "ror",
                "food", 1, 0);
        AttackCard aCard13 = new AttackCard("The MOST INTELLIGENT dinosaur survives.",
                "The survivor receives 1 food token from the loser.", "intelligence",
                "food", 1, 0);
        AttackCard aCard14 = new AttackCard("The dinosaur with the BEST DEFENSES survives.",
                "The survivor receives 1 food token from the loser.", "defenses",
                "food", 1, 0);
        AttackCard aCard15 = new AttackCard("The FASTEST dinosaur survives.",
                "The survivor receives 1 food token from the loser.", "speed",
                "food", 1, 0);
        AttackCard aCard16 = new AttackCard("The dinosaur with the BEST SENSES survives.",
                "TThe survivor receives 1 food token from the loser.", "senses",
                "food", 1, 0);
        AttackCard aCard17 = new AttackCard("The dinosaur with the BEST WEAPONS survives.",
                "The survivor receives 1 food token from the loser.", "weapons",
                "food", 1, 0);
        AttackCard aCard18 = new AttackCard("The LARGEST dinosaur survives.",
                "The survivor receives 1 food token from the loser.", "size",
                "food", 1, 0);
        AttackCard aCard19 = new AttackCard("The dinosaur in ITS OWN HABITAT survives.",
                "The survivor receives 1 food token from the loser.", "habitat",
                "food", 1, 0);

        AttackDeck aDeck = new AttackDeck();
        aDeck.setDeck(0, aCard0);
        aDeck.setDeck(1, aCard1);
        aDeck.setDeck(2, aCard2);
        aDeck.setDeck(3, aCard3);
        aDeck.setDeck(4, aCard4);
        aDeck.setDeck(5, aCard5);
        aDeck.setDeck(6, aCard6);
        aDeck.setDeck(7, aCard7);
        aDeck.setDeck(8, aCard8);
        aDeck.setDeck(9, aCard9);
        aDeck.setDeck(10, aCard10);
        aDeck.setDeck(11, aCard11);
        aDeck.setDeck(12, aCard12);
        aDeck.setDeck(13, aCard13);
        aDeck.setDeck(14, aCard14);
        aDeck.setDeck(15, aCard15);
        aDeck.setDeck(16, aCard16);
        aDeck.setDeck(17, aCard17);
        aDeck.setDeck(18, aCard18);
        aDeck.setDeck(19, aCard19);

        return aDeck;
    }

    private static ChallengeDeck createChallengeDeck(){
        ChallengeCard cCard0 = new ChallengeCard("If you are in YOUR HABITAT: move ahead 5 spaces and play that square."
                , "OR", "Receive 1 food token", 0, 1);

        ChallengeCard cCard1 = new ChallengeCard("","Give the next player a food token from the bank"
                , "", 1, 2);

        ChallengeCard cCard2 = new ChallengeCard("CONGRATULATIONS!",
                "Your Dinosaur has evolved above average (+) SPEED" + "and SIZE.",
                "Keep this card to use in any attack situation for the rest of the game.", 2, 3);

        ChallengeCard cCard3 = new ChallengeCard("CONGRATULATIONS!",
                "Your Dinosaur has evolved above average (+) SENSES" + "and INTELLIGENCE.",
                "Keep this card to use in any attack situation for the rest of the game.", 3, 3);

        ChallengeCard cCard4 = new ChallengeCard("If you are NOT in YOUR HABITAT: move back 5 spaces and play that square",
                "OR", "Lose 1 food token.", 4, 1);

        ChallengeCard cCard5 = new ChallengeCard("If you have 6 or more tokens: lose 1 food token.",
                "OR", "Go back 4 spaces.", 5, 1);

        ChallengeCard cCard6 = new ChallengeCard("Move back 2 spaces and play that square.",
                "OR", "Lose a turn.", 6, 0);

        ChallengeCard cCard7 = new ChallengeCard("Move ahead 2 spaces and play that square.",
                "OR", "Lose a turn.", 7, 0);

        ChallengeCard cCard8 = new ChallengeCard("Attack the next player.",
                "OR", "Lose 3 food tokens.", 8, 0);

        ChallengeCard cCard9 = new ChallengeCard("", "Lose 2 food tokens.",
                "", 9, 2);

        ChallengeCard cCard10 = new ChallengeCard("Go back 3 spaces.", "OR",
                "Lose 1 food token.", 10, 0);

        ChallengeCard cCard11 = new ChallengeCard("Receive a food token.", "OR",
                "Roll again.", 11, 0);

        ChallengeCard cCard12 = new ChallengeCard("Receive a food token from the next player.",
                "OR", "Receive 2 food tokens form the bank.", 12, 0);

        ChallengeCard cCard13 = new ChallengeCard("Return to the previous habitat.", "OR",
                "Lose 2 food tokens.", 13, 0);

        ChallengeCard cCard14 = new ChallengeCard("Move to the first square of the next habitat and DO NOT play that square.",
                "OR", "Receive 1 food token", 14, 0);

        ChallengeCard cCard15 = new ChallengeCard("Go ahead 3 spaces and DO NOT play that square.",
                "OR", "Receive 1 food token.", 15, 0);

        ChallengeCard cCard16 = new ChallengeCard("Move to your next food square and receive a food token .",
                "OR", "Roll again.", 16, 0);

        ChallengeCard cCard17 = new ChallengeCard("Give the next player 1 food token.",
                "OR", "Return 2 tokens to the bank.", 17, 0);

        ChallengeCard cCard18 = new ChallengeCard("Return to the previous Disaster Square and play the square.",
                "OR", "Lose 2 food tokens.", 18, 0);

        ChallengeCard cCard19 = new ChallengeCard("Lose 1 food token.", "OR",
                "Lose a turn.", 19, 0);

        ChallengeDeck cDeck = new ChallengeDeck();
        cDeck.setDeck(0, cCard0);
        cDeck.setDeck(1, cCard1);
        cDeck.setDeck(2, cCard2);
        cDeck.setDeck(3, cCard3);
        cDeck.setDeck(4, cCard4);
        cDeck.setDeck(5, cCard5);
        cDeck.setDeck(6, cCard6);
        cDeck.setDeck(7, cCard7);
        cDeck.setDeck(8, cCard8);
        cDeck.setDeck(9, cCard9);
        cDeck.setDeck(10, cCard10);
        cDeck.setDeck(11, cCard11);
        cDeck.setDeck(12, cCard12);
        cDeck.setDeck(13, cCard13);
        cDeck.setDeck(14, cCard14);
        cDeck.setDeck(15, cCard15);
        cDeck.setDeck(16, cCard16);
        cDeck.setDeck(17, cCard17);
        cDeck.setDeck(18, cCard18);
        cDeck.setDeck(19, cCard19);

        return cDeck;
    }

    private static void challengeByID(Player player, int id, int choice, Player[] players, Space[] board,
                                     AttackDeck aDeck, ChallengeDeck cDeck, NaturalDisasterDeck ndDeck){
        //choice is 1 or 2
        switch(id){
            case 0:
                if(choice == 1){
                    player.move(5);
                }
                else{
                    player.changeFood(1);
                }
                break;
            case 1:
                for(int i = 0; i < players.length; i++){
                    if(players[i] == player && i != (players.length - 1)){
                        players[i+1].changeFood(1);
                    }
                    else{
                        players[0].changeFood(1);
                    }
                }
                break;
            case 2:
                player.setEvolveCardSpdSiz(true);
                break;
            case 3:
                player.setEvolveCardSenInt(true);
                break;
            case 4:
                if(choice == 1){
                    turn(player, -5, board, players, cDeck, aDeck, ndDeck);
                }
                else{
                    player.changeFood(-1);
                }
                break;
            case 5:
                if(choice == 1){
                    player.changeFood(-1);
                }
                else{
                    player.move(-4);
                }
                break;
            case 6:
                if(choice == 1){
                    turn(player, -2, board, players, cDeck, aDeck, ndDeck);
                }
                else{
                    player.setLostTurns(1);
                }
                break;
            case 7:
                if(choice == 1){
                    turn(player, 2, board, players, cDeck, aDeck, ndDeck);
                }
                else{
                    player.setLostTurns(1);
                }
                break;
            case 8:
                if(choice == 1){
                    for(int i = 0; i < players.length; i++){
                        if(players[i] == player && i != (players.length - 1)){
                            attack(player, players[i+1], aDeck, board, false);
                        }
                        else{
                            attack(player, players[0], aDeck, board, false);
                        }
                    }
                }
                else{
                    player.changeFood(-3);
                }
                break;
            case 9:
                player.changeFood(-2);
                break;
            case 10:
                if(choice == 1){
                    player.move(-3);
                }
                else{
                    player.changeFood(-1);
                }
                break;
            case 11:
                if(choice == 1){
                    player.changeFood(1);
                }
                else {
                    int roll = (int)(Math.random() * 6 + 1);
                    turn(player, roll, board, players, cDeck, aDeck, ndDeck);
                }
                break;
            case 12:
                if(choice == 1){
                    for(int i = 0; i < players.length; i++){
                        if(players[i] == player && i != (players.length - 1)){
                            player.changeFood(1);
                            players[i+1].changeFood(-1);
                        }
                        else{
                            player.changeFood(1);
                            players[0].changeFood(-1);
                        }
                    }
                }
                else{
                    player.changeFood(2);
                }
                break;
            case 13:
                if(choice == 1){
                    //return to previous habitat
                    String currentHab = board[player.getLocation()].getHabitat();
                    int count = 0;
                    for(int i = player.getLocation(); board[i].getHabitat().equals(currentHab); i--){
                        count--;
                    }
                    player.move(count);
                }
                else {
                    player.changeFood(-2);
                }
                break;
            case 14:
                if(choice == 1){
                    //move to next habitat
                    String currentHab = board[player.getLocation()].getHabitat();
                    int count = 0;
                    for(int i = player.getLocation(); board[i].getHabitat().equals(currentHab); i++){
                        count++;
                    }
                    player.move(count);
                }
                else {
                    player.changeFood(1);
                }
                break;
            case 15:
                if(choice == 1){
                    player.move(3);
                }
                else {
                    player.changeFood(1);
                }
                break;
            case 16:
                if(choice == 1){
                    //move to your next food square
                    int count = 0;
                    boolean isHerb = player.getDino().isHerbivore();
                    String diet;
                    if(isHerb){
                        diet = "herbivore";
                    }
                    else{
                        diet = "carnivore";
                    }
                    for(int i = player.getLocation(); !board[i].getType().equals(diet); i++){
                        count++;
                    }
                    player.move(count);
                }
                else {
                    int roll = (int)(Math.random() * 6 + 1);
                    turn(player, roll, board, players, cDeck, aDeck, ndDeck);
                }
                break;
            case 17:
                if(choice == 1){
                    for(int i = 0; i < players.length; i++){
                        if(players[i] == player && i != (players.length - 1)){
                            player.changeFood(-1);
                            players[i+1].changeFood(1);
                        }
                        else{
                            player.changeFood(-1);
                            players[0].changeFood(1);
                        }
                    }
                }
                else{
                    player.changeFood(-2);
                }
                break;
            case 18:
                if(choice == 1){
                    //go back to previous disaster square and play it
                    int count = 0;
                    for(int i = player.getLocation(); !board[i].getType().equals("natural disaster"); i--){
                        count--;
                    }
                    turn(player, count, board, players, cDeck, aDeck, ndDeck);
                }
                else {
                    player.changeFood(-2);
                }
                break;
            case 19:
                if(choice == 1){
                    player.changeFood(-1);
                }
                else {
                    player.setLostTurns(1);
                }
                break;
        }
    }

    private static void attack(Player p1, Player p2, AttackDeck aDeck, Space[] board, boolean prev){
        AttackCard aCard = aDeck.draw();
        String statChecked = aCard.getStat();
        boolean tie = false;
        switch(statChecked) { //{"speed", "size", "intelligence", "defenses",
            //    "weapons", "senses", "ror", "ata", "habitat"}
            case "speed":
                if (p1.getDino().getSpeed() > p2.getDino().getSpeed()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getSpeed() < p2.getDino().getSpeed()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "size":
                if (p1.getDino().getSize() > p2.getDino().getSize()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getSize() < p2.getDino().getSize()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "intelligence":
                if (p1.getDino().getIntelligence() > p2.getDino().getIntelligence()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getIntelligence() < p2.getDino().getIntelligence()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "defenses":
                if (p1.getDino().getDefenses() > p2.getDino().getDefenses()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getDefenses() < p2.getDino().getDefenses()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "weapons":
                if (p1.getDino().getWeapons() > p2.getDino().getWeapons()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getWeapons() < p2.getDino().getWeapons()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "senses":
                if (p1.getDino().getSenses() > p2.getDino().getSenses()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getSenses() < p2.getDino().getSenses()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "ror":
                if (p1.getDino().getRor() > p2.getDino().getRor()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getRor() < p2.getDino().getRor()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "ata":
                if (p1.getDino().getAta() > p2.getDino().getAta()) {
                    determinePenalty(p1, p2, aCard);
                } else if (p1.getDino().getAta() < p2.getDino().getAta()) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
            case "habitat":
                if (board[p1.getLocation()].getHabitat().equals(p1.getDino().getHabitat()) &&
                        !board[p2.getLocation()].getHabitat().equals(p2.getDino().getHabitat())) {
                    determinePenalty(p1, p2, aCard);
                } else if (!board[p1.getLocation()].getHabitat().equals(p1.getDino().getHabitat()) &&
                        board[p2.getLocation()].getHabitat().equals(p2.getDino().getHabitat())) {
                    determinePenalty(p2, p1, aCard);
                } else {
                    tie = true;
                }
                break;
        }

        if(tie && !prev){  // have tied once but not twice
            attack(p1, p2, aDeck, board, true);
        }
    }

    private static void determinePenalty(Player p1, Player p2, AttackCard aCard){
        if (aCard.getWinner() == 0) {
            if (aCard.getPenalty().equals("food")) {
                p2.changeFood(-1);
                p1.changeFood(1);
            } else {
                p2.move(-1 * aCard.getPenaltyAmount());
            }
        } else {
            if (aCard.getPenalty().equals("food")) {
                p2.changeFood(-1);
                p1.changeFood(1);
            } else {
                p1.move(aCard.getPenaltyAmount());
            }
        }
    }

    private static void naturalDisaster(Player p, NaturalDisasterDeck ndDeck, Space[] board){
        NaturalDisasterCard ndCard = ndDeck.draw();
        boolean safe = false;
        boolean none = false;
        if(ndCard.getHabitatSafe()){
            if(board[p.getLocation()].getHabitat().equals(p.getDino().getHabitat())){
                safe = true;
            }
        }
        else{
            String statChecked = ndCard.getStat();
            int pStat;
            switch (statChecked){
                case "speed":
                    pStat = p.getDino().getSpeed();
                    break;
                case "size":
                    pStat = p.getDino().getSize();
                    break;
                case "intelligence":
                    pStat = p.getDino().getIntelligence();
                    break;
                case "defenses":
                    pStat = p.getDino().getDefenses();
                    break;
                case "weapons":
                    pStat = p.getDino().getWeapons();
                    break;
                case "senses":
                    pStat = p.getDino().getSenses();
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
            for(int x: ndCard.getSafeStatValues()){
                if(x == pStat){
                    safe = true;
                }
            }
        }
        if(!safe){
            if(none){
                p.setLostTurns(2);
            }
            else{
                p.changeFood(-1 * ndCard.getFoodLost());
            }
        }
    }
}