package yu.softwareDesign.TripTip.domain.page.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class home {
    @GetMapping
    public String home() {
        return "index";
    }
}
