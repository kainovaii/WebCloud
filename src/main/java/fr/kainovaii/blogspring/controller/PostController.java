package fr.kainovaii.blogspring.controller;

import fr.kainovaii.blogspring.model.Post;
import fr.kainovaii.blogspring.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/posts")
public class PostController
{
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts()
    {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id)
    {
        return postService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/create")
    public Post createPostGet()
    {
        Post newPost = new Post();
        newPost.setId(1);
        newPost.setTitle("Diu eius haec Caesare praetorium.");
        newPost.setContent("Sin autem ad adulescentiam perduxissent, dirimi tamen interdum contentione vel uxoriae condicionis vel commodi alicuius, quod idem adipisci uterque non posset. Quod si qui longius in amicitia provecti essent, tamen saepe labefactari, si in honoris contentionem incidissent; pestem enim nullam maiorem esse amicitiis quam in plerisque pecuniae cupiditatem, in optimis quibusque honoris certamen et gloriae; ex quo inimicitias maximas saepe inter amicissimos exstitisse.");
        return postService.save(newPost);
    }

    @GetMapping("/all")
    public String allPosts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts/list";
    }

    @GetMapping("/show/{id}")
    public String showPost(Model model, @PathVariable Long id) {
        return postService.findById(id)
        .map(post -> {
            model.addAttribute("post", post);
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
}