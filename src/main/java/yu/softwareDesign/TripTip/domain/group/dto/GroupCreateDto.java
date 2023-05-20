package yu.softwareDesign.TripTip.domain.group.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"group_name"})
public class GroupCreateDto {
    private String group_name;
//    private String group_image;

    @Builder
    public GroupCreateDto(String group_name) {
        this.group_name = group_name;
    }

    public Group toEntity() {
        return Group.builder().group_name(group_name).build();
    }
}
