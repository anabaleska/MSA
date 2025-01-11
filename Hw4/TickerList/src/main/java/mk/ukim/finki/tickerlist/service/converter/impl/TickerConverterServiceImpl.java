package mk.ukim.finki.tickerlist.service.converter.impl;



import mk.ukim.finki.tickerlist.model.Ticker;
import mk.ukim.finki.tickerlist.model.dto.TickerDTO;
import mk.ukim.finki.tickerlist.service.converter.TickerConverterService;
import org.springframework.stereotype.Service;

@Service
public class TickerConverterServiceImpl implements TickerConverterService {
    @Override
    public TickerDTO convertToTickerDTO(Ticker ticker) {
        return new TickerDTO(ticker.getId(), ticker.getName());
    }
}