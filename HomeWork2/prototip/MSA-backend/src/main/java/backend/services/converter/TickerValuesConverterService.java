package backend.services.converter;

import backend.model.TickerValues;
import backend.model.dto.TickerValuesDTO;

public interface TickerValuesConverterService {
    TickerValuesDTO convertToTickerValuesDTO(TickerValues tickerValues);
}
