package com.workshop.notifier.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.workshop.notifier.model.Delivery;
import com.workshop.notifier.service.NotifierService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("notifiers")
public class WebSocketController {

	NotifierService notifierService;

	public WebSocketController(NotifierService notifierService) {
		this.notifierService = notifierService;
	}

	@GetMapping("/test")
	public void test() throws JsonProcessingException {
		this.notifierService.notifyNewDelivery(new Delivery());
	}
}
