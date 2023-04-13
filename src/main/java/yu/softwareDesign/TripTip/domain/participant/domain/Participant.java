package yu.softwareDesign.TripTip.domain.participant.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

@Entity(name="participant")
@Table(name="PARTICIPANT")
@NoArgsConstructor
public class Participant {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="COST")
    private Double cost;
    @Column(name="IS_CLEAR")
    private Boolean is_clear;

    @Builder
    public Participant(Long id, Double cost, Boolean is_clear) {
        this.id = id;
        this.cost = Double.NaN;
        this.is_clear = Boolean.FALSE;
    }

    @ManyToOne @JoinColumn(name="USER_ID")
    private Subscriber subscriber;

    @ManyToOne @JoinColumn(name="RECEIPT_ID")
    private Receipt receipt;

    @Override
    public String toString() {
        return super.toString();
    }
}
