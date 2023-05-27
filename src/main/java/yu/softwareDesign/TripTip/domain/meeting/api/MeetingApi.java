package yu.softwareDesign.TripTip.domain.meeting.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingManageService;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingSearchService;
import yu.softwareDesign.TripTip.domain.meeting.dto.MeetingCreateDto;

@Tag(name = "meetings", description = "Meeting API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings")
@RequiredArgsConstructor
@Log4j2
public class MeetingApi {
    MeetingSearchService meetingSearchService;
    MeetingManageService meetingManageService;

    // create(get, post), update(get, post), delete(post), list(get), detail(get)

    // TODO
    /**
     * 모임 생성 페이지
     * @param group_id
     * @param model
     * @return 모임 생성 페이지
     */
    @Operation(summary = "모임 생성 페이지", description = "모임을 생성할 수 있는 페이지")
    @GetMapping("/create")
    public String meetingCreatePageRequest(@PathVariable(name = "group_id") Long group_id, Model model) {

        return "meetings/MeetingCreatePage";
    }

    // TODO
    /**
     * 모임 생성 및 수정 요청
     * @param group_id
     * @param dto
     * @return 모임 목록 페이지
     */
    @Operation(summary = "모임 생성 및 수정 요청", description = "모임을 생성 및 수정 요청")
    @PostMapping("/save")
    public RedirectView meetingSaveRequest(@PathVariable(name = "group_id") Long group_id, MeetingCreateDto dto) {


        return (dto.getMeeting_id() != null)
            ? new RedirectView("/api/groups/" + group_id + "/meetings/" + dto.getMeeting_id() + "/detail")
            : new RedirectView("/api/groups/" + group_id + "/detail");
    }

    // TODO
    @Operation(summary = "모임 상세 페이지", description = "모임 상세 정보를 볼 수 있는 페이지")
    @GetMapping("/{meeting_id}/detail")
    public String meetingDetailPageRequest(@PathVariable(name = "group_id") Long group_id,
                                       @PathVariable(name = "meeting_id") Long meeting_id, Model model) {

        return "meetings/MeetingDetailPage";
    }

    // TODO
    @Operation(summary = "모임 수정 페이지", description = "모임을 수정할 수 있는 페이지")
    @GetMapping("/{meeting_id}/update")
    public String meetingUpdatePageRequest(@PathVariable(name = "group_id") Long group_id, Model model) {

        return "meetings/MeetingEditPage";
    }

    // TODO
    @Operation(summary = "모임 삭제 요청", description = "모임을 삭제 요청")
    @PostMapping("/{meeting_id}/delete")
    public RedirectView meetingDeleteRequest(@PathVariable(name = "group_id") Long group_id, Model model) {

        return new RedirectView("/api/groups/"+group_id+"/list");
    }

}
