package com.scannell.mark.casino.playingCard.enumerations;

/**
 * This enumeration represents all possible ranks of a playing card (Ace, King, Queen, etc.)
 * @author mscannell
 *
 */
public enum CardRank {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);
    
    private String ace = "A";
    private String two = "2";
    private String three = "3";
    private String four = "4";
    private String five = "5";
    private String six = "6";
    private String seven = "7";
    private String eight = "8";
    private String nine = "9";
    private String ten = "10";
    private String jack = "J";
    private String queen = "Q";
    private String king = "K";
    
    private int rank;
    
    private CardRank(int rank) {
        this.rank = rank;
    }
    
    public String toString() {
        String string = "";
        
        switch (this) {
            case ACE:
                string = ace;
                break;
            case TWO:
                string = two;
                break;
            case THREE:
                string = three;
                break;
            case FOUR:
                string = four;
                break;
            case FIVE:
                string = five;
                break;
            case SIX:
                string = six;
                break;
            case SEVEN:
                string = seven;
                break;
            case EIGHT:
                string = eight;
                break;
            case NINE:
                string = nine;
                break;
            case TEN:
                string = ten;
                break;
            case JACK:
                string = jack;
                break;
            case QUEEN:
                string = queen;
                break;
            case KING:
                string = king;
                break;
            default:
                string = "";
                break;
        }
        
        return string;
    }
}
