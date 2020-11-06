package it.polito.ezgas.converter;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

public class GasStationConverter {
	
	/**
	 * Convert a GasStation in a GasStationDto
	 * 
	 * @param gasStation
	 * @return the GasStationDto of the GasStation
	 */
	public static GasStationDto toGasStationDto(GasStation gasStation) {
		GasStationDto gasStationDto = new GasStationDto(
				gasStation.getGasStationId(),
				gasStation.getGasStationName(),
				gasStation.getGasStationAddress(), 
				gasStation.getHasDiesel(),
				gasStation.getHasSuper(),
				gasStation.getHasSuperPlus(),
				gasStation.getHasGas(),
				gasStation.getHasMethane(),
				gasStation.getHasPremiumDiesel(),
				gasStation.getCarSharing(), 
				gasStation.getLat(),
				gasStation.getLon(), 
				gasStation.getDieselPrice(),
				gasStation.getSuperPrice(),
				gasStation.getSuperPlusPrice(),
				gasStation.getGasPrice(),
				gasStation.getMethanePrice(),
				gasStation.getPremiumDieselPrice(),
				gasStation.getReportUser(),
				gasStation.getReportTimestamp(),
				gasStation.getReportDependability());
		User u =gasStation.getUser();
		if(u!=null) {
			gasStationDto.setUserDto(UserConverter.toUserDto(u));
		}
		
		return gasStationDto;
	}
	
	public static GasStation toGasStation (GasStationDto gasStationDto) {
		GasStation gasStation = new GasStation(
				gasStationDto.getGasStationName(),
				gasStationDto.getGasStationAddress(), 
				gasStationDto.getHasDiesel(),
				gasStationDto.getHasSuper(),
				gasStationDto.getHasSuperPlus(),
				gasStationDto.getHasGas(),
				gasStationDto.getHasMethane(),
				gasStationDto.getHasPremiumDiesel(),
				gasStationDto.getCarSharing(), 
				gasStationDto.getLat(),
				gasStationDto.getLon(), 
				gasStationDto.getDieselPrice(),
				gasStationDto.getSuperPrice(),
				gasStationDto.getSuperPlusPrice(),
				gasStationDto.getGasPrice(),
				gasStationDto.getMethanePrice(),
				gasStationDto.getPremiumDieselPrice(),
				gasStationDto.getReportUser(),
				gasStationDto.getReportTimestamp(),
				gasStationDto.getReportDependability());
		UserDto uDto = gasStationDto.getUserDto();	
		if(uDto!=null) {
			gasStation.setUser(UserConverter.toUser(uDto));
		}
		return gasStation;
	}
	
}
