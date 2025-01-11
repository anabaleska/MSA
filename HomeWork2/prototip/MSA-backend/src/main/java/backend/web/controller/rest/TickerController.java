package backend.web.controller.rest;

import backend.model.dto.TickerDTO;
import backend.services.TickerService;
import backend.services.converter.TickerConverterService;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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