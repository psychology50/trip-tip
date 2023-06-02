package yu.softwareDesign.TripTip.domain.receipt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;

import java.util.List;

public interface ReceiptRepo extends JpaRepository<Receipt, Long> {
    @Query("SELECT r FROM receipt r, participant p WHERE p.user.user_id = :user_id AND p.receipt.receipt_id = r.receipt_id")
    public List<Receipt> findReceiptByUserId(@Param("user_id") Long user_id);

    @Query("SELECT r FROM receipt r, group g WHERE g.group_id = :group_id AND r.meeting.group.group_id = g.group_id")
    public List<Receipt> findReceiptByGroupId(@Param("group_id") Long group_id);

    @Query("SELECT r FROM receipt r WHERE r.meeting.meeting_id = :meeting_id")
    public List<Receipt> findReceiptByMeetingId(@Param("meeting_id") Long meeting_id);

    @Query("SELECT SUM(r.total) FROM receipt r WHERE r.meeting.meeting_id = :meeting_id")
    public Double totalByMeetingId(@Param("meeting_id") Long meeting_id);


}
