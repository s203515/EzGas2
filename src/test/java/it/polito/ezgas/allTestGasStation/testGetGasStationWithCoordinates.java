package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.GPSDataException;
import exception.InvalidCarSharingException;
import exception.InvalidGasTypeException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
class TestGetGasStationWithCoordinates {
	GasStationServiceimpl gasService;
	
	GasStationRepository gasStationRepository;
	List<GasStation> input = new ArrayList<GasStation>();
	List<GasStationDto> inputDto = new ArrayList<GasStationDto>();
	List<GasStationDto> result = new ArrayList<GasStationDto>();
	
	@BeforeEach
	void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
	
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
		
	}

	@Test
	void testGetGasStationsWithCoordinates1() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		//GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1,"12:10",1);

		input.add(gs);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0, 1, "diesel", "Enjoy");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates2() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",false,false,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		input.add(gs2);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "diesel", "Enjoy");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates3() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,false,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);

		input.add(gs);
		input.add(gs2);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "diesel", "null");
		assertEquals(result.get(1).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(1).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(1).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(1).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(1).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(1).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(1).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(1).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(1).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
		assertEquals(result.get(1).getCarSharing(),				gsDto.getCarSharing());
		assertEquals(result.get(1).getLat(),					gsDto.getLat());
		assertEquals(result.get(1).getLon(),					gsDto.getLon());
		assertEquals(result.get(1).getDieselPrice(),			gsDto.getDieselPrice());
		assertEquals(result.get(1).getGasPrice(),				gsDto.getGasPrice());
		assertEquals(result.get(1).getMethanePrice(),			gsDto.getMethanePrice());
		assertEquals(result.get(1).getSuperPrice(),				gsDto.getSuperPrice());
		assertEquals(result.get(1).getSuperPlusPrice(),			gsDto.getSuperPlusPrice());
		assertEquals(result.get(1).getReportUser(),				gsDto.getReportUser());
		assertEquals(result.get(1).getReportTimestamp(),		gsDto.getReportTimestamp());
		assertEquals(result.get(1).getReportDependability(),	0);
	}
	@Test
	void testGetGasStationsWithCoordinates4() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		//GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1,"12:10",1);

		input.add(gs);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "super", "Car2Go");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates5() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",false,false,false,false,false,false,"Enjoy",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		input.add(gs);
		input.add(gs2);
		
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "super", "Car2Go");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates6() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);

		input.add(gs);
		input.add(gs2);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "super", "Car2Go");
		assertEquals(result.get(1).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(1).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(1).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(1).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(1).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(1).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(1).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(1).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(1).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
		assertEquals(result.get(1).getCarSharing(),				gsDto.getCarSharing());
		assertEquals(result.get(1).getLat(),					gsDto.getLat());
		assertEquals(result.get(1).getLon(),					gsDto.getLon());
		assertEquals(result.get(1).getDieselPrice(),			gsDto.getDieselPrice());
		assertEquals(result.get(1).getGasPrice(),				gsDto.getGasPrice());
		assertEquals(result.get(1).getMethanePrice(),			gsDto.getMethanePrice());
		assertEquals(result.get(1).getSuperPrice(),				gsDto.getSuperPrice());
		assertEquals(result.get(1).getSuperPlusPrice(),			gsDto.getSuperPlusPrice());
		assertEquals(result.get(1).getReportUser(),				gsDto.getReportUser());
		assertEquals(result.get(1).getReportTimestamp(),		gsDto.getReportTimestamp());
		assertEquals(result.get(1).getReportDependability(),	0);
	}
	@Test
	void testGetGasStationsWithCoordinates7() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("shell","via test",false,false,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);

		input.add(gs);
		input.add(gs2);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "superplus", "null");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates8() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("shell","via test",false,false,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);

		input.add(gs);
		input.add(gs2);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0,1, "gas", "null");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates9() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("shell","via test",false,false,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);

		input.add(gs);
		input.add(gs2);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0, 1, "methane", "null");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates14() throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException{
		GasStationDto gsDto = new GasStationDto(1,"shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("shell","via test",false,false,false,false,false,false,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs2 = new GasStation("shell","via test",true,true,true,true,true,true,"Car2Go",0,0,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);

		input.add(gs);
		input.add(gs2);
		when(gasStationRepository.findAll()).thenReturn(input);
		result = gasService.getGasStationsWithCoordinates(0, 0, 1, "premiumdiesel", "null");
		assertEquals(result.get(0).getGasStationName(),			gsDto.getGasStationName());
		assertEquals(result.get(0).getGasStationAddress(),		gsDto.getGasStationAddress());
		assertEquals(result.get(0).getHasDiesel(),				gsDto.getHasDiesel());
		assertEquals(result.get(0).getHasGas(),					gsDto.getHasGas());
		assertEquals(result.get(0).getHasMethane(),				gsDto.getHasMethane());
		assertEquals(result.get(0).getHasSuper(),				gsDto.getHasSuper());
		assertEquals(result.get(0).getHasSuperPlus(),			gsDto.getHasSuperPlus());
		assertEquals(result.get(0).getHasPremiumDiesel(),		gsDto.getHasPremiumDiesel());	
		assertEquals(result.get(0).getPremiumDieselPrice(),		gsDto.getPremiumDieselPrice());	
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
	void testGetGasStationsWithCoordinates11() throws InvalidGasTypeException, GPSDataException{
		assertThrows(exception.GPSDataException.class, ()-> {gasService.getGasStationsWithCoordinates(-91, 0,1, "diesel", "Enjoy");});
	}
	@Test
	void testGetGasStationsWithCoordinates12() throws InvalidGasTypeException, GPSDataException{
		assertThrows(exception.InvalidGasTypeException.class, ()-> gasService.getGasStationsWithCoordinates(0, 0,1, "benzina", "abc"));
	}
	@Test
	void testGetGasStationsWithCoordinates13() throws InvalidGasTypeException, GPSDataException{
		assertThrows(exception.InvalidGasTypeException.class, ()-> gasService.getGasStationsWithCoordinates(0, 0,1, "abc", null ));
		
	}
	@Test
	void testGetGasStationsWithCoordinates15() throws InvalidGasTypeException, GPSDataException{
		assertThrows(exception.InvalidCarSharingException.class, ()-> gasService.getGasStationsWithCoordinates(0, 0,1, "diesel", "test" ));
		
	}
}
