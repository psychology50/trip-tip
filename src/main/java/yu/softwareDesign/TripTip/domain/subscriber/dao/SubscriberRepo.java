package yu.softwareDesign.TripTip.domain.subscriber.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

import java.util.Optional;

public interface SubscriberRepo extends JpaRepository<Subscriber, Long> {
    @Query("select s from subscriber s where s.nickname = :nickname")
    public Optional<Subscriber> findByNickname(@Param("nickname") String nickname);
}
