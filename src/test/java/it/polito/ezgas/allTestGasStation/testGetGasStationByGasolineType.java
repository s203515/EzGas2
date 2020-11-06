package it.polito.ezgas.allTestGasStation;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidGasTypeException;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

class TestGetGasStationByGasolineType {
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
	void testGetGasStationByGasolineType1() throws InvalidGasTypeException {
		result = gasService.getGasStationsByGasolineType(null);
		assertTrue(result.isEmpty());
	}
	@Test
	void testGetGasStationByGasolineType2() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("diesel");
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
	void testGetGasStationByGasolineType3() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);	
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("diesel");

		assertFalse(result.isEmpty());	
	}
	@Test
	void testGetGasStationByGasolineType4() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("gasoline");
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
	void testGetGasStationByGasolineType5() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);	
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("gasoline");

		assertFalse(result.isEmpty());	
	}
	@Test
	void testGetGasStationByGasolineType6() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("premiumgasoline");
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
	void testGetGasStationByGasolineType7() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);	
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("premiumgasoline");

		assertFalse(result.isEmpty());	
	}
	@Test
	void testGetGasStationByGasolineTyp8() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("lpg");
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
	void testGetGasStationByGasolineType9() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);	
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("lpg");

		assertFalse(result.isEmpty());	
	}
	@Test
	void testGetGasStationByGasolineTyp10() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("methane");
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
	void testGetGasStationByGasolineType11() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);	
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("methane");

		assertFalse(result.isEmpty());	
	}
	@Test
	void testGetGasStationByGasolineTyp12() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("premiumdiesel");
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
	void testGetGasStationByGasolineType13() throws InvalidGasTypeException {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);	
		when(gasStationRepository.findAll()).thenReturn(input);
		
		result = gasService.getGasStationsByGasolineType("methane");

		assertFalse(result.isEmpty());	
	}
	@Test
	void testGetGasStationByGasolineType14() throws InvalidGasTypeException {
	

		assertThrows(exception.InvalidGasTypeException.class , () -> gasService.getGasStationsByGasolineType("abc"));
	}
}
