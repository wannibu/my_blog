package black.lyg.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class PersonalController {

    @GetMapping("personal")
    public String personal(Model model) {
        return "admin/personal";
    }


}
