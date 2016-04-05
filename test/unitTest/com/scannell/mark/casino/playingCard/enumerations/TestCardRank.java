package unitTest.com.scannell.mark.casino.playingCard.enumerations;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.enumerations.Strategy;
import com.scannell.mark.casino.playingCard.enumerations.CardRank;

public class TestCardRank {
	@Test
	public void values_1_ACE() {
		CardRank cardRank = CardRank.values()[0];
		assertTrue(cardRank == CardRank.ACE);
	}
}
