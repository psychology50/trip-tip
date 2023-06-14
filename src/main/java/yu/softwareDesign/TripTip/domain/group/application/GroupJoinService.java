package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.dto.GroupJoinDto;
import yu.softwareDesign.TripTip.domain.member.dao.MemberRepo;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

@Service
@RequiredArgsConstructor
public class GroupJoinService {
    private final GroupRepo groupRepo;
    private final MemberRepo memberRepo;
    private final UserRepo userRepo;

    /**
     * @param user
     * @param dto
     * @return Boolean
     */
    @Transactional
    public boolean joinGroup(CustomUser user, GroupJoinDto dto) {
        if (!groupRepo.existsByGroupCode(dto.getGroup_code())) return false;

        Member member = new Member();
        member.setUser(userRepo.findById(user.getUser_id()).get());
        member.setGroup(groupRepo.findByGroupCode(dto.getGroup_code()).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹을 찾을 수 없습니다.")));
        memberRepo.save(member);
        return true;
    }
}
