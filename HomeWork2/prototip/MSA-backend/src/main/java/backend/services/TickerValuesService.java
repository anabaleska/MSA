package backend.services;

import backend.model.TickerValues;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface TickerValuesService {
    Page<TickerValues> findAll(Pageable pageable);
}
