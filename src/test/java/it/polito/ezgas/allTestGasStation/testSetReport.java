package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidGasStationException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.utils.Utils;

class TestSetReport {
	GasStationServiceimpl gasService;
	UserRepository userRepository;
	GasStationRepository gasStationRepository;
	@BeforeEach
	void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		userRepository = mock(UserRepository.class);
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
		gasService.addUserRepository(userRepository);
	}
	@Test
	void testSetReport1() {
		//Integer userId = new Integer(null);
	
		assertThrows(exception.InvalidUserException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1);});
		assertThrows(exception.InvalidUserException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, null);});
	
		assertThrows(exception.InvalidGasStationException.class, ()-> {gasService.setReport(-1, 1.0, 1.0, 1.0, 1.0, 1.0,1.0, 1);});
		assertThrows(exception.InvalidGasStationException.class, ()-> {gasService.setReport(null, 1.0, 1.0, 1.0, 1.0, 1.0,1.0, 1);});

	}
	@Test
	void testSetReport2() {
		//Integer userId = new Integer(null);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"02-01-2020",1);
		User u = new User("test","test","test@gmail.com",3);
		gs.setUser(u);
		when(userRepository.findOne(anyInt())).thenReturn(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, ()-> {gasService.setReport(1, -1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);});
		assertThrows(exception.PriceException.class, ()-> {gasService.setReport(1, 1.0, -1.0, 1.0, 1.0, 1.0, 1.0, 1);});
		assertThrows(exception.PriceException.class, ()-> {gasService.setReport(1, 1.0, 1.0, -1.0, 1.0, 1.0, 1.0, 1);});
		assertThrows(exception.PriceException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, -1.0, 1.0, 1.0, 1);});
		assertThrows(exception.PriceException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, -1.0, 1.0, 1);});
		assertThrows(exception.PriceException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, -1.0, 1);});
		
		assertThrows(exception.InvalidUserException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, -1);});
		assertThrows(exception.InvalidUserException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, null);});
		
		assertThrows(exception.InvalidGasStationException.class, ()-> {gasService.setReport(-1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);});
		assertThrows(exception.InvalidGasStationException.class, ()-> {gasService.setReport(null, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);});

	}
	@Test
	void testSetReport6() throws InvalidGasStationException, PriceException, InvalidUserException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"02-01-2020",1);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		when(userRepository.findOne(anyInt())).thenReturn(null);
		assertThrows(exception.InvalidUserException.class, ()-> {gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);});
	}
	@Test
	void testSetReport3() throws InvalidGasStationException, PriceException, InvalidUserException {
		when(gasStationRepository.findOne(anyInt())).thenReturn(null);
		gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
	}
	@Test
	void testSetReport4() throws InvalidGasStationException, PriceException, InvalidUserException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);
		GasStation gs2 = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);
		User u = new User ("test","test","test@test.com",1);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		when(userRepository.findOne(anyInt())).thenReturn(u);

		gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);


	}
	@Test
	void testSetReport5() throws InvalidGasStationException, PriceException, InvalidUserException {
		GasStation gs = new GasStation("eni","via test",false,false,false,false,false,false,"Enjoy",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);
		GasStation gs2 = new GasStation("eni","via test",false,false,false,false,false,false,"Enjoy",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);
		User u = new User ("test","test","test@test.com",1);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		when(userRepository.findOne(anyInt())).thenReturn(u);

		gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
	}
	@Test
	void testSetReport7() throws InvalidGasStationException, PriceException, InvalidUserException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY");
	    String timestamp = dateFormat.format(Utils.getTodayDate());
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,timestamp,1);
		User u = new User("test","test","test@gmail.com",3);
		User u2 = new User ("test2","test2","test2@gmail.com",1);
		gs.setUser(u);
		when(userRepository.findOne(anyInt())).thenReturn(u2);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		gasService.setReport(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);
	}
}
