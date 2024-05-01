package com.aleenjava.barry.ddd.domain;

import com.aleenjava.barry.ddd.domain.enums.InOutFlag;
import com.aleenjava.barry.ddd.domain.enums.WalletStatus;
import com.aleenjava.barry.ddd.domain.repository.TradeRepository;
import com.aleenjava.barry.ddd.domain.repository.WalletRepository;
import com.aleenjava.barry.ddd.domain.enums.TradeStatus;
import com.aleenjava.barry.ddd.domain.enums.TradeType;
import com.aleenjava.barry.ddd.domain.model.TradeRecord;
import com.aleenjava.barry.ddd.domain.model.Wallet;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;


@Service
public class TradeServiceImpl implements TradeService {

    private final WalletRepository walletRepository;

    private final TradeRepository tradeRepository;

    public TradeServiceImpl(WalletRepository walletRepository, TradeRepository tradeRepository) {
        this.walletRepository = walletRepository;
        this.tradeRepository = tradeRepository;
    }

    /**
     * 充值
     *
     * @param tradeRecord
     * @return
     */
    @Override
    public TradeRecord recharge(TradeRecord tradeRecord) {

        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId());
        if (wallet == null){
            throw new RuntimeException("钱包不存在");
        }

        if (wallet.getWalletStatus().equals(WalletStatus.DESTROYED)) {
            throw new RuntimeException("钱包状态不可用");
        }

        if (tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("交易金额不可用");
        }

        tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        tradeRecord.setTradeType(TradeType.RECHARGE);
        tradeRecord.setInOutFlag(InOutFlag.IN);
        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);

        BigDecimal balance = wallet.getBalance().add(tradeRecord.getTradeAmount());
        wallet.setBalance(balance);
        tradeRecord.setWallet(wallet);
        tradeRecord.setBalance(balance);

        return tradeRepository.save(tradeRecord);
    }

    /**
     * 消费
     *
     * @param tradeRecord
     * @return
     */
    @Override
    public TradeRecord consume(TradeRecord tradeRecord) {

        Wallet wallet = walletRepository.findById(tradeRecord.getWallet().getWalletId());
        if (wallet == null){
            throw new RuntimeException("钱包不存在");
        }

        if (!wallet.getWalletStatus().equals(WalletStatus.AVAILABLE)) {
            throw new RuntimeException("钱包状态不可用");
        }

        BigDecimal balance = wallet.getBalance().subtract(tradeRecord.getTradeAmount());

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("钱包余额不足");
        }

        if (tradeRecord.getTradeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("交易金额不可用");
        }

        tradeRecord.setTradeNumber(UUID.randomUUID().toString());
        tradeRecord.setTradeType(TradeType.CONSUME);
        tradeRecord.setInOutFlag(InOutFlag.OUT);
        tradeRecord.setTradeStatus(TradeStatus.SUCCEED);

        wallet.setBalance(balance);
        tradeRecord.setWallet(wallet);
        tradeRecord.setBalance(balance);

        return tradeRepository.save(tradeRecord);
    }
}
