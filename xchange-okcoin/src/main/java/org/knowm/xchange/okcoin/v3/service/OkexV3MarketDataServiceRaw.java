package org.knowm.xchange.okcoin.v3.service;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.okcoin.FuturesContract;
import org.knowm.xchange.okcoin.OkCoin;
import org.knowm.xchange.okcoin.OkCoinAdapters;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinDepth;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTickerResponse;
import org.knowm.xchange.okcoin.dto.marketdata.OkCoinTrade;
import org.knowm.xchange.okcoin.service.OkCoinBaseService;
import org.knowm.xchange.okcoin.v3.OkCoinV3;
import si.mazi.rescu.RestProxyFactory;

public class OkexV3MarketDataServiceRaw extends OkCoinBaseService {

    private final OkCoinV3 okCoinV3;

    /**
     * Constructor
     */
    public OkexV3MarketDataServiceRaw(Exchange exchange) {

        super(exchange);

        okCoinV3 = RestProxyFactory.createProxy(OkCoinV3.class, exchange.getExchangeSpecification().getSslUri());
    }

    public OkCoinTickerResponseV3 getTicker(CurrencyPair currencyPair) throws IOException {

        String symbol = OkCoinAdapters.adaptSymbol(currencyPair);
        OkCoinTickerResponse v1 = okCoin.getTicker("1", symbol);
        OkCoinTickerResponse v3 = okCoinV3.getTicker("1", symbol);

        return v3;
    }

    public OkCoinTickerResponse getFuturesTicker(CurrencyPair currencyPair, FuturesContract prompt) throws IOException {

        return okCoinV3.getFuturesTicker(OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName());
    }

    public OkCoinDepth getDepth(CurrencyPair currencyPair) throws IOException {

        return okCoin.getDepth("1", OkCoinAdapters.adaptSymbol(currencyPair));
    }

    public OkCoinDepth getFuturesDepth(CurrencyPair currencyPair, FuturesContract prompt) throws IOException {

        return okCoin.getFuturesDepth("1", OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName().toLowerCase());
    }

    public OkCoinTrade[] getTrades(CurrencyPair currencyPair) throws IOException {

        return okCoin.getTrades("1", OkCoinAdapters.adaptSymbol(currencyPair));
    }

    public OkCoinTrade[] getTrades(CurrencyPair currencyPair, long since) throws IOException {

        return okCoin.getTrades("1", OkCoinAdapters.adaptSymbol(currencyPair), since);
    }

    public OkCoinTrade[] getFuturesTrades(CurrencyPair currencyPair, FuturesContract prompt) throws IOException {

        return okCoin.getFuturesTrades("1", OkCoinAdapters.adaptSymbol(currencyPair), prompt.getName().toLowerCase());
    }

}
