package yu.softwareDesign.TripTip.domain.member.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.User;

@Entity(name="member")
@Table(name="MEMBER")
@NoArgsConstructor
@ToString(of = {"user", "group"})
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long member_id;

    @Builder
    public Member(Long id) {
        this.member_id = member_id;
    }

    @ManyToOne @JoinColumn(name="USER_ID")
    private User user;

    @ManyToOne @JoinColumn(name="GROUP_ID")
    private Group group;

    public void setUser(User user) {
        if (this.user != null) {
            this.user.getMembers().remove(this);
        }
        this.user = user;
        if (user != null) {
            user.getMembers().add(this);
        }
    }

    public void setGroup(Group group) {
        if (this.group != null) {
            this.group.getMembers().remove(this);
        }
        this.group = group;
        if (group != null) {
            group.getMembers().add(this);
        }
    }
}
