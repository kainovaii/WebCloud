package fr.kainovaii.shopspring.dto;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.model.Post;

public class PostWithAuthor
{
    private final Post post;
    private final String authorName;

    public PostWithAuthor(Post post, String authorName) {
        this.post = post;
        this.authorName = authorName;
    }

    public Post getPost() {
        return post;
    }

    public String getAuthorName() {
        return authorName;
    }
}