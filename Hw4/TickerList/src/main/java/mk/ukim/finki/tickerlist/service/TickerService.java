package mk.ukim.finki.tickerlist.service;


import mk.ukim.finki.tickerlist.model.Ticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TickerService {
    Optional<Ticker> findByName(String name);
    Page<Ticker> findAll(Pageable pageable);
    Ticker findById(Long id);
}