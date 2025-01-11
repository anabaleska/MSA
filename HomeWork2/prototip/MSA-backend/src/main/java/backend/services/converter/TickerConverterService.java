package backend.services.converter;

import backend.model.Ticker;
import backend.model.dto.TickerDTO;

public interface TickerConverterService {
    TickerDTO convertToTickerDTO(Ticker ticker);
}
