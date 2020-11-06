package it.polito.ezgas.allTestGasStation;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.GPSDataException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;


class TestGetGasStationByProximity {
	GasStationServiceimpl gasService;
	GasStationRepository gasStationRepository;
	List<GasStation> input = new ArrayList<GasStation>();
	List<GasStationDto> result = new ArrayList<GasStationDto>();
	
	@BeforeEach
	void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
		
	}

	@Test
	void testGetGasStationByProximity1()throws GPSDataException {
		assertThrows(exception.GPSDataException.class, ()-> {gasService.getGasStationsByProximity(-91, 0);});
	}
	@Test
	void testGetGasStationByProximity2()throws GPSDataException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		when(gasStationRepository.findAll()).thenReturn(input);
		result =  gasService.getGasStationsByProximity(20, 20, 1);
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getCarSharing(),				gsDto.getCarSharing());
		assertEquals(result.get(0).getLat(),					gsDto.getLat());
		assertEquals(result.get(0).getLon(),					gsDto.getLon());
		assertEquals(result.get(0).getDieselPrice(),			gsDto.getDieselPrice());
		assertEquals(result.get(0).getGasPrice(),				gsDto.getGasPrice());
		assertEquals(result.get(0).getMethanePrice(),			gsDto.getMethanePrice());
		assertEquals(result.get(0).getSuperPrice(),				gsDto.getSuperPrice());
		assertEquals(result.get(0).getSuperPlusPrice(),			gsDto.getSuperPlusPrice());
		assertEquals(result.get(0).getReportUser(),				gsDto.getReportUser());
		assertEquals(result.get(0).getReportTimestamp(),		gsDto.getReportTimestamp());
		assertEquals(result.get(0).getReportDependability(),	0);
	}
	@Test
	void testGetGasStationByProximity3()throws GPSDataException {
		//when(any(Utils.class).isLatLonValid(anyInt(), anyInt())).thenReturn(false);
		//assertThrows(exception.GPSDataException.class, ()-> {gasService.getGasStationsByProximity(0, 0);});
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result =  gasService.getGasStationsByProximity(1, 1,1);
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByProximity4()throws GPSDataException {
		//when(any(Utils.class).isLatLonValid(anyInt(), anyInt())).thenReturn(false);
		//assertThrows(exception.GPSDataException.class, ()-> {gasService.getGasStationsByProximity(0, 0);});
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result =  gasService.getGasStationsByProximity(1, 1);
		assertTrue(result.isEmpty());
	}
}
