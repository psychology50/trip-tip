package yu.softwareDesign.TripTip.domain.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;
import yu.softwareDesign.TripTip.domain.user.application.UserSignService;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;
import yu.softwareDesign.TripTip.domain.user.dto.UserDefaultDto;
import yu.softwareDesign.TripTip.domain.user.dto.UserLoginDto;
import yu.softwareDesign.TripTip.domain.user.dto.UserRegisterDto;

/**
 * User API
 * 회원 가입, 로그인, 로그아웃, 회원 정보 수정, 회원 탈퇴
 *
 */
@Tag(name = "users", description = "User API")
@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Log4j2
public class UserAPI {
    private final UserSearchService userSearchService;
    private final UserSignService userSignService;

    /**
     * 회원 가입 페이지
     * @param model
     * @return 회원 가입 페이지
     */
    @Operation(summary = "회원 가입", description = "회원 가입 페이지")
    @GetMapping("/signup")
    public String registerRequest(Model model) {
        UserRegisterDto form = new UserRegisterDto();
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        log.info("회원 가입 페이지 : {}", model.getAttribute("userRegisterDto"));

        return "users/SignUpPage";
    }

    /**
     * 회원 가입 처리
     * @param form
     * @return 로그인 페이지
     */
    @Operation(summary = "회원 가입", description = "회원 가입 처리")
    @PostMapping("/signup")
    public RedirectView registerResponse(UserRegisterDto form) {
        log.info("회원 가입 처리 : {}", form);
        userSignService.register(form);
        return new RedirectView("users/signInPage");
    }

    /**
     * 비동기 유저 검색
     * @param nickname
     * @return 유저 정보
     */
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<UserDefaultDto> searchUser(@RequestParam("nickname") String nickname) {
        CustomUser user = userSearchService.findUserByNickname(nickname).orElse(null);
        log.info("유저 검색 : {}", user);

        return (user != null)
                ? ResponseEntity.ok().body(UserDefaultDto.builder()
                    .user_id(user.getUser_id())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .build())
                : ResponseEntity.notFound().build();
    }

    /**
     * 로그인 페이지
     * @param error
     * @param exception
     * @param model
     * @return 로그인 페이지
     */
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
        return "users/SignInPage";
    }

    /**
     * 로그인 처리
     * @param request
     * @param response
     * @return 메인 페이지
     */
    @Operation(summary = "로그아웃", description = "로그아웃 처리")
    @GetMapping("/logout")
    public String logoutRequest(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "users/SignInPage";
    }

    // TODO: 구현
    /**
     * 유저 프로필 페이지
     * @param model
     * @return 유저 프로필 페이지
     */
    @Operation(summary = "유저 프로필", description = "유저 개인 정보와 영수증 정보들을 확인할 수 있는 페이지")
    @GetMapping("/profile")
    public String profileRequest(Model model) {

        return "users/UserProfilePage";
    }

//    // TODO: 구현
//    /**
//     * 유저 프로필 편집 페이지
//     * @param model
//     * @return 유저 프로필 편집 페이지
//     */
//    @Operation(summary = "유저 프로필 편집 페이지", description = "유저 개인 정보 수정 페이지")
//    @GetMapping("/update")
//    public String profileUpdatePageRequest(Model model) {
//
//        return "users/UserEditPage";
//    }

//    // TODO: 구현
//    /**
//     * 유저 프로필 편집 처리
//     * @param model
//     * @return 유저 프로필 페이지
//     */
//    @Operation(summary = "유저 프로필 편집", description = "유저 개인 정보 편집")
//    @PostMapping("/update")
//    public RedirectView profileUpdateRequest(Model model) {
//
//        return new RedirectView("api/users/detail");
//    }

    // TODO: 구현
    /**
     * 알림 페이지
     * @param model
     * @return 알림 페이지
     */
    @Operation(summary = "알림 페이지", description = "유저의 알림을 확인할 수 있는 페이지")
    @GetMapping("/notify")
    public String notifyPageRequest(Model model) {

        return "users/NotifyPage";
    }

    // TODO: 구현
    /**
     * 회원 탈퇴 페이지
     * @param model
     * @return 회원 탈퇴 페이지
     */
    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴 페이지")
    @GetMapping("/delete")
    public RedirectView deleteRequest(Model model) {

        return new RedirectView("/api");
    }

    /**
     * 접근 거부 페이지
     * @param exception
     * @param model
     * @return 접근 거부 페이지
     */
    @Operation(summary = "접근 거부", description = "접근 거부 페이지")
    @Parameters({@Parameter(name = "exception", description = "접근 거부 시 예외 메시지")})
    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value="exception", required = false) String exception, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUser user = (CustomUser) authentication.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("exception", exception);
        return "denied";
    }
}
