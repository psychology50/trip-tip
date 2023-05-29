package yu.softwareDesign.TripTip.domain.receipt.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.participant.application.ParticipantSearchService;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.application.ReceiptManageService;
import yu.softwareDesign.TripTip.domain.receipt.application.ReceiptSearchService;
import yu.softwareDesign.TripTip.domain.receipt.dao.ReceiptRepo;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptSelectDto;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;

@Tag(name = "receipts", description = "Receipt API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings/{meeting_id}/receipts")
@RequiredArgsConstructor
@Log4j2
public class ReceiptApi {
    private final ReceiptSearchService receiptSearchService;
    private final ReceiptManageService receiptManageService;
    private final UserSearchService userSearchService;
    private final ParticipantSearchService participantSearchService;

    @GetMapping("/create")
    public String createForm(Authentication authentication,
                             @PathVariable(value = "group_id") Long group_id,
                             @PathVariable(value = "meeting_id") Long meeting_id,
                             Model model) {
        User user = (User)authentication.getPrincipal();
        ReceiptSelectDto receiptSelectDto = ReceiptSelectDto.builder()
                .total(Double.NaN)
                .is_clear(Boolean.FALSE)
                .payer_id(user.getUser_id())
                .payer_name(user.getUsername())
                .users(userSearchService.findUserByGroupId(group_id))
                .build();

        receiptSelectDto.getUsers().forEach(u -> {
                    Participant p = Participant.builder()
                            .cost(Double.NaN)
                            .is_clear(Boolean.FALSE)
                            .build();
                    p.setUser(u);
                    receiptSelectDto.addParticipant(p);
        });

        log.info("Receipt Create Form : {}", receiptSelectDto.getUsers());

        model.addAttribute("receiptSelectDto", receiptSelectDto);
        return "/receipts/ReceiptCreatePage";
    }

    // TODO: users를 잘 받아오긴 하나, Redirection을 하면 에러가 발생하는 것에 주의
    @PostMapping("/create")
    public void create(@PathVariable(value = "group_id") Long group_id,
                               @PathVariable(value = "meeting_id") Long meeting_id,
                               ReceiptSelectDto receiptSelectDto,
                               Authentication authentication) {
        log.info("user_list = {}", receiptSelectDto);
        log.info("user_list = {}", receiptSelectDto.getUsers());
        receiptManageService.selectPay(receiptSelectDto, (User)authentication.getPrincipal(), group_id, meeting_id);

//        return new RedirectView("/api");
    }


}
