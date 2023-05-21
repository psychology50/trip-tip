package yu.softwareDesign.TripTip.domain.receipt.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@ToString(of = {"receipt_name", "total", "is_clear"})
public class ReceiptSelectDto {
    private Long receipt_id;
    private String receipt_name;
    private Double total;
    private Boolean is_clear;

    List<Participant> participants = new ArrayList<>();

    @Builder
    public ReceiptSelectDto(Long receipt_id, String receipt_name, Double total, Boolean is_clear) {
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
    }

    public Receipt toEntity() {
        return Receipt.builder()
                .receipt_id(receipt_id)
                .receipt_name(receipt_name)
                .total(total)
                .is_clear(Boolean.FALSE)
                .build();
    }
}
