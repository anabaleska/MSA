package backend.model.dto;

public class TickerDTO {

    private Long tickerId;
    private String tickerName;
    public Long getTickerId() {
        return tickerId;
    }

    public void setTickerId(Long tickerId) {
        this.tickerId = tickerId;
    }

    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }
}
