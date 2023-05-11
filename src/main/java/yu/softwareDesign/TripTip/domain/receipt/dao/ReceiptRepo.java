package yu.softwareDesign.TripTip.domain.receipt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;

public interface ReceiptRepo extends JpaRepository<Receipt, Long> {
}
