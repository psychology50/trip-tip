package yu.softwareDesign.TripTip.domain.user.dto;

import lombok.Builder;
import yu.softwareDesign.TripTip.domain.user.domain.Address;
import yu.softwareDesign.TripTip.domain.user.domain.Bank;
import yu.softwareDesign.TripTip.domain.user.domain.Phone;
import yu.softwareDesign.TripTip.domain.user.domain.User;

public class UserRegisterDto {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String local;
    private String local_prefix;
    private String local_suffix;
    private String bank_name;
    private String bank_account;
    private String country;
    private String state;
    private String city;
    private String zipCode;

    public String getNickname() {return nickname;}

    @Builder
    public UserRegisterDto(Long id, String username,
                           String password, String nickname,
                           String local, String local_prefix,
                           String local_suffix, String bank_name,
                           String bank_account, String country,
                           String state, String city, String zipCode) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.local = local;
        this.local_prefix = local_prefix;
        this.local_suffix = local_suffix;
        this.bank_name = bank_name;
        this.bank_account = bank_account;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
    }

    public User toEntity() {
        return User.builder()
                .subscriber_id(id)
                .username(username)
                .password(password)
                .nickname(nickname)
                .phone(Phone.builder()
                        .local(local)
                        .local_prefix(local_prefix)
                        .local_suffix(local_suffix)
                        .build())
                .bank(Bank.builder()
                        .bank_name(bank_name)
                        .bank_account(bank_account)
                        .build())
                .address(Address.builder()
                        .country(country)
                        .state(state)
                        .city(city)
                        .zipCode(zipCode)
                        .build())
                .build();
    }

    @Override public String toString() {
        return "이름 : " + this.username + ", 닉네임 : " + this.nickname;
    }
}
