package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.Address;
import yu.softwareDesign.TripTip.domain.user.domain.Bank;
import yu.softwareDesign.TripTip.domain.user.domain.Phone;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.ArrayList;
import java.util.List;

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
    private List<Group> groupList = new ArrayList<>();

    @Builder
    public UserDto(String username, String nickname,
                   Address address, Bank bank, Phone phone) {
        this.username = username;
        this.nickname = nickname;
        this.local = phone.getLocal();
        this.local_prefix = phone.getLocal_prefix();
        this.local_suffix = phone.getLocal_suffix();
        this.bank_name = bank.getBank_name();
        this.bank_account = bank.getBank_account();
    }

    public void addGroup(Group group) { groupList.add(group); }
}
