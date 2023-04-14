package yu.softwareDesign.TripTip.domain.group.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.model.BaseDateEntity;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name="member")
@Table(name="MEMBER")
@NoArgsConstructor
@EqualsAndHashCode(of = {"group_id"})
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
    private Subscriber leader;
    @OneToMany(mappedBy = "group")
    private List<Member> members = new ArrayList<>();
}
