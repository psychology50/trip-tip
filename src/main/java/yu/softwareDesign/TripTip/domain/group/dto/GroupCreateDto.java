package yu.softwareDesign.TripTip.domain.group.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"group_name"})
public class GroupCreateDto {
    private Long group_id;
    private String group_name;
    private String group_code;
    private List<CustomUser> members;

    @Builder
    public GroupCreateDto(Long group_id, String group_name, String group_code, List<CustomUser> members) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_code = group_code;
        this.members = members;
    }

    public Group toEntity(String code) {
        return Group.builder()
                .group_id(group_id)
                .group_name(group_name)
                .group_code(code)
                .build();
    }
}
