package com.aleenjava.barry.ddd.infrastructure;

import com.aleenjava.barry.ddd.domain.repository.WalletRepository;
import com.aleenjava.barry.ddd.domain.model.Wallet;
import com.aleenjava.barry.ddd.infrastructure.jpa.JpaWalletRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WalletRepositoryImpl implements WalletRepository {

    private final JpaWalletRepository jpaWalletRepository;

    public WalletRepositoryImpl(JpaWalletRepository jpaWalletRepository) {
        this.jpaWalletRepository = jpaWalletRepository;
    }

    @Override
    public Wallet findById(String walletId) {
        return jpaWalletRepository.findById(walletId).orElse(null);
    }

    @Override
    public Wallet save(Wallet wallet) {
        return jpaWalletRepository.save(wallet);
    }

    @Override
    public List<Wallet> findAll() {
        return jpaWalletRepository.findAll();
    }
}
