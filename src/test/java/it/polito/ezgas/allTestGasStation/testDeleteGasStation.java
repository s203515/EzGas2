package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidGasStationException;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

class TestDeleteGasStation {
	GasStationServiceimpl gasService;
	GasStationRepository gasStationRepository;
	@BeforeEach
	void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
	}
	
	@Test
	void testDeleteGasStation1() throws InvalidGasStationException{
		
		assertThrows(exception.InvalidGasStationException.class, ()->{gasService.deleteGasStation(null);});
	}
	@Test
	void testDeleteGasStation2() throws InvalidGasStationException{
		
		assertThrows(exception.InvalidGasStationException.class, ()->{gasService.deleteGasStation(-3);});
	}
	@Test
	void testDeleteGasStation3() throws InvalidGasStationException{
		when(gasStationRepository.findOne(anyInt())).thenReturn(null);
		Boolean result = gasService.deleteGasStation(1);
		assertNull(result);
	}
	@Test
	void testDeleteGasStation4() throws InvalidGasStationException{
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);		when(gasStationRepository.findOne(anyInt())).thenReturn(null);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		boolean result = gasService.deleteGasStation(1);
		assertTrue(result);
	}
}
