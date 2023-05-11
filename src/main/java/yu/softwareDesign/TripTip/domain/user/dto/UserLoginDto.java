package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginDto {
    private String nickname;
    private String password;

    @Builder
    public UserLoginDto(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
