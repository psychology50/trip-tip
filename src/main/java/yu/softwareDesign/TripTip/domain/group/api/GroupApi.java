package yu.softwareDesign.TripTip.domain.group.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.group.application.GroupJoinService;
import yu.softwareDesign.TripTip.domain.group.application.GroupManageService;
import yu.softwareDesign.TripTip.domain.group.application.GroupSearchService;
import yu.softwareDesign.TripTip.domain.group.dto.GroupCreateDto;
import yu.softwareDesign.TripTip.domain.group.dto.GroupJoinDto;
import yu.softwareDesign.TripTip.domain.user.domain.User;

@Tag(name = "groups", description = "Group API")
@Controller
@RequestMapping("/api/groups")
@RequiredArgsConstructor
@Log4j2
public class GroupApi {
    //update(get, post), delete(post)
    private final GroupJoinService groupJoinService;
    private final GroupSearchService groupSearchService;
    private final GroupManageService groupManageService;

    /**
     * 그룹 생성 페이지
     * @param model
     * @return 그룹 생성 페이지
     */
    @Operation(summary = "그룹 생성 페이지", description = "그룹을 생성할 수 있는 페이지")
    @GetMapping("")
    public String groupCreateRequest(Model model) {
        model.addAttribute("groupCreateDto", new GroupCreateDto());
        log.info("그룹 생성 페이지 : {}", model.getAttribute("groupCreateDto"));

        return "groups/GroupCreatePage";
    }

    // TODO
    /**
     * 그룹 생성 및 수정 요청
     * @param dto
     * @return 그룹 목록 페이지
     */
    @Operation(summary = "그룹 생성 및 수정 요청", description = "그룹을 생성 요청")
    @PostMapping("/save")
    public RedirectView groupSaveRequest(GroupCreateDto dto, Authentication authentication) {
        Long group_id = groupManageService.saveGroup((User) authentication.getPrincipal(), dto).orElseThrow(()
                -> new IllegalArgumentException("그룹 생성 혹은 수정 실패"))
                .getGroup_id();
        return new RedirectView("api/groups/" + group_id);
    }

    // TODO
    /**
     * 그룹 목록 페이지
     * @param group_id
     * @param model
     * @return
     */
    @Operation(summary = "그룹 상세 페이지", description = "해당 그룹의 상세한 정보를 확인할 수 있는 페이지")
    @Parameters({@Parameter(name = "group_id", description = "그룹 아이디")})
    @PostMapping("/{group_id}/detail")
    public RedirectView groupDetailRequest(@RequestParam(value="group_id", required = true) Long group_id, Model model) {

        return new RedirectView("api/users/" + group_id + "/detail");
    }

    // TODO
    /**
     * 그룹 가입 요청.
     * 수정된 폼은 groupSaveRequest 에서 처리
     * @param model
     * @return 그룹 수정 페이지
     */
    @Operation(summary = "그룹 수정 페이지", description = "그룹을 수정할 수 있는 페이지")
    @GetMapping("/{group_id}/update")
    public String groupUpdatePageRequest(Model model) {

        return "groups/GroupEditPage";
    }

    // TODO : 권한 인증 단계 구현
    /**
     * 그룹 삭제 요청 (그룹장만 가능)
     * @param group_id
     * @return 그룹 목록 페이지
     */
    @Operation(summary = "그룹 삭제 요청", description = "그룹을 삭제 요청")
    @Parameters(@Parameter(name = "group_id", description = "그룹 아이디"))
    @PostMapping("/{group_id}/delete")
    public RedirectView groupDeleteRequest(@RequestParam(value="group_id", required = true) Long group_id) {
        groupManageService.deleteGroup(group_id);
        return new RedirectView("api/groups/");
    }

    /**
     * 그룹 가입 요청
     * @param dto
     * @param authentication
     * @return 그룹 목록 페이지
     */
    @Operation(summary = "그룹 가입 요청", description = "그룹에 가입 요청")
    @PostMapping("/join")
    public RedirectView groupJoinRequest(GroupJoinDto dto, Authentication authentication) {
        groupJoinService.joinGroup((User) authentication.getPrincipal(), dto);
        return new RedirectView("api/groups/");
    }

    /**
     * 그룹 탈퇴 요청
     * @param group_id
     * @param authentication
     * @return 그룹 목록 페이지
     */
    @Operation(summary = "그룹 탈퇴 요청", description = "그룹에서 탈퇴 요청")
    @PostMapping("/{group_id}/leave")
    public RedirectView groupLeaveRequest(@RequestParam(value="group_id", required = true) Long group_id, Authentication authentication) {
//        groupJoinService.leaveGroup((User) authentication.getPrincipal(), dto);
        return new RedirectView("api/groups/");
    }
}
