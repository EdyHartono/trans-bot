package org.transbot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.transbot.service.MessageConverterService;



@SpringBootApplication
public class TransBotApplication {

	private static final Logger LOG = LoggerFactory.getLogger(TransBotApplication.class);

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MessageConverterService messageConverterService;

	
	public static void main(String[] args) {
		SpringApplication.run(TransBotApplication.class, args);
	}
}
