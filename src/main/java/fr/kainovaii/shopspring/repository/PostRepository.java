package fr.kainovaii.shopspring.repository;

import fr.kainovaii.shopspring.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>
{
    @Query("SELECT COUNT(p) FROM Post p")
    long count();

    Optional<Post> findBySlug(String slug);
}