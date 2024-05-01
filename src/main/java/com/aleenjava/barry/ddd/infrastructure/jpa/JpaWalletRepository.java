package com.aleenjava.barry.ddd.infrastructure.jpa;

import com.aleenjava.barry.ddd.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaWalletRepository extends JpaRepository<Wallet, String> {
}
