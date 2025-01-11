package backend.services;
import backend.model.Indicators;

import java.util.List;

public interface IndicatorsService {
    List<Indicators> findAll();

    Indicators findById(Long id);

    List<Indicators> findByStockId(Long id);
}