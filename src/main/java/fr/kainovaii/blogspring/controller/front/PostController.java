package fr.kainovaii.blogspring.controller.front;

import fr.kainovaii.blogspring.model.Post;
import fr.kainovaii.blogspring.model.User;
import fr.kainovaii.blogspring.service.PostService;
import fr.kainovaii.blogspring.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController
{
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping()
    public String allPosts(Model model)
    {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @GetMapping("/{id}")
    public String showPost(Model model, @PathVariable Long id) {
        return postService.findById(id)
        .map(post -> {
            User author = userService.findById(post.getAuthorId())
                    .orElse(null);

            model.addAttribute("post", post);
            model.addAttribute("authorUsername", author != null ? author.getUsername() : "Inconnu");

            return "posts/show";
        })
        .orElse("error/404");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id)
    {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id)
    {
        return postService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }
}