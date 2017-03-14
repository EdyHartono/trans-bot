package org.transbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transbot.service.MessageConverterService;
import org.apache.commons.lang3.StringUtils;

@Service
public class MessageConverterServiceImpl implements MessageConverterService{
	
	PlacesApi p =
	
	@Override
	public AutocompletePrediction[] convertMessageToRoute(String message){
		String startPoint = message.substring(0, message.indexOf("-"));
		String destination = message.substring(message.indexOf("-")+1);
		LatLng jakartaLatLng = new LatLng(-6.21462, 106.84513);
		
//		try{
//			AutocompletePrediction[] startPointPredictions = textSearchRequest.location(jakartaLatLng).input(startPoint).await();
//			return startPointPredictions;
//		} catch(Exception e){
//			return null;
//		}
		return null;
	}
}
