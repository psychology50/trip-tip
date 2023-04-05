package yu.softwareDesign.TripTip.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name="receipt")
@Table(name="RECEIPT")
@NoArgsConstructor
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
        this.total = Double.NaN;
        this.is_clear = Boolean.FALSE;
    }

    @ManyToOne @JoinColumn(name="MEETING_ID")
    private Meeting meeting;
    @OneToMany(mappedBy = "receipt")
    private List<Participant> participants;

    @Override
    public String toString() {
        return super.toString();
    }
}
