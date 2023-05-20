package yu.softwareDesign.TripTip.domain.meeting.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "meetings", description = "Meeting API")
@Controller
@RequestMapping("/api/groups/{group_id}/meetings")
@RequiredArgsConstructor
@Log4j2
public class MeetingApi {
}
