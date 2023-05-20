package yu.softwareDesign.TripTip.global.config.properties;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yu.softwareDesign.TripTip.domain.group.application.GroupSearchService;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.member.dao.MemberRepo;
import yu.softwareDesign.TripTip.domain.participant.dao.ParticipantRepo;
import yu.softwareDesign.TripTip.domain.receipt.dao.ReceiptRepo;
import yu.softwareDesign.TripTip.domain.user.application.UserSearchService;
import yu.softwareDesign.TripTip.domain.user.application.UserSignService;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final UserRepo userRepo;
    private final MemberRepo memberRepo;
    private final GroupRepo groupRepo;
    private final MeetingRepo meetingRepo;
    private final ReceiptRepo receiptRepo;
    private final ParticipantRepo participantRepo;

    @Bean
    public UserSearchService userSearchService() {
        return new UserSearchService(userRepo);
    }

    @Bean
    public UserSignService userSignService() {
        return new UserSignService(userRepo);
    }

    @Bean
    public GroupSearchService groupSearchService() {return new GroupSearchService(groupRepo);}
}
