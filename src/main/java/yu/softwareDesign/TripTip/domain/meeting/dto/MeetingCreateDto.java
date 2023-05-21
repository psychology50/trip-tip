package yu.softwareDesign.TripTip.domain.meeting.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@ToString(of = {"meeting_name", "is_clear"})
public class MeetingCreateDto {
    private Long meeting_id;
    private String meeting_name;
    private LocalDate meeting_day;
    private Boolean is_clear;

    @Builder
    public MeetingCreateDto(Long meeting_id, String meeting_name, LocalDate meeting_day, Boolean is_clear) {
        this.meeting_id = meeting_id;
        this.meeting_name = meeting_name;
        this.meeting_day = meeting_day;
        this.is_clear = Boolean.FALSE;
    }

    public Meeting toEntity() {
        return Meeting.builder()
                .meeting_id(meeting_id)
                .meeting_name(meeting_name)
                .meeting_day(meeting_day)
                .is_clear(Boolean.FALSE)
                .build();
    }
}
