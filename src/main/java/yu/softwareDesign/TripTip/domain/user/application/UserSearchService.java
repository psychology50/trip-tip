package yu.softwareDesign.TripTip.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

import java.util.List;
import java.util.Optional;

/**
 * @sample [yu.softwareDesign.TripTip.domain.user.application.UserSearchServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserSearchService {
    private final UserRepo userRepo;

    public Optional<CustomUser> findUserById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<CustomUser> findUserByNickname(String nickname) {
        return userRepo.findByNickname(nickname);
    }

    public List<CustomUser> findUserByGroupId(Long group_id) {return userRepo.findByGroupId(group_id);}
    public List<CustomUser> findUserAll() {
        return userRepo.findAll();
    }
}
