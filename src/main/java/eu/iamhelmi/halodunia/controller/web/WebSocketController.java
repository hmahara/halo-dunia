package eu.iamhelmi.halodunia.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebSocketController {
	@GetMapping ("index2")
    public String index(Model model) {
		model.addAttribute("message", "hello");
        return "index2";
    }
}
