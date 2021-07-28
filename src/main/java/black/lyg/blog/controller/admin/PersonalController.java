package black.lyg.blog.controller.admin;

import black.lyg.blog.po.Blog;
import black.lyg.blog.po.User;
import black.lyg.blog.service.UserService;
import black.lyg.blog.util.DateUtil;
import black.lyg.blog.util.MD5Util;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class PersonalController {

    @Autowired
    private UserService userService;

    @GetMapping("personal/{username}")
    public String blogs(Model model, @PathVariable String username, @RequestParam(required = false, defaultValue = "1") String page) {
        PageHelper.startPage(Integer.parseInt(page), 8);
        User user = userService.checkUserByName(username);
        user.setPassword("0");
        model.addAttribute("user", user);
        return "admin/personal";
    }

    @PostMapping("personal/update")
    public String userInfoUadate(HttpSession session,String nickname, String email, String password) {
        User user = (User) session.getAttribute("user");
        User user1 = userService.checkUserByName(user.getUsername());
        if(user != null){
            if(nickname.isEmpty()){
                user.setNickName(user1.getNickName());
            }
            else{
                user.setNickName(nickname);
            }
            if(email.isEmpty()){
                user.setEmail(user1.getEmail());
            }
            else {
                user.setEmail(email);
            }
            if(password.isEmpty()){
                user.setPassword(user1.getPassword());
            }
            else{
                user.setPassword(MD5Util.md5(password));
            }
            user.setUpdateTime(DateUtil.getNowDate());
            userService.updateInfo(user);
            return "redirect:/admin/personal/"+user.getUsername();
        }
        return "/error/404";
    }


}
