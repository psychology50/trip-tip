package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

import java.util.List;
import java.util.Optional;

/**
 * @sample [yu.softwareDesign.TripTip.domain.group.application.GroupSearchServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class GroupSearchService {
    private final GroupRepo groupRepo;

    public Optional<Group> findGroupById(Long id) { return groupRepo.findById(id); }

    public List<Group> findGroupAll() {return groupRepo.findAll();}

    public List<Group> findGroupByUser(CustomUser user) {return groupRepo.findGroupByUser(user);}

    public List<Group> findRecentGroupByUser(CustomUser user) {
        return groupRepo.findRecentGroupByUser(user, PageRequest.of(0, 3));
    }


    public Integer getTotalGroupCost(Long group_id) {
        return groupRepo.getTotalGroupCost(group_id).orElseGet(() -> 0.0).intValue();
    }
}
