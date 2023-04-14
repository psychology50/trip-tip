package yu.softwareDesign.TripTip.domain.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

@Entity(name="member")
@Table(name="MEMBER")
@NoArgsConstructor
@EqualsAndHashCode(of = {"member_id"})
@ToString(of = {"subscriber", "group"})
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long member_id;

    @Builder
    public Member(Long id) {
        this.member_id = member_id;
    }

    @ManyToOne @JoinColumn(name="SUBSCRIBER_ID")
    private Subscriber subscriber;

    @ManyToOne @JoinColumn(name="GROUP_ID")
    private Group group;
}
