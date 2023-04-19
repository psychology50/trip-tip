package yu.softwareDesign.TripTip.domain.subscriber.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.subscriber.dao.SubscriberRepo;

@Service
@Transactional
public class SubscriberSearchService {
    private final SubscriberRepo subscriberRepo;

    @Autowired
    public SubscriberSearchService(SubscriberRepo subscriberRepo) {
        this.subscriberRepo = subscriberRepo;
    }


}
