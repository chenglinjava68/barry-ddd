package com.aleenjava.barry.ddd.application.event;

import com.aleenjava.barry.ddd.domain.event.TradeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TradeMQReceiver {

    @RabbitListener(queues = "ddd-trade-succeed")
    public void receiveTradeMessage(TradeEvent tradeEvent){
        log.info("receiveTradeMessage {}",tradeEvent);
    }

}
