package Persistence.Repositories;

import Persistence.Entities.Post;
import Persistence.Entities.Sub;
import Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreator(User user);

    List<Post> findAllByOrderByCreatedDesc();

    List<Post> findAllBySubOrderByCreatedDesc(Sub sub);
}

