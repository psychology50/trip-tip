package yu.softwareDesign.TripTip.domain.meeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;

public interface MeetingRepo extends JpaRepository<Meeting, Long> {
}
