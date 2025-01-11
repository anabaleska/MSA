package backend.services.microservice;

import backend.model.dto.TickerDTO;
import backend.model.dto.TickerPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class TickerServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public TickerPageDTO getPaginatedTickers(Pageable pageable) {
        String url = "http://localhost:8082/api/tickers?page=" + pageable.getPageNumber() + "&size=" + pageable.getPageSize();

        // Use exchange to get the response as TickerPageDTO
        ResponseEntity<TickerPageDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<TickerPageDTO>() {}
        );

        return response.getBody();
    }
}