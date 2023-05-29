package yu.softwareDesign.TripTip.domain.receipt.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.baseModel.BaseDateEntity;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;

@Entity(name="receipt")
@Table(name="RECEIPT")
@NoArgsConstructor
@Getter
@ToString(of = {"receipt_name"})
public class Receipt extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long receipt_id;
    @Column(name="RECEIPT_NAME")
    private String receipt_name;
    @Column(name="TOTAL")
    private Double total;
    @Column(name="IS_CLEAR")
    private Boolean is_clear;

    // 이미지 필드 추가
    @Builder
    public Receipt(Long receipt_id, String receipt_name, Double total, Boolean is_clear) {
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
    }

    @ManyToOne @JoinColumn(name="MEETING_ID")
    private Meeting meeting;
    @OneToMany(mappedBy = "receipt")
    private List<Participant> participants;
    @ManyToOne @JoinColumn(name="USER_ID")
    private User payer;

    public void setMeeting(Meeting meeting) {
        if (this.meeting != null) {
            this.meeting.getReceipts().remove(this);
        }
        this.meeting = meeting;
        if (meeting != null) {
            meeting.getReceipts().add(this);
        }
    }

    public void setPayer(User payer) {
        if (this.payer != null) {
            this.payer.getPay_receipts().remove(this);
        }
        this.payer = payer;
        if (payer != null) {
            payer.getPay_receipts().add(this);
        }
    }
}
