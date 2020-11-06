package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.utils.Utils;

class TestGiveCarSharing {

	@Test
	void testGiveCarSharing1() {
		assertNull(Utils.giveCarSharing("null"));
	}
	@Test
	void testGiveCarSharing2() {
		String result = Utils.giveCarSharing("Enjoy");
		assertEquals("Enjoy",result);
	}

}
