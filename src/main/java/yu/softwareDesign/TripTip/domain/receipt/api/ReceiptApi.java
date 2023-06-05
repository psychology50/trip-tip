package yu.softwareDesign.TripTip.domain.receipt.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yu.softwareDesign.TripTip.domain.meeting.application.MeetingSearchService;
import yu.softwareDesign.TripTip.domain.participant.application.ParticipantSearchService;
import yu.softwareDesign.TripTip.domain.receipt.application.ReceiptManageService;
import yu.softwareDesign.TripTip.domain.receipt.application.ReceiptSearchService;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptCreateDto;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptDetailDto;
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
    public ResponseEntity<Receipt> create(@PathVariable(value = "group_id") Long group_id,
                                          @PathVariable(value = "meeting_id") Long meeting_id,
                                          @RequestBody ReceiptCreateDto receiptCreateDto) {
        log.info("receiptSelectDto = {}", receiptCreateDto);
        log.info("getPayer = {}", receiptCreateDto.getPayer());
        log.info("getUsers = {}", receiptCreateDto.getParticipationUsers());

        return ResponseEntity.ok(receiptManageService.save(receiptCreateDto));
    }

    @GetMapping("/{receipt_id}/detail")
    public String detail(@PathVariable(value = "group_id") Long group_id,
                         @PathVariable(value = "meeting_id") Long meeting_id,
                         @PathVariable(value = "receipt_id") Long receipt_id,
                         @RequestParam(value="exception", required = false) String exception,
                         Model model) {
        Receipt receipt = receiptSearchService.findReceiptById(receipt_id).orElseThrow(() ->
                new IllegalArgumentException("Receipt Not Found"));
        model.addAttribute(
                "receiptDetailDto",
                ReceiptDetailDto.builder()
                        .meeting(receipt.getMeeting())
                        .participants(receipt.getParticipants())
                        .receipt_id(receipt.getReceipt_id())
                        .receipt_name(receipt.getReceipt_name())
                        .total(receipt.getTotal().intValue())
                        .is_clear(receipt.getIs_clear())
                        .payer_id(receipt.getPayer().getUser_id())
                        .payer_username(receipt.getPayer().getUsername())
                        .payer_nickname(receipt.getPayer().getNickname())
                        .payer_cost(
                                participantSearchService.getCostByReceiptIdAndUserId(receipt_id, receipt.getPayer().getUser_id())
                        )
                        .build()
        );
        model.addAttribute("exception", exception);

        return "receipts/ReceiptDetailPage";
    }

    @DeleteMapping("/{receipt_id}/delete")
    @PreAuthorize("@securityService.isPayer(#receipt_id)")
    public ResponseEntity<String> delete(@PathVariable(value = "group_id") Long group_id,
                                         @PathVariable(value = "meeting_id") Long meeting_id,
                                         @PathVariable(value = "receipt_id") Long receipt_id) {
        receiptManageService.delete(receipt_id);
        return ResponseEntity.ok("receipt delete success");
    }
}
