package yu.softwareDesign.TripTip.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<CustomUser, Long> {
    @Query("select u from custom_user u where u.nickname = :nickname")
    Optional<CustomUser> findByNickname(@Param("nickname") String nickname);

    @Query("SELECT u FROM custom_user u INNER JOIN u.members m WHERE m.group.group_id = :group_id")
    List<CustomUser> findByGroupId(@Param("group_id") Long group_id);

    @Query("SELECT u FROM custom_user u INNER JOIN u.participants p WHERE p.receipt.receipt_id = :receipt_id")
    List<CustomUser> findByReceiptId(Long receipt_id);

    @Query("select case when count(u) > 0 then true else false end from custom_user u where u.nickname = :nickname")
    Boolean existsByNickname(String nickname);
}
