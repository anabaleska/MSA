package mk.ukim.finki.tickerlist.service.converter;


import mk.ukim.finki.tickerlist.model.Ticker;
import mk.ukim.finki.tickerlist.model.dto.TickerDTO;

public interface TickerConverterService {
    TickerDTO convertToTickerDTO(Ticker ticker);
}
