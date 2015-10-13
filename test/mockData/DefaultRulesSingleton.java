package mockData;

import rules.BlackjackRules;

public class DefaultRulesSingleton {
    private static BlackjackRules rules;
    
    /**
     * Retrieve a set of rules for the default basic strategy chart.  The default rules are
     * the dealer stands on soft 17 and double after split is not allowed.
     */
    public static BlackjackRules getDefaultRules() {
        if (rules == null) {
            BlackjackRules.Builder rulesBuilder = new BlackjackRules.Builder();
            rulesBuilder.setBlackjackPayoutMultiple(1.5);
            rulesBuilder.setDealerHitsSoft17(false);
            rulesBuilder.setDoubleAfterSplitAllowed(false);
            rulesBuilder.setMaxHandsAfterSplits(4);
            rulesBuilder.setCanResplitAces(false);
            rules = rulesBuilder.build();
        }
        
        return rules;
    }

}
