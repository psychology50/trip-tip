package yu.softwareDesign.TripTip.domain.settlement.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import yu.softwareDesign.TripTip.domain.baseModel.BaseDateEntity;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.time.LocalDateTime;

@Entity(name="settlement")
@Table(name="SETTLEMENT")
@NoArgsConstructor
@Getter
@ToString(of = {"sender", "receiver", "amount", "sentTime", "isSent"})
public class Settlement extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne @JoinColumn(name = "SENDER_ID")
    private User sender;

    @ManyToOne @JoinColumn(name = "RECEIVER_ID")
    private User receiver;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "SENT_TIME")
    private LocalDateTime sentTime;

    @Column(name = "IS_SENT")
    private Boolean isSent;

    @Builder
    public Settlement(User sender, User receiver, Double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.sentTime = null;
        this.isSent = false;
    }

    public void markAsSent() {
        this.sentTime = LocalDateTime.now();
        this.isSent = true;
    }

    public void setSender(User sender) {
        if (this.sender != null) {
            this.sender.getSentSettlements().remove(this);
        }
        this.sender = sender;
        if (sender != null) {
            sender.getSentSettlements().add(this);
        }
    }

    public void setReceiver(User receiver) {
        if (this.receiver != null) {
            this.receiver.getReceivedSettlements().remove(this);
        }
        this.receiver = receiver;
        if (receiver != null) {
            receiver.getReceivedSettlements().add(this);
        }
    }
}
