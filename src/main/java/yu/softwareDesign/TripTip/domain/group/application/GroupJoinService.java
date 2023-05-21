package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.member.dao.MemberRepo;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.user.domain.User;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupJoinService {
    private final GroupRepo groupRepo;
    private final MemberRepo memberRepo;

    /**
     * @param user
     * @param groupCode
     * @return Boolean
     */
    public Boolean joinGroup(User user, String groupCode) {
        if (groupRepo.existsByGroupCode(groupCode)) {
            Member member = new Member();
            member.setUser(user);
            member.setGroup(groupRepo.findByGroupCode(groupCode).get());
            memberRepo.save(member);
            return true;
        } else {
            return false;
        }
    }
}
