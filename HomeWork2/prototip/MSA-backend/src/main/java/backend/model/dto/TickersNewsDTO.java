package backend.model.dto;

import java.util.Date;

public record TickersNewsDTO(
        Long id,
        Long tickerId,
        Long newsId,
        Date date,
        String sentiment
) {}