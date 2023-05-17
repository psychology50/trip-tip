package yu.softwareDesign.TripTip.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;
import yu.softwareDesign.TripTip.domain.user.application.UserSignService;
import yu.softwareDesign.TripTip.domain.user.domain.User;
import yu.softwareDesign.TripTip.domain.user.dto.UserLoginDto;
import yu.softwareDesign.TripTip.domain.user.dto.UserRegisterDto;

@Tag(name = "users", description = "User API")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Log4j2
public class UserAPI {
    private final UserSearchService userSearchService;
    private final UserSignService userSignService;

    @Operation(summary = "회원 가입", description = "회원 가입 페이지")
    @GetMapping("/signup")
    public String registerRequest(Model model) {
        UserRegisterDto form = new UserRegisterDto();
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        log.info("회원 가입 페이지 : {}", model.getAttribute("userRegisterDto"));

        return "users/signUpPage";
    }

    @Operation(summary = "회원 가입", description = "회원 가입 처리")
    @PostMapping("/signup")
    public RedirectView registerResponse(UserRegisterDto form) {
        log.info("회원 가입 처리 : {}", form);
        userSignService.register(form);
        return new RedirectView("users/signInPage");
    }

    @Operation(summary = "로그인", description = "로그인 페이지")
    @Parameters({
            @Parameter(name = "error", description = "로그인 실패 시 에러 메시지"),
            @Parameter(name = "exception", description = "로그인 실패 시 예외 메시지")
    })
    @GetMapping("/signin")
    public String loginRequest(
            @RequestParam(value="error", required = false) String error,
            @RequestParam(value="exception", required = false) String exception,
            Model model) {
        model.addAttribute("userLoginDto", new UserLoginDto());
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "users/signInPage";
    }

    @Operation(summary = "로그아웃", description = "로그아웃 처리")
    @GetMapping("/logout")
    public String logoutRequest(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "users/signInPage";
    }

    @Operation(summary = "접근 거부", description = "접근 거부 페이지")
    @Parameters({@Parameter(name = "exception", description = "접근 거부 시 예외 메시지")})
    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value="exception", required = false) String exception, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("exception", exception);
        return "users/deniedPage";
    }
}
