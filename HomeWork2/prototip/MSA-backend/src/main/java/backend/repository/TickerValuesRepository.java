package backend.repository;

import backend.model.TickerValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface TickerValuesRepository extends JpaRepository<TickerValues,Long> {
    Page<TickerValues> findAll(Pageable pageable);
}
