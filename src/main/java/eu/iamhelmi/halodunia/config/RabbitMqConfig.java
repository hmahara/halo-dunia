package eu.iamhelmi.halodunia.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Value("${rabbitmq.queue.name1}")
    private String queue1;
	
	@Value("${rabbitmq.queue.name2}")
    private String queue2;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // spring bean for rabbitmq queue
    @Bean
    public Queue queue1(){
        return new Queue(queue1);
    }
    
    @Bean
    public Queue queue2(){
        return new Queue(queue2);
    }

    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    // binding between queue and exchange using routing key
    @Bean
    public Binding binding1(){
        return BindingBuilder
                .bind(queue1())
                .to(exchange())
                .with(routingKey);
    }
    
    @Bean
    public Binding binding2(){
        return BindingBuilder
                .bind(queue2())
                .to(exchange())
                .with(routingKey);
    }
    
}
