package com.aleenjava.barry.ddd.infrastructure;

import com.aleenjava.barry.ddd.domain.event.TradeEvent;
import com.aleenjava.barry.ddd.domain.model.TradeRecord;
import com.aleenjava.barry.ddd.domain.repository.TradeRepository;
import com.aleenjava.barry.ddd.infrastructure.cache.Redis;
import com.aleenjava.barry.ddd.infrastructure.jpa.JpaTradeRepository;
import com.aleenjava.barry.ddd.infrastructure.mq.RabbitMQSender;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TradeRepositoryImpl implements TradeRepository {

    private final JpaTradeRepository jpaTradeRepository;

    private final RabbitMQSender rabbitMQSender;

    private final Redis redis;

    public TradeRepositoryImpl(JpaTradeRepository jpaTradeRepository, RabbitMQSender rabbitMQSender, Redis redis) {
        this.jpaTradeRepository = jpaTradeRepository;
        this.rabbitMQSender = rabbitMQSender;
        this.redis = redis;
    }

    @Override
    public TradeRecord save(TradeRecord tradeRecord) {
        return jpaTradeRepository.save(tradeRecord);
    }

    /**
     * 查询订单
     *
     * @param tradeNumber
     * @return
     */
    @Override
    public TradeRecord findByTradeNumber(String tradeNumber) {
        TradeRecord tradeRecord = redis.getTrade(tradeNumber);
        if (tradeRecord == null){
            tradeRecord = jpaTradeRepository.findFirstByTradeNumber(tradeNumber);

            // 缓存
            redis.cacheTrade(tradeRecord);
        }

        return tradeRecord;
    }

    /**
     * 发送事件消息
     * @param tradeEvent
     */
    @Override
    public void sendMQEvent(TradeEvent tradeEvent) {
        // 发送消息
        rabbitMQSender.sendMQTradeEvent(tradeEvent);
    }

    /**
     * 获取所有
     *
     * @return
     */
    @Override
    public List<TradeRecord> findAll() {
        return jpaTradeRepository.findAll();
    }


}
