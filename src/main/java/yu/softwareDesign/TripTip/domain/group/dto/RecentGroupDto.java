package yu.softwareDesign.TripTip.domain.group.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"group_name"})
public class RecentGroupDto {
    private String group_name;
    private String group_image;

    @Builder
    public RecentGroupDto(String group_name, String group_image) {
        this.group_name = group_name;
        this.group_image = group_image;
    }
}
