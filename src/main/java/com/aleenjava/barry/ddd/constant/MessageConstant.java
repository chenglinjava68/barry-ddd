package com.aleenjava.barry.ddd.constant;

import com.aleenjava.barry.ddd.domain.event.TradeEvent;

public class MessageConstant {

    public static final String MESSAGE_EXCHANGE = "barryhome.ddd";

    /**
     * 生成路由
     * @param tradeEvent
     * @return
     */
    public static String tradeEventRoutingKey(TradeEvent tradeEvent) {
        return String.format("trade.%s.%s", tradeEvent.getTradeType(), tradeEvent.getTradeStatus()).toLowerCase();
    }
}
