package backend.services.converter.impl;

import backend.model.Ticker;
import backend.model.dto.TickerDTO;
import backend.services.converter.TickerConverterService;
import org.springframework.stereotype.Service;

@Service
public class TickerConverterServiceImpl implements TickerConverterService {
    @Override
    public TickerDTO convertToTickerDTO(Ticker ticker) {
        return new TickerDTO(ticker.getId(), ticker.getName());
    }
}