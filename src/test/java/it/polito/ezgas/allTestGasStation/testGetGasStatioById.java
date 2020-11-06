package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidGasStationException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.utils.Utils;

class TestGetGasStatioById {
	GasStationRepository gasStationRepository;
	GasStationServiceimpl gasService;
	@BeforeEach
	public void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
	}
	
	@Test
	void testGetGasStationById() throws InvalidGasStationException {
		GasStationDto gsDto = new GasStationDto(3,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStationDto gsDto2 = gasService.getGasStationById(3);
		assertEquals(gsDto.getGasStationName(),		gsDto2.getGasStationName());
		assertEquals(gsDto.getGasStationAddress(),	gsDto2.getGasStationAddress());
		assertEquals(gsDto.getHasDiesel(),			gsDto2.getHasDiesel());
		assertEquals(gsDto.getHasGas(),				gsDto2.getHasGas());
		assertEquals(gsDto.getHasMethane(),			gsDto2.getHasMethane());
		assertEquals(gsDto.getHasSuper(),			gsDto2.getHasSuper());
		assertEquals(gsDto.getHasSuperPlus(),		gsDto2.getHasSuperPlus());
		assertEquals(gsDto.getCarSharing(),			gsDto2.getCarSharing());
		assertEquals(gsDto.getLat(),				gsDto2.getLat());
		assertEquals(gsDto.getLon(),				gsDto2.getLon());
		assertEquals(gsDto.getDieselPrice(),		gsDto2.getDieselPrice());
		assertEquals(gsDto.getGasPrice(),			gsDto2.getGasPrice());
		assertEquals(gsDto.getMethanePrice(),		gsDto2.getMethanePrice());
		assertEquals(gsDto.getSuperPrice(),			gsDto2.getSuperPrice());
		assertEquals(gsDto.getSuperPlusPrice(),		gsDto2.getSuperPlusPrice());
		assertEquals(gsDto.getReportUser(),			gsDto2.getReportUser());
		assertEquals(gsDto.getReportTimestamp(),	gsDto2.getReportTimestamp());
		assertEquals(0,gsDto2.getReportDependability());
	}
	@Test 
	void testGetGasStationById2() throws InvalidGasStationException {
		assertThrows(exception.InvalidGasStationException.class, () -> { gasService.getGasStationById(-3);});
	}
	@Test 
	void testGetGasStationById3() throws InvalidGasStationException {
		assertThrows(exception.InvalidGasStationException.class, () -> { gasService.getGasStationById(null);});
	}
	@Test 
	void testGetGasStationById4() throws InvalidGasStationException {
		when(gasStationRepository.findOne(anyInt())).thenReturn(null);
		assertNull(gasService.getGasStationById(3));
	}
}
