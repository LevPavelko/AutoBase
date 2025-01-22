package AutoBase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @GetMapping(value = "/home")
    public String index(Model model) {

        return "home";
    }

    @GetMapping(value = "/")
    public String index2(Model model) {
        return "home";
    }
}
