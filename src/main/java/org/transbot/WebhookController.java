package org.transbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.*;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.transbot.service.MessageConverterService;
import retrofit2.Response;

@RestController
public class WebhookController {

	private static final Logger LOG = LoggerFactory.getLogger(TransBotApplication.class);
	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MessageConverterService messageConverterService;

	@RequestMapping("/")
	public void  test(){
//		String to=event.getSource().getUserId();
		try {
			DirectionsRoute[] directionsRoutes= messageConverterService.convertMessageToRoute("binus-mangga dua mall").routes;
			String value="";
			int step=1;
			DirectionsStep[] directionsSteps = directionsRoutes[0].legs[0].steps;

			for (DirectionsStep directionsStep : directionsSteps){
				value += String.format("%d.%s\n",step++, directionsStep.htmlInstructions);
				if(directionsStep.travelMode == TravelMode.TRANSIT){

					value +=new StringBuilder().appendCodePoint(0x0001F68B).toString()+directionsStep.transitDetails.departureStop.name+ "\n";
					value += new StringBuilder().appendCodePoint(0x0001F68F).toString()+directionsStep.transitDetails.arrivalStop.name + "\n";
				}
			}

			Message message = new TextMessage(value);
			PushMessage pushMessage = new PushMessage("U3df0ca20b02fa0e3583b6b59fe29990e", message);
			lineMessagingClient.pushMessage(pushMessage);
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage(),e);
		}
	//	return messageConverterService.convertMessageToRoute("binus-tanah abang");
	}

}
