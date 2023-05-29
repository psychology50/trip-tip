package yu.softwareDesign.TripTip.domain.receipt.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
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

    private User payer;
    private List<User> users;
    private Group group;
    private Meeting meeting;
    private List<Participant> participants;

    @Builder
    public ReceiptSelectDto(Long receipt_id, String receipt_name, Double total, Boolean is_clear, List<User> users, Group group, User payer, Meeting meeting, List<Participant> participants) {
        this.receipt_id = receipt_id;
        this.receipt_name = receipt_name;
        this.total = total;
        this.is_clear = is_clear;
        this.payer = payer;
        this.users = users;
        this.group = group;
        this.meeting = meeting;
        this.participants = participants;
    }

    public Receipt toEntity() {
        return Receipt.builder()
                .receipt_id(receipt_id)
                .receipt_name(receipt_name)
                .total(total)
                .is_clear(is_clear)
                .build();
    }

    public void addParticipant(Participant participant) {
        if (participants == null)
            participants = new ArrayList<>();
        participants.add(participant);
    }
}
