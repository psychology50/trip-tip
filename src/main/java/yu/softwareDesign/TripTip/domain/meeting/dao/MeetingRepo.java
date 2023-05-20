package yu.softwareDesign.TripTip.domain.meeting.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;

import java.util.List;

public interface MeetingRepo extends JpaRepository<Meeting, Long> {

    @Query("SELECT m FROM meeting m JOIN m.group.leader u WHERE u.user_id = :user_id")
    List<Meeting> findMeetingByUserId(Long user_id);

    @Query("SELECT m FROM meeting m JOIN m.group g WHERE g.group_id = :group_id")
    List<Meeting> findMeetingByGroupId(Long group_id);
}
