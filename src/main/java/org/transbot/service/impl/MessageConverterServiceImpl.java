package org.transbot.service.impl;

import com.google.maps.*;
import com.google.maps.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transbot.service.APIUtilsService;
import org.transbot.service.MessageConverterService;
import org.apache.commons.lang3.StringUtils;

@Service
public class MessageConverterServiceImpl implements MessageConverterService{

	@Autowired
	private APIUtilsService apiUtilsService;

	@Override
	public DirectionsResult convertMessageToRoute(String message){
		String startPoint = message.substring(0, message.indexOf("-"));
		String destination = message.substring(message.indexOf("-")+1);
		LatLng jakartaLatLng = new LatLng(-6.21462, 106.84513);

		//results[0].geometry.location.lat(), results[0].geometry.location.lng()

		try{
			GeoApiContext geoApiContext = new GeoApiContext().setApiKey("AIzaSyCo3aUKGX_8Sus5P_ACSdzqqUhvYqyE4Sg");
			AutocompletePrediction[] autocompleteStartPointPredictions=PlacesApi.placeAutocomplete(geoApiContext,startPoint).location(jakartaLatLng).await();
			AutocompletePrediction[] autocompleteEndPointPredictions=PlacesApi.placeAutocomplete(geoApiContext,destination).location(jakartaLatLng).await();

			//buat dapatin geo locationnya
			//disini kita kirim si pilihan user aja
			GeocodingResult[] geocodingStartPointResult= GeocodingApi.geocode(geoApiContext,autocompleteStartPointPredictions[1].description).await();
			double startLat=geocodingStartPointResult[0].geometry.location.lat;
			double startLng=geocodingStartPointResult[0].geometry.location.lng;


//			for(AutocompletePrediction autocompletePrediction : autocompleteEndPointPredictions)
//			{
//					System.out.print(autocompletePrediction.description);
//			}
			//System.out.print(startLat+" "+startLng);

			GeocodingResult[] geocodingEndPointResult= GeocodingApi.geocode(geoApiContext,autocompleteEndPointPredictions[0].description).await();

			DirectionsResult directionsResult= DirectionsApi.getDirections(
					geoApiContext,
					geocodingStartPointResult[0].formattedAddress,
					geocodingEndPointResult[0].formattedAddress
			).mode(TravelMode.TRANSIT).transitMode(TransitMode.BUS).await();

			System.out.print(directionsResult);
			System.out.print(directionsResult.routes);
			return directionsResult;

//			return autocompletePredictions;
		} catch(Exception e){
			return null;
		}
	}
}
