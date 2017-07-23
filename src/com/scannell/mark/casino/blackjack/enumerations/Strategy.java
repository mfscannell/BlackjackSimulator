package com.scannell.mark.casino.blackjack.enumerations;

public enum Strategy {
	BASIC_STRATEGY(0),
    COMPOSITION_STRATEGY(1),
    KISS_I_STRATEGY(2),
    MARTINGALE_STRATEGY(3),
    MARTINGALE_KISS_STRATEGY(4);
    
    private String basicStrategy = "Basic Strategy";
    private String compositionStrategy = "Composition Strategy";
    private String kissIStrategy = "KISS I Strategy";
    private String martingaleStrategy  = "Martingale Strategy";
    private String martingaleKissStrategy = "Martingale KISS Strategy";
    
    int strategy;
    
    private Strategy(int strategy) {
        this.strategy = strategy;
    }
    
    public String toString() {
        String string;
        
        switch (this) {
            case BASIC_STRATEGY:
                string = basicStrategy;
                break;
            case COMPOSITION_STRATEGY:
                string = compositionStrategy;
                break;
            case KISS_I_STRATEGY:
                string = kissIStrategy;
                break;
            case MARTINGALE_STRATEGY:
                string = martingaleStrategy;
                break;
            case MARTINGALE_KISS_STRATEGY:
                string = martingaleKissStrategy;
                break;
            default:
                string = "";
                break;
        }
        
        return string;
    }
}
