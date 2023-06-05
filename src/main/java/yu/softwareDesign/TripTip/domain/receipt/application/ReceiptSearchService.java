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

    public List<Receipt> findReceiptAll() {
        return receiptRepo.findAll();
    }
}
