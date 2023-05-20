package yu.softwareDesign.TripTip.domain.receipt.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.receipt.dao.ReceiptRepo;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;

import java.util.List;
import java.util.Optional;

/**
 * @sample [yu.softwareDesign.TripTip.domain.receipt.application.ReceiptSearchServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ReceiptSearchService {
    private final ReceiptRepo receiptRepo;

    public Optional<Receipt> findReceiptById(Long id) {
        return receiptRepo.findById(id);
    }

    public List<Receipt> findReceiptByUserId(Long user_id) {
        return receiptRepo.findReceiptByUserId(user_id);
    }

    public List<Receipt> findReceiptByGroupId(Long group_id) {
        return receiptRepo.findReceiptByGroupId(group_id);
    }

    public List<Receipt> findReceiptByMeetingId(Long meeting_id) {
        return receiptRepo.findReceiptByMeetingId(meeting_id);
    }

    public List<Receipt> findReceiptAll() {
        return receiptRepo.findAll();
    }
}
