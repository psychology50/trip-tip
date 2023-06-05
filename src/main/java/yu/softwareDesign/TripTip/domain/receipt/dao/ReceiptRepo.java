package yu.softwareDesign.TripTip.domain.receipt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;

import java.util.List;

public interface ReceiptRepo extends JpaRepository<Receipt, Long> { }
