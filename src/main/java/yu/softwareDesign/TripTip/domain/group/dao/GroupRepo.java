package yu.softwareDesign.TripTip.domain.group.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface GroupRepo extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM group g JOIN g.members m WHERE m.user = :user")
    List<Group> findGroupByUser(@Param("user") User user);

    @Query("SELECT g FROM group g JOIN g.members m WHERE m.user = :user ORDER BY g.created_date DESC")
    List<Group> findRecentGroupByUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT g FROM group g WHERE g.group_code = :group_code")
    Optional<Group> findByGroupCode(@Param("group_code") String group_code);

    @Query("SELECT case when count(g) > 0 then true else false end from group g where g.group_id = :group_id")
    Boolean existsByGroupId(Long group_id);

    @Query("SELECT case when count(g) > 0 then true else false end from group g where g.group_code = :group_code")
    Boolean existsByGroupCode(String group_code);

    @Query("SELECT SUM(r.total) FROM receipt r WHERE r.meeting.group.group_id = :group_id")
    Optional<Double> getTotalGroupCost(@Param("group_id") Long group_id);
}
