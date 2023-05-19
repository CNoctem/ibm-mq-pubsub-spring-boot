package hu.kh.poc.pubsub.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class Publisher {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${example.topic}")
    String topic;

    @GetMapping("publish/{msg}")
    String publish(@PathVariable String msg) {
        send(msg);
        return "OK";
    }

    public void send(String text) {
        jmsTemplate.convertAndSend(topic, text);
    }

}
