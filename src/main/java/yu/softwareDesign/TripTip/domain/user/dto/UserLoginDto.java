package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import yu.softwareDesign.TripTip.domain.user.domain.RoleType;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {
    private String nickname;
    private String password;

    @Builder
    public UserLoginDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;

    }
}
