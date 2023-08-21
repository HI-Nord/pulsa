package pulsa.Controllers;

import pulsa.Services.Implementations.PostServiceImplementation;
import pulsa.Services.Implementations.SubServiceImplementation;
import pulsa.Services.Implementations.UserServiceImplementation;
import pulsa.Persistence.Entities.Post;
import pulsa.Persistence.Entities.Sub;
import pulsa.Persistence.Entities.User;
import pulsa.Services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class SubController {

    private final PostServiceImplementation postService;
    private final SubServiceImplementation subService;
    private final CloudinaryService cloudinaryService;
    private final UserServiceImplementation userService;

    @Autowired
    public SubController(PostServiceImplementation postService,
                         SubServiceImplementation subService,
                         CloudinaryService cloudinaryService,
                         UserServiceImplementation userService) {
        this.postService = postService;
        this.subService = subService;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
    }

    @RequestMapping(value = "/p/", method = RequestMethod.GET)
    public String subIndex(Model model) {
        List<Sub> allSubs = subService.getSubs();

        model.addAttribute("subs", allSubs);

        return "subIndex";
    }

    @RequestMapping(value = "/p/{slug}", method = RequestMethod.GET)
    public String subPage(@PathVariable("slug") String slug, Model model, HttpSession session) {
        Sub sub = subService.getSubBySlug(slug);
        List<Post> posts = postService.getSubPostsOrderedByCreated(sub);

        model.addAttribute("sub", sub);
        model.addAttribute("posts", posts);

        if (session.getAttribute("user") == null) return "subPage";

        User user = (User) session.getAttribute("user");

        // Cursed session fix
        User dbUser = userService.getUserObjectByUserName(user.getUserName());
        boolean following = dbUser.isFollowing(sub);
        model.addAttribute("following", following);

        return "subPage";
    }

    @RequestMapping(value = "/newSub", method = RequestMethod.GET)
    public String newSubGET(Sub sub) {

        return "newSub";
    }

    @RequestMapping(value = "/newSub", method = RequestMethod.POST)
    public String newSubPOST(String name, @RequestParam("image") MultipartFile image, Model model) {
        if(name.isEmpty()) {
            model.addAttribute("emptyName", true);
            return "subIndex";
        }
        Sub newSub = new Sub(name);
        if (subService.getSubBySlug(newSub.getSlug()) != null) return "subNameDuplicate";
        String imgUrl = "";
        if (!image.isEmpty()) imgUrl = cloudinaryService.uploadImage(image);
        newSub.setImage(imgUrl);
        subService.addNewSub(newSub);
        return "redirect:http://pulsa.nord.is/p/" + newSub.getSlug();
    }

    @RequestMapping(value = "/p/{slug}/toggleFollow", method = RequestMethod.POST)
    public String toggleFollow(@PathVariable("slug") String slug, HttpSession session, Model model) {
        Optional<User> user = userService.getUserByUserName(((User) session.getAttribute("user")).getUserName());
        Sub sub = subService.getSubBySlug(slug);
        if (!user.get().isFollowing(sub)) { userService.addSub(user.get(), sub); }
        else { userService.removeSub(user.get(), sub); }
        return "redirect:http://pulsa.nord.is/p/" + slug;
    }
}
