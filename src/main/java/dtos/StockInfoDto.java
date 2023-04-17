package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockInfoDto {
    @JsonProperty("securities")
    private List<StockDataDto> stockData;
    @JsonProperty("marketdata")
    private List<MarketDataDto> marketData;
}
