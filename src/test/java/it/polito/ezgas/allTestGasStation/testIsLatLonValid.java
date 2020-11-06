package it.polito.ezgas.allTestGasStation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import it.polito.ezgas.utils.Utils;

class TestIslLatLonValid {
	
	@Test
	public void testIsLatLonValid1() {
		boolean res;
		res = Utils.isLatLonValid(91, 150);
		assertFalse(res);
	}
	@Test
	public void testIsLatLonValid2() {
		boolean res;
		res = Utils.isLatLonValid(-91, 150);
		assertFalse(res);
	}
	@Test
	public void testIsLatLonValid3() {
		boolean res;
		res = Utils.isLatLonValid(50, 190);
		assertFalse(res);
	}
	@Test
	public void testIsLatLonValid4() {
		boolean res;
		res = Utils.isLatLonValid(50, -190);
		assertFalse(res);
	}
	@Test
	public void testIsLatLonValid5() {
		boolean res;
		res = Utils.isLatLonValid(-91, -190);
		assertFalse(res);
	}
	@Test
	public void testIsLatLonValid6() {
		boolean res;
		res = Utils.isLatLonValid(50, 150);
		assertTrue(res);
	}

}
