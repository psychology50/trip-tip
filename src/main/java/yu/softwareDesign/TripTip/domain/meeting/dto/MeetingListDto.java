package yu.softwareDesign.TripTip.domain.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MeetingListDto {
    private Long meeting_id;
    private LocalDate meeting_day;

    @Builder
    public MeetingListDto(Long meeting_id, LocalDate meeting_day) {
        this.meeting_id = meeting_id;
        this.meeting_day = meeting_day;
    }
}
