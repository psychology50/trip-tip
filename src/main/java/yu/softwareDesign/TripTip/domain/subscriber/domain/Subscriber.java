package yu.softwareDesign.TripTip.domain.subscriber.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.model.BaseDateEntity;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;

import java.util.ArrayList;
import java.util.List;

@Entity(name="subscriber")
@Table(name="SUBSCRIBER")
@NoArgsConstructor
@EqualsAndHashCode(of = {"subscriber_id"})
@ToString(of = {"username", "nickname"})
public class Subscriber extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="SUBSCRIBER_ID")
    private Long subscriber_id;

    @Column(name = "username") @NonNull
    private String username;
    @Column(name = "password") @NonNull
    private String password;
    @Column(name = "nickname") @NonNull
    private String nickname;

    @Embedded private Phone phone;
    @Embedded private Address address;
    @Embedded private Bank bank;

    // profile_img
//    private String origin_file_name;
//    private String stored_file_path;
//    private Long file_size;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Builder
    public Subscriber(Long subscriber_id, String username, String password, String nickname,
                      Phone phone, Bank bank, Address address, RoleType role)
    {
        this.subscriber_id = subscriber_id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.bank = bank;
        this.address = address;
        this.role = role;
    }

    @OneToMany(mappedBy = "subscriber")
    private List<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "leader")
    private List<Group> is_leader = new ArrayList<>();
    @OneToMany(mappedBy = "subscriber")
    private List<Participant> participants = new ArrayList<>();
}
