package yu.softwareDesign.TripTip.domain.meeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;

import java.util.List;

public interface MeetingRepo extends JpaRepository<Meeting, Long> {

}
