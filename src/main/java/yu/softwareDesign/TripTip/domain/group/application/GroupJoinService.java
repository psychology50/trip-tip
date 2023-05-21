package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.dto.GroupJoinDto;
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
     * @param dto
     * @return Boolean
     */
    public Boolean joinGroup(User user, GroupJoinDto dto) {
        if (!groupRepo.existsByGroupCode(dto.getGroup_code())) return false;

        Member member = new Member();
        member.setUser(user);
        member.setGroup(groupRepo.findByGroupCode(dto.getGroup_code()).get());
        memberRepo.save(member);
        return true;
    }
}
