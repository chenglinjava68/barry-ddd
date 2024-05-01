package com.aleenjava.barry.ddd.domain.repository;

import com.aleenjava.barry.ddd.domain.model.Wallet;

import java.util.List;

public interface WalletRepository {
    /**
     * 根据Id查询
     * @param walletId
     * @return
     */
    Wallet findById(String walletId);

    /**
     * 保存记录
     * @param wallet
     * @return
     */
    Wallet save(Wallet wallet);

    /**
     * 查询所有记录
     * @return
     */
    List<Wallet> findAll();
}
