package yu.softwareDesign.TripTip.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSearchService {
    private final UserRepo userRepo;

    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findUserByNickname(String nickname) {
        return userRepo.findByNickname(nickname);
    }

    public List<User> findUserAll() {
        return userRepo.findAll();
    }
}
