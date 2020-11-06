package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;

class TestGetGasStationByCarSharing {
	GasStationServiceimpl gasService;
	GasStationRepository gasStationRepository;
	
	@BeforeEach
	void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
	}
	@Test
	void testGetGasStationByCarSharing1() {
		//da cambiare in assertThrow se dovessero darci una exception
		List<GasStationDto> result = new ArrayList<GasStationDto>();

		result = gasService.getGasStationByCarSharing(null);
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByCarSharing2() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,null,0.0,0.0,1.0,1.1,1.2,1.3,1.4,1.5,1,"02-01-2020",1.000);

		List<GasStationDto> result = new ArrayList<GasStationDto>();
		List<GasStation> input = new ArrayList<GasStation>();
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationByCarSharing("Car2Go");
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByCarSharing3() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"abc",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);

		List<GasStationDto> result = new ArrayList<GasStationDto>();
		List<GasStation> input = new ArrayList<GasStation>();
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationByCarSharing("Car2Go");
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByCarSharing4() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Car2Go",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);

		List<GasStationDto> result = new ArrayList<GasStationDto>();
		List<GasStation> input = new ArrayList<GasStation>();
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationByCarSharing("Car2Go");
		assertEquals(result.get(0).getGasStationName(),			gs.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gs.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gs.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gs.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gs.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gs.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gs.getHasSuperPlus());
		assertEquals(result.get(0).getCarSharing(),				gs.getCarSharing());
		assertEquals(result.get(0).getLat(),					gs.getLat());
		assertEquals(result.get(0).getLon(),					gs.getLon());
		assertEquals(result.get(0).getDieselPrice(),			gs.getDieselPrice());
		assertEquals(result.get(0).getGasPrice(),				gs.getGasPrice());
		assertEquals(result.get(0).getMethanePrice(),			gs.getMethanePrice());
		assertEquals(result.get(0).getSuperPrice(),				gs.getSuperPrice());
		assertEquals(result.get(0).getSuperPlusPrice(),			gs.getSuperPlusPrice());
		assertEquals(result.get(0).getReportUser(),				gs.getReportUser());
		assertEquals(result.get(0).getReportTimestamp(),		gs.getReportTimestamp());
		assertEquals(result.get(0).getReportDependability(),	gs.getReportDependability());

	}
	@Test
	void testGetGasStationByCarSharing5() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,null,0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);

		List<GasStationDto> result = new ArrayList<GasStationDto>();
		List<GasStation> input = new ArrayList<GasStation>();
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationByCarSharing("Enjoy");
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByCarSharing6() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"abc",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);

		List<GasStationDto> result = new ArrayList<GasStationDto>();
		List<GasStation> input = new ArrayList<GasStation>();
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationByCarSharing("Enjoy");
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByCarSharing7() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,2.0,2.0,2.0,2.0,2.0,2.0,1,"12:10",1);

		List<GasStationDto> result = new ArrayList<GasStationDto>();
		List<GasStation> input = new ArrayList<GasStation>();
		input.add(gs);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationByCarSharing("Enjoy");
		assertEquals(result.get(0).getGasStationName(),			gs.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gs.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gs.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gs.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gs.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gs.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gs.getHasSuperPlus());
		assertEquals(result.get(0).getCarSharing(),				gs.getCarSharing());
		assertEquals(result.get(0).getLat(),					gs.getLat());
		assertEquals(result.get(0).getLon(),					gs.getLon());
		assertEquals(result.get(0).getDieselPrice(),			gs.getDieselPrice());
		assertEquals(result.get(0).getGasPrice(),				gs.getGasPrice());
		assertEquals(result.get(0).getMethanePrice(),			gs.getMethanePrice());
		assertEquals(result.get(0).getSuperPrice(),				gs.getSuperPrice());
		assertEquals(result.get(0).getSuperPlusPrice(),			gs.getSuperPlusPrice());
		assertEquals(result.get(0).getReportUser(),				gs.getReportUser());
		assertEquals(result.get(0).getReportTimestamp(),		gs.getReportTimestamp());
		assertEquals(result.get(0).getReportDependability(),	gs.getReportDependability());

	}
}
