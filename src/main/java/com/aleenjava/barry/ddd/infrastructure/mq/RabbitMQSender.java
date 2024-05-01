package com.aleenjava.barry.ddd.infrastructure.mq;

import com.aleenjava.barry.ddd.constant.MessageConstant;
import com.aleenjava.barry.ddd.domain.event.TradeEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送事件消息
     * @param tradeEvent
     */
    public void sendMQTradeEvent(TradeEvent tradeEvent) {
        // 发送消息
        rabbitTemplate.convertAndSend(MessageConstant.MESSAGE_EXCHANGE, MessageConstant.tradeEventRoutingKey(tradeEvent), tradeEvent);
    }
}
