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
        NaturalDisasterCard card0 = new NaturalDisasterCard("SLIM PICKINGS!\nFAMINE: Food is getting hard to find. Are your senses sharp enough to find food?\nIf your SENSES are average (0) or below average (-) lose 2 food tokens.\nHABITAT SAFE", true, "senses", new int[] {1}, 2);
        NaturalDisasterCard card1 = new NaturalDisasterCard("YUCK!\nPOISONOUS PLANTS: Are your senses keen enough to tell which plants are good to eat and which are poisonous?\nIf your SENSES are average (0) or below average (-) lose 2 food tokens.\nHABITAT SAFE", true, "senses", new int[] {1}, 2);
        NaturalDisasterCard card2 = new NaturalDisasterCard("SPLASH!\nFLOOD: Water is rising everywhere. Are you smart enough to reach higher ground?\nIf your INTELLIGENCE is average (0) or below average (-) lose 4 food tokens.\nHABITAT SAFE", true, "intelligence", new int[] {1}, 4);
        NaturalDisasterCard card3 = new NaturalDisasterCard("OUCH! SAND FELL INTO MY EYES!\nSAND STORM: A sand storm will hurt anyone out in the open. Are you smart enough to find shelter?\nIf your INTELLIGENCE is average (0) or below average (-) lose 1 food token.\nHABITAT SAFE", true, "intelligence", new int[] {1}, 1);
        NaturalDisasterCard card4 = new NaturalDisasterCard("STOP THIEF!\nTINY MAMMALS: Those tiny mammals are after your eggs. Are you able to protect your eggs?\nIf your DEFENSES are average (0) or below average (-) lose 2 food tokens.\nHABITAT SAFE", true, "defenses", new int[] {1}, 2);
        NaturalDisasterCard card5 = new NaturalDisasterCard("BRRR!\nCLIMATE CHANGE: It is getting very cold. Are you able to adapt to this colder climate?\nIf your ABILITY TO ADAPT is average (0) or below average (-) lose 3 food tokens.\nHABITAT SAFE", true, "ata", new int[] {1}, 3);
        NaturalDisasterCard card6 = new NaturalDisasterCard("WATCH OUT!\nVOLCANO erupting! Are you fast enough to run away from the hot lava?\nIf your SPEED is average (0) or below average (-) lose 1 food token.", false, "speed", new int[] {1}, 1);
        NaturalDisasterCard card7 = new NaturalDisasterCard("SHAKE IT UP BABY!\nEARTHQUAKE: Are your senses keen enough to warn you of this disaster?\nIf your SENSES are average (0) or below average (-) lose 1 food token.", false, "senses", new int[] {1}, 1);
        NaturalDisasterCard card8 = new NaturalDisasterCard("BONK!\nROCK SLIDE: Are you fast enough to run away from disaster?\nIf your SPEED is average (0) or below average (-) lose 1 food token.", false, "speed", new int[] {1}, 2);
        NaturalDisasterCard card9 = new NaturalDisasterCard("DO YOU SMELL SMOKE?\nFOREST FIRE: A forest fire is blazing toward you. Can you smell the smoke in time to run away?\nIf your SENSES are average (0) or below average (-) lose 3 food tokens.", false, "senses", new int[] {1}, 2);
        NaturalDisasterCard card10 = new NaturalDisasterCard("GET OUT OF MY WAY!\nOVER POPULATION: The food supply is scarce because of overcrowding. Are you tough enough to fight for your food?\nIf your WEAPONS are average (0) or below average (-) lose 2 food tokens.\nHABITAT SAFE", true, "weapons", new int[] {1}, 2);
        NaturalDisasterCard card11 = new NaturalDisasterCard("SIZZLE!\nRADIATION: Radiation from the sun is damaging the Earth's ozone layer. Can you protect yourself from sun damage?\nIf your DEFENSES are average (0) or below average (-) lose 1 food token.", false, "defenses", new int[] {1}, 1);
        NaturalDisasterCard card12 = new NaturalDisasterCard("CREAK! CRACK! SHIFT!\nSHIFTING CONTINENTS: The conditions in your habitat are changing. Are you able to adapt to these changes?\nIf your ABILITY TO ADAPT is below average (-) lose 3 food tokens.", false, "ata", new int[] {0, 1}, 3);
        NaturalDisasterCard card13 = new NaturalDisasterCard("LOOK OUT!\nMUD SLIDE: The mud will bury everything in its path. Are you fast enough to run to safety?\nIf your SPEED is average (0) or below average (-) lose 2 food tokens.", false, "speed", new int[] {1}, 2);
        NaturalDisasterCard card14 = new NaturalDisasterCard("WHAT A MESS!\nPOLLUTION! Radiation and dust from a super nova are polluting the Earth. Are you able to adapt to changes in your environment?\nIf your ABILITY TO ADAPT is average (0) or below average (-) lose 3 food tokens.", false, "ata", new int[] {1}, 3);
        NaturalDisasterCard card15 = new NaturalDisasterCard("THE SKY IS FALLING!\nMETEORITE SHOWER: Meteorites are crashing to the Earth. Many dinosours die. Are you reproducing fast enough to survive this disaster?\nIf your RATE OF REPRODUCTION is average (0) or below average (-) lose 3 food tokens.", false, "ror", new int[] {1}, 3);
        NaturalDisasterCard card16 = new NaturalDisasterCard("WOW, IT'S HOT!\nCHANGE IN CLIMATE: It is hot enough to fry your eggs. Can you reproduce fast enough to escape extinction?\nIf your RATE OF REPRODUCTION is average (0) or below average (-) lose 3 food tokens.", false, "ror", new int[] {1}, 3);
        NaturalDisasterCard card17 = new NaturalDisasterCard("CRACK!\nLIGHTENING: Are you small enough to avoid being struck by lightening?\nIf your SIZE is above average (+) or average (0) lose 1 food token.", false, "size", new int[] {-1}, 1);
        NaturalDisasterCard card18 = new NaturalDisasterCard("ITCH! ITCH!\nDISEASE! Pesky insects are spreading disease. Dinosaurs who defend themselves by traveling in herds are in danger of spreading disease faster.\nIf your DEFENSES are above average (+) or average (0) lose 1 food token.", false, "defenses", new int[] {-1}, 1);
        NaturalDisasterCard card19 = new NaturalDisasterCard("OOPS!\nQUICKSAND! You fell into quicksand.\nYou are stuck for 2 turns.\nHABITAT SAFE", true, "none");
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
        NaturalDisasterCard myCard = NDDeck.draw();
        System.out.println(myCard.getText());
    }
}