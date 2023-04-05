package yu.softwareDesign.TripTip.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name="member")
@Table(name="MEMBER")
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MEMBER_ID")
    private Long id;

    @Builder
    public Member(Long id) {
        this.id = id;
    }

    @ManyToOne @JoinColumn(name="SUBSCRIBER_ID")
    private Subscriber subscriber;

    @ManyToOne @JoinColumn(name="GROUP_ID")
    private Group group;

    @Override
    public String toString() {
        return id.toString();
    }


}
