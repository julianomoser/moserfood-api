package com.moser.moserfood.core.security.authorizationserver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Juliano Moser
 */
@Controller
public class SecurityController {

    @GetMapping("login")
    public String login() {
        return "pages/login";
    }
}
