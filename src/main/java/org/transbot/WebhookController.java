package org.transbot;

import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.transbot.service.MessageConverterService;

import com.google.maps.model.AutocompletePrediction;

@RestController
public class WebhookController {
	
	@Autowired
	private MessageConverterService messageConverterService;
	
	@RequestMapping("/")
	public DirectionsResult  test(){
		return messageConverterService.convertMessageToRoute("binus-tanah abang");
	}
}
