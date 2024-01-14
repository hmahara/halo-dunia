package eu.iamhelmi.halodunia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;

import eu.iamhelmi.halodunia.dto.TestMessage;
import eu.iamhelmi.halodunia.service.RabbitMqProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")

public class TestController {
	private final RabbitMqProducer rabbitMqProducerService;
	
	@PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestMessage> index(@RequestBody TestMessage message) {
		log.info("Get API message: {}", message);
		rabbitMqProducerService.sendMessage(message.getMessage());
        return new ResponseEntity<TestMessage>(message, HttpStatus.OK);
    }
	
	@PostMapping(value = "/test2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleBody(@RequestBody String message) {
		log.info("Get API message: {}", message);
		//model.addAttribute("message", "hello");
		rabbitMqProducerService.sendMessage(message);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
