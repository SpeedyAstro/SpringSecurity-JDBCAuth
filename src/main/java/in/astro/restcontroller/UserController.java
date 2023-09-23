package in.astro.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/")
    public String welcome(){
        return "<h1>Welcome to Astro World!!</h1>";
    }

    @GetMapping("/admin")
    public String adminProcess(){
        return "<h1>Welcome ADMIN</h1>";
    }

    @GetMapping("/user")
    public String userProcess(){
        return "<h1>Welcome USER</h1>";
    }
}
