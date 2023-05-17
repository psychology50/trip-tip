package yu.softwareDesign.TripTip.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from user u where u.nickname = :nickname")
    Optional<User> findByNickname(@Param("nickname") String nickname);

    @Query("select case when count(u) > 0 then true else false end from user u where u.nickname = :nickname")
    Boolean existsByNickname(String nickname);
}
