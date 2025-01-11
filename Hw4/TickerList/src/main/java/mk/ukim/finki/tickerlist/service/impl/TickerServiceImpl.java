package mk.ukim.finki.tickerlist.service.impl;



import mk.ukim.finki.tickerlist.model.Ticker;
import mk.ukim.finki.tickerlist.repository.TickerRepository;
import mk.ukim.finki.tickerlist.service.TickerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TickerServiceImpl implements TickerService {

    private final TickerRepository tickerRepository;

    public TickerServiceImpl(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }


    @Override
    public Optional<Ticker> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Page<Ticker> findAll(Pageable pageable) {
        return tickerRepository.findAll(pageable);
    }

    @Override
    public Ticker findById(Long id) {
        return tickerRepository.findById(id).get();
    }
}