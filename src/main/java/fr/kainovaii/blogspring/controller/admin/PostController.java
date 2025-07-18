package fr.kainovaii.blogspring.controller.admin;

import fr.kainovaii.blogspring.Component.SnowflakeIdGenerator;
import fr.kainovaii.blogspring.model.Post;
import fr.kainovaii.blogspring.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller("adminPostController")
@RequestMapping("/admin/posts")
public class PostController
{
    private final PostService postService;
    private final SnowflakeIdGenerator idGenerator;

    public PostController(PostService postService, SnowflakeIdGenerator idGenerator)
    {
        this.postService = postService;
        this.idGenerator = idGenerator;
    }

    @GetMapping("")
    public String home(Model model)
    {
        model.addAttribute("posts", postService.findAll());
        return "admin/posts/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id)
    {
        return postService.findById(id)
        .map(post -> {
            model.addAttribute("post", post);
            return "admin/posts/edit";
        })
        .orElse("error/404");
    }

    @PostMapping("/edit")
    public RedirectView edit(@RequestParam long id, @RequestParam String title, @RequestParam String content, RedirectAttributes redirectAttributes)
    {
        postService.findById(id).ifPresentOrElse(post -> {
            post.setTitle(title);
            post.setContent(content);
            postService.save(post);
        }, () -> {
            throw new RuntimeException("Post non trouvé avec l'ID " + id);
        });
        redirectAttributes.addFlashAttribute("successMessage", "Success");
        return new RedirectView("/admin/posts/edit/" + id);
    }

    @GetMapping("/new")
    public String create()
    {
        return "admin/posts/create";
    }

    @PostMapping("/new")
    public RedirectView create(@RequestParam String title, @RequestParam String content,  RedirectAttributes redirectAttributes)
    {
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setContent(content);
        postService.save(newPost);

        redirectAttributes.addFlashAttribute("successMessage", "Success");
        return new RedirectView("/admin/posts");
    }
}