package yu.softwareDesign.TripTip.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name="subscriber")
@Table(name="SUBSCRIBER")
@NoArgsConstructor
public class Subscriber {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SUBSCRIBER_ID")
    private Long id;

    @Column(name = "username") @NonNull
    private String username;
    @Column(name = "password") @NonNull
    private String password;
    @Column(name = "nickname") @NonNull
    private String nickname;
    @Column(name = "phone") @NonNull
    private String phone;
    @Column(name = "bank") @NonNull
    private String bank;
    @Column(name = "bank_account") @NonNull
    private String bank_account;

    // profile_img
//    private String origin_file_name;
//    private String stored_file_path;
//    private Long file_size;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Builder
    public Subscriber(Long id, String username, String password, String nickname,
                      String phone, String bank, String bank_account,
                      RoleType role)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.bank = bank;
        this.bank_account = bank_account;
        this.role = role;
    }

    @OneToMany(mappedBy = "subscriber")
    private List<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "subscriber")
    private List<Group> is_leader = new ArrayList<>();
    @OneToMany(mappedBy = "subscriber")
    private List<Participant> participants = new ArrayList<>();

    @Override
    public String toString() {
        return "username: " + this.username + ", nickname: " + this.nickname;
    }
}
