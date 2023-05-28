package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDefaultDto {
    private Long user_id;
    private String username;
    private String nickname;

    @Builder
    public UserDefaultDto(Long user_id, String username, String nickname) {
        this.user_id = user_id;
        this.username = username;
        this.nickname = nickname;
    }
}


