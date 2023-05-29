package yu.softwareDesign.TripTip.domain.meeting.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingManageService;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingSearchService;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.meeting.dto.MeetingListDto;

import java.time.LocalDate;

@Tag(name = "meetings", description = "Meeting API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings")
@RequiredArgsConstructor
@Log4j2
public class MeetingApi {
    private final MeetingSearchService meetingSearchService;
    private final MeetingManageService meetingManageService;

    @Operation(summary = "모임 생성 및 수정 요청", description = "모임을 생성 및 수정 요청")
    @GetMapping("/save")
    public ResponseEntity<MeetingListDto> meetingSaveRequest(@PathVariable(name = "group_id") Long group_id) {
        Meeting m = meetingManageService.save(group_id);
        return ResponseEntity.ok(MeetingListDto.builder().meeting_id(m.getMeeting_id()).meeting_day(m.getMeeting_day()).build());
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
