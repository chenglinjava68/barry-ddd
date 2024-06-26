package com.aleenjava.barry.ddd.controller;

import com.aleenjava.barry.ddd.domain.model.Wallet;
import com.aleenjava.barry.ddd.domain.repository.WalletRepository;
import com.aleenjava.barry.ddd.infrastructure.client.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/wallet")
@RestController
public class WalletController {

    private final WalletRepository walletRepository;

    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Autowired
    private AuthFeignClient authFeignClient;

    @PostMapping()
    public Wallet save(@RequestBody Wallet wallet) {
        wallet.setUserId(authFeignClient.findByUserName("userName"));
        return walletRepository.save(wallet);
    }

    /**
     * 查询
     *
     * @param walletId
     * @return
     */
    @GetMapping(path = "/{walletId}")
    public Wallet findOne(@PathVariable("walletId") String walletId) {
        return walletRepository.findById(walletId);
    }

    /**
     * 查询全部
     *
     * @return
     */
    @GetMapping()
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }
}
