package yu.softwareDesign.TripTip.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.baseModel.BaseDateEntity;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.settlement.domain.Settlement;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"members", "is_leader", "participants", "pay_receipts", "sentSettlements", "receivedSettlements"})
@Entity(name="custom_user")
@Table(name="CUSTOM_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
//@EqualsAndHashCode(of = {"user_id"}, callSuper=false)
@ToString(of = {"username", "nickname"})
public class CustomUser extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="USER_ID", updatable = false)
    private Long user_id;

    @Column(name = "username") @NonNull
    private String username;
    @Column(name = "password") @NonNull
    private String password;
    @Column(name = "nickname", unique = true) @NonNull
    private String nickname;

    @Embedded private Phone phone;
    @Embedded private Address address;
    @Embedded private Bank bank;

    // profile_img
//    private String origin_file_name;
//    private String stored_file_path;
//    private Long file_size;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @Builder
    public CustomUser(Long user_id, String username, String password, String nickname,
                      Phone phone, Bank bank, Address address, RoleType role)
    {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.bank = bank;
        this.address = address;
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    private List<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "leader")
    private List<Group> is_leader = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    private List<Participant> participants = new ArrayList<>();
    @OneToMany(mappedBy = "payer")
    private List<Receipt> pay_receipts = new ArrayList<>();
    @OneToMany(mappedBy = "sender")
    private List<Settlement> sentSettlements = new ArrayList<>();
    @OneToMany(mappedBy = "receiver")
    private List<Settlement> receivedSettlements = new ArrayList<>();
}
