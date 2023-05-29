package yu.softwareDesign.TripTip.domain.receipt.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.baseModel.BaseDateEntity;

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
    @Column(name="PAYER_ID")
    private Long payer_id;
    @Column(name="PAYER_NAME")
    private String payer_name;

    // 이미지 필드 추가

    @Builder
    public Receipt(Long receipt_id, String receipt_name, Double total, Boolean is_clear, Long payer_id, String payer_name) {
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
        this.payer_id = payer_id;
        this.payer_name = payer_name;
    }

    @ManyToOne @JoinColumn(name="MEETING_ID")
    private Meeting meeting;
    @OneToMany(mappedBy = "receipt")
    private List<Participant> participants;

    public void setMeeting(Meeting meeting) {
        if (this.meeting != null) {
            this.meeting.getReceipts().remove(this);
        }
        this.meeting = meeting;
        if (meeting != null) {
            meeting.getReceipts().add(this);
        }
    }
}
