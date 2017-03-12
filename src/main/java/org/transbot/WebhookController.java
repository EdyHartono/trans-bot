package org.transbot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
	@RequestMapping("/")
	public String test(){
		return "a";
	}
	
}
