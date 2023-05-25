package yu.softwareDesign.TripTip.domain.participant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

import java.util.List;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    @Query("SELECT p FROM participant p WHERE p.receipt.receipt_id = :receipt_id")
    List<Participant> findByReceiptId(Long receipt_id);

    @Query("SELECT p FROM participant p WHERE p.receipt.meeting.meeting_id = :meeting_id")
    List<Participant> findByMeetingId(Long meeting_id);
}
