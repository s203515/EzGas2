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

class TestGetAllGasStations {
	GasStationRepository gasStationRepository;
	GasStationServiceimpl gasService;
	List<GasStation> gasStations = new ArrayList<GasStation>();
	@BeforeEach
	public void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
		when(gasStationRepository.findAll()).thenReturn(gasStations);
	}
	@Test
	void testGetAllGasStations1() {
		List<GasStationDto> gasStationDtos = new ArrayList<GasStationDto>();
		GasStationDto gsDto1 = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStationDto gsDto2 = new GasStationDto(2,"eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStationDto gsDto3 = new GasStationDto(3,"eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs1 = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs3 = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		gasStations.add(gs1);
		gasStations.add(gs2);
		gasStations.add(gs3);
		when(gasStationRepository.findAll()).thenReturn(gasStations);
		gasStationDtos = gasService.getAllGasStations();
		for (int i=0; i<gasStationDtos.size();i++) {
			assertEquals(gasStations.get(i).getGasStationName(),		gasStationDtos.get(i).getGasStationName());
			assertEquals(gasStations.get(i).getGasStationAddress(),		gasStationDtos.get(i).getGasStationAddress());
			assertEquals(gasStations.get(i).getHasDiesel(),				gasStationDtos.get(i).getHasDiesel());
			assertEquals(gasStations.get(i).getHasGas(),				gasStationDtos.get(i).getHasGas());
			assertEquals(gasStations.get(i).getHasMethane(),			gasStationDtos.get(i).getHasMethane());
			assertEquals(gasStations.get(i).getHasSuper(),				gasStationDtos.get(i).getHasSuper());
			assertEquals(gasStations.get(i).getHasSuperPlus(),			gasStationDtos.get(i).getHasSuperPlus());
			assertEquals(gasStations.get(i).getCarSharing(),			gasStationDtos.get(i).getCarSharing());
			assertEquals(gasStations.get(i).getLat(),					gasStationDtos.get(i).getLat());
			assertEquals(gasStations.get(i).getLon(),					gasStationDtos.get(i).getLon());
			assertEquals(gasStations.get(i).getDieselPrice(),			gasStationDtos.get(i).getDieselPrice());
			assertEquals(gasStations.get(i).getGasPrice(),				gasStationDtos.get(i).getGasPrice());
			assertEquals(gasStations.get(i).getMethanePrice(),			gasStationDtos.get(i).getMethanePrice());
			assertEquals(gasStations.get(i).getSuperPrice(),			gasStationDtos.get(i).getSuperPrice());
			assertEquals(gasStations.get(i).getSuperPlusPrice(),		gasStationDtos.get(i).getSuperPlusPrice());
			assertEquals(gasStations.get(i).getReportUser(),			gasStationDtos.get(i).getReportUser());
			assertEquals(gasStations.get(i).getReportTimestamp(),		gasStationDtos.get(i).getReportTimestamp());
			assertEquals(gasStations.get(i).getReportDependability(),	gasStationDtos.get(i).getReportDependability());
		}                               
	}
	@Test
	void testGetAllGasStations2() {
		List<GasStationDto> gasStationDtos = new ArrayList<GasStationDto>();
		gasStationDtos = gasService.getAllGasStations();
		assertEquals(gasStations,gasStationDtos);
	}
}
