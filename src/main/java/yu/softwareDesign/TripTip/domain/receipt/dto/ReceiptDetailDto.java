package yu.softwareDesign.TripTip.domain.receipt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReceiptDetailDto {
    private Meeting meeting;

    private Long receipt_id;
    private String receipt_name;
    private Integer total;
    private Boolean is_clear;
    private List<Participant> participants;
    private Long payer_id;
    private String payer_username;
    private String payer_nickname;
    private Integer payer_cost;

    @Builder
    public ReceiptDetailDto(Meeting meeting, Long receipt_id, String receipt_name, Integer total, Boolean is_clear, List<Participant> participants,
                            Long payer_id, String payer_username, String payer_nickname, Integer payer_cost) {
        this.meeting = meeting;
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
        this.participants = participants;
        this.payer_id = payer_id;
        this.payer_username = payer_username;
        this.payer_nickname = payer_nickname;
        this.payer_cost = payer_cost;
    }
}
