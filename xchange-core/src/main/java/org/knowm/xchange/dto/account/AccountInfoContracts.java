package org.knowm.xchange.dto.account;

import java.math.BigDecimal;

/**
 * Created by Sergey Shurmin on 7/1/17.
 */
public class AccountInfoContracts {
    private BigDecimal wallet;
    private BigDecimal available;
    private BigDecimal equity;
    private BigDecimal margin;
    private BigDecimal upl;
    private BigDecimal rpl;
    private BigDecimal riskRate;

    public AccountInfoContracts() {
    }

    public AccountInfoContracts(BigDecimal wallet, BigDecimal available, BigDecimal equity, BigDecimal margin, BigDecimal upl) {
        this.wallet = wallet;
        this.available = available;
        this.equity = equity;
        this.margin = margin;
        this.upl = upl;
    }

    public AccountInfoContracts(BigDecimal wallet, BigDecimal available, BigDecimal equity, BigDecimal margin, BigDecimal upl, BigDecimal rpl, BigDecimal riskRate) {
        this.wallet = wallet;
        this.available = available;
        this.equity = equity;
        this.margin = margin;
        this.upl = upl;
        this.rpl = rpl;
        this.riskRate = riskRate;
    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public BigDecimal getEquity() {
        return equity;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public BigDecimal getUpl() {
        return upl;
    }

    public BigDecimal getRpl() {
        return rpl;
    }

    public BigDecimal getRiskRate() {
        return riskRate;
    }

    @Override
    public String toString() {
        return "AccountInfoContracts{" +
                "wallet=" + wallet +
                ", available=" + available +
                ", equity=" + equity +
                ", margin=" + margin +
                ", upl=" + upl +
                ", rpl=" + rpl +
                ", riskRate=" + riskRate +
                '}';
    }
}
