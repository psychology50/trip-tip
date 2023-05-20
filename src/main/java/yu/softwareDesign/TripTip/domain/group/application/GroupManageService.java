package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.group.dto.GroupCreateDto;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.user.domain.User;

/**
 * @sample [yu.softwareDesign.TripTip.domain.group.application.GroupCreateServiceTest]
 */
@Service
@RequiredArgsConstructor
public class GroupManageService {
    private final GroupRepo groupRepo;

    @Transactional
    public Group saveGroup(User user, GroupCreateDto group_form) {
        Group group = group_form.toEntity();
        group.setLeader(user);

        Member member = Member.builder().build();
        member.setUser(user); member.setGroup(group);

        if (group.getGroup_id() != null) { // update
            return groupRepo.save(group);
        } else { // create
            return groupRepo.saveAndFlush(group);
        }
    }

    // TODO: group의 모든 정보를 초기화해야 함 (member 관계 정보 유지)
    @Transactional
    public void resetGroup(Group group) {

        groupRepo.save(group);
    }

    // TODO: group과 관련된 모든 정보를 삭제해야 함
    @Transactional
    public void deleteGroup(Group group) {
        group.getMembers().forEach(member -> {
            member.setUser(null);
            member.setGroup(null);
        });
        groupRepo.delete(group);
    }
}
