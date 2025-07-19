package fr.kainovaii.blogspring.controller.admin;

import fr.kainovaii.blogspring.model.Post;
import fr.kainovaii.blogspring.service.PostService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;


@Controller("adminPostController")
@RequestMapping("/admin/posts")
public class PostController
{
    private final PostService postService;

    public PostController(PostService postService)
    {
        this.postService = postService;
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
    public RedirectView edit(
            @RequestParam long id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            RedirectAttributes redirectAttributes) {

        postService.findById(id).ifPresentOrElse(post -> {
            post.setTitle(title);
            post.setContent(content);

            try {
                String uploadedFilename = handleFileUpload(thumbnail);
                if (uploadedFilename != null) {
                    post.setThumbnail(uploadedFilename);
                }
            } catch (IOException e) {
                e.printStackTrace();
                redirectAttributes.addFlashAttribute("errorMessage", "File upload failed");
                // Optionnel : gérer erreur et quitter la lambda
            }

            postService.save(post);
        }, () -> {
            throw new RuntimeException("Post not found with ID " + id);
        });

        redirectAttributes.addFlashAttribute("successMessage", "Post updated successfully");
        return new RedirectView("/admin/posts/edit/" + id);
    }

    @GetMapping("/new")
    public String create()
    {
        return "admin/posts/create";
    }

    @PostMapping("/new")
    public RedirectView create(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
            RedirectAttributes redirectAttributes) {

        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setContent(content);

        try {
            String uploadedFilename = handleFileUpload(thumbnail);
            if (uploadedFilename != null) {
                newPost.setThumbnail(uploadedFilename);
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "File upload failed");
            return new RedirectView("/admin/posts");
        }

        postService.save(newPost);
        redirectAttributes.addFlashAttribute("successMessage", "Post created successfully");
        return new RedirectView("/admin/posts");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable long id, RedirectAttributes redirectAttributes)
    {
        try {
            postService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Post with ID " + id + " not found.");
        }
        redirectAttributes.addFlashAttribute("successMessage", "Success");
        return new RedirectView("/admin/posts");
    }

    private String handleFileUpload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String rootPath = System.getProperty("user.dir");
        File uploadDir = new File(rootPath + File.separator + "uploads");
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File dest = new File(uploadDir, filename);
        file.transferTo(dest);

        return filename;
    }
}