package com.aleenjava.barry.ddd.controller;


import com.aleenjava.barry.ddd.application.TradeManager;
import com.aleenjava.barry.ddd.controller.dto.TradeDTO;
import com.aleenjava.barry.ddd.domain.model.TradeRecord;
import com.aleenjava.barry.ddd.domain.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/trade")
@RestController
public class TradeController {

    @Autowired
    private TradeManager tradeManager;

    @Autowired
    private TradeRepository tradeRepository;

    @PostMapping(path = "/recharge")
    public TradeDTO recharge(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.recharge(tradeDTO.toEntity()));
    }

    @PostMapping(path = "/consume")
    public TradeDTO consume(@RequestBody TradeDTO tradeDTO) {
        return TradeDTO.toDto(tradeManager.consume(tradeDTO.toEntity()));
    }

    @GetMapping(path = "/{tradeNumber}")
    public TradeDTO findByTradeNumber(@PathVariable("tradeNumber") String tradeNumber){
        return TradeDTO.toDto(tradeRepository.findByTradeNumber(tradeNumber));
    }

    @GetMapping()
    public List<TradeRecord> findAll(){
        return tradeRepository.findAll();
    }
}
