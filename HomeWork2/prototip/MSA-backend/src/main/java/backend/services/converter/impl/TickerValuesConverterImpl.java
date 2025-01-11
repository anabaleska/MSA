package backend.services.converter.impl;

import backend.model.TickerValues;
import backend.model.dto.TickerValuesDTO;
import backend.services.TickerService;
import backend.services.converter.TickerValuesConverterService;
import org.springframework.stereotype.Service;

@Service
public class TickerValuesConverterImpl implements TickerValuesConverterService {

    private final TickerService tickerService;

    public TickerValuesConverterImpl(TickerService tickerService) {
        this.tickerService = tickerService;
    }

    @Override
    public TickerValuesDTO convertToTickerValuesDTO(TickerValues tickerValues) {
        return new TickerValuesDTO(tickerValues.getValue_id(),
                tickerValues.getStockId(),
                tickerService.findById(tickerValues.getStockId()).getName(),
                tickerValues.getDate(),
                tickerValues.getLastTransactionPrice(),
                tickerValues.getMaxPrice(),
                tickerValues.getMinPrice(),
                tickerValues.getAveragePrice(),
                tickerValues.getPercentageChange(),
                tickerValues.getAmount(),
                tickerValues.getBest(),
                tickerValues.getTotalVolume());
    }
}
