package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * @sample [yu.softwareDesign.TripTip.domain.group.application.GroupSearchServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
public class GroupSearchService {
    private final GroupRepo groupRepo;

    public Optional<Group> findGroupById(Long id) { return groupRepo.findById(id); }

    public List<Group> findGroupAll() {return groupRepo.findAll();}

    public List<Group> findGroupByUser(User user) {return groupRepo.findGroupByUser(user);}

    public List<Group> findRecentGroupByUser(User user) {
        return groupRepo.findRecentGroupByUser(user, PageRequest.of(0, 3));
    }
}
