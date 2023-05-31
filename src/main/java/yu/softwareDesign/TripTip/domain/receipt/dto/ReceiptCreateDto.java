package yu.softwareDesign.TripTip.domain.receipt.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.user.domain.User;
import yu.softwareDesign.TripTip.domain.user.dto.UserParticipationDto;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@ToString(of = {"receipt_name", "total", "is_clear"})
public class ReceiptCreateDto {
    private Long receipt_id;
    private String receipt_name;
    private Double total;
    private Boolean is_clear;

    private User payer;
    private List<UserParticipationDto> participationUsers;
    private Meeting meeting;

    @Builder
    public ReceiptCreateDto(Long receipt_id, String receipt_name, Double total, Boolean is_clear,
                            List<UserParticipationDto> participationUsers, User payer, Meeting meeting) {
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
        this.payer = payer;
        this.participationUsers = participationUsers;
        this.meeting = meeting;
    }

    public Receipt toEntity() {
        return Receipt.builder()
                .receipt_id(receipt_id)
                .receipt_name(receipt_name)
                .total(total)
                .is_clear(is_clear)
                .build();
    }
}
