package yu.softwareDesign.TripTip.domain.group.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM group g JOIN g.members m WHERE m.user = :user")
    List<Group> findGroupByUser(@Param("user") User user);

    @Query("SELECT g FROM group g JOIN g.members m WHERE m.user = :user ORDER BY g.created_date DESC")
    List<Group> findRecentGroupByUser(@Param("user") User user, Pageable pageable);
}
