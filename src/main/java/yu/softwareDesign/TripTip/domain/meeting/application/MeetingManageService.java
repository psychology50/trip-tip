package yu.softwareDesign.TripTip.domain.meeting.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MeetingManageService {
    private final GroupRepo groupRepo;
    private final MeetingRepo meetingRepo;

    @Transactional
    public Meeting save(Long group_id) {
        Meeting meeting = Meeting.builder()
                .meeting_name("모임")
                .meeting_day(LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .build();
        meeting.setGroup(groupRepo.findById(group_id).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹이 존재하지 않습니다.")
        ));
        return meetingRepo.save(meeting);
    }

    @Transactional
    public void deleteMeeting(User user, Long meeting_id) {
        Meeting meeting = meetingRepo.findById(meeting_id).orElseThrow(() ->
                new IllegalArgumentException("해당 모임이 존재하지 않습니다."));
        if (!meeting.getGroup().getLeader().getUser_id().equals(user.getUser_id())) {
            throw new IllegalArgumentException("모임을 삭제할 권한이 없습니다.");
        }
        meetingRepo.delete(meeting);
    }
}
