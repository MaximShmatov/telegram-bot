package com.ms.maxbot.api;

import dtos.StockInfoDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
public class IssMoexStockApi extends WebClientBase<StockInfoDto> {
    private static final String BASE_URL = "https://iss.moex.com";
    private static final String RESPONSE_FORMAT = ".json";
    private static final String STOCK_INFO_BY_CODE_URI = "/iss/engines/stock/markets/shares/boards/%s/securities/%s";
    private static final Map<String, String> GET_PARAMS = Map.ofEntries(
            Map.entry("iss.meta", "off"),
            Map.entry("iss.json", "extended")
    );

    public IssMoexStockApi() {
        super(BASE_URL, StockInfoDto.class);
    }

    public Flux<StockInfoDto> getStockInfoByCode(String code, String tradingMode) {
        String uri = String.format(STOCK_INFO_BY_CODE_URI, tradingMode, code) + RESPONSE_FORMAT + buildGetParams();
        return getFlux(uri);
    }

    private String buildGetParams() {
        return GET_PARAMS.entrySet().stream()
                .map(entry -> String.join("=", entry.getKey(), entry.getValue()))
                .reduce("?", (accumulator, param) -> accumulator + (accumulator.equals("?") ? "" : "&") + param);
    }
}
