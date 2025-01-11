package mk.ukim.finki.tickerlist.web.rest;

import mk.ukim.finki.tickerlist.model.dto.TickerDTO;
import mk.ukim.finki.tickerlist.service.TickerService;
import mk.ukim.finki.tickerlist.service.converter.TickerConverterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickers")
@CrossOrigin(origins = "http://localhost:3000")
public class TickerController {
    private final TickerService tickerService;
    private final TickerConverterService tickerConverterService;

    public TickerController(TickerService tickerService, TickerConverterService tickerConverterService) {
        this.tickerService = tickerService;
        this.tickerConverterService = tickerConverterService;
    }

    @GetMapping
    public Page<TickerDTO> findAll(@RequestParam(defaultValue = "170") int size, Pageable pageable) {
        Pageable modifiedPageable = PageRequest.of(pageable.getPageNumber(), size);
       return this.tickerService.findAll(modifiedPageable).map(tickerConverterService::convertToTickerDTO);

    }

}