package it.polito.ezgas.allTestGasStation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

class TestGasStationConverter {

	@Test
	void testGasStationConvFromDto() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",21,21,1.0,1.1,1.2,1.3,1.4,1.5,1,"11:02",1);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",21,21,1.0,1.1,1.2,1.3,1.4,1.5,1,"11:02",1);
		GasStation converted = GasStationConverter.toGasStation(gsDto);
		
		
		assertEquals(gs.getGasStationName(),converted.getGasStationName());
		assertEquals(gs.getGasStationAddress(),converted.getGasStationAddress());
		assertEquals(gs.getHasDiesel(),converted.getHasDiesel());
		assertEquals(gs.getHasGas(),converted.getHasGas());
		assertEquals(gs.getHasMethane(),converted.getHasMethane());
		assertEquals(gs.getHasSuper(),converted.getHasSuper());
		assertEquals(gs.getHasSuperPlus(),converted.getHasSuperPlus());
		assertEquals(gs.getHasPremiumDiesel(),converted.getHasPremiumDiesel());
		assertEquals(gs.getCarSharing(),converted.getCarSharing());
		assertEquals(gs.getLat(),converted.getLat());
		assertEquals(gs.getLon(),converted.getLon());
		assertEquals(gs.getDieselPrice(),converted.getDieselPrice());
		assertEquals(gs.getGasPrice(),converted.getGasPrice());
		assertEquals(gs.getMethanePrice(),converted.getMethanePrice());
		assertEquals(gs.getSuperPrice(),converted.getSuperPrice());
		assertEquals(gs.getSuperPlusPrice(),converted.getSuperPlusPrice());
		assertEquals(gs.getReportUser(),converted.getReportUser());
		assertEquals(gs.getReportTimestamp(),converted.getReportTimestamp());
		assertEquals(gs.getReportDependability(),converted.getReportDependability());
	}
	
	@Test
	void testGasStationConvToDto() {
		GasStation gs = new GasStation("eni","via test",true,true,true,true,true,true,"enjoy",21,21,1.0,1.1,1.2,1.3,1.4,1.5,1,"11:02",1);
		GasStationDto gsDto = new GasStationDto(1,"eni","via test",true,true,true,true,true,true,"enjoy",21,21,1.0,1.1,1.2,1.3,1.4,1.5,1,"11:02",1);
		GasStationDto converted = GasStationConverter.toGasStationDto(gs);
		
		
		assertEquals(gsDto.getGasStationName(),converted.getGasStationName());
		assertEquals(gsDto.getGasStationAddress(),converted.getGasStationAddress());
		assertEquals(gsDto.getHasDiesel(),converted.getHasDiesel());
		assertEquals(gsDto.getHasGas(),converted.getHasGas());
		assertEquals(gsDto.getHasMethane(),converted.getHasMethane());
		assertEquals(gsDto.getHasSuper(),converted.getHasSuper());
		assertEquals(gsDto.getHasSuperPlus(),converted.getHasSuperPlus());
		assertEquals(gs.getHasPremiumDiesel(),converted.getHasPremiumDiesel());
		assertEquals(gsDto.getCarSharing(),converted.getCarSharing());
		assertEquals(gsDto.getLat(),converted.getLat());
		assertEquals(gsDto.getLon(),converted.getLon());
		assertEquals(gsDto.getDieselPrice(),converted.getDieselPrice());
		assertEquals(gsDto.getGasPrice(),converted.getGasPrice());
		assertEquals(gsDto.getMethanePrice(),converted.getMethanePrice());
		assertEquals(gsDto.getSuperPrice(),converted.getSuperPrice());
		assertEquals(gsDto.getSuperPlusPrice(),converted.getSuperPlusPrice());
		assertEquals(gsDto.getReportUser(),converted.getReportUser());
		assertEquals(gsDto.getReportTimestamp(),converted.getReportTimestamp());
		assertEquals(gsDto.getReportDependability(),converted.getReportDependability());
	}
}
