package yu.softwareDesign.TripTip.domain.receipt.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.meeting.dao.MeetingRepo;
import yu.softwareDesign.TripTip.domain.participant.dao.ParticipantRepo;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.dao.ReceiptRepo;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.receipt.dto.ReceiptCreateDto;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;

import java.util.ArrayList;
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

    @Transactional
    public Receipt save(ReceiptCreateDto receiptCreateDto) {
        Receipt receipt = receiptCreateDto.toEntity();
        receipt.setPayer(receiptCreateDto.getPayer());
        receipt.setMeeting(receiptCreateDto.getMeeting());

        if (receipt.getReceipt_id() == null) receiptRepo.save(receipt);

        List<Participant> participants = new ArrayList<>();
        receiptCreateDto.getParticipationUsers().forEach(user -> {
            if (user.getSelected()) { // 영수증을 등록해야하는 경우
                Participant participant = Participant.builder().cost(user.getCost()).is_clear(Boolean.FALSE).build();

                participant.setReceipt(receipt);
                participant.setUser(userRepo.findById(user.getUser_id()).orElseThrow());
                participants.add(participantRepo.save(participant));
            } else if (participantRepo.existsByReceiptIdAndUserId(receipt.getReceipt_id(), user.getUser_id())) {
                participantRepo.deleteByReceiptIdAndUserId(receipt.getReceipt_id(), user.getUser_id());
            }
        });
        log.info("Participant save clear = {}", participants);

        Receipt updatedReceipt = Receipt.builder()
                .receipt_id(receipt.getReceipt_id())
                .receipt_name(receipt.getReceipt_name())
                .total(participants.stream().mapToDouble(Participant::getCost).sum())
                .is_clear(receipt.getIs_clear())
                .build();
        updatedReceipt.setPayer(receiptCreateDto.getPayer());
        updatedReceipt.setMeeting(receiptCreateDto.getMeeting());
        return receiptRepo.save(updatedReceipt);
    }

    @Transactional
    public void delete(Long receipt_id) {
        receiptRepo.deleteById(receipt_id);
    }
}
