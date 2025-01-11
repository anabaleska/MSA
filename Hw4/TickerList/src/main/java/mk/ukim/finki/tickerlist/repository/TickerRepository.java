package mk.ukim.finki.tickerlist.repository;


import mk.ukim.finki.tickerlist.model.Ticker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, Long> {
    Optional<Ticker> findByName(String name);
    Page<Ticker> findAll(Pageable pageable);

}
