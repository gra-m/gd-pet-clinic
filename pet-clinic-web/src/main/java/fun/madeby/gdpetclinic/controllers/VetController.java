package fun.madeby.gdpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Gra_m on 2022 03 18
 */

@RequestMapping("/vets")
@Controller
public class VetController {

    @RequestMapping({"", "/", "/index", "/index.html" })
    public String listVets() {
       return "vets/index";
    }
}
