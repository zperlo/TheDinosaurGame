public class ChallengeCard {
    private String choice1;
    private String middlePara; // for choice cards, this is "OR"
    private String choice2;
    private int id;
    private int type; // 0 - 4 for different types of challenge cards

    public ChallengeCard(String choice1, String choice2, int id){ // for type 0 cards
        this.choice1 = choice1;
        this.middlePara = "OR";
        this.choice2 = choice2;
        this.id = id;
        this.type = 0;
    }

    public ChallengeCard(String choice1, String middlePara, String choice2, int id, int type){ // for type 1-4 cards
        this.choice1 = choice1;
        this.middlePara = middlePara;
        this.choice2 = choice2;
        this.id = id;
        this.type = type;
    }

    public String getChoice1(){
        return this.choice1;
    }

    public String getMiddlePara(){
        return this.middlePara;
    }

    public String getChoice2(){
        return this.choice2;
    }

    public int getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }
}