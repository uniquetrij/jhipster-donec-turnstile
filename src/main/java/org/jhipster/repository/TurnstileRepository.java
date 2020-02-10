package org.jhipster.repository;

import java.util.List;
import org.jhipster.domain.Turnstile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Turnstile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TurnstileRepository extends JpaRepository<Turnstile, Long> {
    
    List<Turnstile> findByCameraId(String cameraId);

}
