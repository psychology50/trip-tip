package yu.softwareDesign.TripTip.domain.receipt.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "receipts", description = "Receipt API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings/{meeting_id}/receipts")
@RequiredArgsConstructor
@Log4j2
public class ReceiptApi {


}
