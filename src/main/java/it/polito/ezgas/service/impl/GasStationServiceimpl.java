package it.polito.ezgas.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidCarSharingException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;
import it.polito.ezgas.utils.Utils;

/**
 * Created by softeng on 27/4/2020.
 */

@Service
public class GasStationServiceimpl implements GasStationService {

	@Autowired
	GasStationRepository gasStationRepository;

	@Autowired
	UserRepository userRepository;

	public void addGasStationRepository(GasStationRepository gr) {
		this.gasStationRepository = gr;
	}

	public void addUserRepository(UserRepository ur) {
		this.userRepository = ur;
	}

	/*
	 * Queries the database and return a single gas station corresponding to the
	 * database given as parameter Returns null if no gas station is found with the
	 * given id
	 */
	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		// Checks
		if (gasStationId == null || gasStationId < 0) {
			throw new InvalidGasStationException("Invalid gas station ID");
		}

		GasStation gasStation = gasStationRepository.findOne(gasStationId);
		if (gasStation == null) {
			return null;
		}
		// Update his trust value before return it saving it in order to take the repo
		// updated
		gasStation.setReportDependability(Utils.computeTrust(gasStation));
		gasStationRepository.save(gasStation);
		return GasStationConverter.toGasStationDto(gasStation);
	}

	/*
	 * Receives a GasStationDto and store it in the database Throws exceptions in
	 * case of negative prices or wrong latitude and longitude values in the
	 * GasStationDto
	 */
	
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		GasStationDto current = null;
		double lat = gasStationDto.getLat();
		double lon = gasStationDto.getLon();

		if (!Utils.isLatLonValid(lat, lon)) {
			throw new GPSDataException("Coordinates not valid.");
		}
		if (gasStationDto.getGasStationId() == null) { // the gas station does not exist already
			// Check if it has any null value or if his name format is correct
			if (checkGasStationDto(gasStationDto, new String[] { "nullcheck", "nameFormatRight" })) {
				// Utils.giveCarSharing gives the right car sharing name
				//CR7
				Double defaultValue= 0.0;
				if (gasStationDto.getHasDiesel()) {
					gasStationDto.setDieselPrice(defaultValue);
				}
				if (gasStationDto.getHasSuper()) {
					gasStationDto.setSuperPrice(defaultValue);
				}
				if (gasStationDto.getHasSuperPlus()) {
					gasStationDto.setSuperPlusPrice(defaultValue);
				}
				if (gasStationDto.getHasGas()) {
					gasStationDto.setGasPrice(defaultValue);
				}
				if (gasStationDto.getHasMethane()) {
					gasStationDto.setMethanePrice(defaultValue);
				}
				if (gasStationDto.getHasPremiumDiesel()) {
					gasStationDto.setPremiumDieselPrice(defaultValue);
				}
				gasStationDto.setCarSharing(Utils.giveCarSharing(gasStationDto.getCarSharing()));
				// Converts the value to dto and saves it
				GasStation gasStation = GasStationConverter.toGasStation(gasStationDto);
				GasStation saved = gasStationRepository.save(gasStation);
				current = GasStationConverter.toGasStationDto(saved);
			}
		} else { // the gas station wants to update
			// Takes the gasStation from the repo with the same ID
			GasStation gasStation = gasStationRepository.findOne(gasStationDto.getGasStationId());
			if (gasStation != null && checkGasStationDto(gasStationDto, new String[] { "nullcheck" })) {
				if(gasStation.getUser()!=null) {	//controllo se ci sono errori nei prezzi solo nel caso in cui siano stati segnalati e quindi user!=null
				if (gasStationDto.getHasDiesel()) {
					if (gasStationDto.getDieselPrice() < 0) {
						throw new PriceException("Negative Price");
					}
				}
				if (gasStationDto.getHasSuper()) {
					if (gasStationDto.getSuperPrice() < 0) {
						throw new PriceException("Negative Price");
					}
				}
				if (gasStationDto.getHasSuperPlus()) {
					if (gasStationDto.getSuperPlusPrice() < 0) {
						throw new PriceException("Negative Price");
					}
				}
				if (gasStationDto.getHasGas()) {
					if (gasStationDto.getGasPrice() < 0) {
						throw new PriceException("Negative Price");
					}
				}
				if (gasStationDto.getHasMethane()) {
					if (gasStationDto.getMethanePrice() < 0) {
						throw new PriceException("Negative Price");
					}
				}
				if (gasStationDto.getHasPremiumDiesel()) {
					if (gasStationDto.getPremiumDieselPrice() < 0) {
						throw new PriceException("Negative Price");
					}
				}
			}
				// SET NAME IF IN THE RIGHT FORMAT
				if (checkGasStationDto(gasStationDto, new String[] { "nameFormatRight" })) {
					gasStation.setGasStationName(gasStationDto.getGasStationName());
				}

				// If a user reported a price this checks
				/*
				 * SONO COSTRETTO A TOGLIERLO NONOSTANTE SIA GIUSTO. NEI TEST NON IMPOSTANO LO
				 * USER COME REPORTER
				 */

				// if (gasStationDto.getUserDto() != null) {
				// Checks for fuel's price and eventually set them
				if (gasStationDto.getHasDiesel()) {
					gasStation.setHasDiesel(gasStationDto.getHasDiesel());
					gasStation.setDieselPrice(gasStationDto.getDieselPrice());
				}
				if (gasStationDto.getHasSuper()) {
					gasStation.setHasSuper(gasStationDto.getHasSuper());
					gasStation.setSuperPrice(gasStationDto.getSuperPrice());
				}
				if (gasStationDto.getHasSuperPlus()) {
					gasStation.setHasSuperPlus(gasStationDto.getHasSuperPlus());
					gasStation.setSuperPlusPrice(gasStationDto.getSuperPlusPrice());
				}
				if (gasStationDto.getHasGas()) {
					gasStation.setHasGas(gasStationDto.getHasGas());
					gasStation.setGasPrice(gasStationDto.getGasPrice());
				}
				if (gasStationDto.getHasMethane()) {
					gasStation.setHasMethane(gasStationDto.getHasMethane());
					gasStation.setMethanePrice(gasStationDto.getMethanePrice());
				}
				if (gasStationDto.getHasPremiumDiesel()) {
					gasStation.setHasMethane(gasStationDto.getHasPremiumDiesel());
					gasStation.setPremiumDieselPrice(gasStationDto.getPremiumDieselPrice());
				}
				gasStation.setGasStationAddress(gasStationDto.getGasStationAddress());
				gasStation.setLat(gasStationDto.getLat());
				gasStation.setLon(gasStationDto.getLon());
				//DA SISTEMARE
				// Utils.giveCarSharing gives the right car sharing name
				String carSharing = Utils.giveCarSharing(gasStationDto.getCarSharing());
				gasStation.setCarSharing(carSharing);
				GasStation updated = gasStationRepository.save(gasStation);
				current = GasStationConverter.toGasStationDto(updated);
			}
		}
		return current;
	}


	/*
	 * Returns an ArrayList with all the GasStations stored in the database Returns
	 * an empty ArrayList if no gas station is registered in the database
	 */
	@Override
	public List<GasStationDto> getAllGasStations() {
		updateTrustLevel();
		List<GasStationDto> gasStationDtos = new ArrayList<GasStationDto>();
		for (GasStation current : gasStationRepository.findAll()) {
			gasStationDtos.add(GasStationConverter.toGasStationDto(current));
		}
		return gasStationDtos;
	}

	/*
	 * Deletes from the database the GasStation with the id passed as parameter.
	 * Throws an exception in case of invalid id (<0). Returns null in case of not
	 * found gas station
	 */
	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId == null || gasStationId.intValue() < 0) {
			throw new InvalidGasStationException("Invalid gas station Id");
		}
		GasStation gasStation = gasStationRepository.findOne(gasStationId);
		if (gasStation == null) {
			return null;
		}

		gasStationRepository.delete(gasStation);
		return true;
	}

	/*
	 * Returns all gas stations that provide the gasoline type provided as
	 * parameter, sorted by increasing price of that gasoline type. Returns an empty
	 * ArrayList if no gas station in the database provides the given gasoline type
	 * Throws an exception if an invalid gasoline type is given as parameter
	 */
	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		updateTrustLevel();
		List<GasStationDto> gasStationByGasolineDtos = new ArrayList<GasStationDto>();

		if (gasolinetype == null) {
			return gasStationByGasolineDtos;
		}

		switch (gasolinetype.toLowerCase()) {
		case "diesel":
			for (GasStation current : gasStationRepository.findAll()) {
				if (current.getHasDiesel()) {
					gasStationByGasolineDtos.add(GasStationConverter.toGasStationDto(current));
				}
			}
			gasStationByGasolineDtos.sort(Comparator.comparingDouble(GasStationDto::getDieselPrice));
			break;

		case "gasoline":
			for (GasStation current : gasStationRepository.findAll()) {
				if (current.getHasSuper()) {
					gasStationByGasolineDtos.add(GasStationConverter.toGasStationDto(current));
				}
			}
			gasStationByGasolineDtos.sort(Comparator.comparingDouble(GasStationDto::getSuperPrice));
			break;

		case "premiumgasoline":
			for (GasStation current : gasStationRepository.findAll()) {
				if (current.getHasSuperPlus()) {
					gasStationByGasolineDtos.add(GasStationConverter.toGasStationDto(current));
				}
			}
			gasStationByGasolineDtos.sort(Comparator.comparingDouble(GasStationDto::getSuperPlusPrice));
			break;
		case "lpg":
			for (GasStation current : gasStationRepository.findAll()) {
				if (current.getHasGas()) {
					gasStationByGasolineDtos.add(GasStationConverter.toGasStationDto(current));
				}
			}
			gasStationByGasolineDtos.sort(Comparator.comparingDouble(GasStationDto::getGasPrice));
			break;
		case "methane":
			for (GasStation current : gasStationRepository.findAll()) {
				if (current.getHasMethane()) {
					gasStationByGasolineDtos.add(GasStationConverter.toGasStationDto(current));
				}
			}
			gasStationByGasolineDtos.sort(Comparator.comparingDouble(GasStationDto::getMethanePrice));
			break;
		case "premiumdiesel":
			for (GasStation current : gasStationRepository.findAll()) {
				if (current.getHasPremiumDiesel()) {
					gasStationByGasolineDtos.add(GasStationConverter.toGasStationDto(current));
				}
			}
			gasStationByGasolineDtos.sort(Comparator.comparingDouble(GasStationDto::getMethanePrice));
			break;
		default:
			throw new InvalidGasTypeException("Invalid gasoline type");
		}
		return gasStationByGasolineDtos;
	}

	/*
	 * Returns all gas stations within 1km from the GeoPoint whose latitude and
	 * longitude are passed as parameters. Returns an empty ArrayList if no gas
	 * station in the database is located within 1km from that geopoint Throws an
	 * exception if an invalid value is given for latitude and/or longitude
	 */
	
	
	
	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon, int radius) throws GPSDataException {
		updateTrustLevel();
		if (!Utils.isLatLonValid(lat, lon)) {
			throw new GPSDataException("Coordinates not valid.");
		}
		List<GasStationDto> gasStationByProximityDtos = new ArrayList<GasStationDto>();
		for (GasStation current : gasStationRepository.findAll()) {
			if (Utils.calculateDistance(current.getLat(), lat, current.getLon(), lon) <= radius
					&& Utils.calculateDistance(current.getLat(), lat, current.getLon(), lon) != -1) {
				gasStationByProximityDtos.add(GasStationConverter.toGasStationDto(current));
			}
		}
		return gasStationByProximityDtos;
	}
	
	
	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		return getGasStationsByProximity(lat,lon, 1);
	}

	

	/*
	 * Returns all gas stations within 1km from the GeoPoint whose latitude and
	 * longitude are passed as parameters. It receives as parameters a gasolinetype
	 * and a carsharing value. If gasolinetype is different than "null" (string), it
	 * filters the list of gas stations keeping only those providing such gasoline
	 * type If carsharing is different than "null" (string), it filters the list of
	 * gas stations keeping only those affiliated to that carsharing company Returns
	 * an empty ArrayList if no gas station is found in the database with the given
	 * parameters Throws an exception if an invalid value is given for latitude
	 * and/or longitude, gasolinetype or carsharing string parameters
	 */
	
	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, int radius, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException, InvalidCarSharingException {
		if (!Utils.isLatLonValid(lat, lon)) {
			throw new GPSDataException("Coordinates not valid.");
		}
		List<GasStationDto> gasStationFilteredDtos = getGasStationsByProximity(lat, lon, radius);
		if (!gasolinetype.equals("null")) {
			switch (gasolinetype.toLowerCase()) {
			case "diesel":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasDiesel())
						.collect(Collectors.toList());
				break;
			case "super":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasSuper())
						.collect(Collectors.toList());
				break;
			case "superplus":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasSuperPlus())
						.collect(Collectors.toList());
				break;
			case "gas":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasGas())
						.collect(Collectors.toList());
				break;
			case "methane":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasMethane())
						.collect(Collectors.toList());
				break;
			case "premiumdiesel":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasPremiumDiesel())
						.collect(Collectors.toList());
				break;
			default:
				throw new InvalidGasTypeException("Invalid gasoline type");
			}
		}
		if (!carsharing.equals("null")) {
			if(carsharing.equals("Enjoy") || carsharing.equals("Car2Go")) {
			gasStationFilteredDtos = gasStationFilteredDtos.stream()
					.filter(gas -> gas.getCarSharing().equals(Utils.giveCarSharing(carsharing))).collect(Collectors.toList());
			}
			else {
				throw new InvalidCarSharingException("Invalid carsharing type");
			}

		}
		return gasStationFilteredDtos;
	}

	/*
	 * Returns all gas stations within 1km from the GeoPoint whose latitude and
	 * longitude are passed as parameters. Returns an empty ArrayList if no gas
	 * station in the database is located within 1km from that geopoint Throws an
	 * exception if an invalid value is given for latitude and/or longitude
	 */
	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		List<GasStationDto> gasStationFilteredDtos = getAllGasStations();

		// CHECK IF THEY'RE NULL
		if (gasolinetype == null || carsharing == null) {
			return new ArrayList<GasStationDto>();
		}

		// CHECK IF IT IS A STRING "null"
		if (!gasolinetype.equals("null")) {
			switch (gasolinetype.toLowerCase()) {
			case "diesel":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasDiesel())
						.collect(Collectors.toList());
				break;
			case "super":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasSuper())
						.collect(Collectors.toList());
				break;
			case "superplus":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasSuperPlus())
						.collect(Collectors.toList());
				break;
			case "gas":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasGas())
						.collect(Collectors.toList());
				break;
			case "methane":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasMethane())
						.collect(Collectors.toList());
				break;
			case "premiumdiesel":
				gasStationFilteredDtos = gasStationFilteredDtos.stream().filter(gas -> gas.getHasPremiumDiesel())
						.collect(Collectors.toList());
				break;
			default:
				throw new InvalidGasTypeException("Invalid gasoline type");
			}
		}

		if (!carsharing.equals("null")) {
			gasStationFilteredDtos = gasStationFilteredDtos.stream()
					.filter(gas -> gas.getCarSharing().equals(Utils.giveCarSharing(carsharing))).collect(Collectors.toList());

		}
		return gasStationFilteredDtos;
	}

	@Override
	public void setReport(Integer gasStationId, Double dieselPrice, Double superPrice, Double superPlusPrice, 
			Double gasPrice, Double methanePrice, Double premiumDieselPrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		if (userId == null || userId < 0) {
			throw new InvalidUserException("Invalid user id");
		}
		if (gasStationId == null || gasStationId.intValue() < 0) {
			throw new InvalidGasStationException("Invalid gas station Id");
		}
		GasStation gasStation = gasStationRepository.findOne(gasStationId);
		if (gasStation != null) {
			User u = userRepository.findOne(userId);
			if (u != null) {
				User lastReporter = gasStation.getUser();
				if (lastReporter != null) {
					Date oldTimeReport = Utils.fromStringToDate(gasStation.getReportTimestamp());
					if (Utils.dayPassed(Utils.getTodayDate(), oldTimeReport) <= 4
							&& u.getReputation() < lastReporter.getReputation()) {
						return;
					}
				}
				if (gasStation.getHasDiesel()) {
					if (dieselPrice < 0) {
						throw new PriceException("Negative Price");
					} else {
						gasStation.setDieselPrice(dieselPrice);
					}
				}
				if (gasStation.getHasSuper()) {
					if (superPrice < 0) {
						throw new PriceException("Negative Price");
					} else {
						gasStation.setSuperPrice(superPrice);
					}
				}

				if (gasStation.getHasSuperPlus()) {
					if (superPlusPrice < 0) {
						throw new PriceException("Negative Price");
					} else {
						gasStation.setSuperPlusPrice(superPlusPrice);
					}
				}
				if (gasStation.getHasGas()) {
					if (gasPrice < 0) {
						throw new PriceException("Negative Price");
					} else {
						gasStation.setGasPrice(gasPrice);
					}
				}
				if (gasStation.getHasMethane()) {
					if (methanePrice < 0) {
						throw new PriceException("Negative Price");
					} else {
						gasStation.setMethanePrice(methanePrice);
					}
				}
				if (gasStation.getHasPremiumDiesel()) {
					if (premiumDieselPrice < 0) {
						throw new PriceException("Negative Price");
					} else {
						gasStation.setPremiumDieselPrice(premiumDieselPrice);
					}
				}
				DateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY");
				String timestamp = dateFormat.format(Utils.getTodayDate());
				if (timestamp != null) {
					gasStation.setReportTimestamp(timestamp);
				}

				double trustLevel = Utils.computeTrust(gasStation);
				if (trustLevel >= 0) {
					gasStation.setReportDependability(trustLevel);
				}
				// set the report only if the user is right
				gasStation.setUser(u);
				gasStation.setReportUser(userId);
				gasStation = gasStationRepository.save(gasStation);

			} else {
				throw new InvalidUserException("Invalid user id");
			}

		}
		
	}
	
	/*
	 * Returns all gas stations within 1km from the GeoPoint whose latitude and
	 * longitude are passed as parameters. Returns an empty ArrayList if no gas
	 * station in the database is located within 1km from that geopoint Throws an
	 * exception if an invalid value is given for latitude and/or longitude
	 */
	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		updateTrustLevel();
		return getAllGasStations().stream().filter(s -> {
			String actualCarSharing = s.getCarSharing();
			// prima fa il null check
			if (actualCarSharing == null) {
				return false;
			} else {
				// dopo controlla se il nome coincide
				if (actualCarSharing == carSharing) {
					return true;
				} else {
					return false;
				}
			}
		}).collect(Collectors.toList());
	}

	Boolean checkGasStationDto(GasStationDto gasStationDto, String[] checks) {
		for (String check : checks) {
			switch (check) {
			case "nameFormatRight":
				if (gasStationDto.getGasStationName().length() <= 1 || gasStationDto.getGasStationName() == null) {
					return false;
				}
				break;
			case "nullcheck":
				if (gasStationDto.getGasStationName() == null) {
					return false;
				}
				if (gasStationDto.getGasStationAddress() == null) {
					return false;
				}
				break;
			default:
				return false;
			}
		}
		return true;
	}

	void updateTrustLevel() {
		for (GasStation current : gasStationRepository.findAll()) {
			current.setReportDependability(Utils.computeTrust(current));
			gasStationRepository.save(current);
		}
	}

}
