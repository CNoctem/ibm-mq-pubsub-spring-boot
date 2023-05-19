package hu.kh.poc.pubsub.publisher;

import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.destination.DynamicDestinationResolver;



@Configuration
public class JmsConfig {

    @Bean
    public DynamicDestinationResolver destinationResolver() {
        return new DynamicDestinationResolver() {
            @Override
            public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain) throws JMSException {
                try {
                    if (destinationName.endsWith(".TOPIC")) {
                        pubSubDomain = true;
                    }
                    return super.resolveDestinationName(session, destinationName, pubSubDomain);
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

}