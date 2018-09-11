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

        // Ensure no players have same dino (Doesn't work fully but shouldn't matter in final
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
    }
}