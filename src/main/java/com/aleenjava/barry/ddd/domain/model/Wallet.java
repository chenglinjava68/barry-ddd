package com.aleenjava.barry.ddd.domain.model;


import com.aleenjava.barry.ddd.domain.enums.WalletStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wallet extends BaseEntity {

    /**
     * 钱包ID
     */
    @Id
    private String walletId;
    /**
     * 密码
     */
    private String password;
    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private WalletStatus walletStatus = WalletStatus.AVAILABLE;
    /**
     * 用户Id
     */
    private Integer userId;
    /**
     * 余额
     */
    private BigDecimal balance = BigDecimal.ZERO;

}
