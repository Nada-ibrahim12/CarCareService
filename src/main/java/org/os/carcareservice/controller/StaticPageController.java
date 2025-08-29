package org.os.carcareservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticPageController {

    @GetMapping("/about")
    public String about() {
        return "This is the Car Care Service platform. Here users can request car wash, towing, battery, and other services.";
    }

    @GetMapping("/faq")
    public String faq() {
        return "FAQ: \n1. How to request a service? \n2. How providers are verified? \n3. Payment methods available.";
    }

    @GetMapping("/privacy-policy")
    public String privacyPolicy() {
        return "Privacy Policy: We respect your privacy. Your data is stored securely and used only for service purposes.";
    }

    @GetMapping("/terms")
    public String terms() {
        return "Terms & Conditions: By using this platform, you agree to follow the rules and respect providers.";
    }
}
