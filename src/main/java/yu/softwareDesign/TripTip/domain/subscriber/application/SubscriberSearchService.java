package yu.softwareDesign.TripTip.domain.subscriber.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.subscriber.dao.SubscriberRepo;
import yu.softwareDesign.TripTip.domain.subscriber.domain.Subscriber;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriberSearchService {
    private final SubscriberRepo subscriberRepo;

    public Optional<Subscriber> findUserById(Long id) {
        return subscriberRepo.findById(id);
    }

    public Optional<Subscriber> findUserByNickname(String nickname) {
        return subscriberRepo.findByNickname(nickname);
    }

    public List<Subscriber> findUserAll() {
        return subscriberRepo.findAll();
    }
}
