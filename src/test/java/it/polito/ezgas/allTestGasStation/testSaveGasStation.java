package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.PriceException;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;
import it.polito.ezgas.utils.Utils;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSaveGasStation {
	GasStationRepository gasStationRepository;
	GasStationServiceimpl gasService;
	@BeforeEach
	public void setUp() {
		gasStationRepository = mock(GasStationRepository.class);
		
		gasService = new GasStationServiceimpl();
		gasService.addGasStationRepository(gasStationRepository);
	}
	@Test
	void testSaveGasStation1() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"Enjoy",20,20,-1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs =            new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",20,20,-1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		User u = new User("test","test","test@gmail.com",3);
		gs.setUser(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, () -> {gasService.saveGasStation(gsDto);});
	}
	@Test
	void testSaveGasStation2() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,-1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
					GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,-1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
					User u = new User("test","test","test@gmail.com",3);
					gs.setUser(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation3() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,-1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,-1.2,1.3,1.4,1.5,1,"12:10",1);
		User u = new User("test","test","test@gmail.com",3);
		gs.setUser(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation4() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,-1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,-1.3,1.4,1.5,1,"12:10",1);
		User u = new User("test","test","test@gmail.com",3);
		gs.setUser(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation5() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,-1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,-1.4,1.5,1,"12:10",1);
		User u = new User("test","test","test@gmail.com",3);
		gs.setUser(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation10() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,1.4,-1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",20,20,1.0,1.1,1.2,1.3,-1.4,1.5,1,"12:10",1);
		User u = new User("test","test","test@gmail.com",3);
		gs.setUser(u);
		when(gasStationRepository.findOne(anyInt())).thenReturn(gs);
		assertThrows(exception.PriceException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation6() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",91,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		assertThrows(exception.GPSDataException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation7() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",-20,181,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		assertThrows(exception.GPSDataException.class, () -> gasService.saveGasStation(gsDto));
	}
	@Test
	void testSaveGasStation8() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.save(any(GasStation.class))).thenReturn(gs);
		when(gasStationRepository.findOne(1)).thenReturn(gs);
		
		GasStationDto ret = gasService.saveGasStation(gsDto);
	
		assertEquals(gs.getGasStationName(),		ret.getGasStationName());
		assertEquals(gs.getGasStationAddress(),	ret.getGasStationAddress());
		assertEquals(gs.getHasDiesel(),			ret.getHasDiesel());
		assertEquals(gs.getHasGas(),				ret.getHasGas());
		assertEquals(gs.getHasMethane(),			ret.getHasMethane());
		assertEquals(gs.getHasSuper(),			ret.getHasSuper());
		assertEquals(gs.getHasSuperPlus(),		ret.getHasSuperPlus());
		assertEquals(gs.getHasPremiumDiesel(),		ret.getHasPremiumDiesel());	
		assertEquals(gs.getPremiumDieselPrice(),		ret.getPremiumDieselPrice());	
		assertEquals(gs.getCarSharing(),			ret.getCarSharing());
		assertEquals(gs.getLat(),				ret.getLat());
		assertEquals(gs.getLon(),				ret.getLon());
		assertEquals(gs.getDieselPrice(),		ret.getDieselPrice());
		assertEquals(gs.getGasPrice(),			ret.getGasPrice());
		assertEquals(gs.getMethanePrice(),		ret.getMethanePrice());
		assertEquals(gs.getSuperPrice(),			ret.getSuperPrice());
		assertEquals(gs.getSuperPlusPrice(),		ret.getSuperPlusPrice());
		assertEquals(gs.getReportUser(),			ret.getReportUser());
		assertEquals(gs.getReportTimestamp(),	ret.getReportTimestamp());
		assertEquals(gs.getReportDependability(),ret.getReportDependability());
	}
	@Test
	void testSaveGasStation9() throws PriceException, GPSDataException{
		GasStationDto gsDto = new GasStationDto(null,"eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"Enjoy",-20,20,1.0,1.1,1.2,1.3,1.4,1.5,1,"12:10",1);
		
		when(gasStationRepository.save(any(GasStation.class))).thenReturn(gs);
		when(gasStationRepository.findOne(1)).thenReturn(gs);
		
		GasStationDto ret = gasService.saveGasStation(gsDto);
	
		assertEquals(gs.getGasStationName(),		ret.getGasStationName());
		assertEquals(gs.getGasStationAddress(),	ret.getGasStationAddress());
		assertEquals(gs.getHasDiesel(),			ret.getHasDiesel());
		assertEquals(gs.getHasGas(),				ret.getHasGas());
		assertEquals(gs.getHasMethane(),			ret.getHasMethane());
		assertEquals(gs.getHasSuper(),			ret.getHasSuper());
		assertEquals(gs.getHasSuperPlus(),		ret.getHasSuperPlus());
		assertEquals(gs.getHasPremiumDiesel(),		ret.getHasPremiumDiesel());	
		assertEquals(gs.getPremiumDieselPrice(),		ret.getPremiumDieselPrice());	
		assertEquals(gs.getCarSharing(),			ret.getCarSharing());
		assertEquals(gs.getLat(),				ret.getLat());
		assertEquals(gs.getLon(),				ret.getLon());
		assertEquals(gs.getDieselPrice(),		ret.getDieselPrice());
		assertEquals(gs.getGasPrice(),			ret.getGasPrice());
		assertEquals(gs.getMethanePrice(),		ret.getMethanePrice());
		assertEquals(gs.getSuperPrice(),			ret.getSuperPrice());
		assertEquals(gs.getSuperPlusPrice(),		ret.getSuperPlusPrice());
		assertEquals(gs.getReportUser(),			ret.getReportUser());
		assertEquals(gs.getReportTimestamp(),	ret.getReportTimestamp());
		assertEquals(gs.getReportDependability(),ret.getReportDependability());
	}
}
