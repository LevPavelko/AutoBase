package AutoBase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }
    @GetMapping(value = "/logoutSuccessful")

    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @GetMapping(value = "/g")

    public String g(Model model) {
        model.addAttribute("title", "Logout");
        return "g";
    }


}