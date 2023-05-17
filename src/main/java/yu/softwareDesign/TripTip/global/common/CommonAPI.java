package yu.softwareDesign.TripTip.global.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import yu.softwareDesign.TripTip.domain.user.domain.User;
import yu.softwareDesign.TripTip.domain.user.dto.UserDto;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Log4j2
public class CommonAPI {
    @GetMapping("")
    public String index(Authentication authentication, Model model) {
        log.info("authentication : {}", authentication);
        User user = (User) authentication.getPrincipal();

        UserDto userDto = null;
        if (user != null)
            userDto = user.toDto();

        log.info("userDto : {}", userDto);
//        model.addAttribute("loginUser", userDto);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(@SessionAttribute(value = "admin", required = false) UserDto admin, Model model) {
        log.info("admin : {}", admin);
        model.addAttribute("admin", admin);
        return "admin";
    }
}
