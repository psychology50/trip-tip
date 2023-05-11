package yu.softwareDesign.TripTip.domain.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select s from subscriber s where s.nickname = :nickname")
    public Optional<User> findByNickname(@Param("nickname") String nickname);

    @Query("select case when count(s) > 0 then true else false end from subscriber s where s.nickname = :nickname")
    public Boolean existsByNickname(String nickname);
}
