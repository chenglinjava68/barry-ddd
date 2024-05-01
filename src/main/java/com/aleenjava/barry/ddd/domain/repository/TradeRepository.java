package com.aleenjava.barry.ddd.domain.repository;


import com.aleenjava.barry.ddd.domain.event.TradeEvent;
import com.aleenjava.barry.ddd.domain.model.TradeRecord;

import java.util.List;

public interface TradeRepository {
    /**
     * 保存
     * @param tradeRecord
     * @return
     */
    TradeRecord save(TradeRecord tradeRecord);

    /**
     * 查询订单
     * @param tradeNumber
     * @return
     */
    TradeRecord findByTradeNumber(String tradeNumber);

    /**
     * 发送MQ事件消息
     * @param tradeEvent
     */
    void sendMQEvent(TradeEvent tradeEvent);

    /**
     * 获取所有
     * @return
     */
    List<TradeRecord> findAll();
}
