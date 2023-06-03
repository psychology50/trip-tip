package yu.softwareDesign.TripTip.domain.participant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    @Query("SELECT p FROM participant p WHERE p.receipt.receipt_id = :receipt_id")
    List<Participant> findByReceiptId(Long receipt_id);

    @Query("SELECT p FROM participant p WHERE p.receipt.meeting.meeting_id = :meeting_id")
    List<Participant> findByMeetingId(Long meeting_id);

    @Query("SELECT p FROM participant p WHERE p.receipt.receipt_id = :receipt_id AND p.user.user_id = :user_id")
    Participant findByReceiptIdAndUserId(Long receipt_id, Long user_id);

    @Query("SELECT p.cost FROM participant p WHERE p.receipt.receipt_id = :receipt_id AND p.user.user_id = :user_id")
    Optional<Double> findCostByReceiptIdAndUserId(Long receipt_id, Long user_id);

    @Query("SELECT p FROM participant p WHERE p.receipt.receipt_id = :receipt_id")
    List<Participant> findAllByReceiptId(Long receipt_id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM participant p WHERE p.receipt.receipt_id = :receipt_id AND p.user.user_id = :user_id")
    Boolean existsByReceiptIdAndUserId(Long receipt_id, Long user_id);

    @Query("DELETE FROM participant p WHERE p.receipt.receipt_id = :receipt_id AND p.user.user_id = :user_id")
    void deleteByReceiptIdAndUserId(Long receipt_id, Long user_id);
}
