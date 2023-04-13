package yu.softwareDesign.TripTip.domain.group.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import yu.softwareDesign.TripTip.domain.Member;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name="member")
@Table(name="MEMBER")
@NoArgsConstructor
@Component
public class Group {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GROUP_ID")
    private Long id;

    @Column(name="GROUP_NAME") @NonNull
    private String group_name;

    @Column(name="GROUP_CODE") @NonNull
    private String group_code;

    @Builder
    public Group(Long id, @NonNull String group_name) {
        this.id = id;
        this.group_name = group_name;
        this.group_code = UUID.randomUUID().toString();
    }

    @ManyToOne @JoinColumn(name="LEADER")
    private Subscriber subscriber;
    @OneToMany(mappedBy = "group")
    private List<Member> members = new ArrayList<>();

    @Override
    public String toString() {
        return this.group_name + ": " + this.group_code;
    }
}
