package backend.services.converter;
import backend.model.Indicators;
import backend.model.dto.IndicatorsDTO;
import org.springframework.stereotype.Service;

@Service
public interface IndicatorsConverterService {
    IndicatorsDTO convertToStockIndicatorsDTO(Indicators stockIndicators);
}