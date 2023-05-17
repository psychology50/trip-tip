package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.user.domain.User;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"username", "nickname", "local", "local_prefix", "local_suffix", "bank_name", "bank_account"})
public class UserDto {
    private String username;
    private String nickname;
    private String local;
    private String local_prefix;
    private String local_suffix;
    private String bank_name;
    private String bank_account;

    @Builder
    public UserDto(String username, String nickname,
                   String local, String local_prefix, String local_suffix,
                   String bank_name, String bank_account) {
        this.username = username;
        this.nickname = nickname;
        this.local = local;
        this.local_prefix = local_prefix;
        this.local_suffix = local_suffix;
        this.bank_name = bank_name;
        this.bank_account = bank_account;
    }
}
