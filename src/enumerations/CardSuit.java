package enumerations;

public enum CardSuit {
    SPADES(1),
    CLUBS(2),
    HEARTS(3),
    DIAMONDS(4);
    
    private String spades = "Spades";
    private String clubs = "Clubs";
    private String hearts = "Hearts";
    private String diamonds = "Diamonds";
    
    private int suit;
    
    private CardSuit(int suit) {
        this.suit = suit;
    }
    
    public String toString() {
        String string;
        
        switch (this) {
            case SPADES:
                string = spades;
                break;
            case CLUBS:
                string = clubs;
                break;
            case HEARTS:
                string = hearts;
                break;
            case DIAMONDS:
                string = diamonds;
                break;
            default:
                string = "";
                break;
        }
        return string;
    }
}
