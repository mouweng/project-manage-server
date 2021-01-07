package zju.cst.project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "This is test!";
    }
}
