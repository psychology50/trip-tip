package yu.softwareDesign.TripTip.domain.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class MeetingDetailDto {
    private Group group;

    private Long leader_id;
    private String leader_username;

    private Long meeting_id;
    private LocalDate meeting_day;

    private List<Receipt> receipts;
    private Integer total;


    @Builder
    public MeetingDetailDto(Group group, Long leader_id, String leader_username, Long meeting_id, LocalDate meeting_day, List<Receipt> receipts, Integer total) {
        this.group = group;
        this.leader_id = leader_id;
        this.leader_username = leader_username;
        this.meeting_id = meeting_id;
        this.meeting_day = meeting_day;
        this.receipts = receipts;
        this.total = total;
    }
}
