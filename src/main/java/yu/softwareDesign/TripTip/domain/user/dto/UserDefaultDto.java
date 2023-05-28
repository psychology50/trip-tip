package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDefaultDto {
    private String username;
    private String nickname;

    @Builder
    public UserDefaultDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}


