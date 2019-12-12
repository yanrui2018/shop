package cn.yanrui.shop.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableRabbit
public class RabbitmqConfig {
    private static final Logger log= LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private Environment env;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("spring.rabbitmq.host"));
        connectionFactory.setUsername(env.getProperty("spring.rabbitmq.username"));
        connectionFactory.setPassword(env.getProperty("spring.rabbitmq.password"));
        connectionFactory.setPort(Integer.valueOf(env.getProperty("spring.rabbitmq.port")));
        connectionFactory.setVirtualHost(env.getProperty("spring.rabbitmq.virtual-host"));
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    //配置消费者监听的容器
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(10);
        //factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//设置确认模式手工确认
        return factory;
    }


 /*   @Bean
    public Queue loginUserQueue(){
        return new Queue(env.getProperty("login.user.queue"),true);
    }

    @Bean
    public DirectExchange loginUserExchange(){
        return new DirectExchange(env.getProperty("login.user.exchange"),true,false);
    }

    @Bean
    public Binding loginUserBinding(){
        return BindingBuilder.bind(loginUserQueue()).to(loginUserExchange()).with(env.getProperty("login.user.routingkey"));
    }*/

}
