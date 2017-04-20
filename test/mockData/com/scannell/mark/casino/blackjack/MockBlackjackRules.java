package mockData.com.scannell.mark.casino.blackjack;

import com.scannell.mark.casino.blackjack.BlackjackRules;

public class MockBlackjackRules {
    private static BlackjackRules defaultRules;
    private static BlackjackRules doubleAfterSplitRules;
    private static BlackjackRules dealerHitSoft17Rules;
    
    /**
     * Retrieve a set of default rules.  The default rules are:<br>
     * the payout for Blackjack is 1.5x the bet,<br>
     * the dealer stands on soft 17,<br>
     * double after split is not allowed,<br>
     * the maximum hands after splits is 4,<br>
     * the player can't re-split Aces.
     * @return A set of rules.
     */
    public static BlackjackRules getDefaultRules() {
        if (defaultRules == null) {
            BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
            rulesBuilder.setBlackjackPayoutMultiple(1.5);
            rulesBuilder.setDealerHitsSoft17(false);
            rulesBuilder.setDoubleAfterSplitAllowed(false);
            rulesBuilder.setMaxHandsAfterSplits(4);
            rulesBuilder.setCanResplitAces(false);
            defaultRules = rulesBuilder.build();
        }
        
        return defaultRules;
    }
    
    /**
     * Retrieve a set of rules for the default rules settings except that double-after-split is allowed.
     * @return A set of rules.
     */
    public static BlackjackRules getDASRules() {
    	if (doubleAfterSplitRules ==  null) {
    		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
            rulesBuilder.setBlackjackPayoutMultiple(1.5);
            rulesBuilder.setDealerHitsSoft17(false);
            rulesBuilder.setDoubleAfterSplitAllowed(true);
            rulesBuilder.setMaxHandsAfterSplits(4);
            rulesBuilder.setCanResplitAces(false);
            doubleAfterSplitRules = rulesBuilder.build();
    	}
    	
    	return doubleAfterSplitRules;
    }
    
    /**
     * Retrieve a set of rules for the default rules settings except that dealer must hit soft 17.
     * @return A set of rules.
     */
    public static BlackjackRules getDealerHitSoft17Rules() {
    	if (dealerHitSoft17Rules ==  null) {
    		BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
            rulesBuilder.setBlackjackPayoutMultiple(1.5);
            rulesBuilder.setDealerHitsSoft17(true);
            rulesBuilder.setDoubleAfterSplitAllowed(false);
            rulesBuilder.setMaxHandsAfterSplits(4);
            rulesBuilder.setCanResplitAces(false);
            dealerHitSoft17Rules = rulesBuilder.build();
    	}
    	
    	return dealerHitSoft17Rules;
    }
}
