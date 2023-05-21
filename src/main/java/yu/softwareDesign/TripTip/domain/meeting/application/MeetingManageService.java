package yu.softwareDesign.TripTip.domain.meeting.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.meeting.dto.MeetingCreateDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeetingManageService {
    private final GroupRepo groupRepo;
    private final MeetingRepo meetingRepo;

    @Transactional
    public Meeting saveMeeting(MeetingCreateDto dto, Long group_id) {
        Meeting meeting = dto.toEntity();

        if (meeting.getMeeting_id() == null)
            meetingRepo.saveAndFlush(meeting);

        meeting.setGroup(groupRepo.findById(group_id).orElseGet(Group::new));
        return meetingRepo.save(meeting);
    }

    @Transactional
    public void deleteMeeting(Long meeting_id) {
        meetingRepo.deleteById(meeting_id);
    }
}
