package yu.softwareDesign.TripTip.domain.meeting.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * @sample [yu.softwareDesign.TripTip.domain.meeting.application.MeetingSearchServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MeetingSearchService {
    private final MeetingRepo meetingRepo;

    public Optional<Meeting> findMeetingById(Long id) {
        return meetingRepo.findById(id);
    }
    public List<Meeting> findMeetingAll() {
        return meetingRepo.findAll();
    }
}
