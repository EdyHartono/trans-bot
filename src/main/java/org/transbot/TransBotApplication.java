package org.transbot;

import com.google.maps.GeoApiContext;
import com.google.maps.TextSearchRequest;
import org.hibernate.validator.internal.util.logging.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@LineMessageHandler
@SpringBootApplication
public class TransBotApplication {

	@Autowired
	private LineMessagingClient lineMessagingClient;
	
	private void sendMessage(Event event)
	{
		String to=event.getSource().getUserId();
		Messages messages= Messages.MESSAGES;
		PushMessage pushMessage = new PushMessage(to, (Message) messages);
        lineMessagingClient.pushMessage(pushMessage);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TransBotApplication.class, args);
	}
	
	@EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		//kita cari tau dulu solusinya lalu kirim
		sendMessage(event);
		System.out.println("event: " + event);
        return new TextMessage(event.getMessage().getText());
    }
	
	@EventMapping
	public void handleDefaultMessageEvent(Event event) {
		//default message
		sendMessage(event);
	    System.out.println("event: " + event);
	}
}
