package com.aleenjava.barry.ddd.application.event;

import com.aleenjava.barry.ddd.domain.event.TradeEvent;
import com.aleenjava.barry.ddd.domain.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TradeEventProcessor {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * 交易成功
     * @param tradeEvent
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, condition = "# tradeEvent.tradeStatus.name() == 'SUCCEED'")
    public void tradeSucceed(TradeEvent tradeEvent) {
        tradeRepository.sendMQEvent(tradeEvent);
    }
}
