package yu.softwareDesign.TripTip.domain.group.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import yu.softwareDesign.TripTip.domain.group.domain.Group;

public interface GroupRepo extends JpaRepository<Group, Long> {
}
