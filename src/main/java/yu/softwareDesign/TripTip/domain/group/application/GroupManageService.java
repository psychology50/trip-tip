package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.group.dto.GroupCreateDto;
import yu.softwareDesign.TripTip.domain.member.dao.MemberRepo;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @sample [yu.softwareDesign.TripTip.domain.group.application.GroupCreateServiceTest]
 */
@Service
@RequiredArgsConstructor
public class GroupManageService {
    private final GroupRepo groupRepo;
    private final UserRepo userRepo;
    private final MemberRepo memberRepo;

    private String generateGroupCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 10);
        } while (groupRepo.existsByGroupCode(code));

        return code;
    }

    /**
     * @param user
     * @param group_form
     * @return Group
     */
    @Transactional
    public Optional<Group> save(User user, GroupCreateDto group_form) {
        Group group = (group_form.getGroup_id() != null)
                ? group_form.toEntity(group_form.getGroup_code())
                : group_form.toEntity(generateGroupCode());
        User leader = userRepo.findById(user.getUser_id()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        group.setLeader(leader);

        if (!groupRepo.existsByGroupId(group.getGroup_id())) { // create
            groupRepo.save(group);
        }

        List<User> members = group_form.getMembers();
        List<Member> memberList = new ArrayList<>();

        members.add(leader);
        members.forEach(member_user -> {
            Member member = new Member();
            member.setUser(member_user);
            member.setGroup(group);
            memberList.add(member);
        });
        memberRepo.saveAll(memberList);

        return Optional.of(group); // update
    }

    // TODO: group의 모든 정보를 초기화해야 함 (member 관계 정보 유지)
    @Transactional
    public void settleGroup(Long user_id, Long group_id) {


//        groupRepo.save(group);
    }

    // TODO: group과 관련된 모든 정보를 삭제해야 함
    @Transactional
    public void deleteGroup(Long group_id) {
        Group group = groupRepo.findById(group_id).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹이 존재하지 않습니다.")
        );
        group.getMembers().forEach(member -> {
            member.setUser(null);
            member.setGroup(null);
        });
        group.setLeader(null);
        groupRepo.delete(group);
    }


}
