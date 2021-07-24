package black.lyg.blog.controller;

import black.lyg.blog.po.Comment;
import black.lyg.blog.po.User;
import black.lyg.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Value(value = "${avatar}")
    private String avatar;

    @GetMapping("/comment/{blogId}")
    public String comments(@PathVariable String blogId, Model model) {
        List<Comment> commentsList = commentService.findCommentsByBlogId(Integer.valueOf(blogId));
        model.addAttribute("commentsList", commentsList);
        return "blog :: commentList";
    }

    @PostMapping(value = "addComment")
    public String receiveComment(Comment comment, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            comment.setAvatar(avatar);
        } else {
            comment.setAvatar(user.getAvatar());
        }
        commentService.saveComment(comment);
        return "redirect:comment/" + comment.getBlogId();
    }
}
