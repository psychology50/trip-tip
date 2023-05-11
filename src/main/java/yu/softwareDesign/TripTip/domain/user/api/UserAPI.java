package yu.softwareDesign.TripTip.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;

@Controller
@RequiredArgsConstructor
public class UserAPI {
    private final UserSearchService userSearchService;
}
