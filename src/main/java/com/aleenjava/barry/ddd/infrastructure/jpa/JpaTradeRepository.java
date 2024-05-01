package com.aleenjava.barry.ddd.infrastructure.jpa;

import com.aleenjava.barry.ddd.domain.model.TradeRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaTradeRepository extends JpaRepository<TradeRecord, Integer> {

    /**
     * 根据交易号查询一条记录
     * @param tradeNumber
     * @return
     */
    TradeRecord findFirstByTradeNumber(String tradeNumber);
}
