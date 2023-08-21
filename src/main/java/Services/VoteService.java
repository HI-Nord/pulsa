package Services;

import Persistence.Entities.Voter;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
    Voter addVoter(Voter vote);

    void removeVoter(Voter vote);
}
