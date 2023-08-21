package Services;

import Persistence.Entities.Sub;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubService {
    List<Sub> getSubs();

    Sub getSubById(Long subId);

    Sub addNewSub(Sub sub);

    void deleteSub(Sub sub);

    Sub editSub(Sub sub);

    Sub getSubBySlug(String slug);
}
