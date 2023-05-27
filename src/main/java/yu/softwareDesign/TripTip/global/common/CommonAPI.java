package yu.softwareDesign.TripTip.global.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import yu.softwareDesign.TripTip.domain.group.application.GroupSearchService;
import yu.softwareDesign.TripTip.domain.group.dto.GroupJoinDto;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;
import yu.softwareDesign.TripTip.domain.user.dto.UserDto;

@Tag(name = "home", description = "HomePage API")
@Controller
@RequestMapping("")
@RequiredArgsConstructor
@Log4j2
public class CommonAPI {
    private final UserRepo userRepo;
    private final GroupSearchService groupSearchService;

    @GetMapping("")
    public String home() {
        return "redirect:/api";
    }

    @GetMapping("/api")
    public String index(Authentication authentication, Model model) {
        log.info("authentication : {}", authentication);
        User user = (User) authentication.getPrincipal();

        UserDto userDto = (user != null)
                ? UserDto.builder().username(user.getUsername())
                                   .nickname(user.getNickname())
                                   .address(user.getAddress())
                                   .bank(user.getBank())
                                   .phone(user.getPhone()).build()
                : null;

        // TODO: 최적화 필요
        if (user != null)
            groupSearchService.findRecentGroupByUser(user).forEach(group -> userDto.addGroup(group));

        log.info("userDto : {}", userDto);
        log.info("userDto.groups : {}", userDto.getGroupList());
        model.addAttribute("loginUser", userDto);
        model.addAttribute("groupJoinDto", new GroupJoinDto());
        return "index";
    }

    @GetMapping("/api/admin")
    public String admin(@SessionAttribute(value = "admin", required = false) UserDto admin, Model model) {
        log.info("admin : {}", admin);
        model.addAttribute("admin", admin);
        return "admin";
    }
}
