package org.transbot.service;

import com.google.maps.model.AutocompletePrediction;

public interface MessageConverterService {
	public AutocompletePrediction[] convertMessageToRoute(String message);
}
