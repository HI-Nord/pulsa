package Controllers;

import Services.Implementations.PostServiceImplementation;
import Services.Implementations.ReplyServiceImplementation;
import Services.Implementations.UserServiceImplementation;
import Persistence.Entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomePageController {
    private final PostServiceImplementation postService;
    private final UserServiceImplementation userService;
    private final ReplyServiceImplementation replyService;

    @Autowired
    public HomePageController(PostServiceImplementation postService, UserServiceImplementation userService, ReplyServiceImplementation replyService) {
        this.postService = postService;
        this.userService = userService;
        this.replyService = replyService;
    }

    @RequestMapping("/")
    public String frontPage(Model model) {
        List<Post> allPosts = postService.getPostsOrderedByCreated();

        model.addAttribute("posts", allPosts);

        return "frontPage";
    }
}
