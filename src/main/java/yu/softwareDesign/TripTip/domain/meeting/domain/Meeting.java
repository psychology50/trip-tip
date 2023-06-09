package yu.softwareDesign.TripTip.domain.meeting.domain;

import jakarta.persistence.*;
import lombok.*;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.baseModel.BaseDateEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="meeting")
@Table(name="MEETING")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"meeting_id"}, callSuper=false)
@ToString(of = {"meeting_name", "is_clear"})
public class Meeting extends BaseDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long meeting_id;
    @Column(name="MEETING_NAME")
    private String meeting_name;
    @Column(name="MEETING_DAY")
    private LocalDate meeting_day;
    @Column(name="IS_CLEAR")
    private Boolean is_clear;
    // 이미지 필드 추가

    @Builder
    public Meeting(Long meeting_id, String meeting_name, LocalDate meeting_day, Boolean is_clear) {
        this.meeting_id = meeting_id;
        this.meeting_name = meeting_name;
        this.meeting_day = meeting_day;
        this.is_clear = Boolean.FALSE;
    }

    @ManyToOne @JoinColumn(name="GROUP_ID")
    private Group group;
    @OneToMany(mappedBy = "meeting")
    private List<Receipt> receipts = new ArrayList<>();

    public void setGroup(Group group) {
        if (this.group != null) {
            this.group.getMeetings().remove(this);
        }
        this.group = group;
        if (group != null) {
            group.getMeetings().add(this);
        }
    }
}
