package cn.yanrui.shop.rabbitmq.impl;

import cn.yanrui.shop.rabbitmq.po.Mail;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("producer")
public class Producer{
    @Autowired
    RabbitTemplate rabbitTemplate;
    public void sendMail(String queue, Mail mail) {
        rabbitTemplate.setDefaultReceiveQueue(queue);
        rabbitTemplate.convertAndSend(queue,mail);
    }

}