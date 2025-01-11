package backend.services;
import backend.model.Ticker;
import backend.model.TickersNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TickersNewsService {
    Page<TickersNews> getTickersNewsByTickerId(Pageable pageable, Long tickerId);
    Page<TickersNews> findAll(Pageable pageable);

}