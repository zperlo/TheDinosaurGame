public class Runner {
    public static void main(String[] args) {

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

        // Test for assigning dinos to 4 players randomly
        int p1, p2, p3, p4;
        p1 = (int)(Math.random() * dinoCards.length);
        p2 = (int)(Math.random() * dinoCards.length);
        p3 = (int)(Math.random() * dinoCards.length);
        p4 = (int)(Math.random() * dinoCards.length);

        // Ensure no players have same dino (Doesn't work fully but shouldn't matter in final)
        if (p1 == p2) p2 = (p2 + 1) % dinoCards.length;
        if (p1 == p3) p3 = (p3 + 1) % dinoCards.length;
        if (p2 == p3) p3 = (p3 + 1) % dinoCards.length;
        if (p1 == p4) p4 = (p4 + 1) % dinoCards.length;
        if (p2 == p4) p4 = (p4 + 1) % dinoCards.length;
        if (p3 == p4) p4 = (p4 + 1) % dinoCards.length;

        System.out.println("Player 1 is a " + dinoCards[p1].name);
        System.out.println("Player 2 is a " + dinoCards[p2].name);
        System.out.println("Player 3 is a " + dinoCards[p3].name);
        System.out.println("Player 4 is a " + dinoCards[p4].name);
        
        
        // Creating the Natural Disaster Card Deck
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
        NaturalDisasterDeck NDDeck = new NaturalDisasterDeck();
        NDDeck.setDeck(0, card0);
        NDDeck.setDeck(1, card1);
        NDDeck.setDeck(2, card2);
        NDDeck.setDeck(3, card3);
        NDDeck.setDeck(4, card4);
        NDDeck.setDeck(5, card5);
        NDDeck.setDeck(6, card6);
        NDDeck.setDeck(7, card7);
        NDDeck.setDeck(8, card8);
        NDDeck.setDeck(9, card9);
        NDDeck.setDeck(10, card10);
        NDDeck.setDeck(11, card11);
        NDDeck.setDeck(12, card12);
        NDDeck.setDeck(13, card13);
        NDDeck.setDeck(14, card14);
        NDDeck.setDeck(15, card15);
        NDDeck.setDeck(16, card16);
        NDDeck.setDeck(17, card17);
        NDDeck.setDeck(18, card18);
        NDDeck.setDeck(19, card19);
        //NDDeck tests
        //NaturalDisasterCard myCard = NDDeck.draw();
        //System.out.println(myCard.getPara1());

        //Creating the attack card deck
        AttackCard Acard0 = new AttackCard("The dinosaur with the LEAST WEAPONS loses.",
                "The loser moves back 3 spaces.", "weapons", "move", 3, 0);
        AttackCard Acard1 = new AttackCard("The dinosaur with the BEST ABILITY TO ADAPT survives.",
                "The survivor receives 1 food token from the loser.", "ata",
                "food", 1, 0);
        AttackCard Acard2 = new AttackCard("The dinosaur with the LOWEST RATE OF REPRODUCTION loses.",
                "The loser moves back 4 spaces.", "ror", "move", 4, 0);
        AttackCard Acard3 = new AttackCard("The SLOWEST dinosaur loses.",
                "The loser moves back 3 spaces.", "speed", "move", 3, 0);
        AttackCard Acard4 = new AttackCard("Any dinosaur OUT OF ITS HABITAT loses.",
                "The loser moves back 4 spaces.", "habitat", "move", 4, 0);
        AttackCard Acard5 = new AttackCard("The SMALLEST dinosaur loses.",
                "The loser moves back 2 spaces.", "size", "move", 2, 0);
        AttackCard Acard6 = new AttackCard("The dinosaur with the LOWEST ABILITY TO ADAPT loses.",
                "The loser moves back 3 spaces.", "ata", "move", 3, 0);
        AttackCard Acard7 = new AttackCard("The dinosaur with the LEAST SENSES loses.",
                "The loser moves back 2 spaces.", "senses", "move", 2, 0);
        AttackCard Acard8 = new AttackCard("The LARGEST dinosaur survives.",
                "The survivor moves ahead 4 spaces.", "size", "move", 4, 1);
        AttackCard Acard9 = new AttackCard("The MOST INTELLIGENT dinosaur survives.",
                "The survivor moves ahead 2 spaces.", "intelligence",
                "move", 2, 1);
        AttackCard Acard10 = new AttackCard("The dinosaur with the BEST DEFENSES survives.",
                "The survivor moves ahead 3 spaces.", "weapons",
                "move", 3, 1);
        AttackCard Acard11 = new AttackCard("The FASTEST dinosaur survives.",
                "TThe survivor moves ahead 3 spaces.", "weapons",
                "move", 3, 1);
        AttackCard Acard12 = new AttackCard("The dinosaur with the HIGHEST RATE OF REPRODUCTION survives.",
                "The survivor receives 1 food token from the loser.", "ror",
                "food", 1, 0);
        AttackCard Acard13 = new AttackCard("The MOST INTELLIGENT dinosaur survives.",
                "The survivor receives 1 food token from the loser.", "intelligence",
                "food", 1, 0);
        AttackCard Acard14 = new AttackCard("The dinosaur with the BEST DEFENSES survives.",
                "The survivor receives 1 food token from the loser.", "defenses",
                "food", 1, 0);
        AttackCard Acard15 = new AttackCard("The FASTEST dinosaur survives.",
                "The survivor receives 1 food token from the loser.", "speed",
                "food", 1, 0);
        AttackCard Acard16 = new AttackCard("The dinosaur with the BEST SENSES survives.",
                "TThe survivor receives 1 food token from the loser.", "senses",
                "food", 1, 0);
        AttackCard Acard17 = new AttackCard("The dinosaur with the BEST WEAPONS survives.",
                "The survivor receives 1 food token from the loser.", "weapons",
                "food", 1, 0);
        AttackCard Acard18 = new AttackCard("The LARGEST dinosaur survives.",
                "The survivor receives 1 food token from the loser.", "size",
                "food", 1, 0);
        AttackCard Acard19 = new AttackCard("The dinosaur in ITS OWN HABITAT survives.",
                "The survivor receives 1 food token from the loser.", "habitat",
                "food", 1, 0);

        AttackDeck ADeck = new AttackDeck();
        ADeck.setDeck(0, Acard0);
        ADeck.setDeck(1, Acard1);
        ADeck.setDeck(2, Acard2);
        ADeck.setDeck(3, Acard3);
        ADeck.setDeck(4, Acard4);
        ADeck.setDeck(5, Acard5);
        ADeck.setDeck(6, Acard6);
        ADeck.setDeck(7, Acard7);
        ADeck.setDeck(8, Acard8);
        ADeck.setDeck(9, Acard9);
        ADeck.setDeck(10, Acard10);
        ADeck.setDeck(11, Acard11);
        ADeck.setDeck(12, Acard12);
        ADeck.setDeck(13, Acard13);
        ADeck.setDeck(14, Acard14);
        ADeck.setDeck(15, Acard15);
        ADeck.setDeck(16, Acard16);
        ADeck.setDeck(17, Acard17);
        ADeck.setDeck(18, Acard18);
        ADeck.setDeck(19, Acard19);
        //ADeck tests
        //AttackCard myCard1 = ADeck.draw();
        //System.out.println(myCard1.getPara1());
    }
}