package fr.kainovaii.blogspring.repository;

import fr.kainovaii.blogspring.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>
{

}