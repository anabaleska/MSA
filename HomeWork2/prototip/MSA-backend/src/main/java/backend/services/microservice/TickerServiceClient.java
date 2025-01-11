package backend.services.microservice;

import backend.model.dto.TickerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TickerServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public TickerDTO getTickerInfo() {

        return restTemplate.getForObject("http://localhost:8082/api/tickers", TickerDTO.class);
    }
}
