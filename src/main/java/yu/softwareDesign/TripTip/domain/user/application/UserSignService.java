package yu.softwareDesign.TripTip.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;
import yu.softwareDesign.TripTip.domain.user.dto.UserRegisterDto;

/**
 * @sample [yu.softwareDesign.TripTip.domain.user.application.UserSignServiceTest]
 */
@Service
@Transactional
@RequiredArgsConstructor
public class UserSignService {
    private final UserRepo userRepo;

    public User register(UserRegisterDto form) {
        if (userRepo.existsByNickname(form.getNickname()))
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");

        return userRepo.save(form.toEntity());
    }
}
