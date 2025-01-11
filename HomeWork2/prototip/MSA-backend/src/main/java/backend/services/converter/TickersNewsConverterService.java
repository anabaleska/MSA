package backend.services.converter;
import backend.model.TickersNews;
import backend.model.dto.TickersNewsDTO;

public interface TickersNewsConverterService {
    TickersNewsDTO convertToTickersNewsDTO(TickersNews tickersNews);
}
