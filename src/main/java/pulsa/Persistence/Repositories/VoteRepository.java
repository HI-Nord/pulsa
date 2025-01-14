package pulsa.Persistence.Repositories;

import pulsa.Persistence.Entities.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Voter, Long> {
}

