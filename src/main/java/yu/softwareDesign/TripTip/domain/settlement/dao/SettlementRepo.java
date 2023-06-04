package yu.softwareDesign.TripTip.domain.settlement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yu.softwareDesign.TripTip.domain.settlement.domain.Settlement;

public interface SettlementRepo extends JpaRepository<Settlement, Long> {
}
