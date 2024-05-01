package com.aleenjava.barry.ddd.application;

import com.aleenjava.barry.ddd.domain.TradeService;
import com.aleenjava.barry.ddd.domain.model.TradeRecord;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class TradeManager {

    private final TradeService tradeService;

    public TradeManager(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * 充值
     *
     * @param tradeRecord
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord recharge(TradeRecord tradeRecord) {
        if (tradeRecord.getWallet() == null || Strings.isEmpty(tradeRecord.getWallet().getWalletId())) {
            throw new RuntimeException("参数错误");
        }

        return tradeService.recharge(tradeRecord);
    }

    /**
     * 消费
     *
     * @param tradeRecord
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public TradeRecord consume(TradeRecord tradeRecord) {
        if (tradeRecord.getWallet() == null || Strings.isEmpty(tradeRecord.getWallet().getWalletId())) {
            throw new RuntimeException("参数错误");
        }

        return tradeService.consume(tradeRecord);
    }
}
