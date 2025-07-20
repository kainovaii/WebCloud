package fr.kainovaii.blogspring.repository;

import fr.kainovaii.blogspring.model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>
{

    @Query("SELECT COUNT(p) FROM Post p")
    long countPosts();
}