package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StockDataDto {
    @JsonProperty("SECID")
    private String stockId;
    @JsonProperty("BOARDID")
    private String tradingModeId;
    @JsonProperty("SHORTNAME")
    private String shortName;
    @JsonProperty("LOTSIZE")
    private Integer lotSize;
    @JsonProperty("STATUS")
    private String status;
    @JsonProperty("BOARDNAME")
    private String tradingModeName;
    @JsonProperty("SECNAME")
    private String stockName;
    @JsonProperty("MARKETCODE")
    private String marketCode;
    @JsonProperty("MINSTEP")
    private Double minStep;
    @JsonProperty("LATNAME")
    private String latName;
    @JsonProperty("REGNUMBER")
    private String regNumber;
    @JsonProperty("LISTLEVEL")
    private Integer listLevel;
}
