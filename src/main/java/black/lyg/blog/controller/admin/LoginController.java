package black.lyg.blog.controller.admin;

import black.lyg.blog.po.User;
import black.lyg.blog.service.UserService;
import black.lyg.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping(value = "login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        User admin = userService.checkUser(username, password);
        if (admin!=null){
            admin.setPassword(null);
            session.setAttribute("user",admin);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:admin";
        }
    }

    //注册的页面
    @GetMapping("/add")
    public  String add(){
        return "admin/register";
    }

    @PostMapping("/register")
    public  String register(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String nickname,
                            @RequestParam String email,
                            RedirectAttributes attributes){
        //没有user  创建user给user赋值  写我们要添加的数据
        User user=new User();
        user.setUsername(username);
        user.setPassword(MD5Util.md5(password));
        user.setNickName(nickname);
        user.setEmail(email);
        //检查一下  你输入的用户名存在不存在
        User user1 = userService.checkUser(username, password);
        //不等空  说明用户存在
        if (user1!=null){
            attributes.addFlashAttribute("message","用户已经存在");
            //如果用户存在 我们返回当前页面
            return "redirect:/admin/add";

        }else {
            userService.addUser(user);
            return "redirect:/admin";
        }
    }


    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:admin";
    }

}
