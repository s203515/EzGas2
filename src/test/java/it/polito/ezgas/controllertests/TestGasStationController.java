package it.polito.ezgas.controllertests;

//import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.entity.GasStation;

class TestGasStationController {
//	Integer id;
	@BeforeEach
	void setUp() throws ClientProtocolException, IOException, JSONException{
		GasStation gs = new GasStation( 
				"GasStationNameTest", 
				"Corso Duca degli Abruzzi Turin Piemont Italy", 
				true, 
				true, 
				true, 
				true, 
				true, 
				true,
				"Enjoy",
				45.0534082, 
				7.6563042, 
				1.8, 
				1.8, 
				1.8, 
				1.8, 
				1.8, 
				2.1,
				1, 
				"05-28-2020", 
				50.0);
		GasStationDto g = GasStationConverter.toGasStationDto(gs);
		
//	45.0585717/7.6629516/diesel/Enjoy
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("gasStationId", g.getGasStationId());
	jsonObject.put("gasStationName", g.getGasStationName());
	jsonObject.put("gasStationAddress", g.getGasStationAddress());
	jsonObject.put("hasDiesel", g.getHasDiesel());
	jsonObject.put("hasSuper", g.getHasSuper());
	jsonObject.put("hasSuperPlus", g.getHasSuperPlus());
	jsonObject.put("hasGas", g.getHasGas());
	jsonObject.put("hasMethane", g.getHasMethane());
	jsonObject.put("hasPremiumDiesel", g.getHasPremiumDiesel());
	jsonObject.put("carSharing", g.getCarSharing());
	jsonObject.put("lat", g.getLat());
	jsonObject.put("lon", g.getLon());
	jsonObject.put("dieselPrice", g.getDieselPrice());
	jsonObject.put("superPrice", g.getSuperPrice());
	jsonObject.put("superPlusPrice", g.getSuperPlusPrice());
	jsonObject.put("gasPrice", g.getGasPrice());
	jsonObject.put("methanePrice", g.getMethanePrice());
	jsonObject.put("premiumDieselPrice", g.getPremiumDieselPrice());
	jsonObject.put("reportUser", g.getReportUser());
	jsonObject.put("reportTimestamp", g.getReportTimestamp());
	jsonObject.put("reportDependability", g.getReportDependability());
	HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
	StringEntity params = new StringEntity(jsonObject.toString());
	request.addHeader("content-type","application/json");
	request.setEntity(params);
	HttpResponse response = HttpClientBuilder.create().build().execute(request);
//	id = g.getGasStationId();
//	System.out.println(id);
	}
	
	@Test
	public void testGasStationByid() throws ClientProtocolException,IOException, JSONException {
		setUp();
		
		HttpUriRequest request2 = new HttpGet("http://localhost:8080/gasstation/getGasStation/2");
		HttpResponse response2 = HttpClientBuilder.create().build().execute(request2);
		
		String jsonFromResponse = EntityUtils.toString(response2.getEntity());
		System.out.println(response2);
		assert (response2.getStatusLine().getStatusCode() == 200);
	
	}
	@Test
	public void testGetAllGasStations() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		assert (jsonFromResponse.contains("Corso Duca degli Abruzzi Turin Piemont Italy"));
	}
	@Test
	public void testSaveGasStation() throws ClientProtocolException, IOException, JSONException {
		GasStation gs = new GasStation( 
				"GasStationNameTest", 
				"Corso Duca degli Abruzzi Turin Piemont Italy", 
				true, 
				true, 
				true, 
				true, 
				true, 
				true,
				"GasStationCarSharingTest",
				45.0534082, 
				7.6563042, 
				1.8, 
				1.8, 
				1.8, 
				1.8, 
				1.8, 
				2.1,
				0, 
				"28-05-2020 09.36.00 ", 
				50.0);
		GasStationDto g = GasStationConverter.toGasStationDto(gs);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("gasStationId", g.getGasStationId());
		jsonObject.put("gasStationName", g.getGasStationName());
		jsonObject.put("gasStationAddress", g.getGasStationAddress());
		jsonObject.put("hasDiesel", g.getHasDiesel());
		jsonObject.put("hasSuper", g.getHasSuper());
		jsonObject.put("hasSuperPlus", g.getHasSuperPlus());
		jsonObject.put("hasGas", g.getHasGas());
		jsonObject.put("hasMethane", g.getHasMethane());
		jsonObject.put("hasPremiumDiesel", g.getHasPremiumDiesel());
		jsonObject.put("carSharing", g.getCarSharing());
		jsonObject.put("lat", g.getLat());
		jsonObject.put("lon", g.getLon());
		jsonObject.put("dieselPrice", g.getDieselPrice());
		jsonObject.put("superPrice", g.getSuperPrice());
		jsonObject.put("superPlusPrice", g.getSuperPlusPrice());
		jsonObject.put("gasPrice", g.getGasPrice());
		jsonObject.put("methanePrice", g.getMethanePrice());
		jsonObject.put("premiumDieselPrice", g.getPremiumDieselPrice());
		jsonObject.put("reportUser", g.getReportUser());
		jsonObject.put("reportTimestamp", g.getReportTimestamp());
		jsonObject.put("reportDependability", g.getReportDependability());
		HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
		StringEntity params = new StringEntity(jsonObject.toString());
		request.addHeader("content-type","application/json");
		request.setEntity(params);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		assert (response.getStatusLine().getStatusCode() == 200);
	}
	@Test
	public void testDeleteGasStation() throws ClientProtocolException, IOException, JSONException {
		setUp();
		HttpUriRequest request = new HttpDelete("http://localhost:8080/gasstation/deleteGasStation/34");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		
		assert (response.getStatusLine().getStatusCode() == 200);
	}
	@Test
	public void testGetGasStationByGasolineType() throws ClientProtocolException, IOException, JSONException {
		setUp();
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByGasolineType/diesel");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		assert (jsonFromResponse.contains("Corso Duca degli Abruzzi Turin Piemont Italy"));
		
	}
	@Test
	public void testGetGasStationsByProximity() throws ClientProtocolException, IOException, JSONException  {
		setUp();
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByProximity/45.0/45.0/1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
				
		assert (response.getStatusLine().getStatusCode() == 200);
	}
	@Test
	public void testGetGasStationsWithCoordinates() throws ClientProtocolException, IOException, JSONException {
		setUp();
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/45.0534082/7.6563042/5/Diesel/Enjoy");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());	
		assert (jsonFromResponse.contains("GasStationNameTest"));
		
	}
	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException, JSONException {
		setUp();
		
		PriceReportDto priceReport = new PriceReportDto(1, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("gasStationId", priceReport.getGasStationId());
		jsonObject.put("dieselPrice", priceReport.getDieselPrice());
		jsonObject.put("superPrice", priceReport.getSuperPrice());
		jsonObject.put("superPlusPrice", priceReport.getSuperPlusPrice());
		jsonObject.put("gasPrice", priceReport.getGasPrice());
		jsonObject.put("methanePrice", priceReport.getMethanePrice());
		jsonObject.put("premiumDieselPrice", priceReport.getPremiumDieselPrice());
		jsonObject.put("userId", priceReport.getUserId());
		
		HttpPost request = new HttpPost("http://localhost:8080/gasstation/setGasStationReport");
		StringEntity params = new StringEntity(jsonObject.toString());
		request.addHeader("content-type","application/json");
		request.setEntity(params);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		//HttpUriRequest request = new HttpPost("http://localhost:8080/gasstation/setGasStationReport/5/1/1/1/1/1/1/1");
		
		assert (response.getStatusLine().getStatusCode() == 200);
	}
}
