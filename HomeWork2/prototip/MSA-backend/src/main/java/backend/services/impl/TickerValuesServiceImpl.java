package backend.services.impl;

import backend.model.TickerValues;
import backend.repository.TickerValuesRepository;
import backend.services.TickerValuesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TickerValuesServiceImpl implements TickerValuesService {
    private final TickerValuesRepository tickerValuesRepository;

    public TickerValuesServiceImpl(TickerValuesRepository tickerValuesRepository) {
        this.tickerValuesRepository = tickerValuesRepository;
    }

    @Override
    public Page<TickerValues> findAll(Pageable pageable) {
        return tickerValuesRepository.findAll(pageable);
    }
}
