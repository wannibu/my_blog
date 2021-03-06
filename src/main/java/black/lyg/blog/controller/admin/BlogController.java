package black.lyg.blog.controller.admin;

import black.lyg.blog.po.Blog;
import black.lyg.blog.po.Type;
import black.lyg.blog.po.User;
import black.lyg.blog.service.BlogService;
import black.lyg.blog.service.TagService;
import black.lyg.blog.service.TypeService;
import black.lyg.blog.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @GetMapping("blogs/{username}")
    public String blogs(Model model, @PathVariable String username, @RequestParam(required = false, defaultValue = "1") String page) {
        PageHelper.startPage(Integer.parseInt(page), 8);
        User user = userService.checkUserByName(username);
        List<Blog> allBlogByPage = blogService.findBlogByUserId(user.getUserId());
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlogByPage);
        model.addAttribute("pageInfo", pageInfo);
        List<Type> types = typeService.allType();
        model.addAttribute("types", types);
        model.addAttribute("flag", 1);
        return "admin/blogs";
    }

    @PostMapping("blogs/search")
    public String search(Model model, @RequestParam(required = false, defaultValue = "1") String page,
                         String title, String typeId, Boolean recommend, String username) {
        PageHelper.startPage(Integer.parseInt(page), 8);
        if(title.isEmpty() && typeId.isEmpty() ){
            return "admin/blogs/"+username;
        }
        else{
            Page<Blog> blogPage = blogService.selectBlogByKeyWords(title.equalsIgnoreCase("") ? null : title
                    , typeId.equalsIgnoreCase("") ? null : typeId, recommend ? "1" : null, username);
            PageInfo<Blog> pageInfo = new PageInfo<>(blogPage);
            model.addAttribute("pageInfo", pageInfo);
        }
        return "admin/blogs :: blogList";
    }

    /**
     * ????????????????????????
     * @param model
     * @return
     */
    @GetMapping("blogs/addPage")
    public String addPage(Model model) {
        model.addAttribute("blog",new Blog());
        model.addAttribute("types", typeService.allType());
        model.addAttribute("tags", tagService.finaAllTags());
        return "admin/blogs-input";
    }

    /**
     * ???????????????????????????
     * @param model
     * @return
     */
    @GetMapping("blogs/{id}/edit")
    public String editPage(Model model, @PathVariable String id) {
        Blog blog = blogService.findBlogByBlogId(Integer.valueOf(id));
        if (blog==null){
            return "/error/404";
        }
        model.addAttribute("blog",blog);
        model.addAttribute("tagIds",blogService.findTagsByBlogId(Integer.valueOf(id)));
        model.addAttribute("types", typeService.allType());
        model.addAttribute("tags", tagService.finaAllTags());
        return "admin/blogs-input";
    }

    /**
     * ????????????
     * @param id ??????id
     * @return
     */
    @GetMapping(value = "blogs/{id}/delete")
    public String deleteBlog(@PathVariable String id,RedirectAttributes attributes){
        User user = blogService.findUserByBlogId(id);
        Integer integer = blogService.deleteBlog(Integer.valueOf(id));
        if (integer==1){
            attributes.addFlashAttribute("message","????????????");
        }else {
            attributes.addFlashAttribute("message","????????????");
        }
        return "redirect:/admin/blogs/"+user.getUsername();
    }

    /**
     * ??????(????????????)??????
     * @param blog ??????????????????
     * @param session session
     * @param attributes ??????????????????????????????????????????
     * @param tagIds ?????????????????????
     * @return
     */
    @PostMapping(value = "/blogs/add")
    public String blogAdd(Blog blog, HttpSession session, RedirectAttributes attributes, String tagIds) {
        User user = (User) session.getAttribute("user");
        blog.setUser(user);
        blog.setRecommend(blog.getRecommend() != null);
        blog.setAppreciation(blog.getAppreciation()!=null);
        blog.setShareStatement(blog.getShareStatement()!=null);
        blog.setComment(blog.getComment() != null);
        blog.setType(typeService.getType(blog.getTypeId()));
        blog.setTags(tagService.listTag(tagIds));
        blog.setUserId(user.getUserId());
        blog.setTypeId(blog.getTypeId());
        Blog addBlog = blogService.addBlog(blog);
        if (addBlog != null) {
            attributes.addFlashAttribute("message", "????????????");
        } else {
            attributes.addFlashAttribute("message", "????????????");
        }
        return "redirect:/admin/blogs/"+user.getUsername();
    }


}
