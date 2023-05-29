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
    private Long leader_id;
    private String leader_username;
    private List<Meeting> meetings;

    @Builder
    public GroupDetailDto(Long group_id, String group_name, String group_code, String group_img, Long leader_id, String leader_username, List<Meeting> meetings) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_code = group_code;
        this.group_img = group_img;
        this.leader_id = leader_id;
        this.leader_username = leader_username;
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
