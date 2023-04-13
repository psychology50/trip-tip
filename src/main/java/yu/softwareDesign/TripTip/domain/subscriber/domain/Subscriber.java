package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.Member;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

import java.util.ArrayList;
import java.util.List;

@Entity(name="subscriber")
@Table(name="SUBSCRIBER")
@NoArgsConstructor
@ToString(of = {"username", "nickname"})
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
    @Column(name = "bank") @NonNull
    private String bank;
    @Column(name = "bank_account") @NonNull
    private String bank_account;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="local", column = @Column(name = "local_num", nullable = false))        ,
            @AttributeOverride(name="prefix", column = @Column(name = "local_prefix_num", nullable = false)),
            @AttributeOverride(name="suffix", column = @Column(name = "local_suffix_num", nullable = false))
    })
    private Phone phone;

    // profile_img
//    private String origin_file_name;
//    private String stored_file_path;
//    private Long file_size;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Builder
    public Subscriber(Long id, String username, String password, String nickname,
                      Phone phone, String bank, String bank_account,
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
}
