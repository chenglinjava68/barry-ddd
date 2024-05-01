package com.aleenjava.barry.ddd.domain.event;


import com.aleenjava.barry.ddd.domain.enums.TradeStatus;
import com.aleenjava.barry.ddd.domain.enums.TradeType;
import com.aleenjava.barry.ddd.domain.model.TradeRecord;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;


@ToString
@Getter
public class TradeEvent implements Serializable {

    private final TradeRecord tradeRecord;
    private final TradeStatus tradeStatus;
    private final TradeType tradeType;

    public TradeEvent(TradeRecord tradeRecord) {
        this.tradeRecord = tradeRecord;
        this.tradeStatus = tradeRecord.getTradeStatus();
        this.tradeType = tradeRecord.getTradeType();
    }
}
