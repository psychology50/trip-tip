package yu.softwareDesign.TripTip.domain.receipt.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.participant.dao.ParticipantRepo;
import yu.softwareDesign.TripTip.domain.receipt.dao.ReceiptRepo;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptCreateDto;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;

/**
 *
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class ReceiptManageService {
    private final UserRepo userRepo;
    private final MeetingRepo meetingRepo;
    private final ReceiptRepo receiptRepo;
    private final ParticipantRepo participantRepo;

    // TODO: Update 로직 따로 구현
    @Transactional
    public Receipt selectPay(ReceiptCreateDto dto, User user, Long group_id, Long meeting_id) {
        Receipt receipt = dto.toEntity();

        // receipt pk값 얻고 receipt-meeting 연관관계 매핑
        receiptRepo.saveAndFlush(receipt);
        receipt.setMeeting(meetingRepo.findById(meeting_id).get());

//        List<User> users = dto.getUsers();
//        List<Participant> participants = dto.getParticipants();

        return new Receipt();
    }
}
