package yu.softwareDesign.TripTip.domain.receipt.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.user.domain.User;

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
    private Long payer_id;
    private String payer_name;

    // user 닉네임 정보와 각 cost 수집
    private List<User> users;
    private List<Participant> participants;

    @Builder
    public ReceiptSelectDto(Long receipt_id, String receipt_name, Double total, Boolean is_clear, Long payer_id, String payer_name, List<User> users) {
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
        this.payer_id = payer_id;
        this.payer_name = payer_name;
        this.users = users;
    }

    public Receipt toEntity() {
        return Receipt.builder()
                .receipt_id(receipt_id)
                .receipt_name(receipt_name)
                .total(total)
                .is_clear(is_clear)
                .payer_id(payer_id)
                .build();
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
}
