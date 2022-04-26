package fun.madeby.gdpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Gra_m on 2022 03 18
 */

@Slf4j
@Controller
public class IndexController {

    @GetMapping({"", "/" })
    public String index() {
        log.debug("/ or ''");
        return "index";
    }

    @GetMapping("/oups")
    public String errorMenu() {
        log.debug("/oups");
        return "not-implemented";
    }
}
