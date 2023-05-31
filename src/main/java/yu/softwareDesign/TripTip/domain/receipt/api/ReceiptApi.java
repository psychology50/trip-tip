package yu.softwareDesign.TripTip.domain.receipt.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yu.softwareDesign.TripTip.domain.group.application.GroupSearchService;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingSearchService;
import yu.softwareDesign.TripTip.domain.participant.application.ParticipantSearchService;
import yu.softwareDesign.TripTip.domain.receipt.application.ReceiptManageService;
import yu.softwareDesign.TripTip.domain.receipt.application.ReceiptSearchService;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptCreateDto;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;
import yu.softwareDesign.TripTip.domain.user.domain.User;
import yu.softwareDesign.TripTip.domain.user.dto.UserParticipationDto;

import java.util.stream.Collectors;

@Tag(name = "receipts", description = "Receipt API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings/{meeting_id}/receipts")
@RequiredArgsConstructor
@Log4j2
public class ReceiptApi {
    private final ReceiptSearchService receiptSearchService;
    private final ReceiptManageService receiptManageService;
    private final UserSearchService userSearchService;
    private final GroupSearchService groupSearchService;
    private final MeetingSearchService meetingSearchService;
    private final ParticipantSearchService participantSearchService;

    @GetMapping("/create")
    public String create(@PathVariable(value = "group_id") Long group_id,
                         @PathVariable(value = "meeting_id") Long meeting_id,
                         Authentication authentication,
                         Model model) {
        User user = (User)authentication.getPrincipal();

        model.addAttribute("payer", user);
        model.addAttribute("group_id", group_id);
        model.addAttribute("meeting_id", meeting_id);
        return "receipts/ReceiptCreatePage";
    }

    @GetMapping("/create-form")
    public ResponseEntity<ReceiptCreateDto> createForm(Authentication authentication,
                                                       @PathVariable(value = "group_id") Long group_id,
                                                       @PathVariable(value = "meeting_id") Long meeting_id) {
        User user = (User)authentication.getPrincipal();

        ReceiptCreateDto receiptCreateDto = ReceiptCreateDto.builder()
                .total(0.0)
                .is_clear(Boolean.FALSE)
                .payer(user)
                .participationUsers(userSearchService.findUserByGroupId(group_id).stream()
                        .map(u -> UserParticipationDto.builder()
                                .user_id(u.getUser_id())
                                .username(u.getUsername())
                                .nickname(u.getNickname())
                                .cost(0.0)
                                .selected(Boolean.TRUE)
                                .build())
                        .collect(Collectors.toList()))
                .meeting(meetingSearchService.findMeetingById(meeting_id).get())
                .build();

        log.info("Receipt Create Form : {}", receiptCreateDto.getParticipationUsers());
//        model.addAttribute("receiptSelectDto", receiptSelectDto);
        return ResponseEntity.ok(receiptCreateDto);
    }

    // TODO: users를 잘 받아오긴 하나, Redirection을 하면 에러가 발생하는 것에 주의
    @PostMapping("/create")
    public void create(@PathVariable(value = "group_id") Long group_id,
                               @PathVariable(value = "meeting_id") Long meeting_id,
                               @RequestBody ReceiptCreateDto receiptCreateDto,
                               Authentication authentication) {
        log.info("receiptSelectDto = {}", receiptCreateDto);
        log.info("getPayer = {}", receiptCreateDto.getPayer());
        log.info("getUsers = {}", receiptCreateDto.getParticipationUsers());

//        receiptManageService.selectPay(receiptSelectDto, (User)authentication.getPrincipal(), group_id, meeting_id);

//        return new RedirectView("/api");
    }
}
