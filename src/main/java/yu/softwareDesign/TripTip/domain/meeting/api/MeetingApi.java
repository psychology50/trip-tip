package yu.softwareDesign.TripTip.domain.meeting.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingManageService;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingSearchService;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.meeting.dto.MeetingDetailDto;
import yu.softwareDesign.TripTip.domain.meeting.dto.MeetingListDto;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;

@Tag(name = "meetings", description = "Meeting API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings")
@RequiredArgsConstructor
@Log4j2
public class MeetingApi {
    private final MeetingSearchService meetingSearchService;
    private final MeetingManageService meetingManageService;

    // TODO : 생성일이 UTC 기준이라 오차 생김
    @Operation(summary = "모임 생성 및 수정 요청", description = "모임을 생성 및 수정 요청")
    @GetMapping("/save")
    public ResponseEntity<MeetingListDto> meetingSaveRequest(@PathVariable(name = "group_id") Long group_id) {
        Meeting m = meetingManageService.save(group_id);
        return ResponseEntity.ok(MeetingListDto.builder().meeting_id(m.getMeeting_id()).meeting_day(m.getMeeting_day()).build());
    }

    @Operation(summary = "모임 상세 페이지", description = "모임 상세 정보를 볼 수 있는 페이지")
    @GetMapping("/{meeting_id}/detail")
    public String meetingDetailPageRequest(@PathVariable(name = "group_id") Long group_id,
                                       @PathVariable(name = "meeting_id") Long meeting_id, Model model) {
        Meeting meeting = meetingSearchService.findMeetingById(meeting_id).orElseThrow(() ->
                new IllegalArgumentException("해당 모임이 존재하지 않습니다.")
        );
        model.addAttribute("meetingDetailDto", MeetingDetailDto.builder()
                .group(meeting.getGroup())
                .leader_id(meeting.getGroup().getLeader().getUser_id())
                .leader_username(meeting.getGroup().getLeader().getUsername())
                .meeting_id(meeting.getMeeting_id())
                .meeting_day(meeting.getMeeting_day())
                .receipts(meeting.getReceipts())
                .total((meeting.getReceipts() != null)
                        ? (int) meeting.getReceipts().stream().mapToDouble(Receipt::getTotal).sum() : 0)
                .build());

        return "meetings/MeetingDetailPage";
    }

//    // TODO
//    @Operation(summary = "모임 수정 페이지", description = "모임을 수정할 수 있는 페이지")
//    @GetMapping("/{meeting_id}/update")
//    public String meetingUpdatePageRequest(@PathVariable(name = "group_id") Long group_id, Model model) {
//
//        return "meetings/MeetingEditPage";
//    }

    @Operation(summary = "모임 삭제 요청", description = "모임을 삭제 요청")
    @PreAuthorize("@securityService.isMember(#group_id)")
    @DeleteMapping("/{meeting_id}/delete")
    public ResponseEntity<String> meetingDeleteRequest(@PathVariable(name = "group_id") Long group_id,
                                                       @PathVariable(name = "meeting_id") Long meeting_id,
                                                       Authentication authentication) {
        meetingManageService.deleteMeeting((User)authentication.getPrincipal(), meeting_id);
        return ResponseEntity.ok("모임 삭제 완료");
    }
}
