package org.transbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.model.*;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.transbot.model.State;
import org.transbot.repository.StateRepository;
import org.transbot.service.MessageConverterService;
import retrofit2.Response;

@LineMessageHandler
public class WebhookController {

	private static final Logger LOG = LoggerFactory.getLogger(TransBotApplication.class);
	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MessageConverterService messageConverterService;
	
//	@RequestMapping("/")
//	public void  test(){
////		String to=event.getSource().getUserId();
//
//		String to = "U3df0ca20b02fa0e3583b6b59fe29990e";
//		try {
//			String value= messageConverterService.convertMessageToRoute(to,"binus-mangga dua mall");
//			Message message = new TextMessage(value);
//			PushMessage pushMessage = new PushMessage(to, message);
//			lineMessagingClient.pushMessage(pushMessage);
//		}
//		catch (Exception e)
//		{
//			LOG.error(e.getMessage(),e);
//		}
//	//	return messageConverterService.convertMessageToRoute("binus-tanah abang");
//	}

	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		String value= messageConverterService.convertMessageToRoute(event.getSource().getUserId(), event.getMessage().getText());
		return new TextMessage(value);
	}

}
