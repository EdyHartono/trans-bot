package org.transbot;

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
	public AutocompletePrediction[] test(@RequestParam String message){
		return messageConverterService.convertMessageToRoute(message);
	}
	
}
