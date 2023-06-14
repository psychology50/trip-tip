package yu.softwareDesign.TripTip.domain.participant.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

@Entity(name="participant")
@Table(name="PARTICIPANT")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"participant_id"})
@ToString(of = {"cost"})
public class Participant {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long participant_id;
    @Column(name="COST")
    private Double cost;
    @Column(name="IS_CLEAR")
    private Boolean is_clear;

    @Builder
    public Participant(Long participant_id, Double cost, Boolean is_clear) {
        this.participant_id = participant_id;
        this.cost = cost;
        this.is_clear = is_clear;
    }

    @ManyToOne @JoinColumn(name="USER_ID")
    private CustomUser user;

    @ManyToOne @JoinColumn(name="RECEIPT_ID")
    private Receipt receipt;

    public void setUser(CustomUser user) {
        if (this.user != null) {
            this.user.getParticipants().remove(this);
        }
        this.user = user;
        if (user != null) {
            user.getParticipants().add(this);
        }
    }

    public void setReceipt(Receipt receipt) {
        if (this.receipt != null) {
            this.receipt.getParticipants().remove(this);
        }
        this.receipt = receipt;
        if (receipt != null) {
            receipt.getParticipants().add(this);
        }
    }
}
