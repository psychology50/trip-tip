package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;

/**
 * @sample [yu.softwareDesign.TripTip.domain.group.application.GroupCreateServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GroupCreateService {
    private final GroupRepo groupRepo;


}
