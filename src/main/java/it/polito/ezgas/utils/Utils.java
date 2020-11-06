package it.polito.ezgas.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import exception.GPSDataException;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

public class Utils {
	
	/**
	 * Calculate the distance between two points
	 * 
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @return
	 */
	public static double calculateDistance(double lat1, double lat2, double lon1, double lon2) throws GPSDataException {
		if (!isLatLonValid(lat1, lon1) || !isLatLonValid(lat2, lon2)) {
			throw new GPSDataException("Invalid GPS Data \n");
		}
		 
		// The math module contains a function named toRadians which converts from
		// degrees to radians.
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// Haversine formula
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		// Radius of earth in kilometers. Use 3956 for miles
		double r = 6371;
		// calculate the result
		return (c * r);
	}

	/**
	 * Check the validity of coordinates
	 * 
	 * @param lat
	 * @param lon
	 * @return
	 */
	public static boolean isLatLonValid(double lat, double lon) {
		if (lat > 90 || lat < -90 || lon > 180 || lon < -180) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static double computeTrust(GasStation gasStation) {
		User u=gasStation.getUser();
		if(u==null) {
			return 0;
		}
		//System.out.println(gasStation.getReportTimestamp());
		//String timestamp=gasStation.getReportTimestamp();
		//String[] a = timestamp.split("-");
		//long time = Date.UTC(Integer.parseInt(a[2])-1900, Integer.parseInt(a[0])-1, Integer.parseInt(a[1]), 0, 0,0);
		//Date timeReport = new Date (time);
		Date timeReport = fromStringToDate(gasStation.getReportTimestamp());
		double obsolescence = computeObsolescence(getTodayDate(),timeReport);
		//System.out.println(obsolescence);
		return ((double)(50*(u.getReputation()+5)/10 + 50*obsolescence));
	}
	
	private static double computeObsolescence(Date today, Date timeStamp) {
		//System.out.println("TODAY:"+today.toString());
		//System.out.println("TIMESTAMP:"+timeStamp.toString());
		double dayPassed = dayPassed(today, timeStamp);
		//System.out.println("Day Passed:"+dayPassed);
		if( dayPassed < 7 ) {
			return ((double)(1-dayPassed/7));
		}
		return 0;
	}
	
	public static Date getTodayDate() {
		TimeZone timeZone = TimeZone.getTimeZone("Europe/Rome");
		Calendar calendar= Calendar.getInstance();
		calendar.setTimeZone(timeZone);
		return calendar.getTime();
	}

	public static String giveCarSharing(String carSharing) {
		if (carSharing.equals("null")) {
			return null;
		} else {
			return carSharing;
		}
	}

	public static double dayPassed(Date todayDate, Date timeReport) {
		return (todayDate.getTime()-timeReport.getTime())/(1000*60*60*24);
	}
	
	public static Date fromStringToDate(String timestamp) {
		String[] a = timestamp.split("-");
		long time = Date.UTC(Integer.parseInt(a[2])-1900, Integer.parseInt(a[0])-1, Integer.parseInt(a[1]), 0, 0,0);
		Date timeReport = new Date (time);
		return timeReport;
	}
	
}
