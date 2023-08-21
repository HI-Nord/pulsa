package pulsa.Persistence.Repositories;

import pulsa.Persistence.Entities.Sub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepository extends JpaRepository<Sub, Long> {
    Sub findBySlug(String slug);
}

