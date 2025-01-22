package AutoBase.dao;

import AutoBase.model.Trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("SELECT t FROM Trip t WHERE t.driver.id = ?1 AND t.endDate IS NULL")
    Trip findByDriverId(Long id);

    @Query("SELECT t FROM Trip t WHERE t.driver.id = ?1 AND t.endDate IS NOT NULL")
    List<Trip> findAllDriverId(Long id);
}
