package yu.softwareDesign.TripTip.domain.receipt.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.participant.dao.ParticipantRepo;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.dao.ReceiptRepo;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptSelectDto;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;

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

//    @Transactional
//    public Receipt DutchPay(ReceiptDutchDto dto, User user, Long meeting_id) {
//        Receipt receipt = dto.toEntity();
//
//        if (dto.getReceipt_id() == null) {
//            receipt.setMeeting(meetingRepo.findById(meeting_id).get());
//            receiptRepo.saveAndFlush(receipt);
//        }
//
//
//    }
//
//    @Transactional
//    public Receipt SelectPay(ReceiptSelectDto dto, User user, Long meeting_id) {
//        Receipt receipt = dto.toEntity();
//
//        if (dto.getReceipt_id() == null) {
//            receipt.setMeeting(meetingRepo.findById(meeting_id).get());
//            receiptRepo.saveAndFlush(receipt);
//        }
//
//        List<Participant> participants = new ArrayList<>();
//        dto.getUsers().forEach(u -> {
//            Participant participant = Participant.builder().build();
//            participantRepo.saveAndFlush(participant);
//        });
//
//        receiptRepo.save(receipt);
//    }

    @Transactional
    public Receipt selectPay(ReceiptSelectDto dto, User user, Long meeting_id) {
        Receipt receipt = dto.toEntity();

        if (receipt.getReceipt_id() == null) {
            receiptRepo.saveAndFlush(receipt);
            receipt.setMeeting(meetingRepo.findById(meeting_id).get());
        }

        // 기존의 참가자 정보 조회
        List<Participant> participants = participantRepo.findByReceiptId(receipt.getReceipt_id());
        List<User> old_users = userRepo.findByReceiptId(receipt.getReceipt_id());

        // 새로운 참가자 정보 비교
        List<User> users = dto.getUsers();



        return new Receipt();
    }
}
