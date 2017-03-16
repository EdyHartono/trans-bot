package org.transbot.service;

import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.PlacesSearchResponse;

public interface MessageConverterService {
	String  convertMessageToRoute(String userId,String message);
}
