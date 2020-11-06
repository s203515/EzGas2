package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.utils.Utils;

class TestComputeTrust {
	
	@BeforeEach
	void setUp() {
		

		Utils utils = new Utils();
	}
	@Test
	void testComputeTrust1() {
		GasStation gs = new GasStation();
		double ret = Utils.computeTrust(gs);
		assertEquals(0.0,ret);
	}
	@Test
	void testComputeTrust2(){
		
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0.0,0.0,1.0,1.1,1.2,1.3,1.4,1.5,1,"02-01-2020",1.000);
		User u = new User();
		u.setReputation(5);
		gs.setUser(u);
		double ret = Utils.computeTrust(gs);
		assertEquals(50.0,ret);
	}
	@Test
	void testComputeTrust3(){
		
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY");
	    String timestamp = dateFormat.format(Utils.getTodayDate());
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,timestamp,1);
		User u = new User();
		u.setReputation(5);
		gs.setUser(u);
		double ret = Utils.computeTrust(gs);
		assertEquals(100.0,ret);
	}
}
