package org.knowm.xchange.dto.trade;

import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sergey Shurmin on 6/23/17.
 */
public class ContractLimitOrder extends LimitOrder {

    public ContractLimitOrder(OrderType type, BigDecimal tradableAmount, CurrencyPair currencyPair, String id, Date timestamp, BigDecimal limitPrice) {
        super(type, tradableAmount, currencyPair, id, timestamp, limitPrice);
    }

    public ContractLimitOrder(OrderType type, BigDecimal tradableAmount, CurrencyPair currencyPair, String id, Date timestamp, BigDecimal limitPrice, BigDecimal averagePrice, BigDecimal cumulativeAmount, OrderStatus status) {
        super(type, tradableAmount, currencyPair, id, timestamp, limitPrice, averagePrice, cumulativeAmount, status);
    }

    private BigDecimal contractPrice;

    public BigDecimal getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(BigDecimal contractPrice) {
        this.contractPrice = contractPrice;
    }
}
