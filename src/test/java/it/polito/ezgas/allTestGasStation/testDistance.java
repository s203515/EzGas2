package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exception.GPSDataException;
import it.polito.ezgas.utils.Utils;

class TestDistance {

	

	@Test
	void testDistance1() {
		double res;
		try {
			res = Utils.calculateDistance(53.32055555555556, 53.31861111111111, -1.7297222222222221, -1.6997222222222223);
			assertEquals(res,2.0043678382716137,0.001);
		} catch (GPSDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//1 metro di errore consentito
		
	}

	@Test
	void testDistance2() {
		double res;
		try {
			res = Utils.calculateDistance(53.32055555555556, 53.32055555555556, 1, 1);
			assertEquals(res,0,0.1);
		} catch (GPSDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	void testDistance3() {
	Assertions.assertThrows(exception.GPSDataException.class, () -> Utils.calculateDistance(90.1, 53.32055555555556, 20, 1));
	}

	@Test
	void testDistance4() {
		Assertions.assertThrows(exception.GPSDataException.class, () -> Utils.calculateDistance(89, 53.32055555555556, 180.1, 1));
		
	}

	@Test
	void testDistance5() {
		Assertions.assertThrows(exception.GPSDataException.class, () -> Utils.calculateDistance(-90.1, 90.1, 180.1, -180.1));
		
	}
	@Test
	void testDistance6() {
		Assertions.assertThrows(exception.GPSDataException.class, () -> Utils.calculateDistance(89, 53.32055555555556, -180.1, 1));
		
	}

}