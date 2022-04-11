package fun.madeby.gdpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 03 18
 */
@RequestMapping({"/", ""})
@Controller
public class IndexController {

    @RequestMapping({"", "index", "index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/oups")
    public String errorMenu() {
        return "not-implemented";
    }
}
