package yu.softwareDesign.TripTip.domain.group.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;

import java.util.List;

@Getter
@NoArgsConstructor
public class GroupDetailDto {
    private Long group_id;
    private String group_name;
    private String group_code;
    private String group_img;
    private String leader;
    private List<Meeting> meetings;

    @Builder
    public GroupDetailDto(Long group_id, String group_name, String group_code, String group_img, String leader, List<Meeting> meetings) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_code = group_code;
        this.group_img = group_img;
        this.leader = leader;
        this.meetings = meetings;
    }

    public Group toEntity() {
        return Group.builder()
                .group_id(group_id)
                .group_name(group_name)
                .group_code(group_code)
                .build();
    }
}
