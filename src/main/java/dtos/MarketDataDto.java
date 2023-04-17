package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDataDto {
    @JsonProperty("OPEN")
    private Double openPrice;
    @JsonProperty("LOW")
    private Double minPrice;
    @JsonProperty("HIGH")
    private Double maxPrice;
    @JsonProperty("LAST")
    private Double lastPrice;
    @JsonProperty("VALUE")
    private Double rubleVolume;
    @JsonProperty("VALUE_USD")
    private Double usdVolume;
    @JsonProperty("LCLOSEPRICE")
    private Double closePrice;
    @JsonProperty("LCURRENTPRICE")
    private Double currentPrice;
    @JsonProperty("CHANGE")
    private Double change;
    @JsonProperty("SYSTIME")
    private LocalDateTime dateTime;

    public void setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime.replace(" ", "T"));
    }

}
