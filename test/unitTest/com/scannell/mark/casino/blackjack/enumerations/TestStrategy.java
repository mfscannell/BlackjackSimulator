package unitTest.com.scannell.mark.casino.blackjack.enumerations;

import static org.junit.Assert.*;

import org.junit.Test;

import com.scannell.mark.casino.blackjack.enumerations.Strategy;

public class TestStrategy {

	@Test
	public void values_0_BASIC_STRATEGY() {
		Strategy strategy = Strategy.values()[0];
		assertTrue(strategy == Strategy.BASIC_STRATEGY);
	}
	
	@Test
	public void values_1_COMPOSITION_STRATEGY() {
		Strategy strategy = Strategy.values()[1];
		assertTrue(strategy == Strategy.COMPOSITION_STRATEGY);
	}
	
	@Test
	public void values_2_KISSI_STRATEGY() {
		Strategy strategy = Strategy.values()[2];
		assertTrue(strategy == Strategy.KISS_I_STRATEGY);
	}
}
