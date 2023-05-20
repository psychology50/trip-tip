package yu.softwareDesign.TripTip.domain.group.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.baseModel.BaseDateEntity;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name="group")
@Table(name="GROUP_TBL")
@NoArgsConstructor
@EqualsAndHashCode(of = {"group_id"}, callSuper=false)
@ToString(of = {"group_name", "group_code", "leader"})
public class Group extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GROUP_ID")
    private Long group_id;

    @Column(name="GROUP_NAME") @NonNull
    private String group_name;

    @Column(name="GROUP_CODE") @NonNull
    private String group_code;

    @Builder
    public Group(Long group_id, @NonNull String group_name) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_code = UUID.randomUUID().toString();
    }

    @ManyToOne @JoinColumn(name="LEADER")
    private User leader;
    @OneToMany(mappedBy = "group")
    private List<Member> members = new ArrayList<>();

    public String getGroup_name() {
        return group_name;
    }
}
