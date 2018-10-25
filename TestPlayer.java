import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestPlayer {
    private Dinosaur exampleDino = new Dinosaur("Velociraptor", false, "Desert", 1,-1,
            1,0,1,0,0,-1);
    private Player examplePlayer = new Player(new Dinosaur("Styracosaurus", true, "Forest",0,-1,
            1, -1,-1,1,0,0), 5);

    @Test public void testMove(){
        for(int i = 0; i < 30; i++){
            int oldLocation = examplePlayer.getLocation();
            examplePlayer.move(i);
            if(oldLocation + i > 105)
                assertEquals(oldLocation, examplePlayer.getLocation());
            else
                assertEquals(oldLocation + i, examplePlayer.getLocation());
        }
        for(int i = 0; i >= -30; i--){
            int oldLocation = examplePlayer.getLocation();
            examplePlayer.move(i);
            if(oldLocation + i < 0) {
                assertEquals(0, examplePlayer.getLocation());
            }
            else
                assertEquals(oldLocation + i, examplePlayer.getLocation());
        }
    }

    @Test public void testChangeFood(){
        for(int i = 0; i < 30; i++){
            int oldFood = examplePlayer.getFoodTokens();
            examplePlayer.changeFood(i);
            assertEquals(oldFood + i, examplePlayer.getFoodTokens());
        }
        for(int i = 0; i >= -15; i--){
            int oldFood = examplePlayer.getFoodTokens();
            examplePlayer.changeFood(i);
            assertEquals(oldFood + i, examplePlayer.getFoodTokens());
        }
    }

    @Test public void testSetEvolveCardSenInt(){
        boolean isEvolvedSenInt = true;
        examplePlayer.setEvolveCardSenInt(isEvolvedSenInt);
        assertEquals(isEvolvedSenInt, examplePlayer.isEvolveCardSenInt());

        isEvolvedSenInt = false;
        examplePlayer.setEvolveCardSenInt(isEvolvedSenInt);
        assertEquals(isEvolvedSenInt, examplePlayer.isEvolveCardSenInt());
    }

    @Test public void testSetEvolveCardSpdSiz(){
        boolean isEvolvedSpdSiz = true;
        examplePlayer.setEvolveCardSenInt(isEvolvedSpdSiz);
        assertEquals(isEvolvedSpdSiz, examplePlayer.isEvolveCardSenInt());

        isEvolvedSpdSiz = false;
        examplePlayer.setEvolveCardSenInt(isEvolvedSpdSiz);
        assertEquals(isEvolvedSpdSiz, examplePlayer.isEvolveCardSenInt());
    }

    @Test public void testSetLostTurns(){
        for(int i = 0; i < 5; i++){
            examplePlayer.setLostTurns(i);
            assertEquals(i, examplePlayer.getLostTurns());
        }
    }

    @Test public void testSetSecondChance(){
        boolean isSecondChance = true;
        examplePlayer.setSecondChance(isSecondChance);
        assertEquals(isSecondChance, examplePlayer.getSecondChance());

        isSecondChance = false;
        examplePlayer.setSecondChance(isSecondChance);
        assertEquals(isSecondChance, examplePlayer.getSecondChance());
    }

    @Test public void testSetFoodTokens(){
        for(int i = 0; i < 15; i++){
            examplePlayer.setFoodTokens(i);
            assertEquals(i, examplePlayer.getFoodTokens());
        }
    }

    @Test public void testSetExtinct(){
        boolean isExtinct = true;
        examplePlayer.setExtinct(isExtinct);
        assertEquals(isExtinct, examplePlayer.isExtinct());

        isExtinct = false;
        examplePlayer.setExtinct(isExtinct);
        assertEquals(isExtinct, examplePlayer.isExtinct());
    }
}
