package backend.services.converter.impl;
import backend.model.TickersNews;
import backend.model.dto.TickersNewsDTO;
import backend.services.converter.TickersNewsConverterService;
import org.springframework.stereotype.Service;

@Service
public class TickersNewsConverterServiceImpl implements TickersNewsConverterService {
    @Override
    public TickersNewsDTO convertToTickersNewsDTO(TickersNews tickersNews) {
        return new TickersNewsDTO(tickersNews.getId(), tickersNews.getTickerId(),
                tickersNews.getNewsId(), tickersNews.getDate(),tickersNews.getSentiment());
    }
}