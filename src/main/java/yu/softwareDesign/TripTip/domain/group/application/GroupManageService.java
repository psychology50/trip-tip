package yu.softwareDesign.TripTip.domain.group.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yu.softwareDesign.TripTip.domain.group.dao.GroupRepo;
import yu.softwareDesign.TripTip.domain.group.domain.Group;
import yu.softwareDesign.TripTip.domain.group.dto.GroupCreateDto;
import yu.softwareDesign.TripTip.domain.meeting.domain.Meeting;
import yu.softwareDesign.TripTip.domain.member.dao.MemberRepo;
import yu.softwareDesign.TripTip.domain.member.domain.Member;
import yu.softwareDesign.TripTip.domain.participant.domain.Participant;
import yu.softwareDesign.TripTip.domain.receipt.domain.Receipt;
import yu.softwareDesign.TripTip.domain.settlement.dao.SettlementRepo;
import yu.softwareDesign.TripTip.domain.settlement.domain.Settlement;
import yu.softwareDesign.TripTip.domain.user.dao.UserRepo;
import yu.softwareDesign.TripTip.domain.user.domain.CustomUser;

import java.util.*;

/**
 * @sample [yu.softwareDesign.TripTip.domain.group.application.GroupCreateServiceTest]
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class GroupManageService {
    private final GroupRepo groupRepo;
    private final UserRepo userRepo;
    private final MemberRepo memberRepo;
    private final SettlementRepo settlementRepo;

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
    public Optional<Group> save(CustomUser user, GroupCreateDto group_form) {
        Group group = (group_form.getGroup_id() != null)
                ? group_form.toEntity(group_form.getGroup_code())
                : group_form.toEntity(generateGroupCode());
        CustomUser leader = userRepo.findById(user.getUser_id()).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        group.setLeader(leader);

        if (!groupRepo.existsByGroupId(group.getGroup_id())) { // create
            groupRepo.save(group);
        }

        List<CustomUser> members = group_form.getMembers();
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
    public void settleGroup(Long group_id) {
        Map<CustomUser, Map<CustomUser, Double>> settlementMap = new HashMap<>();
        Group group = groupRepo.findById(group_id).orElseThrow(() ->
                new IllegalArgumentException("해당 그룹이 존재하지 않습니다.")
        );

        for (Meeting m : group.getMeetings()) {
            List<Receipt> receipts = m.getReceipts();

            for (Receipt r :receipts) {
                CustomUser payer = r.getPayer();
                List<Participant> participants = r.getParticipants();

                for (Participant p : participants) {
                    CustomUser debtor = p.getUser();
                    Double cost = p.getCost();

                    if (payer.getUser_id().equals(debtor.getUser_id())) {
                        continue;
                    }

                    if (settlementMap.containsKey(payer)) {
                        Map<CustomUser, Double> debtorMap = settlementMap.get(payer);
                        if (debtorMap.containsKey(debtor)) {
                            debtorMap.put(debtor, debtorMap.get(debtor) + cost);
                        } else {
                            debtorMap.put(debtor, cost);
                        }
                    } else {
                        Map<CustomUser, Double> debtorMap = new HashMap<>();
                        debtorMap.put(debtor, cost);
                        settlementMap.put(payer, debtorMap);
                    }

                    if (settlementMap.containsKey(debtor)) {
                        Map<CustomUser, Double> debtorMap = settlementMap.get(debtor);
                        if (debtorMap.containsKey(payer)) {
                            debtorMap.put(payer, debtorMap.get(payer) - cost);
                        } else {
                            debtorMap.put(payer, -cost);
                        }
                    } else {
                        Map<CustomUser, Double> debtorMap = new HashMap<>();
                        debtorMap.put(payer, -cost);
                        settlementMap.put(debtor, debtorMap);
                    }

                }

            }
        }

        for (CustomUser payer : settlementMap.keySet()) {
            Map<CustomUser, Double> debtorMap = settlementMap.get(payer);
            for (CustomUser debtor : debtorMap.keySet()) {
                Double cost = debtorMap.get(debtor);
                if (cost > 0) {
                    Settlement settlement = Settlement.builder()
                            .amount(cost)
                            .build();
                    settlement.setReceiver(payer);
                    settlement.setSender(debtor);
                    settlementRepo.save(settlement);
                }
            }
        }
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
