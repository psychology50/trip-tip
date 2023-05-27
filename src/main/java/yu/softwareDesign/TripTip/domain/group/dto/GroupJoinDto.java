package yu.softwareDesign.TripTip.domain.group.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter @Setter
@NoArgsConstructor
public class GroupJoinDto {
    private String group_code;

    public GroupJoinDto(String group_code) {
        this.group_code = group_code;
    }
}
